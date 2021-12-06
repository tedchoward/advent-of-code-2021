package com.tedchoward.aoc2021.dec05;

import java.util.*;
import lombok.val;

public class VentPlotter {

    record Point(int x, int y) {}

    record Line(Point start, Point end) implements Iterable<Point> {
        private static class PointIterator implements Iterator<Point> {

            enum Direction {
                INCREMENT,
                DECREMENT,
                NONE,
            }

            private final Direction xDirection;
            private final Direction yDirection;
            private final Line line;
            private int currentX;
            private int currentY;

            public PointIterator(Line line) {
                this.line = line;

                if (line.start().x() < line.end().x()) {
                    xDirection = Direction.INCREMENT;
                } else if (line.start().x() > line.end().x()) {
                    xDirection = Direction.DECREMENT;
                } else {
                    xDirection = Direction.NONE;
                }

                if (line.start().y() < line.end().y()) {
                    yDirection = Direction.INCREMENT;
                } else if (line.start().y() > line.end().y()) {
                    yDirection = Direction.DECREMENT;
                } else {
                    yDirection = Direction.NONE;
                }

                currentX =
                    switch (xDirection) {
                        case INCREMENT -> this.line.start().x() - 1;
                        case DECREMENT -> this.line.start().x() + 1;
                        case NONE -> this.line.start().x();
                    };

                currentY =
                    switch (yDirection) {
                        case INCREMENT -> this.line.start().y() - 1;
                        case DECREMENT -> this.line.start().y() + 1;
                        case NONE -> this.line.start().y();
                    };
            }

            private OptionalInt nextX() {
                return switch (xDirection) {
                    case INCREMENT -> currentX + 1 <= line.end().x()
                        ? OptionalInt.of(currentX + 1)
                        : OptionalInt.empty();
                    case DECREMENT -> currentX - 1 >= line.end().x()
                        ? OptionalInt.of(currentX - 1)
                        : OptionalInt.empty();
                    case NONE -> OptionalInt.of(currentX);
                };
            }

            private OptionalInt nextY() {
                return switch (yDirection) {
                    case INCREMENT -> currentY + 1 <= line.end().y()
                        ? OptionalInt.of(currentY + 1)
                        : OptionalInt.empty();
                    case DECREMENT -> currentY - 1 >= line.end().y()
                        ? OptionalInt.of(currentY - 1)
                        : OptionalInt.empty();
                    case NONE -> OptionalInt.of(currentY);
                };
            }

            @Override
            public boolean hasNext() {
                return nextX().isPresent() && nextY().isPresent();
            }

            @Override
            public Point next() {
                val nextX = nextX().orElseThrow();
                val nextY = nextY().orElseThrow();

                currentX = nextX;
                currentY = nextY;

                return new Point(nextX, nextY);
            }
        }

        boolean isHorizontal() {
            return start.y() == end.y();
        }

        boolean isVertical() {
            return start.x() == end.x();
        }

        boolean isHorizontalOrVertical() {
            return isHorizontal() || isVertical();
        }

        @Override
        public Iterator<Point> iterator() {
            return new PointIterator(this);
        }
    }

    static class SimpleGrid {

        private final List<List<Integer>> columns = new ArrayList<>();

        public List<Integer> getColumn(int index) {
            while (columns.size() <= index) {
                columns.add(new ArrayList<>());
            }

            return columns.get(index);
        }

        public OptionalInt getValue(int x, int y) {
            if (columns.size() > x) {
                val column = columns.get(x);
                if (column != null && column.size() > y) {
                    val value = column.get(y);
                    if (value != null) {
                        return OptionalInt.of(value);
                    }
                }
            }

            return OptionalInt.empty();
        }

        public void setValue(int x, int y, int value) {
            val column = getColumn(x);
            while (column.size() <= y) {
                column.add(null);
            }

            column.set(y, value);
        }

        public long getOverlappingCount() {
            return columns.stream().flatMap(Collection::stream).filter(Objects::nonNull).filter(n -> n > 1).count();
        }

        public void print() {
            val maxX = columns.size();
            val maxY = (int) columns.stream().map(List::size).max(Comparator.comparingInt(a -> a)).orElse(0);

            val sb = new StringBuilder();
            for (var y = 0; y < maxY; y++) {
                sb.setLength(0);

                for (var x = 0; x < maxX; x++) {
                    getValue(x, y).ifPresentOrElse(sb::append, () -> sb.append('.'));
                }

                System.out.println(sb);
            }
        }
    }

    public long countOverlappingPoints(List<Line> lines) {
        final SimpleGrid grid = new SimpleGrid();

        lines
            .stream()
            .filter(Line::isHorizontalOrVertical)
            .forEach(line -> {
                for (val point : line) {
                    val x = point.x();
                    val y = point.y();
                    val value = grid.getValue(x, y).orElse(0) + 1;
                    grid.setValue(x, y, value);
                }
            });

        grid.print();

        return grid.getOverlappingCount();
    }

    public long countAllOverlappingPoints(List<Line> lines) {
        final SimpleGrid grid = new SimpleGrid();

        for (val line : lines) {
            for (val point : line) {
                val x = point.x();
                val y = point.y();
                val value = grid.getValue(x, y).orElse(0) + 1;
                grid.setValue(x, y, value);
            }
        }

        grid.print();

        return grid.getOverlappingCount();
    }
}
