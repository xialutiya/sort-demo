package com.demo.dynamic;


import java.util.Stack;

import static java.lang.Math.max;

/**
 * 求字符串的最长子序列.
 */
public class ChildrenStr {

    private static int[][] arr;
    private static int[] X;
    private static int[] Y;
    private static int num;

    public static void main(String[] args) {

        X = new int[]{1, 2, 3, 2, 4, 1, 2};
        Y = new int[]{2, 4, 3};
        int len = childrenStr(0, X.length, 0, Y.length);
        System.out.println(len);

        arr = new int[X.length + 1][Y.length + 1];
        num = childrenStr(X.length, Y.length);
        System.out.println(num);
        for (int i = 0; i < X.length; i++) {
            for (int j = 0; j < Y.length; j++) {
                System.out.print(arr[i][j] + ", ");
            }
            System.out.println();
        }
//        StringBuilder sb = new StringBuilder();
        Stack<Integer> sb = new Stack<>();
        LHS(X.length - 1, Y.length - 1, sb);
    }

    /**
     * 最长公共子序列
     *
     * @param i 当前子问题.
     * @param m 字符串长度.
     * @param j 当前子问题.
     * @param n 字符串长度.
     * @return
     */
    public static int childrenStr(int i, int m, int j, int n) {
        if (i == m || j == n) {
            return 0;
        } else if (X[i] == Y[j]) {
            return 1 + childrenStr(i + 1, m, j + 1, n);
        } else {
            return max(childrenStr(i + 1, m, j, n), childrenStr(i, m, j + 1, n));
        }
    }

    /**
     * 动态规划求解最长子序列长度..
     *
     * @param m 字符串长度.
     * @param n 字符串长度.
     * @return 最长子序列长度.
     */
    private static int childrenStr(int m, int n) {

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

    /**
     * 获取所有最长子序列.
     *
     * @param m 字符串长度.
     * @param n 字符串长度.
     */
    public static void LHS(int m, int n, Stack<Integer> sb) {

        Stack<Integer> stringBuilder = sb;
        if (num == 0) {
            while (!sb.isEmpty()) {
                System.out.println(sb.peek());
            }
            sb.pop();
            return;
        }
        if (X[m] == Y[n]) {
            num--;
            stringBuilder.push(X[m]);
            LHS(m - 1, n - 1, stringBuilder);
        } else if (arr[m - 1][n] > arr[m][n - 1]) {
            LHS(m - 1, n, stringBuilder);
        } else if (arr[m][n - 1] > arr[m - 1][n]) {
            LHS(m, n - 1, stringBuilder);
        } else {
            LHS(m - 1, n, stringBuilder);
            LHS(m, n - 1, stringBuilder);
        }
    }

}
