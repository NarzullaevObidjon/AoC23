package com.obid.jon.day4;

import com.obid.jon.Reader;

import java.util.Arrays;
import java.util.List;

public class First {
    public static void main(String[] args) {
        List<String> lines = Reader.readLines(4, 1);
        int res = 0;
        for (String line : lines) {
            int sum = 0;
            String[] split = line.split(":")[1].split("\\|");
            List<Integer> win = Arrays.stream(split[0].trim().split("\\s+")).map(Integer::parseInt).toList();
            List<Integer> nums = Arrays.stream(split[1].trim().split("\\s+")).map(Integer::parseInt).toList();

            for (Integer num : nums) {
                if (win.contains(num)) {
                    sum = sum == 0 ? 1 : sum * 2;
                }
            }
            res += sum;
        }
        System.out.println(res);
    }
}
