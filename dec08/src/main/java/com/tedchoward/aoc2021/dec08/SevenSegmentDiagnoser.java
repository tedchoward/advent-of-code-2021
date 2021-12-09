package com.tedchoward.aoc2021.dec08;

import java.util.*;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.val;

public class SevenSegmentDiagnoser {

    private static final Map<Character, Integer> CHAR_TO_BINARY_POSITION = Map.of(
        'a',
        0b0000001,
        'b',
        0b0000010,
        'c',
        0b0000100,
        'd',
        0b0001000,
        'e',
        0b0010000,
        'f',
        0b0100000,
        'g',
        0b1000000
    );

    @Getter
    static class Entry {

        private final List<String> signalPatterns = new ArrayList<>();
        private final List<String> outputValue = new ArrayList<>();
    }

    public long countOnesFoursSevensAndEights(List<Entry> entries) {
        val segmentValues = entries.stream().flatMap(entry -> entry.getOutputValue().stream()).toList();
        return segmentValues
            .stream()
            .map(String::length)
            .filter(length -> length == 2 || length == 4 || length == 3 || length == 7)
            .count();
    }

    public int calculateAllDisplayValues(List<Entry> entries) {
        return entries.stream().map(this::getDisplayValue).reduce(Integer::sum).orElseThrow();
    }

    public int getDisplayValue(Entry entry) {
        val one = entry
            .getSignalPatterns()
            .stream()
            .filter(signalPattern -> signalPattern.length() == 2)
            .findFirst()
            .map(this::getIntValue)
            .orElseThrow();
        val four = entry
            .getSignalPatterns()
            .stream()
            .filter(signalPattern -> signalPattern.length() == 4)
            .findFirst()
            .map(this::getIntValue)
            .orElseThrow();
        val seven = entry
            .getSignalPatterns()
            .stream()
            .filter(signalPattern -> signalPattern.length() == 3)
            .findFirst()
            .map(this::getIntValue)
            .orElseThrow();
        val eight = entry
            .getSignalPatterns()
            .stream()
            .filter(signalPattern -> signalPattern.length() == 7)
            .findFirst()
            .map(this::getIntValue)
            .orElseThrow();

        val zeroSixNine = entry
            .getSignalPatterns()
            .stream()
            .filter(signalPattern -> signalPattern.length() == 6)
            .map(this::getIntValue)
            .collect(Collectors.toSet());

        if (zeroSixNine.size() != 3) {
            throw new IllegalStateException("zeroSixNine.size() != 3");
        }

        val nine = zeroSixNine.stream().filter(sp -> (four & (sp ^ eight)) == 0).findFirst().orElseThrow();

        val zeroSix = zeroSixNine.stream().filter(sp -> !Objects.equals(sp, nine)).toList();
        if (zeroSix.size() != 2) {
            throw new IllegalStateException("zeroSix.size() != 2");
        }

        val cd = zeroSix.stream().reduce((acc, curr) -> acc ^ curr).orElseThrow();
        val c = cd & one;

        val zero = zeroSix.stream().filter(sp -> (sp & c) != 0).findFirst().orElseThrow();
        val six = zeroSix.stream().filter(sp -> !Objects.equals(sp, zero)).findFirst().orElseThrow();

        val twoThreeFive = entry
            .getSignalPatterns()
            .stream()
            .filter(signalPattern -> signalPattern.length() == 5)
            .map(this::getIntValue)
            .collect(Collectors.toSet());
        if (twoThreeFive.size() != 3) {
            throw new IllegalStateException("twoThreeFive.size() != 3");
        }
        val five = twoThreeFive.stream().filter(sp -> (sp & c) == 0).findFirst().orElseThrow();
        val twoThree = twoThreeFive.stream().filter(sp -> !Objects.equals(sp, five)).toList();

        val e = five ^ six;
        val two = twoThree.stream().filter(sp -> (sp & e) != 0).findFirst().orElseThrow();
        val three = twoThree.stream().filter(sp -> !Objects.equals(sp, two)).findFirst().orElseThrow();

        final Map<Integer, String> digitValues = Map.of(
            zero,
            "0",
            one,
            "1",
            two,
            "2",
            three,
            "3",
            four,
            "4",
            five,
            "5",
            six,
            "6",
            seven,
            "7",
            eight,
            "8",
            nine,
            "9"
        );

        val resultString = entry
            .getOutputValue()
            .stream()
            .map(this::getIntValue)
            .map(digitValues::get)
            .reduce("", (acc, curr) -> acc + curr);
        val result = Integer.parseInt(resultString);
        return result;
    }

    private int getIntValue(String signalPattern) {
        var intValue = 0;
        for (val ch : signalPattern.toCharArray()) {
            val charIntValue = CHAR_TO_BINARY_POSITION.get(ch);
            intValue |= charIntValue;
        }

        return intValue;
    }
}
