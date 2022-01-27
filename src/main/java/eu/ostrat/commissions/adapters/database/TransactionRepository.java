package eu.ostrat.commissions.adapters.database;

import eu.ostrat.commissions.adapters.database.models.TransactionRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionRow, Long> {

    @Query("SELECT SUM(amount) FROM TransactionRow WHERE clientId = :clientId AND date BETWEEN :startDate AND :endDate")
    BigDecimal clientTurnover(Long clientId, LocalDate startDate, LocalDate endDate);
}
