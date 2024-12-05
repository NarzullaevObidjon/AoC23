package com.obid.jon.day5;

import com.obid.jon.Reader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class First {
    public static void main(String[] args) {
        List<String> lines = Reader.readLines(5, 1);

        List<Long> seeds = new ArrayList<>();
        List<List<Triple>> sections = new ArrayList<>();

        List<Triple> current = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (i == 0) {
                List<Long> list = Arrays.stream(line.split(": ")[1].split(" ")).map(Long::parseLong).toList();
                seeds.addAll(list);
            } else if (!line.isEmpty()) {
                if (line.contains(":")) {
                    current = new ArrayList<>();
                    sections.add(current);
                } else {
                    List<Long> list = Arrays.stream(line.split(" ")).map(Long::parseLong).toList();
                    current.add(new Triple(list.get(0), list.get(1), list.get(2)));
                }
            }
        }

        for (List<Triple> triples : sections) {
            List<Long> newSeeds = new ArrayList<>();
            m:
            for (Long seed : seeds) {
                for (int k = 0; k < triples.size(); k++) {
                    Triple triple = triples.get(k);
                    if (triple.destination <= seed && triple.destination + triple.length >= seed) {
                        newSeeds.add(triple.source + (seed - triple.destination));
                        continue m;
                    }
                    if (k == triples.size() - 1) {
                        newSeeds.add(seed);
                    }
                }
            }
            seeds = newSeeds;
        }

        Long i = seeds.stream().sorted().findFirst().get();
        System.out.println(i);
    }

    static class Triple {
        long source;
        long destination;
        long length;

        public Triple(long source, long destination, long length) {
            this.destination = destination;
            this.source = source;
            this.length = length;
        }
    }
}
