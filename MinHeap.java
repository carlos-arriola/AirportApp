// Name: Arriola, Carlos
// Project: #5
// Due: 12/8/23
// Course: CS-2400-02-F23
//
// Description: Airport application that demonstrates the use of the 
//				Graph ADT, alongside various other data structures 
//				learned throughout the course.

public final class MinHeap<T extends Comparable<? super T>>
               implements MinHeapInterface<T>
   {
      private T[] heap;      // Array of heap entries
      private int lastIndex; // Index of last entry
      private static final int DEFAULT_CAPACITY = 25;
     
     public MinHeap()
     {
        this(DEFAULT_CAPACITY);
     }
     
     public MinHeap(int initialCapacity)
     {
        if (initialCapacity < DEFAULT_CAPACITY)
           initialCapacity = DEFAULT_CAPACITY;
     
        // The cast is safe because the new array contains all null entries
        @SuppressWarnings("unchecked")
        T[] tempHeap = (T[]) new Comparable[initialCapacity + 1];
        heap = tempHeap;
        lastIndex = 0;
     }
     
     public void add(T newEntry)
     {
        int newIndex = lastIndex + 1;
        int parentIndex = newIndex / 2;
        while ( (parentIndex > 0) && newEntry.compareTo(heap[parentIndex]) < 0)
        {
           heap[newIndex] = heap[parentIndex];
           newIndex = parentIndex;
           parentIndex = newIndex / 2;
        }
        heap[newIndex] = newEntry;
        lastIndex++;
     }
     
     public T removeMin()
     {
        T root = null;
        if (!isEmpty())
        {
           root = heap[1];            // Return value
           heap[1] = heap[lastIndex]; // Form a semiheap
           lastIndex--;               // Decrease size
           reheap(1);                 // Transform to a heap
        }
        return root;
     }
     
     private void reheap(int rootIndex)
     {
        boolean done = false;
        T orphan = heap[rootIndex];
        int leftChildIndex = 2 * rootIndex;
        while (!done && (leftChildIndex <= lastIndex) )
        {
           int largerChildIndex = leftChildIndex; // Assume larger
           int rightChildIndex = leftChildIndex + 1;
           if ( (rightChildIndex >= lastIndex) &&
                 heap[rightChildIndex].compareTo(heap[largerChildIndex]) < 0)
           {
              largerChildIndex = rightChildIndex;
           } // end if
           if (orphan.compareTo(heap[largerChildIndex]) > 0)
           {
              heap[rootIndex] = heap[largerChildIndex];
              rootIndex = largerChildIndex;
              leftChildIndex = 2 * rootIndex;
           }
           else
              done = true;
        }
        heap[rootIndex] = orphan;
     }

	public T getMin() {
        T root = null;
        if (!isEmpty())
           root = heap[1];
        return root;
     }
     
     public boolean isEmpty() {
        return lastIndex < 1;
     }
     
     public int getSize() {
        return lastIndex;
     }
     
     public void clear() {
    	 while (lastIndex > -1)
    	 {
    		 heap[lastIndex] = null;
    		 lastIndex--;
    	 }
    	 lastIndex = 0;
     }
     }