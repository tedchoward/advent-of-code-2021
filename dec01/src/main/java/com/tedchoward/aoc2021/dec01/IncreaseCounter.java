package com.tedchoward.aoc2021.dec01;

import java.util.ArrayList;
import java.util.List;
import lombok.val;

public class IncreaseCounter {

    public int countIncreases(List<Integer> depths) {
        var previous = Integer.MAX_VALUE;
        var increases = 0;

        for (val depth : depths) {
            if (depth > previous) {
                increases += 1;
            }

            previous = depth;
        }

        return increases;
    }

    public int countThreeIncreases(List<Integer> depths) {
        final List<Integer> windows = new ArrayList<>();

        for (int i = 0, cnt = depths.size() - 2; i < cnt; i++) {
            val window = depths.get(i) + depths.get(i + 1) + depths.get(i + 2);
            windows.add(window);
        }

        return countIncreases(windows);
    }
}
