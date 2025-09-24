package org.example;
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
            } else if (arg.equals("bench")){
                Benchmark.benchmark();
                break;
            }
        }

        Run.main(args);

    }
    private static void help(){
        System.out.println("""
                Usage: 
                java -cp target/asik1_designandalgorithms-1.0-SNAPSHOT.jar org.example.Main bench \n
                
                java -cp target/asik1_designandalgorithms-1.0-SNAPSHOT.jar org.example.Main <algorithm> [options] \n            
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