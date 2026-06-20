package com.fayda.health.infrastructure.fayda;

import com.fayda.health.domain.port.outbound.service.FaydaIdentityPort;
import com.fayda.health.domain.valueobject.FaydaId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

/**
 * Outbound adapter for the Ethiopia Fayda National ID verification API.
 */
@Component
public class FaydaIdentityClientAdapter implements FaydaIdentityPort {

    private final RestClient restClient;
    private final String apiKey;

    public FaydaIdentityClientAdapter(
            RestClient.Builder restClientBuilder,
            @Value("${fayda.api.base-url}") String baseUrl,
            @Value("${fayda.api.key}") String apiKey) {
        this.restClient = restClientBuilder.baseUrl(baseUrl).build();
        this.apiKey = apiKey;
    }

    @Override
    public FaydaVerificationResult verify(FaydaId faydaId, String fullName) {
        FaydaApiResponse response = restClient.get()
                .uri("/verify/{faydaId}?name={name}", faydaId.value(), fullName)
                .header("X-API-Key", apiKey)
                .retrieve()
                .body(FaydaApiResponse.class);

        if (response == null) {
            return new FaydaVerificationResult(false, null, "No response from Fayda API");
        }
        return new FaydaVerificationResult(response.verified(), response.legalName(), response.message());
    }

    private record FaydaApiResponse(boolean verified, String legalName, String message) {}
}
