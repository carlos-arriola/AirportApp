// Name: Arriola, Carlos
// Project: #5
// Due: 12/8/23
// Course: CS-2400-02-F23
//
// Description: Airport application that demonstrates the use of the 
//				Graph ADT, alongside various other data structures 
//				learned throughout the course.

/** An collection of functions for the Stack ADT */
public interface StackInterface<T> {
	/** Adds a new entry to the top of this stack.
	@param newEntry An object to be added to the stack. */
	public void push(T newEntry);
	
	/** Removes and returns this stack's top entry.
	@return The object at the top of the stack. */
	public T pop();
	
	/** Retrieves this stack's top entry.
	@return The object at the top of the stack. */
	public T peek();
	
	/** Detects whether this stack is empty.
	@return True if the stack is empty. */
	public boolean isEmpty();
	
	/** Removes all entries from this stack. */
	public void clear();
}
