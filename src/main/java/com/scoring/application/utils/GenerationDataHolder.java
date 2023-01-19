package com.scoring.application.utils;

import com.scoring.domain.DataGenerationRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GenerationDataHolder {

    private static volatile DataGenerationRequest openProducer = null;

    public static void initializeHolder(DataGenerationRequest dataGenerationRequest) {
        openProducer = dataGenerationRequest;
    }

    public static void updateHolder(DataGenerationRequest dataGenerationRequest) {
        openProducer = dataGenerationRequest;
    }

    public static DataGenerationRequest getCurrentGenerationData() {
        return openProducer;
    }
}
