package com.scoring.application.utils;

import com.scoring.infrastructure.web.model.DataGenerationRequest;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
public class ProducersHolder {

    private static final Map<String, DataGenerationRequest> openProducers = new HashMap<>();

    public static String createNewProducer(DataGenerationRequest dataGenerationRequest) {
        String producerId = "BIK-PRODUCER-%s".formatted(UUID.randomUUID());
        openProducers.put(producerId, dataGenerationRequest);
        log.info("Created producer: {}", producerId);
        return producerId;
    }

    public static DataGenerationRequest getProducerRequest(String producerId) {
         DataGenerationRequest request = openProducers.get(producerId);
         if (request == null) {
             log.error("Producer {} not registered! Actual producers: {}", producerId, openProducers);
         }
         return request;
    }
}
