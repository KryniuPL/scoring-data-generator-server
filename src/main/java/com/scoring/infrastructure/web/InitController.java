package com.scoring.infrastructure.web;

import com.scoring.application.GeneratorStarter;
import com.scoring.application.utils.RequestHolder;
import com.scoring.infrastructure.web.model.DataGenerationRequest;
import io.confluent.ksql.api.client.Client;
import io.confluent.ksql.api.client.ClientOptions;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;
import jakarta.inject.Inject;

import javax.validation.Valid;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Validated
@Controller("/api/init")
public class InitController {

    @Inject
    private GeneratorStarter generatorStarter;

    @Post
    public void init(@Body @Valid DataGenerationRequest dataGenerationRequest) {
        RequestHolder.setDataGenerationRequest(dataGenerationRequest);
        generatorStarter.startDataGeneration();
    }
}
