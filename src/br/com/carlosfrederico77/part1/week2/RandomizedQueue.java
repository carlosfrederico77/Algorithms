package br.com.carlosfrederico77.part1.week2;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

/*----------------------------------------------------------------
 *  Author:        Carlos Machado
 *  Written:       3/4/2017
 *  Last updated:  3/4/2017
 *
 *  Compilation:   javac RandomizedQueue.java
 *  Execution:     java RandomizedQueue
 *  
 *  This class implements a RandomizedQueue witch implements Iterable<Item>
 *  
 *----------------------------------------------------------------*/

public class RandomizedQueue<Item> implements Iterable<Item> {

	private int n; // size of the queue
	private Item[] queue;

	/**
	 * Constructs an empty randomized queue
	 */
	public RandomizedQueue() {
		queue = (Item[]) new Object[2];
		n = 0;
	}

	/**
	 * Verifies if the queue is empty
	 */
	public boolean isEmpty() {
		return n == 0;
	}

	/**
	 * It returns the number of items on the queue
	 */
	public int size() {
		return n;
	}

	/**
	 * It resizes the underlying array holding the elements
	 */
	private void resize(int capacity) {
		assert capacity >= n;
		Item[] temp = (Item[]) new Object[capacity];
		for (int i = 0; i < n; i++) {
			temp[i] = queue[i];
		}
		queue = temp;
	}

	/**
	 * Adds the item in the end of the queue
	 */
	public void enqueue(Item item) {
		if (item == null) {
			throw new NullPointerException();
		}
		if (n == queue.length)
			resize(2 * queue.length); // double size of array if necessary
		queue[n] = item;
		n++;
	}

	/**
	 * Removes and returns the item from the front of the queue
	 */
	public Item dequeue() {
		assertNotEmpty();
		
		int position;
		//if (n == 1)
		//	position = 1;
		//else
			position = StdRandom.uniform(0, n);
		
		Item result = queue[position];
		queue[position] = queue[n - 1];
		queue[n - 1] = null;
		n--;
		if (n > 0 && n == queue.length / 4)
			resize(queue.length / 2); // shrink size of array if necessary
		return result;
	}

	/**
	 * Returns (but do not removes) a random item
	 */
	public Item sample() {
		assertNotEmpty();
		
		int position;
		//if (n == 1)
		//	position = 1;
		//else
			position = StdRandom.uniform(0, n);
		Item result = queue[position];
		return result;
	}

	private void assertNotEmpty() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
	}

	/**
	 * Returns an independent iterator over items in random order
	 */
	public Iterator<Item> iterator() {
		return new RandomizedQueueIterator();
	}

	private class RandomizedQueueIterator implements Iterator<Item> {
		private int i = 0;
		private Item[] queueB = (Item[]) new Object[n];

		public RandomizedQueueIterator() {
			for (int j = 0; j < n; j++) {
				queueB[j] = queue[j];
			}
			StdRandom.shuffle(queueB);
		}

		public boolean hasNext() {
			return i < n;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			return queueB[i++];
		}
	}

	/**
	 * A simples trial of this class
	 */
	public static void main(String[] args) {
		RandomizedQueue<String> test = new RandomizedQueue<String>();
		test.enqueue("Sofia");
		test.dequeue();
		test.enqueue("Luisa");
		test.enqueue("Luciana");
		test.enqueue("Fred");
		test.dequeue();
		for (String string : test) {
			System.out.print(string + " ");
		}
		System.out.println();
		test.enqueue("Fred");
		test.enqueue("Fox");
		test.enqueue("Pingo");
		test.enqueue("Tango");
		test.enqueue("Sofia");
		test.enqueue("Luisa");
		test.dequeue();
		test.dequeue();
		test.enqueue("XXXXXX");
		test.dequeue();
		for (String string : test) {
			System.out.print(string + " ");
		}
		System.out.println();

	}
}
