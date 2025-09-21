package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CSVWriter {

    private final String file;
    private boolean headerWritten = false;

    public CSVWriter(String file) {
        this.file=file;
    }

    public void writer(String algorithm, int size,
                       int n, long time, Metrics metrics ){

        //FileWriter is a class that opens file to write there info
        //true is for writing new info in the end of the file(like append() do in arrays)
        //PrintWriter gives methods to print info into file

        try (PrintWriter pw = new PrintWriter(new FileWriter(file, true))){
            if (!headerWritten){
                pw.println("algorithm,size,n,time,depth,comparisons");
                headerWritten = true;
            }
            pw.printf("%s,%d,%d,%d,%d,%d\n",algorithm, size,n,time,
                    metrics.getRecursionDepth(),metrics.getCompare());
        }catch (IOException e) {
            //error handlinf for any problem connected with file, prints error message
            e.printStackTrace();
        }
    }
}
