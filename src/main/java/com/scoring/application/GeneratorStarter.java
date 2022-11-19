package com.scoring.application;

import com.scoring.application.generator.ClientsGenerator;
import com.scoring.application.utils.RequestHolder;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class GeneratorStarter {

    @Inject
    private ClientsGenerator clientsGenerator;

    public void startDataGeneration() {
        Long numberOfClients = RequestHolder.getDataGenerationRequest().numberOfClients();
        clientsGenerator.generateClients(numberOfClients);
    }

}
