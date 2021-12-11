package com.tedchoward.aoc2021.dec10;

import java.util.*;
import lombok.Getter;
import lombok.val;

public class NavigationSubSystem {

    static class Error extends RuntimeException {

        @Getter
        private final char expected;

        @Getter
        private final char actual;

        public Error(char expected, char actual) {
            super();
            this.expected = expected;
            this.actual = actual;
        }
    }

    private static final Map<Character, Integer> SYNTAX_ERROR_SCORES = Map.of(')', 3, ']', 57, '}', 1197, '>', 25137);

    private static final Map<Character, Integer> AUTO_COMPLETE_SCORES = Map.of(')', 1, ']', 2, '}', 3, '>', 4);

    public int scoreCorruptedLineErrors(List<String> lines) {
        var score = 0;
        for (val line : lines) {
            try {
                final List<Character> stack = new ArrayList<>();

                for (val ch : line.toCharArray()) {
                    switch (ch) {
                        case '(' -> stack.add(')');
                        case '[' -> stack.add(']');
                        case '{' -> stack.add('}');
                        case '<' -> stack.add('>');
                        case ')' -> consumeToken(')', stack);
                        case ']' -> consumeToken(']', stack);
                        case '}' -> consumeToken('}', stack);
                        case '>' -> consumeToken('>', stack);
                    }
                }
            } catch (Error e) {
                val s = SYNTAX_ERROR_SCORES.get(e.getActual());
                score += s;
            }
        }

        return score;
    }

    public long scoreIncompleteLineErrors(List<String> lines) {
        final List<Long> scores = new ArrayList<>();

        for (val line : lines) {
            var score = 0L;
            try {
                final List<Character> stack = new ArrayList<>();

                for (val ch : line.toCharArray()) {
                    switch (ch) {
                        case '(' -> stack.add(')');
                        case '[' -> stack.add(']');
                        case '{' -> stack.add('}');
                        case '<' -> stack.add('>');
                        case ')' -> consumeToken(')', stack);
                        case ']' -> consumeToken(']', stack);
                        case '}' -> consumeToken('}', stack);
                        case '>' -> consumeToken('>', stack);
                    }
                }

                while (stack.size() > 0) {
                    val t = stack.remove(stack.size() - 1);
                    val s = AUTO_COMPLETE_SCORES.get(t);
                    score *= 5;
                    score += s;
                }

                scores.add(score);
            } catch (Error e) {
                // ignore corrupt lines
            }
        }

        scores.sort(Comparator.naturalOrder());
        val middleIndex = scores.size() / 2;
        return scores.get(middleIndex);
    }

    private void consumeToken(Character token, List<Character> stack) {
        val expected = stack.remove(stack.size() - 1);
        if (token != expected) {
            throw new Error(expected, token);
        }
    }
}
