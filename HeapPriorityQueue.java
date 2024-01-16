// Name: Arriola, Carlos
// Project: #5
// Due: 12/8/23
// Course: CS-2400-02-F23
//
// Description: Airport application that demonstrates the use of the 
//				Graph ADT, alongside various other data structures 
//				learned throughout the course.

/** Implementation of a priority queue through the use of an inner minheap; minimums are prioritized */
public class HeapPriorityQueue<T extends Comparable<? super T>> implements PriorityQueueInterface<T> {
	private MinHeapInterface<T> pq;
	
	/** Constructs a new HeapPriorityQueue, initializes a distinct empty MinHeap*/
	public HeapPriorityQueue() {
	         pq = new MinHeap<>();
	}
	
	/** Adds a new entry to PQ
	@param newEntry The new entry to be added */
	public void add(T newEntry) {
		pq.add(newEntry);
	}

	/** Removes the smallest entry from PQ 
	@return result The minimum entry from the PQ */
	public T remove() {
		T result = pq.removeMin();
		return result;
	}

	/** Returns the minimum value of the PQ 
	@return The minimum value of the PQ */
	public T peek() {
		return pq.getMin();
	}

	/** Checks if the PQ is empty 
	@return True if the PQ is empty */
	public boolean isEmpty() {
		return pq.isEmpty();
	}

	/** Returns the size of the PQ 
	@return The size of the PQ */
	public int getSize() {
		return pq.getSize();
	}

	/** Removes all entries from the PQ */
	public void clear() {
		pq.clear();
	}
}
