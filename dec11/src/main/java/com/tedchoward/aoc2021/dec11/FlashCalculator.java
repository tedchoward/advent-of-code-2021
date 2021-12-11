package com.tedchoward.aoc2021.dec11;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.val;

public class FlashCalculator {

    private final List<List<Integer>> rows;

    public FlashCalculator(List<List<Integer>> rows) {
        this.rows = new ArrayList<>();
        for (val row : rows) {
            val newRow = new ArrayList<>(row);
            this.rows.add(newRow);
        }
    }

    public long countFlashes(int numCycles) {
        var flashes = 0;

        while (--numCycles >= 0) {
            incrementOctupses();
            flashes += countFlashesCycle();
        }

        return flashes;
    }

    public int findFirstAllFlashCycle() {
        var i = 1;

        while (true) {
            incrementOctupses();
            var count = countFlashesCycle();

            if (count == 100) {
                return i;
            }

            i += 1;
        }
    }

    private long countFlashesCycle() {
        val cnt = 10;
        for (var y = 0; y < cnt; y++) {
            for (var x = 0; x < cnt; x++) {
                if (rows.get(y).get(x) > 9) {
                    flash(x, y);
                }
            }
        }

        return rows.stream().flatMap(Collection::stream).filter(value -> value == 0).count();
    }

    private void incrementOctupses() {
        for (val row : rows) {
            for (int x = 0, cnt = row.size(); x < cnt; x++) {
                row.set(x, row.get(x) + 1);
            }
        }
    }

    private void flash(int x, int y) {
        rows.get(y).set(x, 0);

        // top-left
        if (x > 0 && y > 0) {
            if (incrementOctopus(x - 1, y - 1)) {
                flash(x - 1, y - 1);
            }
        }

        // top
        if (y > 0) {
            if (incrementOctopus(x, y - 1)) {
                flash(x, y - 1);
            }
        }

        // top-right
        if (x < 9 && y > 0) {
            if (incrementOctopus(x + 1, y - 1)) {
                flash(x + 1, y - 1);
            }
        }

        // left
        if (x > 0) {
            if (incrementOctopus(x - 1, y)) {
                flash(x - 1, y);
            }
        }

        // right
        if (x < 9) {
            if (incrementOctopus(x + 1, y)) {
                flash(x + 1, y);
            }
        }

        // bottom-left
        if (x > 0 && y < 9) {
            if (incrementOctopus(x - 1, y + 1)) {
                flash(x - 1, y + 1);
            }
        }

        // bottom
        if (y < 9) {
            if (incrementOctopus(x, y + 1)) {
                flash(x, y + 1);
            }
        }

        // bottom-right
        if (x < 9 && y < 9) {
            if (incrementOctopus(x + 1, y + 1)) {
                flash(x + 1, y + 1);
            }
        }
    }

    /**
     *
     * @return true if we need to flash this octopus
     */
    private boolean incrementOctopus(int x, int y) {
        var value = rows.get(y).get(x);
        // if value is 0, it has already been flashed this cycle
        if (value != 0) {
            value += 1;
            rows.get(y).set(x, value);
            return value > 9;
        }

        return false;
    }
}
