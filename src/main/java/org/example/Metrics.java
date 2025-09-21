package org.example;

public class Metrics {
    private long compare=0;
    private long recursionDepth =0;
    private long currDepth=0;

    public void incrementCompare(){
        compare++;
    }

    public long getCompare(){
        return compare;
    }

    public void enterRecursion(){
        currDepth++;
        recursionDepth =Math.max(recursionDepth,currDepth);
    }

    public void exitRecursion(){
        currDepth--;
    }

    public long getRecursionDepth(){
        return recursionDepth;
    }

    public void reset(){
        compare=0;
        recursionDepth=0;
        currDepth=0;
    }
}
