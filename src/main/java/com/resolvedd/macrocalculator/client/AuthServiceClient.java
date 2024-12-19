package com.resolvedd.macrocalculator.client;

import com.resolvedd.macrocalculator.dto.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class AuthServiceClient {

    private final WebClient webClient;

    public AuthServiceClient(@Value("${auth.service.url}") String authServiceUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(authServiceUrl)
                .build();
    }

    public AuthenticationResponse validateToken(String token) {

        try {
            return
                    webClient.get()
                            .uri("/api/auth/authenticate")
                            .header("Authorization", "Bearer " + token)
                            .retrieve()
                            .bodyToMono(AuthenticationResponse.class)
                            .block();
        } catch (Exception e) {
            System.err.println("Error validating token: " + e.getMessage());
            return null;
        }
    }
}
