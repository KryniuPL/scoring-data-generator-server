package com.scoring.application.supplier;

import com.scoring.application.repository.NamesRepository;
import com.scoring.domain.Client;
import com.scoring.domain.ClientType;
import com.scoring.domain.Sex;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.UUID;
import java.util.function.Supplier;

import static com.scoring.application.utils.RandomUtils.*;

@Singleton
public class ClientSupplier implements Supplier<Client> {

    @Inject
    NamesRepository namesRepository;

    @Override
    public Client get() {
        Sex sex = randomEnum(Sex.class);

        return Client.builder()
                .clientId(UUID.randomUUID())
                .firstName(namesRepository.getRandomFirstName(sex))
                .lastName(namesRepository.getRandomSurname(sex))
                .pesel(randomPesel())
                .clientType(randomEnum(ClientType.class))
                .build();
    }
}
