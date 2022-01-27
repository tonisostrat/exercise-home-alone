package eu.ostrat.commissions.core.models;

import eu.ostrat.commissions.infra.Past;
import org.immutables.value.Value;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.time.LocalDate;

@Value.Immutable
public interface Transaction {

    @Past
    LocalDate getDate();

    Long getClientId();

    @DecimalMin("0")
    BigDecimal getAmount();

    String getCurrency();
}
