package org.example;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Select {

    @Test
    void randomArraysTest() {
        CSVWriter csv=new CSVWriter("selectMetrics.csv");
        Metrics metrics = new Metrics();
        Random rand = new Random();


        for (int t = 0; t < 50; t++) {
            int size = rand.nextInt(500) + 1;
            int[] arr = new int[size];

            for (int i = 0; i < size; i++) {
                arr[i] = rand.nextInt(5000);
            }

            int[] sorted = arr.clone();
            Arrays.sort(sorted);

            int k = rand.nextInt(size);

            long start = System.nanoTime();
            int selected = DetermenisticSelect.select(arr.clone(), 0, arr.length - 1, k, metrics);
            long end = System.nanoTime();

            assertEquals(sorted[k], selected,
                    "Failed on iteration " + t + " with k=" + k);


            csv.writer("DeterministicSelect", t, size, (end - start), metrics);

            metrics.reset();

        }
    }
}
