package com.resolvedd.macrocalculator.security;

import com.resolvedd.macrocalculator.client.AuthServiceClient;
import com.resolvedd.macrocalculator.dto.AuthenticationResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static jakarta.servlet.http.HttpServletResponse.SC_FORBIDDEN;
import static jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

@Component
@RequiredArgsConstructor
public class AuthValidationFilter extends OncePerRequestFilter {

    private final AuthServiceClient authServiceClient;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        // Check if Authorization header exists
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            // Retrieve user information from the Auth Service
            AuthenticationResponse userInfo = authServiceClient.validateToken(token);

            if (userInfo != null) {
                // Populate SecurityContext with authenticated user
                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(userInfo.getUsername(), null, null)
                );
            } else {
                response.sendError(SC_FORBIDDEN, "Invalid token!");
                return;
            }
        } else {
            // Respond with 401 Unauthorized if the token is missing
            response.sendError(SC_UNAUTHORIZED, "Missing token!");
            return;
        }

        // Token is valid, proceed with the request
        filterChain.doFilter(request, response);
    }
}
