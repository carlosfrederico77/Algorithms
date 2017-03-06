package br.com.carlosfrederico77.part1.week1;
/*----------------------------------------------------------------
 *  Author:        Carlos Machado
 *  Written:       3/2/2017
 *  Last updated:  3/2/2017
 *
 *  Compilation:   javac Percolation.java
 *  Execution:     java Percolation
 *  
 *  This class solve the percolation problem using the WeightedQuickUnionUF approach
 *  
 *----------------------------------------------------------------*/
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

   private int[][] matrix;
   private int size;
   private int count=0;
   private int n;
   private int root1;
   private int root2;
   private WeightedQuickUnionUF alg4;

   /**
    *   Constructor of the Percolation class
    *   It creates n-by-n grid, with all sites blocked
    *   Throws a NullPointerException if n less or equals to zero.
    */
   public Percolation(int n) throws IllegalArgumentException{ 
	   if (n <= 0){
		   throw new IllegalArgumentException();
	   }
	   matrix = new int[n][n];
	   size = n*n;
	   root1 = 0;
	   root2 = size+1;
	   this.n = n;
	   alg4  = new WeightedQuickUnionUF(size+2);
	   for (int i = 0; i < n; i++) {
 		   alg4.union(root1, i+1);
		   alg4.union(root2, (n*n+1)-(n-i));
		   for (int j = 0; j < n; j++) {
			   matrix[i][j] = 0;			   
		   }
	   }
   }

   /**
    *   It opens a site, denoted by row and col, in the n-by-n grid
    *   Throws a IndexOutOfBoundsException if it do not respect the boundaries of the grid.
    */   
   public void open(int row, int col) throws IndexOutOfBoundsException{ 

	  if (row < 1 || row > n || col < 1 || col > n){
    	  throw new IndexOutOfBoundsException();
      }	   
	  if (!isOpen(row, col)){
		  matrix[row-1][col-1] = 1;
		  count++;
		  propagateUnion(row,col);		  
	  }
   }

   /**
    *   It verifies the 4 options of connections around the just opened site
    *   And verifies if the system already percolates 
    */
   private void propagateUnion(int r, int c){
	      int r1 = r-1;
	      int c1 = c-1;	      
		  if (r1-1>=0 && isOpen(r-1, c)){ 
			  if (alg4.find((n*r)-n+c) != alg4.find(n*(r-1)-n+c) ){ 
			    alg4.union((n*r)-n+c,(n*(r-1)-n+c));
			    propagateUnion(r-1,c);
			  }
		  }
		  if (r1+1<n && isOpen(r+1, c)){ 
			  if (alg4.find((n*r)-n+c) != alg4.find(n*(r+1)-n+c) ){
  			    alg4.union((n*r)-n+c,(n*(r+1)-n+c));
			    propagateUnion(r+1,c);
			  }
		  }
		  if (c1+1<n && isOpen(r, c+1)){ 
			  if (alg4.find((n*r)-n+c) != alg4.find((n*r)-n+c+1)){
				 alg4.union((n*r)-n+c,((n*r)-n+c+1));
			     propagateUnion(r,c+1);
			  }
		  }
		  if (c1-1>=0 && isOpen(r, c-1)){ //west cell
			  if (alg4.find((n*r)-n+c) != alg4.find((n*r)-n+c-1)){
				 alg4.union((n*r)-n+c,((n*r)-n+c-1));
			     propagateUnion(r,c-1);
			  }
		  }		  		  	   
   }
   
   /**
    *   It checks if a site, denoted by row and col, is open
    *   Throws a IndexOutOfBoundsException if it do not respect the boundaries of the grid.
    */   
   public boolean isOpen(int row, int col){ 
	   if (row < 1 || row > n || col < 1 || col > n){
	    	  throw new IndexOutOfBoundsException();
	      }
      return matrix[row-1][col-1]==1;
   }
   
   /**
    *   It checks if a site, denoted by row and col, is still closed
    *   Throws a IndexOutOfBoundsException if it do not respect the boundaries of the grid.
    */   
   public boolean isFull(int row, int col){ 
	   if (row < 1 || row > n || col < 1 || col > n){
	    	  throw new IndexOutOfBoundsException();
	      }
	   int leaf = (n*row-n+col);
	   return (isOpen(row, col) && alg4.connected(root1, leaf ));
   }
   
   /**
    *   It gives the number of open sites
    */   
   public int numberOfOpenSites(){      	   
	   return count;
   }
   
   /**
    *   It checks if the system do percolates
    */   
   public boolean percolates(){              	   
	 return alg4.connected(root1, root2);   
   }

   
   /**
    *   A simples trial of this class
    */   
   public static void main(String[] args){   
	   
	   Percolation perc = new Percolation(2);

	   for (int i = 0; i < perc.n; i++) {
		   for (int j = 0; j < perc.n; j++) {
			   System.out.print(perc.matrix[i][j]+" ");			   
		   }
		   System.out.println();
	   }
	   System.out.println();
	   System.out.println();
	   
	   perc.open(1, 1);
	   perc.open(2, 1);
	   
	   for (int i = 0; i < perc.n; i++) {
		   for (int j = 0; j < perc.n; j++) {
			   System.out.print(perc.matrix[i][j]+" ");			   
		   }
		   System.out.println();
	   }
	   
   }
}