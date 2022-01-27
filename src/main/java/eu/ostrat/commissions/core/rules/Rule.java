package eu.ostrat.commissions.core.rules;

import eu.ostrat.commissions.core.models.Transaction;

import java.math.BigDecimal;

public interface Rule {

    BigDecimal calculate(Transaction transaction);
}
