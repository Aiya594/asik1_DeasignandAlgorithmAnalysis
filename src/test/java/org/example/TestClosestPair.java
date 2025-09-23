package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestClosestPair {

    @Test
    void testSmallSet() {
        CSVWriter csv = new CSVWriter("closestPairMetrics.csv");

        ClosestPair.Point[] points = {
                new ClosestPair.Point(0, 0),
                new ClosestPair.Point(3, 4),
                new ClosestPair.Point(1, 1)
        };

        Metrics metrics = new Metrics();
        long start = System.nanoTime();
        double res = ClosestPair.closestPair(points, metrics);
        long end = System.nanoTime();

        assertEquals(Math.sqrt(2), res, 1e-9);

        csv.writer("ClosestPairTestSmall", points.length, 0, (end - start), metrics);

    }

    @Test
    void testTwoPoints() {
        CSVWriter csv = new CSVWriter("closestPairMetrics.csv");

        ClosestPair.Point[] points = {
                new ClosestPair.Point(1, 2),
                new ClosestPair.Point(4, 6)
        };

        Metrics metrics = new Metrics();
        long start = System.nanoTime();
        double res = ClosestPair.closestPair(points, metrics);
        long end = System.nanoTime();

        assertEquals(5.0, res, 1e-9);

        csv.writer("ClosestPairTestTwoPoints", points.length, 0, (end - start), metrics);
    }

    @Test
    void testLargerSet() {
        CSVWriter csv = new CSVWriter("closestPairMetrics.csv");

        ClosestPair.Point[] points = {
                new ClosestPair.Point(2, 3),
                new ClosestPair.Point(12, 30),
                new ClosestPair.Point(40, 50),
                new ClosestPair.Point(5, 1),
                new ClosestPair.Point(12, 10),
                new ClosestPair.Point(3, 4)
        };

        Metrics metrics = new Metrics();
        long start = System.nanoTime();
        double res = ClosestPair.closestPair(points, metrics);
        long end = System.nanoTime();

        assertEquals(Math.sqrt(2), res, 1e-9);

        csv.writer("ClosestPairTestLargerSet", points.length, 0, (end - start), metrics);
    }

}
