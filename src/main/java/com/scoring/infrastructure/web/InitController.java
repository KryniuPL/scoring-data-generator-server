package com.scoring.infrastructure.web;

import com.scoring.application.GeneratorStarter;
import com.scoring.domain.DataGenerationRequest;
import com.scoring.infrastructure.web.model.DataGenerationPayload;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

@Slf4j
@Validated
@Controller("/api/init")
public class InitController {

    @Inject
    private GeneratorStarter generatorStarter;

    @Post
    public DataGenerationPayload init(@Body @Valid DataGenerationPayload dataGenerationPayload) {
        DataGenerationRequest dataGenerationRequest = dataGenerationPayload.toDataGenerationRequest();
        log.info("Data generation initialized with input parameters: {}", dataGenerationRequest);
        return dataGenerationPayload;
//        String producerId = ProducersHolder.createNewProducer(dataGenerationRequest);
//        generatorStarter.startDataGeneration(producerId);
    }

}
