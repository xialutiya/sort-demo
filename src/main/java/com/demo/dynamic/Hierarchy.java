package com.demo.dynamic;

public class Hierarchy {

    private static int[] arr;

    public static void main(String[] args) {
        int n = 5;
        arr = new int[n + 1];
        int hierarchy = hierarchy(n);
        System.out.println(hierarchy);
    }

    public static int hierarchy(int n) {
        if (n <= 1) {
            return 1;
        }
        if (arr[n] > 0) {
            return arr[n];
        }
        return arr[n] = n * hierarchy(n - 1);
    }
}
