// Name: Arriola, Carlos
// Project: #5
// Due: 12/8/23
// Course: CS-2400-02-F23
//
// Description: Airport application that demonstrates the use of the 
//				Graph ADT, alongside various other data structures 
//				learned throughout the course.

/** A collection of functions for the Queue ADT */
public interface QueueInterface<T>
   {
      /** Adds a new entry to the back of this queue.
          @param newEntry  An object to be added. */
      public void enqueue(T newEntry);
 
      /** Removes and returns the entry at the front of this queue.
          @return  The object at the front of the queue.
          @throws  EmptyQueueException if the queue is empty before the operation. */
      public T dequeue();

     /** Retrieves the entry at the front of this queue.
         @return  The object at the front of the queue.
         @throws  EmptyQueueException if the queue is empty. */
     public T getFront();

     /** Detects whether this queue is empty.
         @return  True if the queue is empty, or false otherwise. */
     public boolean isEmpty();

     /** Removes all entries from this queue. */
     public void clear();
  }