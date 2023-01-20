package com.scoring.application.repository;

public interface ScoringRepository {
    Integer getAllCount();
    Integer getCountOfScoresGreaterThan(Integer score);
}
