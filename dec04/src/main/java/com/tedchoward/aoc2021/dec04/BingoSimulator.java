package com.tedchoward.aoc2021.dec04;

import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.val;
import thinwire.util.ArrayGrid;
import thinwire.util.Grid;

public class BingoSimulator {

    static class BingoCard {

        @Data
        @AllArgsConstructor
        static class Cell {

            private final int value;
            private boolean marked;
        }

        private final Grid values = new ArrayGrid();
        private final Map<Integer, Cell> reverseLookup = new HashMap<>();
        private int lastCalledValue = -1;

        public BingoCard(String cardData) {
            val rows = values.getRows();
            cardData
                .lines()
                .forEach(line -> {
                    val row = new ArrayGrid.Row();
                    Arrays
                        .stream(line.trim().split("\\s+"))
                        .map(Integer::parseInt)
                        .forEach(value -> {
                            val cell = new Cell(value, false);
                            row.add(cell);
                            reverseLookup.put(value, cell);
                        });
                    rows.add(row);
                });
        }

        public void markValueIfPresent(int value) {
            lastCalledValue = value;
            val cell = reverseLookup.get(value);
            if (cell != null) {
                cell.setMarked(true);
            }
        }

        public OptionalInt getWinningScore() {
            if (isWinningCard()) {
                return OptionalInt.of(calculateScore());
            }

            return OptionalInt.empty();
        }

        private boolean isWinningCard() {
            for (val row : values.getRows()) {
                if (row.stream().allMatch(cell -> ((Cell) cell).isMarked())) {
                    return true;
                }
            }

            for (val column : values.getColumns()) {
                if (column.stream().allMatch(cell -> ((Cell) cell).isMarked())) {
                    return true;
                }
            }

            return false;
        }

        private int calculateScore() {
            return getSumOfUnmarkedCells() * lastCalledValue;
        }

        private int getSumOfUnmarkedCells() {
            return values
                .getRows()
                .stream()
                .flatMap(Collection::stream)
                .filter(cell -> !((Cell) cell).isMarked())
                .map(cell -> ((Cell) cell).getValue())
                .reduce(0, Integer::sum);
        }
    }

    public int determineWinningScore(List<Integer> numbersToDraw, List<String> bingoCardData) {
        val bingoCards = bingoCardData.stream().map(BingoCard::new).toList();

        for (val calledNumber : numbersToDraw) {
            for (val bingoCard : bingoCards) {
                bingoCard.markValueIfPresent(calledNumber);
                if (bingoCard.getWinningScore().isPresent()) {
                    return bingoCard.getWinningScore().getAsInt();
                }
            }
        }

        throw new IllegalArgumentException("No winning card found for input");
    }

    public int determineLastWinningScore(List<Integer> numbersToDraw, List<String> bingoCardData) {
        val bingoCards = new ArrayList<>(bingoCardData.stream().map(BingoCard::new).toList());
        BingoCard lastBingoCard = null;

        for (val calledNumber : numbersToDraw) {
            if (bingoCards.isEmpty()) {
                break;
            }

            for (var it = bingoCards.iterator(); it.hasNext();) {
                val bingoCard = it.next();
                bingoCard.markValueIfPresent(calledNumber);
                if (bingoCard.getWinningScore().isPresent()) {
                    lastBingoCard = bingoCard;
                    it.remove();
                }
            }
        }

        if (lastBingoCard != null) {
            return lastBingoCard
                .getWinningScore()
                .orElseThrow(() -> new IllegalArgumentException("No winning card found for input"));
        }

        throw new IllegalArgumentException("No winning card found for input");
    }
}
