package com.scoring.domain;

import lombok.Getter;

@Getter
public enum AccountStatus {
    STANDARD(0),
    VINDICATION(4),
    EXECUTION(3),
    PAID(1),
    ENHANCED(2);

    private final Integer statusScore;

    AccountStatus(Integer statusScore) {
        this.statusScore = statusScore;
    }
}
