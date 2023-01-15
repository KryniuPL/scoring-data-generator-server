package com.scoring.application;

import com.scoring.application.generator.ClientsGenerator;
import com.scoring.application.utils.ProducersHolder;
import com.scoring.domain.DataGenerationRequest;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class GeneratorStarter {

    @Inject
    private ClientsGenerator clientsGenerator;

    public void startDataGeneration(DataGenerationRequest dataGenerationRequest) {
        String producerId = ProducersHolder.createNewProducer(dataGenerationRequest);
        clientsGenerator.generateClients(producerId);
    }

}
