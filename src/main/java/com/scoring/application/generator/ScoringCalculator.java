package com.scoring.application.generator;

import com.scoring.domain.account.Account;
import com.scoring.domain.client.Client;
import com.scoring.domain.client.ClientJob;
import com.scoring.domain.client.ClientMartialStatus;
import com.scoring.domain.client.ClientSummary;
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
        Client client = clientSummary.client();
        List<Account> accounts = clientSummary.accounts();

        // Scoring related to client's financial data
        Integer act_cc = calculateActualCreditScoring(client, accounts);
        Integer act_cins_min_seniority = calculateActualAgeScoring(accounts);
        Integer act_cins_n_loan = calculateActualLoansScoring(accounts);
        Integer act_cins_n_static = calculateActualFinishedLoans(accounts);

        // Scoring related to client's personal data
        Integer app_char_jor_code = calculateJobScoring(client.clientJob());
        Integer app_char_martial_status = calculateMartialScoring(client.clientMartialStatus());
        Integer app_number_of_children = calculateChildrenScoring(client.numberOfChildren());

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

    private Integer calculateActualCreditScoring(Client client, List<Account> accounts) {
        int clientsLoad = accounts.stream()
                .map(Account::installmentAmount)
                .reduce(0, Integer::sum)
                + client.spending();

        int clientIncome = client.income();
        double ratioOfLoadAndIncome = (double) clientsLoad / (double) clientIncome;

        if (ratioOfLoadAndIncome >= 1.0535455861) return -1;
        else if (ratioOfLoadAndIncome >= 0.857442348) return 29;
        else if (ratioOfLoadAndIncome >= 0.3324658426) return 40;
        else if (ratioOfLoadAndIncome >= 0.248125937) return 49;
        else return 61;
    }

    private Integer calculateActualAgeScoring(List<Account> accounts) {
        Integer monthsFromLastLoan = accounts.stream()
                .filter(Account::finished)
                .max(Comparator.comparing(Account::endDate))
                .map(account -> Period.between(LocalDate.now(clock), account.endDate()).getMonths())
                .orElse(null);

        if (monthsFromLastLoan == null) return 53;
        else if (monthsFromLastLoan <= 22) return -1;
        else if (monthsFromLastLoan <= 36) return 50;
        else if (monthsFromLastLoan <= 119) return 76;
        else return 99;
    }

    private Integer calculateActualLoansScoring(List<Account> accounts) {
        int numberOfActualLoans = Math.toIntExact(accounts.stream().filter(account -> !account.finished()).count());
        return numberOfActualLoans <= 1 ? 57 : -1;
    }

    private Integer calculateActualFinishedLoans(List<Account> accounts) {
        int finishedLoans = Math.toIntExact(accounts.stream().filter(Account::finished).count());

        if (finishedLoans <= 0) return -1;
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
