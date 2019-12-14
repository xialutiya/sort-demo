package com.demo.sort;

public class MergeSort {

    private static int[] temp;

    public static void main(String[] args) {
        int[] arr = {9, 5, 3, 1, 9, 6, 0, 1, 9};
        mergeSort(arr, 0, arr.length - 1);
        for (int num : arr) {
            System.out.print(num + ", ");
        }
    }

    public static void mergeSort(int[] arr, int left, int right) {
        if (arr == null || arr.length == 0) {
            return;
        }
        temp = new int[arr.length];
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int pointOne = left;
        int pointTwo = mid + 1;
        int loc = left;
        while (pointOne <= mid && pointTwo <= right) {
            if (arr[pointOne] < arr[pointTwo]) {
                temp[loc] = arr[pointOne];
                pointOne++;
                loc++;
            } else {
                temp[loc] = arr[pointTwo];
                pointTwo++;
                loc++;
            }
        }
        while (pointOne <= mid) {
            temp[loc++] = arr[pointOne++];
        }
        while (pointTwo <= right) {
            temp[loc++] = arr[pointTwo++];
        }
        for (int i = left; i <= right; i++) {
           arr[i] = temp[i];
        }
    }
}
