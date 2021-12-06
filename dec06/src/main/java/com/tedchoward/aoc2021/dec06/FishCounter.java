package com.tedchoward.aoc2021.dec06;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.val;

public class FishCounter {

    private record FishSpawnData(long age, long daysRemaining) {}

    private final Map<FishSpawnData, Long> simpleCache = new HashMap<>();

    private long calculateNumberOfFishSpawned(long age, long daysRemaining) {
        val cacheKey = new FishSpawnData(age, daysRemaining);
        if (simpleCache.containsKey(cacheKey)) {
            return simpleCache.get(cacheKey);
        }

        long numberOfChildren = Math.max((long) Math.ceil(((double) daysRemaining - (double) age) / 7.0), 0);

        for (long i = 0, cnt = numberOfChildren; i < cnt; i++) {
            numberOfChildren += calculateNumberOfFishSpawned(9, (daysRemaining - age) - (7 * i));
        }

        simpleCache.put(cacheKey, numberOfChildren);
        return numberOfChildren;
    }

    public long calculateFish(List<Integer> initialValues, int numberOfDays) {
        long count = initialValues.size();

        for (val age : initialValues) {
            count += calculateNumberOfFishSpawned(age, numberOfDays);
        }

        return count;
    }
}
