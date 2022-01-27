package eu.ostrat.commissions;

import eu.ostrat.commissions.core.models.ImmutableTransaction;
import eu.ostrat.commissions.core.models.Transaction;
import eu.ostrat.commissions.core.rules.DouglasAdamsRule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RuleTest {

    Transaction transaction;

    @BeforeEach
    void setUp() {
        transaction = ImmutableTransaction.builder()
            .clientId(1L)
            .date(LocalDate.now())
            .amount(BigDecimal.valueOf(1000))
            .currency("EUR")
            .build();
    }

    @Test
    public void testDouglasAdamsRule() {
        final DouglasAdamsRule rule = new DouglasAdamsRule();

        final BigDecimal ruleOutput = rule.calculate(ImmutableTransaction.copyOf(transaction).withClientId(42L));

        Assertions.assertNotNull(ruleOutput);
    }

    @Test
    public void testDouglasAdamsRuleFail() {
        final DouglasAdamsRule rule = new DouglasAdamsRule();

        final BigDecimal ruleOutput = rule.calculate(transaction);

        Assertions.assertNull(ruleOutput);
    }
}
