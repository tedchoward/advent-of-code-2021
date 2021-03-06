package com.tedchoward.aoc2021.dec07;

import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import lombok.val;

public class CrabAligner {

    public int calculateOptimumAlignemnt(List<Integer> initialPositions) {
        val sortedSet = sortPositions(initialPositions);

        val smallest = sortedSet.first();
        val largest = sortedSet.last();

        var optimalCost = Integer.MAX_VALUE;

        for (var i = smallest; i < largest; i++) {
            val targetPosition = i;
            val fuelCost = initialPositions
                .stream()
                .map(pos -> Math.abs(pos - targetPosition))
                .reduce(Integer::sum)
                .orElseThrow();
            if (fuelCost < optimalCost) {
                optimalCost = fuelCost;
            }
        }

        return optimalCost;
    }

    public int calculateActualOptimumAlignment(List<Integer> initialPositions) {
        val sortedSet = sortPositions(initialPositions);

        val smallest = sortedSet.first();
        val largest = sortedSet.last();

        var optimalCost = Integer.MAX_VALUE;

        for (var i = smallest; i < largest; i++) {
            val targetPosition = i;
            val fuelCost = initialPositions
                .stream()
                .map(pos -> calculateFuelCost(pos, targetPosition))
                .reduce(Integer::sum)
                .orElseThrow();

            if (fuelCost < optimalCost) {
                optimalCost = fuelCost;
            }
        }

        return optimalCost;
    }

    private int calculateFuelCost(int initialPosition, int destination) {
        val steps = Math.abs(initialPosition - destination);
        val cost = steps * ((1 + steps) / 2.0);
        return (int) cost;
    }

    private SortedSet<Integer> sortPositions(List<Integer> initialPositions) {
        val sortedSet = new TreeSet<Integer>(Comparator.comparingInt(a -> a));
        sortedSet.addAll(initialPositions);
        return sortedSet;
    }
}
