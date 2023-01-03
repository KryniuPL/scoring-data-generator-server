package com.scoring.application.supplier;

import com.scoring.application.repository.NamesRepository;
import com.scoring.domain.Client;
import com.scoring.domain.ClientJob;
import com.scoring.domain.ClientMartialStatus;
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
        Integer clientAge = randomInteger(18, 100);

        return Client.builder()
                .clientId(UUID.randomUUID())
                .firstName(namesRepository.getRandomFirstName(sex))
                .lastName(namesRepository.getRandomSurname(sex))
                .clientJob(defineClientJobBaseOnAge(sex, clientAge))
                .clientMartialStatus(defineClientMartialStatusBaseOnAge(clientAge))
                .pesel(randomPesel())
                .clientType(randomEnum(ClientType.class))
                .build();
    }

    private ClientMartialStatus defineClientMartialStatusBaseOnAge(Integer age) {
        if (age < 24) return ClientMartialStatus.SINGLE;
        else if(age < 35) return ClientMartialStatus.MARRIED;
        else if (age < 55) return randomEnum(new ClientMartialStatus[] {ClientMartialStatus.MARRIED, ClientMartialStatus.DIVORCED});
        else return randomEnum(new ClientMartialStatus[] {ClientMartialStatus.MARRIED, ClientMartialStatus.DIVORCED, ClientMartialStatus.WIDOWED});
    }

    private ClientJob defineClientJobBaseOnAge(Sex sex, Integer age) {
        if (age < 20) return ClientJob.UNEMPLOYED;
        else if(sex == Sex.FEMALE && age < 60) return randomEnum(new ClientJob[]{ClientJob.PERMANENT, ClientJob.CONTRACT, ClientJob.COMPANY_OWNER});
        else if(sex == Sex.MALE && age < 65) return randomEnum(new ClientJob[]{ClientJob.PERMANENT, ClientJob.CONTRACT, ClientJob.COMPANY_OWNER});
        else return ClientJob.RETIRED;
    }
}
