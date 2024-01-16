// Name: Arriola, Carlos
// Project: #5
// Due: 12/8/23
// Course: CS-2400-02-F23
//
// Description: Airport application that demonstrates the use of the 
//				Graph ADT, alongside various other data structures 
//				learned throughout the course.

/** A collection of functions for a priority queue */
public interface PriorityQueueInterface<T extends Comparable<? super T>>
   {
     /** Adds a new entry to this priority queue.
         @param newEntry  An object to be added. */
     public void add(T newEntry);
  
    /** Removes and returns the entry having the highest priority.
         @return  Either the object having the highest priority or, if the
                  priority queue is empty before the operation, null. */
    public T remove();
  
    /** Retrieves the entry having the highest priority.
        @return  Either the object having the highest priority or, if the
                 priority queue is empty, null. */
    public T peek();
  
    /** Detects whether this priority queue is empty.
        @return  True if the priority queue is empty, or false otherwise. */
   public boolean isEmpty();
  
    /** Gets the size of this priority queue.
        @return  The number of entries currently in the priority queue. */
    public int getSize();
  
    /** Removes all entries from this priority queue. */
    public void clear();
  }