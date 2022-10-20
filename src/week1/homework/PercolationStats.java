package week1.homework;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {

    private static final double CONST = 1.96;
    private final double[] openSites;
    private double stddev, mean;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException("Inconsistent arguments.");
        openSites = new double[trials];
        for (int trial = 0; trial < trials; trial++) {
            Percolation percolation = new Percolation(n);
            int count = 0;
            while (!percolation.percolates()) {
                int number1 = StdRandom.uniform(n) + 1;
                int number2 = StdRandom.uniform(n) + 1;
                if (!percolation.isOpen(number1, number2)) {
                    percolation.open(number1, number2);
                    count++;
                }
            }
            openSites[trial] = (double) count / (double) (n * n);
        }
        creaateMean();
        creaateStdev();
    }

    private void creaateMean(){
        mean = StdStats.mean(openSites);
    }

    private void creaateStdev(){
        stddev = StdStats.stddev(openSites);
    }


    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (CONST * Math.sqrt(stddev) / Math.sqrt(openSites.length));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (CONST * Math.sqrt(stddev) / Math.sqrt(openSites.length));
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        Stopwatch clock = new Stopwatch();
        PercolationStats ps = new PercolationStats(n, t);
        double time = clock.elapsedTime();

        System.out.println("mean:\t\t\t\t" + ps.mean());
        System.out.println("stddev:\t\t\t\t" + ps.stddev());
        System.out.println("95% confidence interval:\t\t" + ps.confidenceLo() + ", " + ps.confidenceHi());
        System.out.println("elapsed time:\t\t\t" + time + "s");
    }

}
