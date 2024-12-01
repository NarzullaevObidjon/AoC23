package com.obid.jon.day3;

import com.obid.jon.Reader;
import com.obid.jon.Utils;

import java.util.ArrayList;
import java.util.List;

public class Second {
    public static void main(String[] args) {
        List<String> lines = Reader.readLines(3, 2);
        char[][] matrix = toMatrix(lines);
        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            char[] row = matrix[i];
            for (int j = 0; j < row.length; j++) {
                if (row[j] == '*') {
                    List<Integer> list = new ArrayList<>();
                    if (j != 0 && Utils.isNumber(row[j - 1])) {
                        list.add(getPrevNumber(row, j - 1));
                    }
                    if (j != row.length - 1 && Utils.isNumber(row[j + 1])) {
                        list.add(getNextNumber(row, j + 1));
                    }
                    if (i != 0) {
                        if (Utils.isNumber(matrix[i - 1][j])) {
                            list.add(getNumberWithin(matrix[i - 1], j));
                        } else {
                            if (j != 0 && Utils.isNumber(matrix[i - 1][j - 1])) {
                                list.add(getPrevNumber(matrix[i - 1], j - 1));
                            }
                            if (j != row.length - 1 && Utils.isNumber(matrix[i - 1][j + 1])) {
                                list.add(getNextNumber(matrix[i - 1], j + 1));
                            }
                        }
                    }
                    if (i != matrix.length - 1) {
                        if (Utils.isNumber(matrix[i + 1][j])) {
                            list.add(getNumberWithin(matrix[i + 1], j));
                        } else {
                            if (j != 0 && Utils.isNumber(matrix[i + 1][j - 1])) {
                                list.add(getPrevNumber(matrix[i + 1], j - 1));
                            }
                            if (j != row.length - 1 && Utils.isNumber(matrix[i + 1][j + 1])) {
                                list.add(getNextNumber(matrix[i + 1], j + 1));
                            }
                        }
                    }
                    if (list.size() == 2) {
                        System.out.println(list.get(0) + " " + list.get(1));
                        sum += list.get(0) * list.get(1);
                    }
                }
            }
        }
        System.out.println(sum);
    }

    private static Integer getNumberWithin(char[] matrix, int i) {
        int start = i;
        int end = i + 1;
        while (start >= 0) {
            if (!Utils.isNumber(matrix[start])) {
                start++;
                break;
            }
            start--;
        }
        while (end < matrix.length) {
            if (!Utils.isNumber(matrix[end])) {
                break;
            }
            end++;
        }
        return Utils.toInt(matrix, start, end);
    }

    private static Integer getPrevNumber(char[] row, int i) {
        int end = -1;
        for (int j = i; j >= 0; j--) {
            if (Utils.isNumber(row[j])) {
                if (end == -1) {
                    end = j;
                }
            } else {
                return Utils.toInt(row, j + 1, end + 1);
            }
        }
        return Utils.toInt(row, 0, end + 1);
    }

    private static Integer getNextNumber(char[] row, int i) {
        int start = -1;
        for (int j = i; j < row.length; j++) {
            if (Utils.isNumber(row[j])) {
                if (start == -1) {
                    start = j;
                }
            } else {
                return Utils.toInt(row, start, j);
            }
        }
        return Utils.toInt(row, start, row.length);
    }

    private static char[][] toMatrix(List<String> lines) {
        char[][] matrix = new char[lines.size()][lines.get(0).length()];
        for (int i = 0; i < lines.size(); i++) {
            matrix[i] = lines.get(i).toCharArray();
        }
        return matrix;
    }
}
