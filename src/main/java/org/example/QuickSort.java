package org.example;

import java.util.Random;

import static org.example.Utils.partition;

public class QuickSort {
    private static final Random rand = new Random();

    private static Metrics metrics;

    private static void quickSort(int[] arr, int low, int high){

        metrics.enterRecursion();

        while(low<high){
            int piv=partition(arr, low, high,rand, metrics);
            if (piv!=-1){
                int leftSize=(piv-1)-low;
                int rightSize=high-(piv+1);

                if (leftSize<rightSize){
                    quickSort(arr, low, piv-1);
                    low=piv+1;
                } else{
                    quickSort(arr, piv+1, high);
                    high=piv-1;
                }
            } else{
                metrics.exitRecursion();
                return;
            }

        }

        metrics.exitRecursion();
    }

    public static void sortingQuick(int[] arr, Metrics metr) {
        metrics=metr;
        quickSort(arr, 0, arr.length-1);
    }



}
