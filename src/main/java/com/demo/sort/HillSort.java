package com.demo.sort;

public class HillSort {
    public static void main(String[] args) {
        int[] arr = new int[]{5, 2, 1, 6, 8, 7, 5, 0};
        hillSort(arr);
        for (int num : arr) {
            System.out.print(num + ", ");
        }
    }

    public static void hillSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int len = arr.length;
        for (int k = len/2; k > 0; k /= 2) {
            for (int i = k; i < len; i++) {
                int data = arr[i];
                int j = i - k;
                for (; j >= 0; j -= k) {
                    if (arr[j] > data) {
                        arr[j + k] = arr[j];
                    } else {
                        break;
                    }
                }
                arr[j + k] = data;
            }
        }
    }
}
