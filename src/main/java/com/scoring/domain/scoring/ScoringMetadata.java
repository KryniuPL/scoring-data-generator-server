package com.scoring.domain.scoring;

public record ScoringMetadata(
        Integer loadAndIncomeScoring,
        Integer previousLoansScoring,
        Integer actualLoansScoring,
        Integer finishedLoansScoring,
        Integer jobScoring,
        Integer martialStatusScoring,
        Integer childrenNumberScoring
) {

    public Integer sum() {
        return this.loadAndIncomeScoring
                + this.previousLoansScoring
                + this.actualLoansScoring
                + this.finishedLoansScoring
                + this.jobScoring
                + this.martialStatusScoring
                + this.childrenNumberScoring;
    }

}
