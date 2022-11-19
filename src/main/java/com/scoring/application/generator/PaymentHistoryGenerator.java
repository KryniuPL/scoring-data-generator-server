package com.scoring.application.generator;

import com.scoring.application.producer.PaymentHistoryProducer;
import com.scoring.application.supplier.PaymentHistorySupplier;
import com.scoring.application.utils.RequestHolder;
import com.scoring.domain.Account;
import com.scoring.domain.PaymentHistory;
import com.scoring.infrastructure.web.model.PaymentsRange;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import jakarta.inject.Inject;

import java.util.Optional;

import static com.scoring.application.utils.RandomUtils.randomInteger;

@KafkaListener(offsetReset = OffsetReset.EARLIEST, threads = 10)
public class PaymentHistoryGenerator {

    @Inject
    private PaymentHistoryProducer paymentHistoryProducer;

    @Inject
    private PaymentHistorySupplier paymentHistorySupplier;

    @Topic("accounts")
    public void generatePaymentsHistory(Account account) {
        PaymentsRange paymentsRange = RequestHolder.getDataGenerationRequest().paymentsRange();
        int paymentsCount = Optional.ofNullable(paymentsRange.fixedValue())
                .orElse(randomInteger(paymentsRange.min(), paymentsRange.max()));
        for (int i = 0; i < paymentsCount; i++) {
            PaymentHistory paymentHistory = paymentHistorySupplier.get(account);
            paymentHistoryProducer.sendPaymentHistory(paymentHistory.paymentId(), paymentHistory);
        }
    }
}
