package com.demo.sort;

public class SelectSort {
    public static void main(String[] args) {
        int[] arr = new int[]{5, 7, 3, 1, 0, 0, 9, 8, 5};
        selectSort(arr);
        for (int num : arr) {
            System.out.print(num + ", ");
        }
    }

    public static void selectSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int data = i;
            int j = i + 1;
            for (; j < arr.length; j++) {
                if (arr[j] < arr[data]) {
                    data = j;
                }
            }
            if (data > i) {
                arr[data] = arr[data] + arr[i];
                arr[i] = arr[data] - arr[i];
                arr[data] = arr[data] - arr[i];
            }
        }
    }
}
