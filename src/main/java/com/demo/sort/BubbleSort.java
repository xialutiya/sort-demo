package com.demo.sort;

public class BubbleSort {
    public static void main(String[] args) {
//        int[] arr = new int[]{6, 3, 9, 5, 0, 1, 7};
//        int[] arr = {1, 2, 3, 4, 5, 6, 7};
//        int[] arr = {7, 6, 5, 4, 3, 2, 1};
        int[] arr = {3, 4, 2, 1, 5, 6, 7, 8};
        bubbleSort(arr);
        for (int num : arr) {
            System.out.print(num + ", ");
        }
    }

    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int lastExchangeIndex = 0;
        int sortBorder = arr.length - 1;
        for (int i = 0; i < arr.length - 1; i++) {
            boolean flag = true;
            for (int j = 0; j < sortBorder; j++) {
                if (arr[j] > arr[j + 1]) {
                    arr[j] = arr[j] ^ arr[j + 1];
                    arr[j + 1] = arr[j] ^ arr[j + 1];
                    arr[j] = arr[j] ^ arr[j + 1];
                    lastExchangeIndex = j;
                    flag = false;
                }
            }
            sortBorder = lastExchangeIndex;
            if (flag) {
                break;
            }
        }
    }
}
