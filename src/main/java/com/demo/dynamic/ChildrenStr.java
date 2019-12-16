package com.demo.dynamic;

import static java.lang.Math.max;

public class ChildrenStr {

    private static int[][] arr;

    public static void main(String[] args) {
        int[] X = {1, 2, 3, 2, 4, 1, 2, 3, 2, 6, 7, 8, 1, 2, 5, 9, 0, 5};
        int[] Y = {2, 4, 3, 1, 2, 1, 3, 9, 5, 0};
        int len = childrenStr(X, 0, X.length, Y, 0, Y.length);
        System.out.println(len);

        arr = new int[X.length + 1][Y.length + 1];
        int num = childrenStr(X, X.length, Y, Y.length);
        System.out.println(num);
        for (int i = 0; i < X.length; i++) {
            for (int j = 0; j < Y.length; j++) {
                System.out.print(arr[i][j] + ", ");
            }
            System.out.println();
        }
    }

    // 最长公共子序列.
    public static int childrenStr(int[] X, int i, int m, int[] Y, int j, int n) {
        if (i == m || j == n) {
            return 0;
        } else if (X[i] == Y[j]) {
            System.out.println(Y[j]);
            return 1 + childrenStr(X, i + 1, m, Y, j + 1, n);
        } else {
            return max(childrenStr(X, i + 1, m, Y, j, n), childrenStr(X, i, m, Y, j + 1, n));
        }
    }

    private static int childrenStr(int[] X, int m, int[] Y, int n) {

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                arr[i][j] = arr[i + 1][j + 1];
                if (X[i] == Y[j]) {
                    arr[i][j]++;
                }
                if (arr[i][j + 1] > arr[i][j]) {
                    arr[i][j] = arr[i][j + 1];
                }
                if (arr[i + 1][j] > arr[i][j]) {
                    arr[i][j] = arr[i + 1][j];
                }
            }
        }
        return arr[0][0];
    }
}
