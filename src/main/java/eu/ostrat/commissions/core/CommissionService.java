package eu.ostrat.commissions.core;

import eu.ostrat.commissions.adapters.database.TransactionRepository;
import eu.ostrat.commissions.adapters.database.models.TransactionRow;
import eu.ostrat.commissions.adapters.rate.RateAdapter;
import eu.ostrat.commissions.core.models.Commission;
import eu.ostrat.commissions.core.models.ImmutableCommission;
import eu.ostrat.commissions.core.models.ImmutableTransaction;
import eu.ostrat.commissions.core.models.Transaction;
import eu.ostrat.commissions.core.rules.Rule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
@Validated
public class CommissionService {

    private final String baseCurrency;

    private final TransactionRepository transactions;

    private final RateAdapter rateAdapter;

    private final List<Rule> rules;

    public CommissionService(
        @Value("${app.base}") String baseCurrency,
        TransactionRepository transactions,
        RateAdapter rateAdapter,
        List<Rule> rules
    ) {
        this.baseCurrency = baseCurrency;
        this.transactions = transactions;
        this.rateAdapter = rateAdapter;
        this.rules = rules;
    }

    public Commission handleTransaction(@Valid Transaction transaction) {
        final BigDecimal rate = rateAdapter.getRate(transaction.getCurrency(), baseCurrency, transaction.getDate());

        final Transaction convertedTransaction = ImmutableTransaction.copyOf(transaction)
            .withAmount(transaction.getAmount().multiply(rate))
            .withCurrency(baseCurrency);

        final BigDecimal commission = calculateCommission(convertedTransaction);

        transactions.save(new TransactionRow(
            transaction.getClientId(),
            transaction.getDate(),
            convertedTransaction.getAmount(),
            convertedTransaction.getCurrency(),
            transaction.getAmount(),
            transaction.getCurrency(),
            commission,
            baseCurrency
        ));

        return ImmutableCommission.builder()
            .amount(commission)
            .currency(baseCurrency)
            .build();
    }

    private BigDecimal calculateCommission(Transaction transaction) {
        return rules.stream()
            .map(rule -> rule.calculate(transaction))
            .filter(Objects::nonNull)
            .min(BigDecimal::compareTo)
            .orElseThrow(() -> new RuntimeException("Unexpected error"));
    }
}
