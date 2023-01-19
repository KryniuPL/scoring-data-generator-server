package com.scoring.infrastructure.web;

import com.scoring.application.GeneratorStarter;
import com.scoring.application.utils.GenerationDataHolder;
import com.scoring.domain.DataGenerationRequest;
import com.scoring.infrastructure.web.model.DataGenerationPayload;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

@Slf4j
@Validated
@Controller("/api/resume")
public class ResumeController {

    @Inject
    private GeneratorStarter generatorStarter;

    @Get
    public Response resume() {
        log.info("Resume endpoint called");
        DataGenerationRequest dataGenerationRequest = GenerationDataHolder.getCurrentGenerationData();
        String producerId = generatorStarter.startDataGeneration(dataGenerationRequest);
        return new Response(producerId);
    }

    record Response(String producerId) {
    }
}
