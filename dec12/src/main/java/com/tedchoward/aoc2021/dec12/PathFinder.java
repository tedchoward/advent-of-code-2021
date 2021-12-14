package com.tedchoward.aoc2021.dec12;

import java.util.*;
import lombok.*;

public class PathFinder {

    @RequiredArgsConstructor
    static class Cave {

        @Getter
        private final String name;

        @Getter
        private final boolean big;

        @Getter
        private final List<Cave> connectedCaves = new ArrayList<>();

        @Getter
        @Setter
        private int visitCount = 0;

        public void visit() {
            visitCount += 1;
        }

        public boolean canVisit() {
            return big || visitCount == 0;
        }

        public void reset() {
            visitCount = 0;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cave cave = (Cave) o;
            return name.equals(cave.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        @Override
        public String toString() {
            return "Cave{" + "name='" + name + '\'' + ", visitCount=" + visitCount + '}';
        }
    }

    private final Map<String, Cave> caves = new HashMap<>();
    private Cave start;
    private Cave end;
    private boolean visitedSmallTwice = false;
    private final List<Cave> currentPath = new ArrayList<>();

    public PathFinder(List<String> connections) {
        for (val connection : connections) {
            val parts = connection.split("-");
            val left = getCave(parts[0]);
            val right = getCave(parts[1]);
            left.getConnectedCaves().add(right);
            right.getConnectedCaves().add(left);
        }
    }

    public int countPaths() {
        return countPaths(start);
    }

    private int countPaths(Cave cave) {
        var count = 0;
        for (val c : cave.getConnectedCaves()) {
            if (c.equals(start)) {
                continue;
            }

            if (c.equals(end)) {
                count += 1;
            } else if (c.canVisit()) {
                c.visit();
                count += countPaths(c);
            }
        }

        cave.reset();

        return count;
    }

    public int countMorePaths() {
        return countMorePaths(start);
    }

    private int countMorePaths(Cave cave) {
        currentPath.add(cave);
        var count = 0;
        for (val c : cave.getConnectedCaves()) {
            if (c.equals(start)) {
                continue;
            }

            if (c.equals(end)) {
                count += 1;
            } else if (canVisit(c)) {
                visit(c);
                count += countMorePaths(c);
            }
        }

        reset(cave);

        currentPath.remove(currentPath.size() - 1);
        return count;
    }

    private boolean canVisit(Cave cave) {
        if (cave.isBig()) {
            return true;
        }

        if (visitedSmallTwice) {
            return cave.getVisitCount() < 1;
        }

        return cave.getVisitCount() < 2;
    }

    private void visit(Cave cave) {
        cave.visit();

        if (!cave.isBig() && !visitedSmallTwice && cave.getVisitCount() > 1) {
            visitedSmallTwice = true;
        }
    }

    private void reset(Cave cave) {
        if (!cave.isBig() && cave.getVisitCount() > 1) {
            visitedSmallTwice = false;
        }

        cave.setVisitCount(cave.getVisitCount() - 1);
    }

    private Cave getCave(String name) {
        if (caves.containsKey(name)) {
            return caves.get(name);
        }

        val isBig = name.toUpperCase(Locale.ROOT).equals(name);
        val cave = new Cave(name, isBig);

        if ("start".equals(name)) {
            start = cave;
        } else if ("end".equals(name)) {
            end = cave;
        }

        caves.put(name, cave);

        return cave;
    }
}
