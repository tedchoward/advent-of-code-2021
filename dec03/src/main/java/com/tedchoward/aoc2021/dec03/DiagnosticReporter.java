package com.tedchoward.aoc2021.dec03;

import java.util.ArrayList;
import java.util.List;
import lombok.val;

public class DiagnosticReporter {

    static class DiagnosticData {

        static class Digit {

            private int ones = 0;
            private int zeroes = 0;

            public int getValue(int total) {
                if ((total - ones) <= ones) {
                    return 1;
                }

                return 0;
            }

            public int getInverseValue(int total) {
                if ((total - ones) <= ones) {
                    return 0;
                }

                return 1;
            }
        }

        private final List<Digit> digits = new ArrayList<>();
        private int count = 0;

        public DiagnosticData() {}

        public DiagnosticData(List<Integer> data) {
            for (val number : data) {
                addNumber(number);
            }
        }

        private Digit getDigit(int index) {
            while (digits.size() <= index) {
                digits.add(new Digit());
            }

            return digits.get(index);
        }

        public void addNumber(int number) {
            count += 1;
            for (int i = 0; number > 0; number >>= 1) {
                if ((number & 0x01) == 1) {
                    getDigit(i).ones += 1;
                } else {
                    getDigit(i).zeroes += 1;
                }
                i += 1;
            }
        }

        public int getGamma() {
            var gamma = 0;

            for (int i = digits.size(); --i >= 0;) {
                gamma <<= 1;
                val digit = digits.get(i);
                gamma |= digit.getValue(count);
            }

            return gamma;
        }

        public int getEpsilon() {
            var epsilon = 0;

            for (int i = digits.size(); --i >= 0;) {
                epsilon <<= 1;
                val digit = digits.get(i);
                epsilon |= digit.getInverseValue(count);
            }

            return epsilon;
        }
    }

    public int calculatePowerConsumption(List<Integer> data) {
        val disgnosticData = new DiagnosticData();
        for (val number : data) {
            disgnosticData.addNumber(number);
        }

        val gamma = disgnosticData.getGamma();
        val epsilon = disgnosticData.getEpsilon();

        return gamma * epsilon;
    }

    public int calculateLifeSupportRating(List<Integer> data) {
        val disgnosticData = new DiagnosticData(data);

        var oxygenGeneratorRating = 0;
        List<Integer> filteredData = new ArrayList<>(data);

        for (int i = disgnosticData.digits.size(); --i >= 0;) {
            val ddata = new DiagnosticData(filteredData);
            val digit = ddata.getDigit(i);

            val mask = 1 << i;
            if (digit.getValue(filteredData.size()) == 1) {
                filteredData = filteredData.stream().filter(num -> (num & mask) > 0).toList();
            } else {
                filteredData = filteredData.stream().filter(num -> (num & mask) == 0).toList();
            }

            if (filteredData.size() == 1) {
                oxygenGeneratorRating = filteredData.get(0);
                break;
            }
        }

        var cO2ScrubberRating = 0;
        filteredData = new ArrayList<>(data);

        for (int i = disgnosticData.digits.size(); --i >= 0;) {
            val ddata = new DiagnosticData(filteredData);
            val digit = ddata.getDigit(i);

            val mask = 1 << i;
            if (digit.getInverseValue(filteredData.size()) == 1) {
                filteredData = filteredData.stream().filter(num -> (num & mask) > 0).toList();
            } else {
                filteredData = filteredData.stream().filter(num -> (num & mask) == 0).toList();
            }
            if (filteredData.size() == 1) {
                cO2ScrubberRating = filteredData.get(0);
                break;
            }
        }

        return oxygenGeneratorRating * cO2ScrubberRating;
    }
}
