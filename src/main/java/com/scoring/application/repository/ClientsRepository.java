package com.scoring.application.repository;

import com.scoring.domain.Client;

import java.util.List;
import java.util.UUID;

public interface ClientsRepository {
    List<UUID> getAllClientsUUIDS();
}
