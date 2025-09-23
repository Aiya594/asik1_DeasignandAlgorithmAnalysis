package org.example;

import static org.example.Utils.insertionSort;
import static org.example.Utils.swap;

public class DetermenisticSelect {

    public static int select(int[] arr, int left, int right, int k, Metrics metrics) {
        while (true) {
            if (left == right) {
                return arr[left];
            }

            metrics.enterRecursion();

            int pivotIndex = medianOfMedians(arr, left, right, metrics);

            int pivotFinal = partitionWithPivot(arr, left, right, pivotIndex, metrics);

            int len = pivotFinal - left;

            if (k == len) {
                metrics.exitRecursion();
                return arr[pivotFinal];
            } else if (k < len) {
                right = pivotFinal - 1;
            } else {
                k = k - len - 1;
                left = pivotFinal + 1;
            }

            metrics.exitRecursion();
        }
    }

    private static int partitionWithPivot(int[] arr, int left, int right, int pivotIndex, Metrics metrics) {
        int pivot = arr[pivotIndex];
        swap(arr, pivotIndex, right);

        int i = left;
        for (int j = left; j < right; j++) {
            metrics.incrementCompare();
            if (arr[j] < pivot) {
                swap(arr, i, j);
                i++;
            }
        }

        swap(arr, i, right);
        return i;
    }

    private static int medianOfMedians(int[] arr, int left, int right, Metrics metrics) {
        int n = right - left + 1;

        if (n < 5) {
            insertionSort(arr, left, right, metrics);
            return left + n / 2;
        }

        int numOfMedians = 0;

        for (int i = left; i <= right; i += 5) {
            int subRight = Math.min(i + 4, right);

            insertionSort(arr, i, subRight, metrics);

            int median = i + (subRight - i) / 2;

            swap(arr, left + numOfMedians, median);
            numOfMedians++;
        }

        return medianOfMedians(arr, left, left + numOfMedians - 1, metrics);
    }


}
