package com.scoring.application.listener;

import com.scoring.application.generator.ClientSummaryGenerator;
import com.scoring.domain.PaymentHistory;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.messaging.annotation.MessageHeader;
import jakarta.inject.Inject;

@KafkaListener(offsetReset = OffsetReset.EARLIEST)
public class PaymentsHistoryListener {

    @Inject
    ClientSummaryGenerator clientSummaryGenerator;

    @Topic("payments-history")
    public void receivePaymentHistory(PaymentHistory paymentHistory, @MessageHeader("PRODUCER-ID") String producerId) {
        clientSummaryGenerator.generateClientSummaries(paymentHistory, producerId);
    }
}
