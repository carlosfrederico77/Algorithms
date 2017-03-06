package br.com.carlosfrederico77.part1.week2;
/*----------------------------------------------------------------
 *  Author:        Carlos Machado (carlosfrederico77)
 *  Written:       3/4/2017
 *  Last updated:  3/4/2017
 *
 *  Compilation:   javac Deque.java
 *  Execution:     java Deque
 *  
 *  This class implements a Deque
 *  
 *----------------------------------------------------------------*/
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
 
        private int n;          // size of the deque
        private Node first;     // top of deque
        private Node last;      // end of deque
     
     /* 
      * inner linked list class
      * 
      */
     private class Node {
         private Item item;
         private Node next;
         private Node prev;
     }

    /**
     *   Construct an empty deque
     */   
    public Deque() {
         first = null;
         last = null;
         n = 0;     
    }
    
    /**
     *   Not supported
     */   
    private void remove() {
     throw new UnsupportedOperationException();
    }
    
    /**
     *  Verifies if the deque empty
     */   
    public boolean isEmpty() {
      return n == 0;
    }
    
    /**
     *  It returns the number of items on the deque
     */   
    public int size() {
      return n;
    }
    
    /**
     *  Adds the item to the front of the deque
     */   
    public void addFirst(Item item) {
        if (item == null) {
          throw new NullPointerException();
        }
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        if (oldfirst != null)
          oldfirst.prev = first;
        n++;  
        if (n == 1) {
         last = first;
        }
    }
    
    /**
     *  Adds the item to the end of the deque
     */   
    public void addLast(Item item) {
     if (item == null) {
        throw new NullPointerException();
     }
     Node oldlast = last;
     last = new Node();
     last.item = item;
     last.prev = oldlast;
     if (oldlast != null)
    	 oldlast.next = last;     
     n++;  
     if (n == 1) {
       first = last;
     }     
    }

    /**
     *  Removes and returns the item from the front of the deque
     */   
    public Item removeFirst() {
        if (isEmpty()) {
           throw new NoSuchElementException();
        }
        Item item = first.item;        // save item to return
        if (first.next != null) {
             first = first.next;            // delete first node
             first.prev = null;
        }
        n--;
        if (n == 0) {
         last = null;
         first = null;
         }
        return item;     
    }
    
    /**
     *  Removes and returns the item from the end of the deque
     */   
    public Item removeLast() {
        if (isEmpty()) {
          throw new NoSuchElementException();
        }
        Item item = last.item;
        if (last.prev != null) {
           last = last.prev;  
           last.next = null;
        }
        n--;
        if (n == 0) {
         first = null;
         last = null;
        }
        return item;     
    }
    
    /**
     *   Return an iterator over items in order from front to end
     */   
    public Iterator<Item> iterator() {
        return new DequeIterator();
     }

     // an iterator, doesn't implement remove() since it's optional
     private class DequeIterator implements Iterator<Item> {
         private Node current = first;
         public boolean hasNext() { 
          return current != null ;                     
         }
         public void remove() { 
          throw new UnsupportedOperationException();  
         }

         public Item next() {
             if (!hasNext()) 
               throw new NoSuchElementException();
             Item item = current.item;             
             current = current.next;              
             return item;
         }
     }

    /**
     *   A simples trial of this class
     */   
    public static void main(String[] args) {
     Deque<String> dequeF = new Deque<String>();
     dequeF.addFirst("Alfa"); 
     dequeF.addFirst("Beta");
     dequeF.addLast("Gama");
     dequeF.addLast("Teta");
     dequeF.addLast("Bravo");
     dequeF.addFirst("Fox");
     dequeF.addFirst("Zeta");
     for (String string : dequeF) {
		System.out.print(string + " ");
	}
     System.out.println();
     dequeF.removeFirst();
     dequeF.removeLast();
     dequeF.addFirst("Alfa"); 
     dequeF.addFirst("Beta");
     dequeF.addLast("Gama");
     dequeF.addLast("Teta");
     dequeF.addLast("Bravo");
     dequeF.addFirst("Fox");
     dequeF.addFirst("Zeta");
     dequeF.removeFirst();
     dequeF.removeLast();     
     for (String string : dequeF) {
		System.out.print(string + " ");
	}
     System.out.println();
     
    }
}
