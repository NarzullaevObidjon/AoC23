package com.obid.jon.day24;

import com.obid.jon.Reader;

import java.util.ArrayList;
import java.util.List;

public class First {
    static long[] bounds = {200000000000000L, 400000000000000L};

    public static void main(String[] args) {
        List<String> lines = Reader.readLines(24, 1);
        List<Hold> holds = parse(lines);
        // x = x_0 + vx * t;
        // y = y_0 + vy * t;

        // y = a + d * (x - b) / c;
        // y = m + q * (x - n) / p;

        // a + d * (x - b) / c = m + q * (x - n) / p
        // a - m = q * (x - n) / p - d * (x - b) / c
        // pc * (a - m) = -dpx+dpb+qcx-qcn;
        // (pc * (a-m) + qcn - qbp)/(qc - dp) = x

        int count = 0;
        boolean[] crossed = new boolean[holds.size()];
        for (int i = 0; i < holds.size(); i++) {
            if (!crossed[i]) {
                for (int j = i + 1; j < holds.size(); j++) {
                    if (!crossed[j]) {
                        Point point = findIntersectPoint(holds.get(i), holds.get(j));
                        if (point.x >= bounds[0] && point.x <= bounds[1] && point.y >= bounds[0] && point.y <= bounds[1]) {
                            count++;
                            crossed[j] = true;
                            break;
                        }
                    }
                }
            }
        }
        System.out.println(count);
    }

    public static Point findIntersection(
            double a, double d, double b, double c,
            double m, double q, double n, double p) {

        // Calculate denominator to ensure lines are not parallel
        double denominator = (-d * p + q * c);
        if (denominator == 0) {
            return new Point(0, 0);
        }

        // Calculate numerator for x
        double numerator = (p * c * (a - m)) + (q * c * n) - (d * p * b);

        // Calculate x
        double x = numerator / denominator;

        // Calculate y using either line equation (e.g., the first line)
        double y = a + d * (x - b) / c;

        return new Point(x, y);
    }

    private static Point findIntersectPoint(Hold equation1, Hold equation2) {
        return findIntersection(
                equation1.initial.y, equation1.vy, equation1.initial.x, equation1.vx,
                equation2.initial.y, equation2.vy, equation2.initial.x, equation2.vx
        );
    }

    private static List<Hold> parse(List<String> lines) {
        List<Hold> holds = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split("@");
            String[] initialParts = parts[0].split(",");
            String[] velocityParts = parts[1].split(",");

            // Parse initial point (x, y)
            long x = Long.parseLong(initialParts[0].trim());
            long y = Long.parseLong(initialParts[1].trim());

            // Parse velocities (vx, vy)
            long vx = Long.parseLong(velocityParts[0].trim());
            long vy = Long.parseLong(velocityParts[1].trim());

            // Create and add Hold object
            Hold hold = new Hold();
            hold.initial = new Point(x, y);
            hold.vx = vx;
            hold.vy = vy;

            holds.add(hold);
        }

        return holds;
    }

    static class Hold {
        Point initial;
        long vx;
        long vy;
    }

    static class Point {
        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        double x;
        double y;
    }
}
