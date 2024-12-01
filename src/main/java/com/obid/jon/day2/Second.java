package com.obid.jon.day2;

import com.obid.jon.Reader;

import java.util.ArrayList;
import java.util.List;

public class Second {
    public static void main(String[] args) {
        List<String> lines = Reader.readLines(2, 1);
        List<Wrapper> result = parse(lines);

        int sum = 0;
        for (Wrapper wr : result) {
            int max1 = 0;
            int max2 = 0;
            int max3 = 0;
            for (RGB set : wr.sets) {
                max1 = Math.max(max1, set.red);
                max2 = Math.max(max2, set.green);
                max3 = Math.max(max3, set.blue);
            }
            sum += max1 * max2 * max3;
        }
        System.out.println(sum);
    }

    private static List<Wrapper> parse(List<String> lines) {
        ArrayList<Wrapper> wrappers = new ArrayList<>();
        for (String line : lines) {
            int semi = line.indexOf(":");
            int id = Integer.parseInt(line.substring(line.indexOf(" ") + 1, semi));
            String right = line.substring(semi + 1);
            String[] split = right.split(";");

            Wrapper wrapper = new Wrapper();
            wrapper.id = id;
            ArrayList<RGB> rgbs = new ArrayList<>();
            wrapper.sets = rgbs;

            wrappers.add(wrapper);

            for (String s : split) {
                RGB rgb = new RGB();
                String[] tokens = s.trim().split("\\s|,\\s");
                for (int j = 0; j < tokens.length; j += 2) {
                    if (tokens[j + 1].equals("blue")) {
                        rgb.blue = Integer.parseInt(tokens[j]);
                    } else if (tokens[j + 1].equals("red")) {
                        rgb.red = Integer.parseInt(tokens[j]);
                    } else {
                        rgb.green = Integer.parseInt(tokens[j]);
                    }
                }
                rgbs.add(rgb);
            }
        }
        return wrappers;
    }

    static class Wrapper {
        int id;
        List<RGB> sets;
    }

    static class RGB {
        int red;
        int green;
        int blue;
    }
}
