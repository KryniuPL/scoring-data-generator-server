package com.scoring.infrastructure.web;

import com.scoring.application.repository.ScoringRepository;
import com.scoring.application.utils.GenerationDataHolder;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.validation.Validated;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
@Validated
@Controller("/api/scoring")
public class ScoringRestController {

    @Inject
    ScoringRepository scoringRepository;

    @Get("/scoreRatio/{points}")
    public ScoreRatioResponse getScoreRatio(@Min(0) @Max(600) @PathVariable Integer points) {
        Integer allScores = scoringRepository.getAllCount();
        Integer scoresGreaterThan = scoringRepository.getCountOfScoresGreaterThan(points);
        double scoresAbove = BigDecimal.valueOf((double) scoresGreaterThan / (double) allScores)
                .setScale(2, RoundingMode.HALF_DOWN)
                .doubleValue();

        return new ScoreRatioResponse(scoresAbove, 1 - scoresAbove);
    }

    record ScoreRatioResponse(Double scoresAbove, Double scoredBelow) {
    }

    record ProcessingIntervalResponse(Integer processingInterval) {
    }
}
