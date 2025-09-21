package org.example;

import java.util.Arrays;

public class MergeSort {
    private static final int cutOff=15;

    private static Metrics metrics;


    private static void insertionSort(int[] arr,int l, int r) {

        for (int i = l + 1; i <= r; i++) {
            int element = arr[i];
            int j = i - 1;
            while (j >= l) {

                metrics.incrementCompare();

                if (arr[j] > element) {
                    arr[j + 1] = arr[j];
                    j--;

                } else {
                    break;
                }
            }
            arr[j + 1] = element;
        }

    }

    private static void mergeSort(int[] arr, int[] buf, int l, int r) {
        metrics.enterRecursion();

        if (r-l+1<=cutOff){
            insertionSort(arr, l, r);

            metrics.exitRecursion();
            return;
        }
        if (l<r){

            int mid=l+(r-l)/2;

            mergeSort(arr, buf, l, mid);
            mergeSort(arr, buf, mid+1, r);

            metrics.incrementCompare();

            if (arr[mid]<=arr[mid+1]){
                metrics.exitRecursion();
                return;
            }

            merge(arr, buf, l,mid,r);

        }
        metrics.exitRecursion();
    }

    private static void merge(int[] arr, int[] buf, int l, int mid, int r) {
        System.arraycopy(arr, l, buf, l, mid - l + 1);

        int i=l;
        int j=mid+1;
        int k=l;

        while(i<=mid && j<=r){

            metrics.incrementCompare();

            if (buf[i]<=arr[j]){
                arr[k++]=buf[i++];
            } else{
                arr[k++]=arr[j++];
            }
        }

        while(i<=mid){
            arr[k++]=buf[i++];
        }

    }


    public static void sortMergeWithBuffer(int[] arr, Metrics metr) {
        metrics =metr;
        int len=arr.length;

        int[] buf= Arrays.copyOf(arr,len);
        mergeSort(arr, buf, 0,len-1);
    }
}
