package main.java.com.algth.w01;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	 private double[] treshold;
	    private int t;
	    
	    // perform T independent computational experiments on an N-by-N grid
	    public PercolationStats(int N, int T)    
	    {
	         if (N < 1 || T < 1)
	         {
	             throw new IllegalArgumentException();
	         }
	         t = T;
	         treshold = new double[t];
	         
	         for (int i = 0; i < treshold.length; i++)
	         {
	             treshold[i] = calcTreshold(N);
	         }
	    } 
	    
	    private double calcTreshold(int n)
	    {
	        double counter = 0;
	        int i, j;
	        Percolation perc = new Percolation(n);
	        while (!perc.percolates())
	        {
	            i = StdRandom.uniform(n)+1;
	            j = StdRandom.uniform(n)+1;
	            if (!perc.isOpen(i, j))
	            {
	                counter++;
	                perc.open(i, j);
	            }
	        }
	        return counter / (n*n);
	    }
	    
	    // sample mean of percolation threshold
	    public double mean()                     
	    {
	        /*
	        double sum = 0;
	        for (int i = 0; i < treshold.length; i++)
	        {
	            sum += treshold[i];
	        }
	        return sum / t;
	        */
	        return StdStats.mean(treshold);
	    }
	    
	    // sample standard deviation of percolation threshold
	    public double stddev()                   
	    {
	        /*
	        double m = mean();
	        double sum = 0;
	        for (int i = 0; i < treshold.length; i++)
	        {
	            sum += (treshold[i] - m)*(treshold[i] - m);
	        }
	        if (t == 1)
	            return Double.NaN;
	        return sum / (t - 1);
	        */
	        return StdStats.stddev(treshold);
	    }
	    
	    // returns lower bound of the 95% confidence interval
	    public double confidenceLo()             
	    {
	        return mean() - (1.96*stddev())/(Math.sqrt(t)); 
	    }
	    
	    // returns upper bound of the 95% confidence interval
	    public double confidenceHi()             
	    {
	        return mean() + (1.96*stddev())/(Math.sqrt(t)); 
	    }
	    

	    public static void main(String[] args) {
	        int n = Integer.parseInt(args[0]);
	        int t = Integer.parseInt(args[1]);
	        PercolationStats stats = new PercolationStats(n, t);
	        StdOut.println("mean                    = " + stats.mean());
	        StdOut.println("stddev                  = " + stats.stddev());
	        StdOut.println("95% confidence interval = " + stats.confidenceLo()
	                + ", " + stats.confidenceHi());
	    }
}