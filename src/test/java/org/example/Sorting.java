package org.example;

import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Sorting {
    @Test
    void adversarialArraysTest(){
        CSVWriter csv=new CSVWriter("sortingMetrics.csv");
        int count = 0;
        Metrics metrics = new Metrics();

        //increasing array
        int[] increasing={5,6,7,8,9,10,11};
        int[] copyIncMerge=Arrays.copyOf(increasing, increasing.length);
        int[] copyIncQuick=Arrays.copyOf(increasing, increasing.length);

        long startIncMerge = System.nanoTime();
        MergeSort.sortMergeWithBuffer(copyIncMerge,metrics);
        long endIncMerge = System.nanoTime();

        csv.writer("MergeSort", count,increasing.length,(endIncMerge-startIncMerge),metrics);
        metrics.reset();

        long startIncQuick = System.nanoTime();
        QuickSort.sortingQuick(copyIncQuick,metrics);
        long endIncQuick = System.nanoTime();
        count++;

        csv.writer("QuickSort", count,increasing.length,(endIncQuick-startIncQuick),metrics);
        metrics.reset();

        //decreasing
        int[] decreasing={9,8,7,6,5,4,3,2,1};
        int[] copyDecMerge =Arrays.copyOf(decreasing, decreasing.length);
        int[] copyDecQuick=Arrays.copyOf(decreasing, decreasing.length);

        long startDecMerge = System.nanoTime();
        MergeSort.sortMergeWithBuffer(copyDecMerge,metrics);
        long endDecMerge = System.nanoTime();

        csv.writer("MergeSort", count,decreasing.length,(endDecMerge - startDecMerge),metrics);
        metrics.reset();

        long startDecQuick = System.nanoTime();
        QuickSort.sortingQuick(copyDecQuick,metrics);
        long endDecQuick = System.nanoTime();
        count++;

        csv.writer("QuickSort",count,decreasing.length,(endDecQuick-startDecQuick),metrics);
        metrics.reset();
    }

    @Test
    void randomArraysTest(){
        Random rand =new Random();

        CSVWriter csv=new CSVWriter("sortingMetrics.csv");
        Metrics metrics = new Metrics();

        for (int i =0; i<100; i++){
            int size=rand.nextInt(1000)+1; //the length of each tested array

            int[] arr=new int[size];

            for(int j=0;j<size;j++){
                arr[j]=rand.nextInt(size);
            }

            int[] arrMerge=Arrays.copyOf(arr, arr.length);
            int[] arrQuick=Arrays.copyOf(arr, arr.length);


            int[] expected = Arrays.copyOf(arr, size);
            Arrays.sort(expected);

            long startMerge = System.nanoTime();
            MergeSort.sortMergeWithBuffer(arrMerge,metrics);
            long endMerge = System.nanoTime();

            assertArrayEquals(expected, arrMerge, "MergeSort sorted incorrectly");

            csv.writer("MergeSort",i,size,(endMerge-startMerge),metrics);
            metrics.reset();


            long startQuick = System.nanoTime();
            QuickSort.sortingQuick(arrQuick,metrics);
            long endQuick = System.nanoTime();

            assertArrayEquals(expected, arrQuick, "QuickSort sorted incorrectly");

            csv.writer("QuickSort",i,size,(endQuick-startQuick),metrics);
            metrics.reset();


        }
    }



}



