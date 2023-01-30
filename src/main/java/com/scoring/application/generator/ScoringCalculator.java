package com.scoring.application.generator;

import com.scoring.application.utils.GenerationDataHolder;
import com.scoring.domain.account.Account;
import com.scoring.domain.client.Client;
import com.scoring.domain.client.ClientJob;
import com.scoring.domain.client.ClientMartialStatus;
import com.scoring.domain.client.ClientSummary;
import com.scoring.domain.range.ActualLoansRange;
import com.scoring.domain.range.ChildrenRange;
import com.scoring.domain.range.CustomerLoadRange;
import com.scoring.domain.range.FinishedLoansRange;
import com.scoring.domain.range.ScoringCardConfig;
import com.scoring.domain.range.SeniorityRange;
import com.scoring.domain.scoring.ScoringMetadata;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.time.Clock;
import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;

@Singleton
@RequiredArgsConstructor
public class ScoringCalculator {

    @Inject
    private final Clock clock;

    public ScoringMetadata calculateScoring(ClientSummary clientSummary) {
        ScoringCardConfig scoringCardConfig = GenerationDataHolder.getCurrentGenerationData().scoringCardConfig();
        Client client = clientSummary.client();
        List<Account> accounts = clientSummary.accounts();

        // Scoring related to client's financial data
        Integer act_cc = calculateActualCreditScoring(client, accounts, scoringCardConfig.customerLoadRange());
        Integer act_cins_min_seniority = calculateActualAgeScoring(accounts, scoringCardConfig.customerSeniorityRange());
        Integer act_cins_n_loan = calculateActualLoansScoring(accounts, scoringCardConfig.actualLoansRange());
        Integer act_cins_n_static = calculateActualFinishedLoans(accounts, scoringCardConfig.finishedLoansRange());

        // Scoring related to client's personal data
        Integer app_char_jor_code = calculateJobScoring(client.clientJob());
        Integer app_char_martial_status = calculateMartialScoring(client.clientMartialStatus());
        Integer app_number_of_children = calculateChildrenScoring(client.numberOfChildren(), scoringCardConfig.childrenRange());

        return new ScoringMetadata(
                act_cc,
                act_cins_min_seniority,
                act_cins_n_loan,
                act_cins_n_static,
                app_char_jor_code,
                app_char_martial_status,
                app_number_of_children
        );
    }

    private Integer calculateActualCreditScoring(Client client, List<Account> accounts, CustomerLoadRange customerLoadRange) {
        int clientsLoad = accounts.stream()
                .map(Account::installmentAmount)
                .reduce(0, Integer::sum)
                + client.spending();

        int clientIncome = client.income();
        double ratioOfLoadAndIncome = (double) clientsLoad / (double) clientIncome;

        if (ratioOfLoadAndIncome >= customerLoadRange.firstConditionValue()) return -1;
        else if (ratioOfLoadAndIncome >= customerLoadRange.secondConditionValue()) return 29;
        else if (ratioOfLoadAndIncome >= customerLoadRange.thirdConditionValue()) return 40;
        else if (ratioOfLoadAndIncome >= customerLoadRange.lastConditionValue()) return 49;
        else return 61;
    }

    private Integer calculateActualAgeScoring(List<Account> accounts, SeniorityRange seniorityRange) {
        Integer monthsFromLastLoan = accounts.stream()
                .filter(Account::finished)
                .max(Comparator.comparing(Account::endDate))
                .map(account -> Period.between(LocalDate.now(clock), account.endDate()).getMonths())
                .orElse(null);

        if (monthsFromLastLoan == null) return 53;
        else if (monthsFromLastLoan <= seniorityRange.firstConditionValue()) return -1;
        else if (monthsFromLastLoan <= seniorityRange.secondConditionValue()) return 50;
        else if (monthsFromLastLoan <= seniorityRange.lastConditionValue()) return 76;
        else return 99;
    }

    private Integer calculateActualLoansScoring(List<Account> accounts, ActualLoansRange actualLoansRange) {
        int numberOfActualLoans = Math.toIntExact(accounts.stream().filter(account -> !account.finished()).count());
        return numberOfActualLoans <= actualLoansRange.conditionValue() ? 57 : -1;
    }

    private Integer calculateActualFinishedLoans(List<Account> accounts, FinishedLoansRange finishedLoansRange) {
        int finishedLoans = Math.toIntExact(accounts.stream().filter(Account::finished).count());

        if (finishedLoans <= finishedLoansRange.firstConditionValue()) return -1;
        else if (finishedLoans <= finishedLoansRange.secondConditionValue()) return 49;
        else if (finishedLoans <= finishedLoansRange.lastConditionValue()) return 54;
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

    private Integer calculateChildrenScoring(Integer numberOfChildren, ChildrenRange childrenRange) {
        if (numberOfChildren <= childrenRange.min()) return -1;
        else if (numberOfChildren <= childrenRange.max()) return 23;
        else return 57;
    }
}
