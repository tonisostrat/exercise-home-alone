package eu.ostrat.commissions.core.rules;

import eu.ostrat.commissions.core.models.Transaction;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DouglasAdamsRule implements Rule {

    private final BigDecimal fixed = BigDecimal.valueOf(0.05);

    @Override
    public BigDecimal calculate(Transaction transaction) {
        return transaction.getClientId() == 42L ? fixed : null;
    }
}
