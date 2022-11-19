package com.scoring.application.supplier;

import com.scoring.domain.Client;
import com.scoring.domain.ClientType;
import jakarta.inject.Singleton;

import java.util.UUID;
import java.util.function.Supplier;

import static com.scoring.application.utils.RandomUtils.randomEnum;

@Singleton
public class ClientSupplier implements Supplier<Client> {
    @Override
    public Client get() {
        return new Client(UUID.randomUUID(),"gfsdg", "gfds", "fgds", randomEnum(ClientType.class));
    }
}
