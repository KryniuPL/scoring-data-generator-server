package com.scoring.application.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;

public class RandomUtils {

    private static final SecureRandom random = new SecureRandom();

    public static <T extends Enum<?>> T randomEnum(Class<T> clazz) {
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    public static Integer randomInteger(Integer min, Integer max) {
        return random.nextInt(min, max + 1);
    }

    public static BigDecimal randomBigDecimal() {
        return new BigDecimal(BigInteger.valueOf(random.nextInt(100001)), 2);
    }
}
