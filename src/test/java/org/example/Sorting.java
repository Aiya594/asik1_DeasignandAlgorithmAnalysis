package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvWriter;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Sorting {

    @Test
    void adversarialArraysTest(){
        CSVWriter csv=new CSVWriter("metrics.csv");
        int count = 0;
        Metrics metrics = new Metrics();
        //increasing array

        int[] increasing={5,6,7,8,9,10,11};
        int[] copyInc=Arrays.copyOf(increasing, increasing.length);
        long startInc = System.nanoTime();
        MergeSort.sortMergeWithBuffer(copyInc,metrics);
        long endInc = System.nanoTime();
        count++;

        csv.writer("MergeSort", count,increasing.length,(endInc-startInc),metrics);
        metrics.reset();

        //decreasing
        int[] decreasing={9,8,7,6,5,4,3,2,1};
        int[] copyDec=Arrays.copyOf(decreasing, decreasing.length);
        long startDec = System.nanoTime();
        MergeSort.sortMergeWithBuffer(copyInc,metrics);
        long endDec = System.nanoTime();
        count++;

        csv.writer("MergeSort", count,decreasing.length,(endDec-startDec),metrics);
    }

    @Test
    void randomArraysTest(){
        Random rand =new Random();

        CSVWriter csv=new CSVWriter("metrics.csv");
        Metrics metrics = new Metrics();
        for (int i =0; i<100; i++){
            int size=rand.nextInt(1000)+1; //the length of each tested array

            int[] arr=new int[size];

            for(int j=0;j<size;j++){
                arr[j]=rand.nextInt(size);
            }

            int[] expected = Arrays.copyOf(arr, size);
            Arrays.sort(expected);

            long startTime = System.nanoTime();
            MergeSort.sortMergeWithBuffer(arr,metrics);
            long endTime = System.nanoTime();

            assertArrayEquals(expected, arr, "Array not sorted correctly");

            csv.writer("MergeSort",i,size,(endTime-startTime),metrics);
            metrics.reset();
        }
    }



}



