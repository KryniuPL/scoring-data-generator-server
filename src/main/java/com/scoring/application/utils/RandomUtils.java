package com.scoring.application.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.time.LocalDate;

public class RandomUtils {

    private static final SecureRandom random = new SecureRandom();
    private static final int PESEL_LENGTH = 11;

    public static String randomPesel() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < PESEL_LENGTH; i++) {
            stringBuilder.append(randomInteger(0, 9));
        }
        return stringBuilder.toString();
    }

    public static <T extends Enum<?>> T randomEnum(Class<T> clazz) {
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    public static <T extends Enum<?>> T randomEnum(T[] enumConstants) {
        int x = random.nextInt(enumConstants.length);
        return enumConstants[x];
    }

    public static Integer randomInteger(Integer min, Integer max) {
        return random.nextInt(min, max + 1);
    }

    public static Integer randomLong(Integer min, Integer max) {
        return Math.toIntExact(random.nextLong(min, max + 1));
    }

    public static Long randomLong(Long min, Long max) {
        return random.nextLong(min, max + 1);
    }

    public static BigDecimal randomBigDecimal() {
        return new BigDecimal(BigInteger.valueOf(random.nextInt(100001)), 2);
    }

    public static BigDecimal randomBigDecimal(BigDecimal min, BigDecimal max) {
        BigDecimal randomBigDecimal = min.add(BigDecimal.valueOf(Math.random()).multiply(max.subtract(min)));
        return randomBigDecimal.setScale(2, RoundingMode.HALF_DOWN);
    }

    public static LocalDate randomDate(LocalDate startInclusive, LocalDate endExclusive) {
        long startEpochDay = startInclusive.toEpochDay();
        long endEpochDay = endExclusive.toEpochDay();
        long randomDay = random.nextLong(startEpochDay, endEpochDay);

        return LocalDate.ofEpochDay(randomDay);
    }

    public static boolean randomBoolean() {
        return random.nextBoolean();
    }

}
