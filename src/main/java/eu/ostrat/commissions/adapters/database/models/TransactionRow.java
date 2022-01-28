package eu.ostrat.commissions.adapters.database.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transaction_data")
public class TransactionRow {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "client_id")
    private Long clientId;

    @Column
    private LocalDate date;

    @Column(precision = 20, scale = 4)
    private BigDecimal amount;

    @Column
    private String currency;

    @Column(name = "original_amount", precision = 20, scale = 4)
    private BigDecimal originalAmount;

    @Column(name = "original_currency")
    private String originalCurrency;

    @Column(name = "commission_amount", precision = 20, scale = 4)
    private BigDecimal commissionAmount;

    @Column(name = "commission_currency")
    private String commissionCurrency;

    public TransactionRow() {}

    public TransactionRow(
        Long clientId,
        LocalDate date,
        BigDecimal amount,
        String currency,
        BigDecimal originalAmount,
        String originalCurrency,
        BigDecimal commissionAmount,
        String commissionCurrency
    ) {
        this.clientId = clientId;
        this.date = date;
        this.amount = amount;
        this.currency = currency;
        this.originalAmount = originalAmount;
        this.originalCurrency = originalCurrency;
        this.commissionAmount = commissionAmount;
        this.commissionCurrency = commissionCurrency;
    }

    public Long getId() {
        return id;
    }

    public Long getClientId() {
        return clientId;
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getOriginalAmount() {
        return originalAmount;
    }

    public String getOriginalCurrency() {
        return originalCurrency;
    }

    public BigDecimal getCommissionAmount() {
        return commissionAmount;
    }

    public String getCommissionCurrency() {
        return commissionCurrency;
    }
}
