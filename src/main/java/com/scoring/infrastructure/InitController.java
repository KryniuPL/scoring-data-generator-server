package com.scoring.infrastructure;

import com.scoring.application.GeneratorStarter;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

@Controller("/api/init")
public class InitController {

    @Inject
    private GeneratorStarter generatorStarter;

    @Post
    public void init(@Body DataGenerationRequest dataGenerationRequest) {
        generatorStarter.startDataGeneration(dataGenerationRequest);
    }
}
