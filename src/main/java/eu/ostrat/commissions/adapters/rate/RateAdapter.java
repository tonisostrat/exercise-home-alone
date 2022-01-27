package eu.ostrat.commissions.adapters.rate;

import eu.ostrat.commissions.adapters.rate.models.RateResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Component
public class RateAdapter {

    private final RestTemplate api;

    public RateAdapter(@Value("${app.adapters.currency.endpoint}") String endpoint) {
        this.api = new RestTemplateBuilder()
            .rootUri(endpoint)
            .build();
    }

    public BigDecimal getRate(String baseCurrency, String targetCurrency, LocalDate date) {
        if (baseCurrency.equals(targetCurrency)) {
            return BigDecimal.ONE;
        }

        try {
            final ResponseEntity<RateResponse> response = api.getForEntity(
                "/{date}?base={base}",
                RateResponse.class,
                date.toString(),
                baseCurrency
            );

            if (!baseCurrency.equals(response.getBody().getBase())) {
                throw new RuntimeException("Unsupported currency: " + baseCurrency);
            }

            return Optional.ofNullable(response.getBody().getRates().get(targetCurrency))
                .orElseThrow(() -> new RuntimeException("Unsupported currency: " + targetCurrency));
        } catch (RestClientException e) {
            throw new RuntimeException("Failed to query exchange rates");
        }
    }
}
