package com.resolvedd.macrocalculator.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class AuthServiceClient {

    private final WebClient webClient;

    public AuthServiceClient(@Value("${auth.service.url}") String authServiceUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(authServiceUrl)
                .build();
    }

    /**
     * Validates the token and username with the Auth Service.
     * @param username Username to validate
     * @param token JWT token
     * @return true if valid, false otherwise
     */
    public boolean validateToken(String username, String token) {
        try {
            return Boolean.TRUE.equals(
                    webClient.get()
                            .uri(uriBuilder -> uriBuilder
                                    .path("/api/auth/authenticate")
                                    .queryParam("username", username)
                                    .build())
                            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                            .retrieve()
                            .toBodilessEntity()
                            .map(response -> response.getStatusCode().is2xxSuccessful())
                            .block()
            );
        } catch (Exception e) {
            System.err.println("Error validating token: " + e.getMessage());
            return false; // Assume invalid if any exception occurs
        }
    }
}
