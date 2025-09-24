package org.example;

import java.util.Arrays;
import java.util.Random;

public class Benchmark {

    public static void benchmark() {
        int[] sizes = {100, 1000, 5000, 10000};
        int runs = 5;

        CSVWriter csv = new CSVWriter("benchmark.csv");

        for (int n : sizes) {
            int k = n / 2;
            Random rand = new Random(42);
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = rand.nextInt(1_000_000);
            }

            Metrics metrics = new Metrics();

            // DeterministicSelect
            long totalSelect = 0;
            for (int i = 0; i < runs; i++) {
                int[] copy = arr.clone();
                metrics.reset();
                long start = System.nanoTime();
                DetermenisticSelect.select(copy, 0, copy.length - 1, k, metrics);
                long end = System.nanoTime();
                totalSelect += (end - start);
            }
            csv.writer("DeterministicSelect", n, k, totalSelect / runs, metrics);
            metrics.reset();

            // QuickSort
            metrics = new Metrics();
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

            // MergeSort
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

            // Arrays.sort (Java built-in)
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

            // ClosestPair
            ClosestPair.Point[] points = new ClosestPair.Point[n];
            for (int i = 0; i < n; i++) {
                points[i] = new ClosestPair.Point(rand.nextDouble(), rand.nextDouble());
            }
            long totalClosest = 0;
            for (int i = 0; i < runs; i++) {
                metrics.reset();
                long start = System.nanoTime();
                ClosestPair.closestPair(points, metrics);
                long end = System.nanoTime();
                totalClosest += (end - start);
            }
            csv.writer("ClosestPair", n, k, totalClosest / runs, metrics);
            metrics.reset();
        }

        System.out.println("Benchmark results saved in benchmark.csv");
    }

    }


