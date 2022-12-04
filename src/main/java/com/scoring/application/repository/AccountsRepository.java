package com.scoring.application.repository;

import com.scoring.domain.Account;

import java.util.List;
import java.util.UUID;

public interface AccountsRepository {
    List<Account> getAllByClientId(UUID clientId);
}
