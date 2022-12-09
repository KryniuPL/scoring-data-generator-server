package com.scoring.infrastructure.repository;

import com.scoring.application.repository.NamesRepository;
import com.scoring.domain.Sex;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class InMemoryNamesRepository extends NamesRepository {

    private static final List<String> FEMALE_NAMES = List.of(
            "ANNA",
            "KATARZYNA",
            "MARIA",
            "MAŁGORZATA",
            "AGNIESZKA",
            "BARBARA",
            "EWA",
            "MAGDALENA",
            "ELŻBIETA",
            "KRYSTYNA",
            "JOANNA",
            "ALEKSANDRA",
            "MONIKA",
            "ZOFIA",
            "TERESA",
            "DANUTA",
            "NATALIA",
            "JULIA",
            "KAROLINA",
            "MARTA"
    );

    private static final List<String> MALE_NAMES = List.of(
            "PIOTR",
            "KRZYSZTOF",
            "ANDRZEJ",
            "TOMASZ",
            "PAWEŁ",
            "JAN",
            "MICHAŁ",
            "MARCIN",
            "JAKUB",
            "ADAM",
            "MAREK",
            "STANISŁAW",
            "ŁUKASZ",
            "GRZEGORZ",
            "MATEUSZ",
            "WOJCIECH",
            "MARIUSZ",
            "DARIUSZ",
            "ZBIGNIEW",
            "MACIEJ"
    );

    private static final List<String> FEMALE_SURNAMES = List.of(
            "NOWAK",
            "KOWALSKA",
            "WIŚNIEWSKA",
            "WÓJCIK",
            "KOWALCZYK",
            "KAMIŃSKA",
            "LEWANDOWSKA",
            "ZIELIŃSKA",
            "WOŹNIAK",
            "SZYMAŃSKA",
            "DĄBROWSKA",
            "KOZŁOWSKA",
            "MAZUR",
            "JANKOWSKA",
            "KWIATKOWSKA",
            "WOJCIECHOWSKA",
            "KRAWCZYK",
            "KACZMAREK",
            "PIOTROWSKA",
            "GRABOWSKA"
    );
    private static final List<String> MALE_SURNAMES = List.of(
            "NOWAK",
            "KOWALSKI",
            "WIŚNIEWSKI",
            "WÓJCIK",
            "KOWALCZYK",
            "KAMIŃSKI",
            "LEWANDOWSKI",
            "ZIELIŃSKI",
            "WOŹNIAK",
            "SZYMAŃSKI",
            "DĄBROWSKI",
            "KOZŁOWSKI",
            "MAZUR",
            "JANKOWSKI",
            "KWIATKOWSKI",
            "WOJCIECHOWSKI",
            "KRAWCZYK",
            "KACZMAREK",
            "PIOTROWSKI",
            "GRABOWSKI"
    );


    public String getRandomFirstName(Sex sex) {
        return switch (sex) {
            case MALE -> getRandom(MALE_NAMES);
            case FEMALE -> getRandom(FEMALE_NAMES);
        };
    }

    public String getRandomSurname(Sex sex) {
        return switch (sex) {
            case MALE -> getRandom(MALE_SURNAMES);
            case FEMALE -> getRandom(FEMALE_SURNAMES);
        };
    }
}
