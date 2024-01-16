// Name: Arriola, Carlos
// Project: #5
// Due: 12/8/23
// Course: CS-2400-02-F23
//
// Description: Airport application that demonstrates the use of the 
//				Graph ADT, alongside various other data structures 
//				learned throughout the course.

/** A collection of functions for the MinHeap ADT */
public interface MinHeapInterface<T extends Comparable<? super T>>
   {
      /** Adds a new entry to this heap.
          @param newEntry  An object to be added. */
      public void add(T newEntry);
 
      /** Removes and returns the smallest item in this heap.
          @return  Either the smallest object in the heap or,
                   if the heap is empty before the operation, null. */
     public T removeMin();

     /** Retrieves the smallest item in this heap.
         @return  Either the smallest object in the heap or,
                  if the heap is empty, null. */
     public T getMin();

     /** Detects whether this heap is empty.
         @return  True if the heap is empty, or false otherwise. */
     public boolean isEmpty();

     /** Gets the size of this heap.
         @return  The number of entries currently in the heap. */
     public int getSize();

     /** Removes all entries from this heap. */
     public void clear();
     }