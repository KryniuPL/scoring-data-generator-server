package com.scoring.application;

import com.scoring.application.generator.AccountsGenerator;
import com.scoring.application.generator.ClientsGenerator;
import com.scoring.domain.Client;
import com.scoring.infrastructure.DataGenerationRequest;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class GeneratorStarter {

    @Inject
    private ClientsGenerator clientsGenerator;

    @Inject
    private AccountsGenerator accountsGenerator;

    public void startDataGeneration(DataGenerationRequest dataGenerationRequest) {
        List<Client> clients = clientsGenerator.generateClients(dataGenerationRequest.numberOfClients());
        accountsGenerator.generateAccounts(clients, dataGenerationRequest.numberOfAccountsPerClient());
    }

}
