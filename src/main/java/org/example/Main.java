package org.example;

import java.util.Arrays;
import java.util.Random;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        if (args.length <1) {
            help();
            return;
        }
        for (String arg : args) {
            if (arg.equals("--help") || arg.equals("help")) {
                help();
                break;
            }
        }

        String alg= args[0];

        int size = 100;
        int k = 0;
        String file = "userMetrics.csv";

        for (int i = 1; i < args.length; i++) {
            switch (args[i]) {
                case "--n" ->{
                    size = Integer.parseInt(args[++i]);
                }
                case "--k" ->{
                    k = Integer.parseInt(args[++i]);
                }
                default ->{
                    System.out.println("Unknown flag: "+args[i]+" try --help");
                }
            }
        }

        CSVWriter csv=new CSVWriter(file);
        Metrics metrics=new Metrics();
        Random rand=new Random();
        int count=0;

        int[] arr=new int[size];
        for (int i = 0; i < size; i++) {
            arr[i]=rand.nextInt(size);
        }



        long start,end;

        switch (alg) {
            case "merge" -> {
                int[] copy = Arrays.copyOf(arr, arr.length);
                start = System.nanoTime();
                MergeSort.sortMergeWithBuffer(copy, metrics);
                count++;
                end = System.nanoTime();

                System.out.printf("""
                                Original arr: %s 
                                Merge sorted arr: %s""",
                        Arrays.toString(arr), Arrays.toString(copy)
                );

                csv.writer("MergeSort", count, size, (end - start), metrics);
                metrics.reset();
            }
            case "quick" -> {
                int[] copy = Arrays.copyOf(arr, arr.length);
                start = System.nanoTime();
                QuickSort.sortingQuick(copy, metrics);
                count++;
                end = System.nanoTime();

                System.out.printf("""
                                Original arr: %s 
                                Quick sorted arr: %s""",
                        Arrays.toString(arr), Arrays.toString(copy)
                );

                csv.writer("QuickSOrt", count, size, (end - start), metrics);
                metrics.reset();
            }
            case "select" -> {
                if (k>size || k<0) {
                    System.out.println("k must be 0<k<size");
                    break;
                }
                start = System.nanoTime();
                int res = DetermenisticSelect.select(arr, 0, arr.length - 1, k, metrics);
                count++;
                end = System.nanoTime();

                System.out.println("Result: " + res);

                csv.writer("DetermenisticSelect", count, size, (end - start), metrics);
                metrics.reset();

            }
            case "closest" -> {

                ClosestPair.Point[] points = new ClosestPair.Point[size];
                for (int i = 0; i < size; i++) {
                    points[i] = new ClosestPair.Point(rand.nextDouble() * size, rand.nextDouble() * size);
                }

                start = System.nanoTime();
                double res = ClosestPair.closestPair(points, metrics);
                count++;
                end = System.nanoTime();

                System.out.println("Closest distant: " + res);
                csv.writer("ClosestPair", count, size, (end - start), metrics);
                metrics.reset();

            }
            default -> System.out.println("Unknown algorithm:" + alg);
        }
    }

    private static void help(){
        System.out.println("""
                Usage: java -jar  .\\target\\asik1_designandalgorithms-1.0-SNAPSHOT..jar <algorithm> [options] \n            
                            <algorithm>:
                              merge       Run Merge Sort
                              quick       Run Quick Sort
                              select      Run Deterministic Select (Median of Medians)
                              closest     Run Closest Pair (divide-and-conquer) \n
                            [options]:
                              --n <size>      Size of the array (for sorting/select) or number of points (for closest pair)
                              --k <index>     k-th order statistic (for select only, 0-based index)
                              --help          Show this help message \n
                            Examples:
                              java -jar algo.jar merge --n 1000
                              java -jar algo.jar quick --n 500
                              java -jar algo.jar select --n 1000 --k 500
                              java -jar algo.jar closest --n 200
                """);
    }
}