package org.example;

import java.util.Random;

public class Utils {
    private Utils() {};

    public static void swap(int[] arr, int a, int b) {
        if (arr==null){
            return;
        }

        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }


    public static int partition(int[] arr, int low, int high, Random rand, Metrics metrics){
        if (arr==null){
            return -1;
        }

        if (low < 0 || high >= arr.length || low > high){
            return -1;
        }

        int pivotIdx=low+rand.nextInt(high-low+1);
        swap(arr, pivotIdx, high);

        int pivot =arr[high];

        int i=low-1;

        for(int j=low; j<high; j++){

            metrics.incrementCompare();

            if (arr[j]<=pivot){
                i++;
                swap(arr, i, j);

            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

}
