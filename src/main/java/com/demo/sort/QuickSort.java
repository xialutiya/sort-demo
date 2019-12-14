package com.demo.sort;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {9, 3, 1, 7, 5, 3};
//        int[] arr = {4, 7, 6, 5, 8, 3, 2, 1};
//        quickSort(arr, 0, arr.length - 1);
        quickSortSingle(arr, 0, arr.length - 1);
        for (int num : arr) {
            System.out.print(num + ", ");
        }
    }

    public static void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivot = arr[left];
        int pointOne = left;
        int pointTwo = right;
        while (pointOne < pointTwo) {
            while (pointOne < pointTwo && arr[pointTwo] >= pivot) {
                pointTwo--;
            }
            while (pointOne < pointTwo && arr[pointOne] <= pivot) {
                pointOne++;
            }
            if (pointOne < pointTwo) {
                arr[pointOne] = arr[pointOne] + arr[pointTwo];
                arr[pointTwo] = arr[pointOne] - arr[pointTwo];
                arr[pointOne] = arr[pointOne] - arr[pointTwo];
            }
        }
        arr[left] = arr[pointOne];
        arr[pointOne] = pivot;
        quickSort(arr, left, pointOne - 1);
        quickSort(arr, pointOne + 1, right);
    }

    public static void quickSortSingle(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivot = arr[left];
        int mark = left;
        for (int i = left + 1; i <= right; i++) {
            if (arr[i] < pivot) {
                mark++;
                if (mark < i) {
                    arr[i] = arr[mark] + arr[i];
                    arr[mark] = arr[i] - arr[mark];
                    arr[i] = arr[i] - arr[mark];
                }
            }
        }
        arr[left] = arr[mark];
        arr[mark] = pivot;
        quickSortSingle(arr, left, mark - 1);
        quickSortSingle(arr, mark + 1, right);
    }

}
