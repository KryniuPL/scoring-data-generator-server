package com.scoring.application.generator;

import com.scoring.application.producer.AccountProducer;
import com.scoring.application.supplier.AccountSupplier;
import com.scoring.application.utils.ProducersHolder;
import com.scoring.domain.Account;
import com.scoring.domain.Client;
import jakarta.inject.Inject;

public class AccountsGenerator {

    @Inject
    private AccountProducer accountProducer;

    @Inject
    private AccountSupplier accountSupplier;

    public void generateAccount(Client client, String producerId) {
        Long numberOfAccountsPerClient = ProducersHolder.getProducerRequest(producerId).numberOfAccountsPerClient();
        for (int i = 0; i < numberOfAccountsPerClient; i++) {
            Account account = accountSupplier.get(client);
            accountProducer.sendAccount(account.accountId(), account, producerId);
        }
    }
}
