package com.obid.jon.day3;

import com.obid.jon.Reader;
import com.obid.jon.Utils;

import java.util.List;

public class First {
    public static void main(String[] args) {
        List<String> lines = Reader.readLines(3, 1);
        char[][] matrix = toMatrix(lines);
        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (i == 113) {
                System.out.println("-");
            }
            char[] row = matrix[i];
            int start = -1;
            for (int j = 0; j < row.length; j++) {
                if (Utils.isNumber(row[j])) {
                    if (start == -1) {
                        start = j;
                    }
                    if (j == row.length - 1) {
                        int num = Utils.toInt(row, start, row.length);
                        if (isSymbol(row[start - 1])) {
                            sum += num;
                            System.out.print(num + ",");
                        } else if (i != 0 && isThereAnySymbol(matrix[i - 1], start - 1, j) || i != matrix.length - 1 && isThereAnySymbol(matrix[i + 1], start - 1, j)) {
                            sum += num;
                            System.out.print(num + ",");
                        }
                    }
                } else {
                    if (row[j] == '.') {
                        if (start != -1) {
                            int num = Utils.toInt(row, start, j);
                            if (start != 0 && isSymbol(row[start - 1])) {
                                sum += num;
                                System.out.print(num + ",");
                            } else if (i != 0 && isThereAnySymbol(matrix[i - 1], start - 1, j) || i != matrix.length - 1 && isThereAnySymbol(matrix[i + 1], start - 1, j)) {
                                sum += num;
                                System.out.print(num + ",");
                            }
                            start = -1;
                        }
                    } else {
                        if (start != -1) {
                            int num = Utils.toInt(row, start, j);
                            sum += num;
                            System.out.print(num + ",");
                            start = -1;
                        }
                    }
                }
            }
            System.out.println();
        }
        System.out.println(sum);
    }

    private static boolean isThereAnySymbol(char[] row, int start, int end) {
        if (start == -1) start = 0;
        if (end == row.length) end = row.length - 1;
        for (int i = start; i <= end; i++) {
            if (isSymbol(row[i])) {
                return true;
            }
        }
        return false;
    }

    private static boolean isSymbol(char c) {
        return !Utils.isNumber(c) && c != '.';
    }

    private static char[][] toMatrix(List<String> lines) {
        char[][] matrix = new char[lines.size()][lines.get(0).length()];
        for (int i = 0; i < lines.size(); i++) {
            matrix[i] = lines.get(i).toCharArray();
        }
        return matrix;
    }
}
