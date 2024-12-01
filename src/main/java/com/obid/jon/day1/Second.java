package com.obid.jon.day1;

import com.obid.jon.Reader;

import java.util.List;
import java.util.Map;

public class Second {
    private static final String[] DIGITS = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

    public static void main(String[] args) {
        List<String> lines = Reader.readLines(1, 2);

        int first = 0;
        int second = 0;

        int sum = 0;
        for (String line : lines) {
            m:
            for (int i = 0; i < line.length(); i++) {
                char aChar = line.charAt(i);
                if (aChar >= '0' && aChar <= '9') {
                    first = aChar - '0';
                    break;
                }
                for (int j = 0; j < DIGITS.length; j++) {
                    if (line.startsWith(DIGITS[j], i)) {
                        first = j + 1;
                        break m;
                    }
                }
            }
            n:
            for (int i = line.length() - 1; i >= 0; i--) {
                char c = line.charAt(i);
                if (c >= '0' && c <= '9') {
                    second = c - '0';
                    break;
                }
                for (int j = 0; j < DIGITS.length; j++) {
                    if (line.startsWith(DIGITS[j], i)) {
                        second = j + 1;
                        break n;
                    }
                }
            }
            sum += first * 10 + second;
        }
        System.out.println(sum);
    }
}
