// Name: Arriola, Carlos
// Project: #5
// Due: 12/8/23
// Course: CS-2400-02-F23
//
// Description: Airport application that demonstrates the use of the 
//				Graph ADT, alongside various other data structures 
//				learned throughout the course.

import java.util.Iterator;
import java.util.NoSuchElementException;

/** Implementation of DictionaryInterface that enables additions through hashing and linear probing, 
 * several methods were left unimplemented due to their lack of use in Project 4*/
public class HashedDictionary<K, V> implements DictionaryInterface<K, V> {
	
	private int numberOfEntries;
	private int numberOfCollisions;
	private static final int DEFAULT_CAPACITY = 5; // Must be prime
	private static final int MAX_CAPACITY = 10000;
	private static final int MAX_SIZE = 2 * MAX_CAPACITY;

	// Initialize the hashtable as an array of entries of keys and values
	private TableEntry<K, V>[] hashTable;
	private int tableSize;
	private boolean integrityOK = false;
	// Empty entry for reference against other entries
	protected final TableEntry<K, V> AVAILABLE = new TableEntry<>(null, null);
	
	/** Default; calls the next constructor */
	public HashedDictionary()
	{
		this(DEFAULT_CAPACITY);
	}
	
	/** Creates a HashedDictionary with a given initial capacity.
	@param initialCapacity The integer capacity desired. */
	public HashedDictionary(int initialCapacity)
	{
		initialCapacity = checkCapacity(initialCapacity);
		numberOfEntries = 0;
		// Set up hash table:
		// Initial size of hash table is same as initialCapacity if it is prime;
		// otherwise increase it until it is prime size
		tableSize = getNextPrime(initialCapacity);
		checkSize(tableSize); // Check that size is not too large
		// The cast is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
		TableEntry<K, V>[] temp = (TableEntry<K, V>[])new TableEntry[tableSize];
		hashTable = temp;
		integrityOK = true;
	}
	
	/** Private class that identifies each entry of the table with a key and value */
	private static class TableEntry<S, T>
	{
		private S key;
		private T value;
		
		/** Constructor for TableEntry
		@param searchKey The key for which the table entry can be accessed
		@param dataValue The datavalue to be associated with the table entry's key */
		private TableEntry(S searchKey, T dataValue) 
		{
			key = searchKey;
			value = dataValue;
		}
		
		/** Accessor for value 
		@return The value of this table entry */
		private T getValue() 
		{
			return value;
		}

		/** Accessor for key
		@return The key of this table entry */
		public S getKey() 
		{
			return key;
		}
	}
	
	private class ValueIterator implements Iterator<V>
	{
		private int currentIndex; // Current position in hash table
		private int numberLeft; // Number of entries left in iteration
		
		/** Constructor for a KeyIterator, initializes fields */
		private ValueIterator()
		{
			currentIndex = 0;
			numberLeft = numberOfEntries;
		}
		
		/** Determines whether or not there are entries remaining to iterate
		@return True if there are entries in the dictionary remaining to iterate */
		public boolean hasNext() {
			return numberLeft > 0;
		}

		/** Returns the key of the next occupied entry in the hash table
		@return result The key of the next entry to iterate */
		public V next()
		{
		V result = null;
		if (hasNext())
		{
			// Skip table locations that do not contain a current entry
			while ( (hashTable[currentIndex] == null) ||
					(hashTable[currentIndex] == AVAILABLE))
			{
				currentIndex++;
			}
			result = hashTable[currentIndex].getValue();
			numberLeft--;
			currentIndex++;
			}
		else
			throw new NoSuchElementException("There are no further entries in the table to iterate");
		return result;
		}
		
		
		
		/** Unimplemented standard remove function for an iterator */
		public void remove()
		{
			throw new UnsupportedOperationException("Unimplemented method 'remove'");
		}
	}
	
