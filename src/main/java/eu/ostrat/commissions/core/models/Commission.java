package eu.ostrat.commissions.core.models;

import org.immutables.value.Value;

import java.math.BigDecimal;

@Value.Immutable
public interface Commission {

    BigDecimal getAmount();

    String getCurrency();
}
