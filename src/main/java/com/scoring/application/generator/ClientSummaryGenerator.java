package com.scoring.application.generator;

import com.scoring.application.producer.ClientSummaryProducer;
import com.scoring.application.supplier.ClientSummarySupplier;
import com.scoring.application.utils.ProducersHolder;
import com.scoring.domain.client.ClientSummary;
import com.scoring.domain.PaymentHistory;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class ClientSummaryGenerator {

    @Inject
    ClientSummaryProducer clientSummaryProducer;

    @Inject
    ClientSummarySupplier clientSummarySupplier;

    private final List<PaymentHistory> clientPayments = new ArrayList<>();

    public void generateClientSummaries(PaymentHistory paymentHistory, String producerId) {
        Long numberOfPaymentsPerAccount = ProducersHolder.getProducerRequest(producerId).getNumberOfPaymentsPerClient();

        clientPayments.add(paymentHistory);

        if (clientPayments.size() == numberOfPaymentsPerAccount) {
            ClientSummary clientSummary = clientSummarySupplier.get(clientPayments);
            clientSummaryProducer.sendClientSummary(clientSummary.summaryId(), clientSummary, producerId);

            clientPayments.clear();
        }
    }
}
