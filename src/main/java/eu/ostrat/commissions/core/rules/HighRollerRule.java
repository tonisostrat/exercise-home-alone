package eu.ostrat.commissions.core.rules;

import eu.ostrat.commissions.adapters.database.TransactionRepository;
import eu.ostrat.commissions.core.models.Transaction;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class HighRollerRule implements Rule {

    private final BigDecimal fixed = BigDecimal.valueOf(0.03);

    private final BigDecimal qualifier = BigDecimal.valueOf(1000);

    private final TransactionRepository transactionRepository;

    public HighRollerRule(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public BigDecimal calculate(Transaction transaction) {
        final BigDecimal turnover = transactionRepository.clientTurnover(
            transaction.getClientId(),
            transaction.getDate().withDayOfMonth(1),
            transaction.getDate().withDayOfMonth(1).plusMonths(1).minusDays(1)
        );

        if (turnover != null && turnover.compareTo(qualifier) >= 0) {
            return fixed;
        }

        return null;
    }
}
