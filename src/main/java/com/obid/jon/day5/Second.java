package com.obid.jon.day5;

import com.obid.jon.Reader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;

public class Second {

    public static void main(String[] args) {
        List<String> lines = Reader.readLines(5, 1);

        List<Range> seedRanges = new ArrayList<>();
        List<List<Triple>> sections = new ArrayList<>();

        List<Triple> current = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (i == 0) {
                List<Long> seedPairs = Arrays.stream(line.split(": ")[1].split(" "))
                        .map(Long::parseLong)
                        .toList();
                for (int j = 0; j < seedPairs.size(); j += 2) {
                    seedRanges.add(new Range(seedPairs.get(j), seedPairs.get(j + 1)));
                }
            } else if (!line.isEmpty()) {
                if (line.contains(":")) {
                    current = new ArrayList<>();
                    sections.add(current);
                } else {
                    List<Long> list = Arrays.stream(line.split(" "))
                            .map(Long::parseLong)
                            .toList();
                    current.add(new Triple(list.get(0), list.get(1), list.get(2)));
                }
            }
        }

        for (List<Triple> triples : sections) {
            List<Range> newRanges = new ArrayList<>();
            for (Range range : seedRanges) {
                LongStream.range(range.start, range.start + range.length)
                        .forEach(seed -> {
                            boolean mapped = false;
                            for (Triple triple : triples) {
                                if (seed >= triple.destination && seed < triple.destination + triple.length) {
                                    long mappedSeed = triple.source + (seed - triple.destination);
                                    addToRanges(newRanges, mappedSeed);
                                    mapped = true;
                                    break;
                                }
                            }
                            if (!mapped) {
                                addToRanges(newRanges, seed);
                            }
                        });
            }
            seedRanges = newRanges;
        }

        long lowestLocation = seedRanges.stream()
                .flatMapToLong(range -> LongStream.range(range.start, range.start + range.length))
                .min()
                .orElseThrow();
        System.out.println(lowestLocation);
    }

    static void addToRanges(List<Range> ranges, long value) {
        for (Range range : ranges) {
            if (range.start + range.length == value) {
                range.length++;
                return;
            }
        }
        ranges.add(new Range(value, 1));
    }

    static class Triple {
        long source;
        long destination;
        long length;

        public Triple(long source, long destination, long length) {
            this.source = source;
            this.destination = destination;
            this.length = length;
        }
    }

    static class Range {
        long start;
        long length;

        public Range(long start, long length) {
            this.start = start;
            this.length = length;
        }
    }
}
