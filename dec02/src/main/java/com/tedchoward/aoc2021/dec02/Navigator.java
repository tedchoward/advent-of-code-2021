package com.tedchoward.aoc2021.dec02;

import java.util.List;
import lombok.val;

public class Navigator {

    enum Direction {
        FORWARD,
        DOWN,
        UP,
    }

    record Command(Direction direction, int units) {}

    public int trackCourse(List<Command> commands) {
        var horizontalPosition = 0;
        var depth = 0;

        for (val command : commands) {
            switch (command.direction()) {
                case FORWARD -> horizontalPosition += command.units();
                case DOWN -> depth += command.units();
                case UP -> depth -= command.units();
            }
        }

        return horizontalPosition * depth;
    }

    public int trackAimedCourse(List<Command> commands) {
        var aim = 0;
        var horizontalPosition = 0;
        var depth = 0;

        for (val command : commands) {
            switch (command.direction()) {
                case FORWARD -> {
                    horizontalPosition += command.units();
                    depth += (aim * command.units());
                }
                case DOWN -> aim += command.units();
                case UP -> aim -= command.units();
            }
        }

        return horizontalPosition * depth;
    }
}
