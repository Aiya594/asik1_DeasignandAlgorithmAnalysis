package org.example;

import java.util.Random;

public class QuickSort {
    private static final Random rand = new Random();

    private static Metrics metrics;

    public static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    private static int partition(int[] arr, int low, int high){

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

    private static void quickSort(int[] arr, int low, int high){

        metrics.enterRecursion();

        while(low<high){
            int piv=partition(arr, low, high);

            int leftSize=piv-1-low;
            int rightSize=high-(piv+1);

            if (leftSize<rightSize){
                quickSort(arr, low, piv-1);
                low=piv+1;
            } else{
                quickSort(arr, piv+1, high);
                high=piv-1;
            }
        }

        metrics.exitRecursion();
    }

    public static void sortingQuick(int[] arr, Metrics metr) {
        metrics=metr;
        quickSort(arr, 0, arr.length-1);
    }



}
