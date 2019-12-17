package com.demo.dynamic;

import static java.lang.Math.max;

//https://blog.csdn.net/u013241673/article/details/79981934
public class ChildrenStr {

    private static int[][] arr;

    public static void main(String[] args) {
        int[] X = {1, 2, 3, 5, 3, 7, 5, 3};
        int[] Y = {2, 4, 3, 3, 5, 7, 5};
//        int lenDown = childrenStrDown(X, 0, X.length, Y, 0, Y.length);
//        System.out.println("lenDown: " + lenDown);
//        int lenUp = childrenStrUp(X, X.length - 1, Y, Y.length - 1);
//        System.out.println("lenUp: " + lenUp);

        arr = new int[X.length + 1][Y.length + 1];
////        int num = childrenStr(X, X.length, Y, Y.length);
////        System.out.println(num);
////        for (int i = 0; i < X.length; i++) {
////            for (int j = 0; j < Y.length; j++) {
////                System.out.print(arr[i][j] + ", ");
////            }
////            System.out.println();
////        }
        int numDown = childrenStrPlanDown(X, X.length, Y, Y.length);
        System.out.println("numDown: " + numDown);
        for (int i = 1; i <= X.length; i++) {
            for (int j = 1; j <= Y.length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }

        getStr(X, Y);
    }

    // 最长公共子序列.
    public static int childrenStrDown(int[] X, int i, int m, int[] Y, int j, int n) {
        if (i == m || j == n) {
            return 0;
        } else if (X[i] == Y[j]) {
            return 1 + childrenStrDown(X, i + 1, m, Y, j + 1, n);
        } else {
            return max(childrenStrDown(X, i + 1, m, Y, j, n), childrenStrDown(X, i, m, Y, j + 1, n));
        }
    }

    // 最长公共子序列.
    public static int childrenStrUp(int[] X, int i, int[] Y, int j) {
        if (i < 0 || j < 0) {
            return 0;
        } else if (X[i] == Y[j]) {
            return 1 + childrenStrUp(X, i - 1, Y, j - 1);
        } else {
            return max(childrenStrUp(X, i - 1, Y, j), childrenStrUp(X, i, Y, j - 1));
        }
    }

    private static int childrenStrPlanUp(int[] X, int m, int[] Y, int n) {

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

    public static int childrenStrPlanDown(int[] X, int m, int[] Y, int n) {
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                arr[i][j] = arr[i - 1][j - 1];
                if (X[i - 1] == Y[j - 1]) {
                    arr[i][j]++;
                }
                if (arr[i][j - 1] > arr[i][j]) {
                    arr[i][j] = arr[i][j - 1];
                }
                if (arr[i - 1][j] > arr[i][j]) {
                    arr[i][j] = arr[i - 1][j];
                }
            }
        }
        return arr[m][n];
    }

    public static void getStr(int[] X, int[] Y) {
        StringBuilder sb = new StringBuilder();
        int i = X.length, j = Y.length;
        int n = 5;
        while (n > 0) {
            if (X[i - 1] == Y[j - 1]) {
                sb.append(X[i - 1]);
                i--;
                j--;
                n--;
            } else if (arr[i - 1][j] > arr[i][j - 1]) {
                i--;
            } else if (arr[i][j - 1] >= arr[i - 1][j]) {
                j--;
            }
        }
        System.out.println(sb.reverse().toString());
    }
}
