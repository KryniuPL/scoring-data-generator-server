package com.scoring.infrastructure.repository;

import com.scoring.application.repository.ClientsRepository;
import com.scoring.domain.Client;
import jakarta.inject.Inject;

import java.util.List;
import java.util.UUID;

public class KafkaClientsRepository implements ClientsRepository {

    @Inject
    private io.confluent.ksql.api.client.Client kafkaDatabaseClient;

    @Override
    public List<UUID> getAllClientsUUIDS() {
        return kafkaDatabaseClient.executeQuery("select * from clients;")
                .join()
                .stream()
                .map(row -> UUID.fromString(row.getString("CLIENTID")))
                .toList();
    }
}
