package com.scoring.application.generator;

import com.scoring.application.producer.AccountProducer;
import com.scoring.application.supplier.AccountSupplier;
import com.scoring.application.utils.RequestHolder;
import com.scoring.domain.Account;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import jakarta.inject.Inject;

import java.util.UUID;

@KafkaListener(offsetReset = OffsetReset.EARLIEST)
public class AccountsGenerator {

    @Inject
    private AccountProducer accountProducer;

    @Inject
    private AccountSupplier accountSupplier;

    @Topic("clients")
    public void receive(@KafkaKey UUID clientId) {
        Long numberOfAccountsPerClient = RequestHolder.getDataGenerationRequest().numberOfAccountsPerClient();
        for (int i = 0; i < numberOfAccountsPerClient; i++) {
            Account account = accountSupplier.get(clientId);
            accountProducer.sendAccount(account.accountId(), account);
        }
    }
}
