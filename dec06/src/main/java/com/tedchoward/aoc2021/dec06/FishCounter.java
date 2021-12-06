package com.tedchoward.aoc2021.dec06;

import lombok.val;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FishCounter {
    static class LanternFish {
        private int value;

        public LanternFish() {
            this(8);
        }

        public LanternFish(int value) {
            this.value = value;
        }

        public void ageOneDay() {
            if (this.value == 0) {
                this.value = 6;
            } else {
                this.value -= 1;
            }
        }

        public boolean isSpawning() {
            return this.value == 0;
        }
    }

    private record FishSpawnData(long age, long daysRemaining) {}
    private final Map<FishSpawnData, Long> simpleCache = new HashMap<>();

    public long calculateNumberOfFishSpawned(long age, long daysRemaining) {
        val cacheKey = new FishSpawnData(age, daysRemaining);
        if (simpleCache.containsKey(cacheKey)) {
            return simpleCache.get(cacheKey);
        }

        long numberOfChildren = Math.max((long) Math.ceil(((double)daysRemaining - (double)age) / 7.0), 0);

        for (long i = 0, cnt = numberOfChildren; i < cnt; i++) {
            numberOfChildren += calculateNumberOfFishSpawned(9, (daysRemaining - age) - (7 * i));
        }

        simpleCache.put(cacheKey, numberOfChildren);
        return numberOfChildren;
    }

    static class School {
        private final List<LanternFish> lanternFish = new ArrayList<>();

        public School(List<Integer> initialValues) {
            lanternFish.addAll(initialValues.stream().map(LanternFish::new).toList());
        }

        public void nextDay() {
            var fishToAdd = 0;
            for (val fish : lanternFish) {
                if (fish.isSpawning()) {
                    fishToAdd += 1;
                }
                fish.ageOneDay();
            }

            while (--fishToAdd >= 0) {
                lanternFish.add(new LanternFish());
            }
        }

        public long size() {
            return lanternFish.stream().count();
        }
    }

    public long countFish(List<Integer> initialValues, int numberOfDays) {
        val school = new School(initialValues);

        for (var i = 0; i < numberOfDays; i++) {
            school.nextDay();
        }

        return school.size();
    }

    public long calculateFish(List<Integer> initialValues, int numberOfDays) {
        long count = initialValues.size();

        for (val age : initialValues) {
            count += calculateNumberOfFishSpawned(age, numberOfDays);
        }

        return count;
    }
}
