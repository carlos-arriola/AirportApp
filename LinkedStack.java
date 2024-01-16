// Name: Arriola, Carlos
// Project: #5
// Due: 12/8/23
// Course: CS-2400-02-F23
//
// Description: Airport application that demonstrates the use of the 
//				Graph ADT, alongside various other data structures 
//				learned throughout the course.

import java.util.EmptyStackException;

/** A class of stacks whose entries are stored in a chain of nodes. */
public final class LinkedStack<T> implements StackInterface<T>{
	private Node topNode;

	/** Default constructor for the LinkedStack, initialized with 0 entries*/
	public LinkedStack() {
		topNode = null;
	}
	
	/** Pushes a new value onto the top of the stack 
	@param newEntry The dataportion to be added */
	public void push(T newEntry) {
		Node newNode = new Node(newEntry, topNode);
		topNode = newNode;		
	}
	
	/** Removes the top entry of the stack, may throw EmptyStackException if empty
	@return The top entry of the stack to be removed*/
	public T pop() {
		T top = peek();
		topNode = topNode.getNextNode();
		return top;
	}
	
	/** Returns the top entry in the stack, throws EmptyStackException if empty
	@throws EmptyStackException Exception to be thrown if stack is empty upon method call
	@return The top entry in the stack */
	public T peek()
	{
		if (isEmpty())
			throw new EmptyStackException();
		else
			return topNode.getData();
	}
	
	/** Checks whether or not the stack is empty
	@return True if the stack has no entries, and false otherwise */
	public boolean isEmpty() {
		return topNode == null;
	}
	
	/** Removes all entries in the stack*/
	public void clear() {
		topNode = null;
	}
	
/** A private helper class Node for linking the data in the stack*/
	private class Node {
		private T data;
		private Node next;
		
		/** Constructs a Node with a given data portion, but does not specify the next Node 
		@param dataPortion The desired data to be stored in the topNode. */
		private Node (T dataPortion)
		{
			this(dataPortion, null);
		}
		
		/** Returns the next Node of the current Node
		 * @return A reference to the next Node in the sequence */
		private LinkedStack<T>.Node getNextNode() {
			return next;
		}
	
		/** Returns the data of the current Node
		 * @return Data of the current Node */
		private T getData() {
			return data;
		}
	
		/** Constructs a Node with a given data portion, and a reference to the next Node. 
		@param dataPortion The desired data to be stored in the topNode.
		@param nextNode The reference to the next node in the chain */
		private Node (T dataPortion, Node nextNode)
		{
			data = dataPortion;
			next = nextNode;
		}
	}
	}
