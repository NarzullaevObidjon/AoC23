package com.obid.jon.day1;

import com.obid.jon.Reader;

import java.util.List;

public class First {
    public static void main(String[] args) {
        List<String> lines = Reader.readLines(1, 1);

        int first = 0;
        int second = 0;

        long sum = 0;
        for (String line : lines) {
            char[] chars = line.toCharArray();
            for (char aChar : chars) {
                if (aChar >= '0' && aChar <= '9') {
                    first = aChar - '0';
                    break;
                }
            }
            for (int i = chars.length - 1; i >= 0; i--) {
                if (chars[i] >= '0' && chars[i] <= '9') {
                    second = chars[i] - '0';
                    break;
                }
            }
            sum += first * 10L + second;
        }
        System.out.println(sum);
    }
}
