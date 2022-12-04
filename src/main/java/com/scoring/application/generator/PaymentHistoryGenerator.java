package com.scoring.application.generator;

import com.scoring.application.producer.PaymentHistoryProducer;
import com.scoring.application.supplier.PaymentHistorySupplier;
import com.scoring.application.utils.RequestHolder;
import com.scoring.domain.Account;
import com.scoring.domain.PaymentHistory;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import jakarta.inject.Inject;

@KafkaListener(offsetReset = OffsetReset.EARLIEST, threads = 10)
public class PaymentHistoryGenerator {

    @Inject
    private PaymentHistoryProducer paymentHistoryProducer;

    @Inject
    private PaymentHistorySupplier paymentHistorySupplier;

    @Topic("accounts")
    public void generatePaymentsHistory(Account account) {
        Long numberOfPayments = RequestHolder.getDataGenerationRequest().numberOfPaymentsPerAccount();
        for (int i = 0; i < numberOfPayments; i++) {
            PaymentHistory paymentHistory = paymentHistorySupplier.get(account);
            paymentHistoryProducer.sendPaymentHistory(paymentHistory.paymentId(), paymentHistory);
        }
    }
}
