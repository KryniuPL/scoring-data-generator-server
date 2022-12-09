package com.scoring.application.repository;

import com.scoring.domain.Sex;

import java.util.List;
import java.util.Random;

public abstract class NamesRepository {

    public abstract String getRandomFirstName(Sex sex);
    public abstract String getRandomSurname(Sex sex);

    protected String getRandom(List<String> names) {
        int rnd = new Random().nextInt(names.size());
        return names.get(rnd);
    }

}
