package br.com.carlosfrederico77.part1.week1;
/*----------------------------------------------------------------
 *  Author:        Carlos Machado
 *  Written:       3/1/2017
 *  Last updated:  3/1/2017
 *
 *  Compilation:   javac PercolationStats.java
 *  Execution:     java PercolationStats intNumber intNumber
 *  
 *  This class tries the Percolation.java class, giving statistics about it
 *  
 *----------------------------------------------------------------*/
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	
	private int T;
	private double[] vectorP;

   /**
    *   It performs trials independent experiments on an n-by-n grid
    */	
   public PercolationStats(int n, int trials) throws IllegalArgumentException{
	   
	   if (n <= 0 || trials <=0){
		   throw new IllegalArgumentException();
	   }
	   
	   this.T = trials;
	   vectorP = new double[trials];
	   for (int i = 0; i < trials; i++) {	
		    Percolation perc = new Percolation(n);
			int row = StdRandom.uniform(1, n+1);
			int col = StdRandom.uniform(1, n+1);
			perc.open(row, col);
			while (!perc.percolates()){   
				row = StdRandom.uniform(1, n+1);
				col = StdRandom.uniform(1, n+1);
				perc.open(row, col);	
			}
			vectorP[i] = (double) perc.numberOfOpenSites()/(n*n);
	   }	   
   }
   
   /**
    *   It provides the sample mean of percolation threshold
    */
   public double mean() {                         
	   return StdStats.mean(vectorP);
   }
   
   /**
    *   It provides the sample standard deviation of percolation threshold
    */
   public double stddev(){
	   return StdStats.stddev(vectorP);
   }
   
   /**
    *   It provides the low endpoint of 95% confidence interval
    */
   public double confidenceLo(){
	   return (this.mean() - (1.96 * Math.sqrt(this.stddev()) / Math.sqrt(T) ));
   }
   
   /**
    *   It provides the high endpoint of 95% confidence interval
    */
   public double confidenceHi(){
	   return (this.mean() + (1.96 * Math.sqrt(this.stddev()) / Math.sqrt(T) ));	   
   }
	   
   public static void main(String[] args) {
	   int n = Integer.parseInt(args[0]);
	   int trials = Integer.parseInt(args[1]);
	   PercolationStats ps = new PercolationStats(n,trials);
	   System.out.println("mean                    = "+ ps.mean());
	   System.out.println("stdev                   = "+ ps.stddev());
	   System.out.println("95% confidence interval = ["+ ps.confidenceLo() + ", "+ ps.confidenceHi()+"]");	   
	}

}
