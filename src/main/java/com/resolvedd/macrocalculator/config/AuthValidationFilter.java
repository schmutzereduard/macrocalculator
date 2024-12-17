package com.resolvedd.macrocalculator.config;

import com.resolvedd.macrocalculator.client.AuthServiceClient;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public class AuthValidationFilter extends OncePerRequestFilter {

    private final AuthServiceClient authServiceClient;

    public AuthValidationFilter(AuthServiceClient authServiceClient) {
        this.authServiceClient = authServiceClient;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        String username = request.getParameter("username");

        if (authHeader != null && authHeader.startsWith("Bearer ") && username != null) {
            String token = authHeader.substring(7);

            if (authServiceClient.validateToken(username, token)) {
                // Token is valid, set the authenticated user in the SecurityContext
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(username, null, List.of());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid token or username");
                return;
            }
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing token or username");
            return;
        }

        filterChain.doFilter(request, response);
    }

}
