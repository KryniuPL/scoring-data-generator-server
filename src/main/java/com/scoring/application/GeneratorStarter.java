package com.scoring.application;

import com.scoring.application.generator.ClientsGenerator;
import com.scoring.application.utils.GenerationDataHolder;
import com.scoring.domain.DataGenerationRequest;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class GeneratorStarter {

    @Inject
    private ClientsGenerator clientsGenerator;

    public String startDataGeneration(DataGenerationRequest dataGenerationRequest) {
        GenerationDataHolder.initializeHolder(dataGenerationRequest);
        return clientsGenerator.generateClients("BIK");
    }

}
