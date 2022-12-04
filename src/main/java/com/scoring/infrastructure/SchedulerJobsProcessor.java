package com.scoring.infrastructure;

import com.scoring.application.generator.ClientSummaryGenerator;
import com.scoring.application.repository.PaymentsRepository;
import com.scoring.application.utils.RequestHolder;
import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Singleton
public class SchedulerJobsProcessor {

    @Inject
    private PaymentsRepository paymentsRepository;

    @Inject
    private ClientSummaryGenerator clientSummaryGenerator;

    private Boolean initialPhaseCompleted = false;

    @Scheduled(fixedRate = "5s")
    void checkPaymentsSize() {
        if (RequestHolder.getDataGenerationRequest() != null && !initialPhaseCompleted) {
            Long desiredPaymentsCount = RequestHolder.getDataGenerationRequest().getNumberOfPayments();
            Long actualPaymentsCount = paymentsRepository.countAll();

            if (desiredPaymentsCount.equals(actualPaymentsCount)) {
                log.info("Generation of clients, accounts and payments finished");
                initialPhaseCompleted = true;
                clientSummaryGenerator.generateClientSummaries();
            } else {
                log.info("Actual number of payments: {}", actualPaymentsCount);
                log.info("Processing of initial dataset is still ongoing, checking once again in 5s");
            }
        }
    }

}
