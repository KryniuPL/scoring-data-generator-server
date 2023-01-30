package com.scoring.infrastructure.web;

import com.scoring.application.utils.GenerationDataHolder;
import com.scoring.domain.DataGenerationRequest;
import com.scoring.domain.range.ScoringCardConfig;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

@Slf4j
@Validated
@Controller("/api/update/card")
public class UpdateScoringCardController {

    @Post
    public void init(@Body @Valid ScoringCardConfig scoringCardConfig) {
        log.info("Update scoring card requested");
        DataGenerationRequest currentGenerationData = GenerationDataHolder.getCurrentGenerationData();
        DataGenerationRequest updatedWithScoringCard = currentGenerationData.withScoringCardConfig(scoringCardConfig);
        GenerationDataHolder.updateHolder(updatedWithScoringCard);
    }

}
