package eu.ostrat.commissions.adapters.rate.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

import java.math.BigDecimal;
import java.util.Map;

@Value.Immutable
@Value.Style(privateNoargConstructor = true)
@JsonDeserialize(as = ImmutableRateResponse.class)
public interface RateResponse {

    String getBase();

    Map<String, BigDecimal> getRates();
}
