package com.scoring.infrastructure.web;

import com.scoring.application.utils.GenerationDataHolder;
import com.scoring.domain.DataGenerationRequest;
import com.scoring.infrastructure.web.model.DataGenerationPayload;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

@Slf4j
@Validated
@Controller("/api/update")
public class UpdateController {

    @Post
    public void init(@Body @Valid DataGenerationPayload dataGenerationPayload) {
        log.info("Update parameters requested");
        DataGenerationRequest dataGenerationRequest = dataGenerationPayload.toDataGenerationRequest();
        GenerationDataHolder.updateHolder(dataGenerationRequest);
    }

}
