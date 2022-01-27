package eu.ostrat.commissions.web.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value.Immutable
@Value.Style(privateNoargConstructor = true)
@JsonDeserialize(as = ImmutableTransactionPayload.class)
public interface TransactionPayload {

    LocalDate getDate();

    @JsonProperty("client_id")
    Long getClientId();

    BigDecimal getAmount();

    String getCurrency();
}