	/** Implementation of the Java util Iterator*/
	private class KeyIterator implements Iterator<K>
	{
		private int currentIndex; // Current position in hash table
		private int numberLeft; // Number of entries left in iteration
		
		/** Constructor for a KeyIterator, initializes fields */
		private KeyIterator()
		{
			currentIndex = 0;
			numberLeft = numberOfEntries;
		}
		
		/** Determines whether or not there are entries remaining to iterate
		@return True if there are entries in the dictionary remaining to iterate */
		public boolean hasNext() {
			return numberLeft > 0;
		}

		/** Returns the key of the next occupied entry in the hash table
		@return result The key of the next entry to iterate */
		public K next()
		{
		K result = null;
		if (hasNext())
		{
			// Skip table locations that do not contain a current entry
			while ( (hashTable[currentIndex] == null) ||
					(hashTable[currentIndex] == AVAILABLE))
			{
				currentIndex++;
			}
			result = hashTable[currentIndex].getKey();
			numberLeft--;
			currentIndex++;
			}
		else
			throw new NoSuchElementException("There are no further entries in the table to iterate");
		return result;
		}
		
		
		
		/** Unimplemented standard remove function for an iterator */
		public void remove()
		{
			throw new UnsupportedOperationException("Unimplemented method 'remove'");
		}
	}
	
	/** Adds a new TableEntry into the hashtable, with a given key and value assignment
	@param key The key for which the table entry can be accessed
	@param value The value to be associated with the table entry's key 
	@return result The value of an overwritten TableEntry, or null otherwise 
	*/
	public V add(K key, V value)
	{
	   checkIntegrity();
	   if ((key == null) || (value == null))
	      throw new IllegalArgumentException();
	   else
	   {
		   		V result = null;	      
	      		// Get the hash index of a key
	    	  	int index = getHashIndex(key);
	    	  	
	    	  	// If the hashTable entry at this index ISN'T null, that implies there's an entry there
	    		if (hashTable[index] != null)
	    		{
	    			// If the key of this entry is the same as the one we're adding, then we've just found
	    			// the same TableEntry, no collision has occurred
	    			if (key.equals(hashTable[index].getKey()))
	    			{
	    				// This should not occur with how we have structured main however, so simply
	    				// return result without further action
	    				return result;
	    			}
	    			else
	    				// Assertion: The key of this table entry is not the same as we're looking for
	    				// A collision has occurred!
	    			{
	    			// Probe beginning at the previously defined hash index for an available position
	    			index = linearProbe(index, key);
		    	  	hashTable[index] = new TableEntry<>(key, value);
		    	  	numberOfEntries++;
		    	  	numberOfCollisions++;
	    			}
	    		}
	    		else // Asserting hashTable[index] == null, it should be safe to assign an entry here
	    		{	    		
		    	  	hashTable[index] = new TableEntry<>(key, value);
	    			numberOfEntries++;
	    		}
	    		return result;
	      }
	   }


	/** Returns the TableEntry value of a given key
	@param key The key with a desired value
	@return result The value associated with the key, or null if it was not found */
	public V getValue(K key) {
		checkIntegrity();
		V result = null;
		int index = locateIndex(key);
		if ((hashTable[index] != null) && (key.equals(hashTable[index].getKey())))
		{
			result = hashTable[index].getValue(); // Key found; get value
		}
		// Else key not found; return null
		return result;
	}
	
	/** Locates the index of a given key
	@param key The key with a desired index 
	@return index The index associated with the key, or the last position in the hashTable if it is not found */
	private int locateIndex(K key)
	{
	   int index = 0;
	   while (index < tableSize - 1)
	   {
		   if (hashTable[index] != null)
		   {
			   if (key.equals(hashTable[index].getKey()))
			   {
					   break;
			   }
		   }
	     index++;
	   }
	   return index;
	}
	
