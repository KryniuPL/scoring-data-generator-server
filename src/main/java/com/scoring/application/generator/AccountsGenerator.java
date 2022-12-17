package com.scoring.application.generator;

import com.scoring.application.producer.AccountProducer;
import com.scoring.application.supplier.AccountSupplier;
import com.scoring.application.utils.ProducersHolder;
import com.scoring.domain.Account;
import jakarta.inject.Inject;

import java.util.UUID;

public class AccountsGenerator {

    @Inject
    private AccountProducer accountProducer;

    @Inject
    private AccountSupplier accountSupplier;

    public void generateAccount(UUID clientId, String producerId) {
        Long numberOfAccountsPerClient = ProducersHolder.getProducerRequest(producerId).numberOfAccountsPerClient();
        for (int i = 0; i < numberOfAccountsPerClient; i++) {
            Account account = accountSupplier.get(clientId);
            accountProducer.sendAccount(account.accountId(), account, producerId);
        }
    }
}
