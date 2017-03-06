import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*----------------------------------------------------------------
 *  Author:        Carlos Machado
 *  Written:       3/5/2017
 *  Last updated:  3/5/2017
 *
 *  Compilation:   javac Permutation.java
 *  Execution:     java Permutation
 *  
 *  This class that takes a command-line integer k; reads in a sequence of strings 
 *  from standard input using StdIn.readString(); and prints exactly k of them, 
 *  uniformly at random
 *  
 *----------------------------------------------------------------*/

public class Permutation {

   public static void main(String[] args) {
     int k = Integer.parseInt(args[0]);
     RandomizedQueue<String> rndq = new RandomizedQueue<String>();
     while (!StdIn.isEmpty()) {
         rndq.enqueue(StdIn.readString());
     }
     int count = 0;
     if (k == 0) return;
     for (String string : rndq) {    	 
         StdOut.println(string);
         count++;
         if (count == k) break;
     }
   }
}
