package com.scoring.application.supplier;

import com.scoring.application.repository.NamesRepository;
import com.scoring.domain.client.Client;
import com.scoring.domain.client.ClientJob;
import com.scoring.domain.client.ClientMartialStatus;
import com.scoring.domain.client.ClientType;
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
                .income(defineClientIncomeBaseOnAge(clientAge))
                .spending(defineClientSpendingBaseOnAge(clientAge))
                .numberOfChildren(randomInteger(0, 3))
                .build();
    }

    private ClientMartialStatus defineClientMartialStatusBaseOnAge(Integer age) {
        if (age < 30) return ClientMartialStatus.SINGLE;
        else if (age < 35) return ClientMartialStatus.MARRIED;
        else if (age < 55)
            return randomEnum(new ClientMartialStatus[]{ClientMartialStatus.MARRIED, ClientMartialStatus.DIVORCED});
        else
            return randomEnum(new ClientMartialStatus[]{ClientMartialStatus.MARRIED, ClientMartialStatus.DIVORCED, ClientMartialStatus.WIDOWED});
    }

    private ClientJob defineClientJobBaseOnAge(Sex sex, Integer age) {
        if (age < 30) return ClientJob.UNEMPLOYED;
        else if (sex == Sex.FEMALE && age < 60)
            return randomEnum(new ClientJob[]{ClientJob.PERMANENT, ClientJob.CONTRACT, ClientJob.COMPANY_OWNER});
        else if (sex == Sex.MALE && age < 65)
            return randomEnum(new ClientJob[]{ClientJob.PERMANENT, ClientJob.CONTRACT, ClientJob.COMPANY_OWNER});
        else return ClientJob.RETIRED;
    }

    private Integer defineClientIncomeBaseOnAge(Integer age) {
        if (age < 30) return randomInteger(2000, 4000);
        else if (age < 35) return randomInteger(4000, 8000);
        else if (age < 65) return randomInteger(8000, 12000);
        else return 6000;
    }

    private Integer defineClientSpendingBaseOnAge(Integer age) {
        if (age < 30) return randomInteger(400, 1000);
        else if (age < 35) return randomInteger(1000, 2000);
        else if (age < 65) return randomInteger(2000, 4000);
        else return 3000;
    }
}
