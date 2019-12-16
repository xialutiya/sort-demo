package com.demo.dynamic;

public class Fibonacci {

    private static int[] arr;

    public static void main(String[] args) {
        int n = 5;
        arr = new int[2 * n];
        int up = fibonacciUp(n);
        System.out.println(up);
        int down = fibonacciDown(n);
        System.out.println(down);
        int sum = fibonacciSum(n);
        System.out.println(sum);
    }

    public static int fibonacciUp(int n) {
        if (n <= 2) {
            return 1;
        }
        if (arr[n] > 0) {
            return arr[n];
        }
        return arr[n] = fibonacciUp(n - 1) + fibonacciUp(n - 2);
    }

    public static int fibonacciDown(int n) {
        if (n <= 1) {
            return 1;
        }
        arr[0] = 1;
        arr[1] = 1;
        for (int i = 2; i < n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr[n - 1];
    }

    public static int fibonacciSum(int n) {
        int a = 1, b = 1, sum = 0;
        for (int i = 2; i < n; i++) {
            sum = a + b;
            a = b;
            b = sum;
        }
        return sum;
    }

}
