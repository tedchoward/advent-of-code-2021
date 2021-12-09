package com.tedchoward.aoc2021.dec09;

import java.util.*;
import lombok.val;

public class SmokeFlowModeler {

    private final List<List<Integer>> heightMap;
    private final int rowCnt;
    private final int colCnt;

    public SmokeFlowModeler(List<List<Integer>> heightMap) {
        this.heightMap = heightMap;
        rowCnt = heightMap.size();
        colCnt = heightMap.get(0).size();
    }

    public int calculateRiskLevel() {
        var riskLevel = 0;

        for (int y = 0, rowCnt = heightMap.size(); y < rowCnt; y++) {
            val row = heightMap.get(y);
            for (int x = 0; x < colCnt; x++) {
                val value = heightMap.get(y).get(x);

                if (isLowest(value, x, y)) {
                    riskLevel += value + 1;
                }
            }
        }
        return riskLevel;
    }

    public int findBasins() {
        val basinSizes = new ArrayList<Integer>();

        for (int y = 0; y < rowCnt; y++) {
            for (int x = 0; x < colCnt; x++) {
                val value = heightMap.get(y).get(x);
                if (isLowest(value, x, y)) {
                    basinSizes.add(calculateBasinSize(value, x, y, new HashSet<>()));
                }
            }
        }

        basinSizes.sort(Collections.reverseOrder());

        return basinSizes.stream().limit(3).reduce(1, (acc, curr) -> acc * curr);
    }

    private record Point(int x, int y) {}

    private int calculateBasinSize(int value, int x, int y, Set<Point> checkedLocations) {
        checkedLocations.add(new Point(x, y));
        var basinSize = 1;

        if (y > 0 && !checkedLocations.contains(new Point(x, y - 1))) {
            val above = heightMap.get(y - 1).get(x);
            if (above != 9 && above >= value + 1) {
                basinSize += calculateBasinSize(above, x, y - 1, checkedLocations);
            }
        }

        if (y < (rowCnt - 1) && !checkedLocations.contains(new Point(x, y + 1))) {
            val below = heightMap.get(y + 1).get(x);
            if (below != 9 && below >= value + 1) {
                basinSize += calculateBasinSize(below, x, y + 1, checkedLocations);
            }
        }

        if (x > 0 && !checkedLocations.contains(new Point(x - 1, y))) {
            val left = heightMap.get(y).get(x - 1);
            if (left != 9 && left >= value + 1) {
                basinSize += calculateBasinSize(left, x - 1, y, checkedLocations);
            }
        }

        if (x < (colCnt - 1) && !checkedLocations.contains(new Point(x + 1, y))) {
            val right = heightMap.get(y).get(x + 1);
            if (right != 9 && right >= value + 1) {
                basinSize += calculateBasinSize(right, x + 1, y, checkedLocations);
            }
        }

        return basinSize;
    }

    private boolean isLowest(int value, int x, int y) {
        if (y > 0) {
            val above = heightMap.get(y - 1).get(x);
            if (value >= above) {
                return false;
            }
        }

        if (y < (rowCnt - 1)) {
            val below = heightMap.get(y + 1).get(x);
            if (value >= below) {
                return false;
            }
        }

        if (x > 0) {
            val left = heightMap.get(y).get(x - 1);
            if (value >= left) {
                return false;
            }
        }

        if (x < (colCnt - 1)) {
            val right = heightMap.get(y).get(x + 1);
            if (value >= right) {
                return false;
            }
        }

        return true;
    }
}
