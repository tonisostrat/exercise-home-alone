package eu.ostrat.commissions.core.rules;

import eu.ostrat.commissions.core.models.Transaction;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DefaultRule implements Rule {

    final BigDecimal cut = BigDecimal.valueOf(0.005);

    final BigDecimal fixed = BigDecimal.valueOf(0.05);

    @Override
    public BigDecimal calculate(Transaction transaction) {
        return transaction.getAmount().multiply(cut).max(fixed);
    }
}
