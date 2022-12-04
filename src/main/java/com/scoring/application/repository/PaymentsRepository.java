package com.scoring.application.repository;

import com.scoring.domain.PaymentHistory;

import java.util.List;
import java.util.UUID;

public interface PaymentsRepository {
    Long countAll();
    List<PaymentHistory> getAllByAccountId(UUID accountId);
}
