// Name: Arriola, Carlos
// Project: #5
// Due: 12/8/23
// Course: CS-2400-02-F23
//
// Description: Airport application that demonstrates the use of the 
//				Graph ADT, alongside various other data structures 
//				learned throughout the course.

/** An array implementation of the QueueInterface */
public final class ArrayQueue<T> implements QueueInterface<T>
   {
      private T[] queue;
      private int frontIndex;
      private int backIndex;
      private static final int DEFAULT_CAPACITY = 50;

     /** Call the next constructor */
     public ArrayQueue()
     {
        this(DEFAULT_CAPACITY);
     }

     /** Constructs an ArrayQueue of a specified capacity
     @param initialCapacity A specified capacity for the ArrayQueue to begin at */
     public ArrayQueue(int initialCapacity)
     {
        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] tempQueue = (T[]) new Object[initialCapacity + 1];
        queue = tempQueue;
        frontIndex = 0;
        backIndex = initialCapacity;
     }
     
     @Override
     /** Adds a new entry to the back of the queue
     @param newEntry The entry to be added */
     public void enqueue(T newEntry) {
	   backIndex = (backIndex + 1) % queue.length;
	   queue[backIndex] = newEntry;	
     }
     
     @Override
     /** Returns the front entry of the queue
     @return The front index of the queue, or an exception if it does not exist*/
     public T getFront() {
    	 if (isEmpty())
    		 throw new RuntimeException();
    	 else
    		 return queue[frontIndex];
     }
     
     @Override
     /** Checks whether or not the queue is empty 
     @return True if the queue is empty */
     public boolean isEmpty() {		
    	 return frontIndex == ((backIndex + 1) % queue.length);
     }

     @Override
     /** Removes all entries in the queue */
     public void clear() {
    	 while(!isEmpty())
    		 dequeue();
     }

     @Override
     /** Removes the front entry in the queue, and shifts the queue as needed 
     @return front The front entry that is removed, if it exists */
     public T dequeue() {
    	 if (isEmpty())
    		 throw new RuntimeException();
    	 else
    	 {
    		 T front = queue[frontIndex];
    		 queue[frontIndex] = null;
    		 frontIndex = (frontIndex + 1) % queue.length;
    		 return front;
    	 }
     }
}