package com.scoring.infrastructure.web;

import com.scoring.application.GeneratorStarter;
import com.scoring.application.utils.ProducersHolder;
import com.scoring.infrastructure.web.model.DataGenerationRequest;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;
import jakarta.inject.Inject;

import javax.validation.Valid;

@Validated
@Controller("/api/init")
public class InitController {

    @Inject
    private GeneratorStarter generatorStarter;

    @Post
    public void init(@Body @Valid DataGenerationRequest dataGenerationRequest) {
        String producerId = ProducersHolder.createNewProducer(dataGenerationRequest);
        generatorStarter.startDataGeneration(producerId);
    }
}
