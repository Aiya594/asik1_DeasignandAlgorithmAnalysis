package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ClosestPair {

    static class Point {
        final double x, y;
        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    private static double distance(Point a, Point b, Metrics metrics) {
        metrics.incrementCompare();

        double dx = a.x - b.x;
        double dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    private static double bruteForce(Point[] pts, int l, int r, Metrics metrics) {
        double minDist = Double.POSITIVE_INFINITY;

        for (int i = l; i <= r; i++) {
            for (int j = i + 1; j <= r; j++) {
                minDist = Math.min(minDist, distance(pts[i], pts[j],metrics));
            }
        }
        return minDist;
    }

    public static double closestPair(Point[] points,Metrics metrics) {
        Point[] pts = points.clone();
        Arrays.sort(pts, Comparator.comparingDouble(p -> p.x));
        return closestPairRec(pts, 0, pts.length - 1, metrics);
    }

    private static double closestPairRec(Point[] pts, int l, int r, Metrics metrics) {
        metrics.enterRecursion();

        if (r - l <= 3) {
            metrics.exitRecursion();
            return bruteForce(pts, l, r,metrics);
        }

        int mid = (l + r) / 2;
        double midX = pts[mid].x;

        double dLeft = closestPairRec(pts, l, mid,metrics);
        double dRight = closestPairRec(pts, mid + 1, r,metrics);
        double d = Math.min(dLeft, dRight);

        List<Point> strip = new ArrayList<>();
        for (int i = l; i <= r; i++) {
            if (Math.abs(pts[i].x - midX) < d) {
                strip.add(pts[i]);
            }
        }

        strip.sort(Comparator.comparingDouble(p -> p.y));

        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size() && (strip.get(j).y - strip.get(i).y) < d; j++) {
                d = Math.min(d, distance(strip.get(i), strip.get(j), metrics));
            }
        }

        metrics.exitRecursion();
        return d;
    }



}
