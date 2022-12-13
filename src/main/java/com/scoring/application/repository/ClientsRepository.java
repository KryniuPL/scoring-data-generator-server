package com.scoring.application.repository;

import java.util.List;
import java.util.UUID;

public interface ClientsRepository {
    List<UUID> getAllClientsUUIDS();
}
