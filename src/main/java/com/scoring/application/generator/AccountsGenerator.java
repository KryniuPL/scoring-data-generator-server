package com.scoring.application.generator;

import com.scoring.application.producer.AccountProducer;
import com.scoring.application.supplier.AccountSupplier;
import com.scoring.domain.Account;
import com.scoring.domain.Client;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.stream.Stream;

@Singleton
public class AccountsGenerator {

    @Inject
    private AccountProducer accountProducer;

    @Inject
    private AccountSupplier accountSupplier;

    public void generateAccounts(List<Client> clients, Long numberOfAccountsPerClient) {
        clients.forEach(client -> {
            for (int i = 0; i < numberOfAccountsPerClient; i++) {
                Account account = accountSupplier.get(client.clientId());
                accountProducer.sendAccount(account.accountId(), account);
            }
        });
    }
}
