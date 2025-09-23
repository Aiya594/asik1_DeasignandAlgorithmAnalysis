package org.example;

import java.util.Arrays;
import java.util.Random;

public class Benchmark {

    public static void benchmark() {
        int n = 10000;
        int runs = 5;
        int k = n / 2;

        Random rand = new Random(42);
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(1_000_000);
        }

        CSVWriter csv = new CSVWriter("benchmark.csv");
        Metrics metrics = new Metrics();


        //deterministicSelect
        long totalSelect = 0;
        for (int i = 0; i < runs; i++) {
            int[] copy = arr.clone();
            metrics.reset();
            long start = System.nanoTime();
            DetermenisticSelect.select(copy, 0, copy.length - 1, k, metrics);
            long end = System.nanoTime();
            totalSelect += (end - start);
        }
        csv.writer("DetermenisticSelect", n, k, totalSelect / runs, metrics);
        metrics.reset();

        //quickSort
        long totalQuick = 0;
        for (int i = 0; i < runs; i++) {
            int[] copy = arr.clone();
            metrics.reset();
            long start = System.nanoTime();
            QuickSort.sortingQuick(copy, metrics);
            int kth = copy[k];
            long end = System.nanoTime();
            totalQuick += (end - start);
        }
        csv.writer("QuickSort", n, k, totalQuick / runs, metrics);
        metrics.reset();

        //mergeSort
        long totalMerge = 0;
        for (int i = 0; i < runs; i++) {
            int[] copy = arr.clone();
            metrics.reset();
            long start = System.nanoTime();
            MergeSort.sortMergeWithBuffer(copy, metrics);
            int kth = copy[k];
            long end = System.nanoTime();
            totalMerge += (end - start);
        }
        csv.writer("MergeSort", n, k, totalMerge / runs, metrics);
        metrics.reset();

        //built-in arrays.sort
        long totalJavaSort = 0;
        for (int i = 0; i < runs; i++) {
            int[] copy = arr.clone();
            long start = System.nanoTime();
            Arrays.sort(copy);
            int kth = copy[k];
            long end = System.nanoTime();
            totalJavaSort += (end - start);
        }
        csv.writer("JavaArraysSort", n, k, totalJavaSort / runs, metrics);
        metrics.reset();

        System.out.println("Results are in benchmark.csv");




    }

}
