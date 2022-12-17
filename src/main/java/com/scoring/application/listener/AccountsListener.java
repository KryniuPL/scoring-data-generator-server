package com.scoring.application.listener;

import com.scoring.application.generator.PaymentHistoryGenerator;
import com.scoring.domain.Account;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.messaging.annotation.MessageHeader;
import jakarta.inject.Inject;

@KafkaListener(offsetReset = OffsetReset.EARLIEST, threads = 12)
public class AccountsListener {

    @Inject
    PaymentHistoryGenerator paymentHistoryGenerator;

    @Topic("accounts")
    public void receiveAccount(Account account, @MessageHeader("PRODUCER-ID") String producerId) {
        paymentHistoryGenerator.generatePaymentsHistory(account, producerId);
    }
}
