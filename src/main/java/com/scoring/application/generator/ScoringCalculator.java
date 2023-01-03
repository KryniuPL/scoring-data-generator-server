package com.scoring.application.generator;

import com.scoring.domain.ClientJob;
import com.scoring.domain.ClientMartialStatus;
import com.scoring.domain.ClientSummary;
import jakarta.inject.Singleton;

import static com.scoring.application.utils.RandomUtils.randomDouble;
import static com.scoring.application.utils.RandomUtils.randomInteger;

@Singleton
public class ScoringCalculator {

    public Integer calculateScoring(ClientSummary clientSummary) {
        Integer act_cc = calculateActualCreditScoring(randomDouble(1.00, 1.50));
        Integer act_cins_min_seniority = calculateActualAgeScoring(randomInteger(15, 130));
        Integer act_cins_n_loan = calculateActualLoansScoring(randomInteger(0, 2));
        Integer act_cins_n_static = calculateActualFinishedLoans(randomInteger(0, 3));
        Integer app_char_jor_code = calculateJobScoring(clientSummary.clientJob());
        Integer app_char_martial_status = calculateMartialScoring(clientSummary.clientMartialStatus());
        Integer app_number_of_children = calculateChildrenScoring(randomInteger(0, 3));

        return act_cc
                + act_cins_min_seniority
                + act_cins_n_loan
                + act_cins_n_static
                + app_char_jor_code
                + app_char_martial_status
                + app_number_of_children;
    }

    private Integer calculateActualCreditScoring(Double ratioOfLoadAndIncome) {
        if (ratioOfLoadAndIncome >= 1.0535455861) return -1;
        else if (ratioOfLoadAndIncome >= 0.857442348) return 29;
        else if (ratioOfLoadAndIncome >= 0.3324658426) return 40;
        else if (ratioOfLoadAndIncome >= 0.248125937) return 49;
        else return 61;
    }

    private Integer calculateActualAgeScoring(Integer monthsFromLastLoan) {
        if (monthsFromLastLoan == null) return 53;
        else if (monthsFromLastLoan <= 22) return -1;
        else if (monthsFromLastLoan <= 36) return 50;
        else if (monthsFromLastLoan <= 119) return 76;
        else return 99;
    }

    private Integer calculateActualLoansScoring(Integer numberOfActualLoans) {
        return numberOfActualLoans <= 1 ? 57 : -1;
    }

    private Integer calculateActualFinishedLoans(Integer finishedLoans) {
        if (finishedLoans == null) return 49;
        else if (finishedLoans <= 0) return -1;
        else if (finishedLoans <= 1) return 49;
        else if (finishedLoans <= 2) return 54;
        else return 87;
    }

    private Integer calculateJobScoring(ClientJob clientJob) {
        return switch (clientJob) {
            case COMPANY_OWNER -> 58;
            case RETIRED -> 76;
            case PERMANENT -> 81;
            default -> -1;
        };
    }

    private Integer calculateMartialScoring(ClientMartialStatus clientMartialStatus) {
        return switch (clientMartialStatus) {
            case DIVORCED -> 40;
            case MARRIED -> 55;
            case WIDOWED -> 57;
            default -> -1;
        };
    }

    private Integer calculateChildrenScoring(Integer numberOfChildren) {
        if (numberOfChildren <= 0) return -1;
        else if (numberOfChildren <= 1) return 23;
        else return 57;
    }
}
