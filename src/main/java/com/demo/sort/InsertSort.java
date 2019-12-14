package com.demo.sort;

public class InsertSort {
    public static void main(String[] args) {
        int[] arr = new int[]{5, 3, 6, 1, 0, 0, 9};
        insertSort(arr);
        for (int i : arr) {
            System.out.print(i + ", ");
        }
    }

    public static void insertSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            int data = arr[i];
            int j = i-1;
            for (; j >= 0; j--) {
                if (arr[j] > data) {
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            arr[j + 1] = data;
        }
    }
}