	/** Open addressing method linear probe, searches for the next unoccupied entry in the hashtable, or the index of the key if found
	@param index The index where the search begins
	@param key The */
	private int linearProbe(int index, K key)
	{
	checkIntegrity();
	// While the current index is not null and does not equal to the specified key
	while ((hashTable[index] != null) && (!key.equals(hashTable[index].getKey())))
	{
				index = (index + 1) % tableSize; // Linear probing
	}
	// Assertion: Either key or null is found at hashTable[index]
		return index; // Index of either key or null
	}

	/** Constructs a KeyIterator for the HashedDictionary
	@return A new KeyIterator with the default constructor */
	public Iterator<K> getKeyIterator() {
		return new KeyIterator();
	}

	/** Unimplemented getValueIterator function for a dictionary */ 
	public Iterator<V> getValueIterator() {
		return new ValueIterator();
	}

	/** Unimplemented isEmpty function for a dictionary */ 
	public boolean isEmpty() {
		throw new UnsupportedOperationException("Unimplemented method 'isEmpty'");
	}
	
	/** Unimplemented isEmpty function for a dictionary */ 
	public V remove(K key) {
		throw new UnsupportedOperationException("Unimplemented method 'remove'");
	}
	
	/** Unimplemented contains function for a dictionary */ 
	public boolean contains(K key) {
		throw new UnsupportedOperationException("Unimplemented method 'contains'");
	}

	/** Unimplemented getSize function for a dictionary */ 
	public int getSize() {
		throw new UnsupportedOperationException("Unimplemented method 'getSize'");
	}

	/** Unimplemented clear function for a dictionary */ 
	public void clear() {
		throw new UnsupportedOperationException("Unimplemented method 'clear'");
	}
	
	/** Accessor for numberOfCollisions
	@return numberOfCollisions*/
	public int getCollisionCount() {
		return numberOfCollisions;
	}

	/** Checks a given capacity for valid size 
	@param capacity A given int capacity to evaluate
	@return capacity If there were no issues with its size*/
	private int checkCapacity(int capacity)
	{
		if (capacity > MAX_CAPACITY)
			throw new IllegalStateException("Attempt to create a dictionary whose " +
					"capacity exceeds allowed maximum of " + MAX_CAPACITY);
		if (capacity < 1)
			throw new IllegalStateException("Attempt to create a dictionary whose " +
					"capacity is less than allowed minimum of 1");
		
		return capacity;
			
	}

	/** Checks a given table size for valid size 
	@param size A given int table size to evaluate
	@return size If there were no issues with the table size */
	private void checkSize(int size)
	{
		if (size > MAX_SIZE)
			throw new IllegalStateException("Attempt to create a hashtable whose " +
					"size exceeds allowed maximum of " + MAX_SIZE);
		if (size < 1)
			throw new IllegalStateException("Attempt to create a hashtable whose " +
					"size is less than allowed minimum of 1");
	}
	
	/** Determines the next prime number from a given starting point
	@param num A starting point to evaluate the next prime
	@return num The next prime number from the starting point */
	private static int getNextPrime(int num)
	{
		if (isPrime(num))
			return num;
		else
		{
			while (!isPrime(num))
				num++;
		}
		return num;
	}
	
	/** Determines whether or not an int value is prime
	@param num An int to evaluate 
	@return True if the num is prime */
	private static boolean isPrime(int num)
    {
        if(num<=1)
        {
            return false;
        }
       for(int i=2;i<=num/2;i++)
       {
           if((num%i)==0)
               return false;
       }
       return true;
    }

	/** Verifies the integrity of the HashedDictionary */
	private void checkIntegrity()
	{
		if (!integrityOK)
			throw new SecurityException("HashedDictionary object is corrupt.");
	}

	/** Evaluates the hash index of a given key 
	@param key A key to evaluate a hash index for 
	@return hashIndex The key converted into a hash index */
	private int getHashIndex(K key)
	{
		int hashIndex = key.hashCode() % tableSize;
		
		if (hashIndex < 0)
			hashIndex = hashIndex + tableSize;

		return hashIndex;
	}
}