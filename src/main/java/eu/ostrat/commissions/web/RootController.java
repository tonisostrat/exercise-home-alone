package eu.ostrat.commissions.web;

import eu.ostrat.commissions.core.CommissionService;
import eu.ostrat.commissions.core.models.ImmutableTransaction;
import eu.ostrat.commissions.web.models.TransactionPayload;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    private final CommissionService commissionService;

    public RootController(CommissionService commissionService) {
        this.commissionService = commissionService;
    }

    @PostMapping
    public ResponseEntity<?> storeTransaction(@RequestBody TransactionPayload transaction) {
        return ResponseEntity.ok(commissionService.handleTransaction(ImmutableTransaction.builder()
            .date(transaction.getDate())
            .clientId(transaction.getClientId())
            .amount(transaction.getAmount())
            .currency(transaction.getCurrency())
            .build())
        );
    }
}
