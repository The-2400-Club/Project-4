public class MaxHeap
{
	private int[] heap;	//Array of heap entries
	
	private int lastIndex;	//Index of last entry
	private int numberOfSwaps = 0;	//initialization of number of swaps
	
	private boolean initialized = false;
	
	private static final int DEFAULT_CAPACITY = 25;
	private static final int MAX_CAPACITY = 10000;

	public MaxHeap()
	{
		this(DEFAULT_CAPACITY); // Call next constructor
	}	//end default constructor
	
	public MaxHeap(int initialCapacity)
	{
		//The cast is safe because the new array contains all null entries
		if (initialCapacity < DEFAULT_CAPACITY)
			initialCapacity = DEFAULT_CAPACITY;
		else checkCapacity(initialCapacity);
		
		int[] tempHeap = new int[initialCapacity + 1];
		heap = tempHeap;
		lastIndex = 0;
		initialized = true;
	}	//end constructor
	
	//Method used by heap constructor.
	private void reheap(int rootIndex)
	{
		boolean done = false;
			
		int orphan = heap[rootIndex];
		int leftChildIndex = 2 * rootIndex;
			
		while (!done && (leftChildIndex <= lastIndex))
		{
			int largerChildIndex = leftChildIndex;
			int rightChildIndex = leftChildIndex + 1;
				
			if ((rightChildIndex <= lastIndex) && (heap[rightChildIndex] > heap[largerChildIndex]))
			{
				largerChildIndex = rightChildIndex;
			}	//end if

			if (orphan < heap[largerChildIndex])
			{
				heap[rootIndex] = heap[largerChildIndex];
					
				rootIndex = largerChildIndex;
					
				leftChildIndex = 2 * rootIndex;
					
				numberOfSwaps++;	//Swap counter
			}
			else
				done = true;
		}	//end while
			
		heap[rootIndex] = orphan;
			
	}	//end reheap

	// Adds to the heap and is used for sequential insertion method.
	public void add(int newEntry)
	{
		checkInitialization();
		
		int newIndex = lastIndex + 1;
		int parentIndex = newIndex / 2;
		
		while ((parentIndex > 0) && (newEntry > heap[parentIndex]))
		{
			heap[newIndex] = heap[parentIndex];
			
			newIndex = parentIndex;
			parentIndex = newIndex / 2;
			
			numberOfSwaps++;	//swap counter
		}
		
		heap[newIndex] = newEntry;
		
		lastIndex++;
		
		ensureCapacity();
	}	//end add
	
	//Removes heap's root.
	public int removeMax()
	{
		checkInitialization();
		
		int root = 0;
		
		if (!isEmpty())
		{
			root = heap[1];				//Return value
			
			heap[1] = heap[lastIndex];	//Form a semiheap
			lastIndex--;				//Decrease size
			
			reheap(1);					//Transform to a heap
		}	//end if	
		
		return root;
		
	}	//end removeMax
		
	//Finds the root of the heap.
	public int getMax()
	{
		checkInitialization();
		
		int root = 0;
		
		if (!isEmpty())
			root = heap[1];
		
		return root;
	}	//end getMax();
		
	// Throws an exception if the client requests a capacity that is too large.
	private void checkCapacity(int initialCapacity) 
	{
		if (initialCapacity > MAX_CAPACITY)
			throw new IllegalStateException("Attempt to create a bag whose " + "capacity exceeds allowed " + "maximum of " + MAX_CAPACITY);
	}	//end checkCapacity
	
	//Checks to make sure if the heap is at good capacity and doubles if it is not.
	private void ensureCapacity()
	{
		if(lastIndex+1 >= heap.length)
			heap = doubleCapacity();			
	}	//end ensureCapacity
	
	//Doubles the capacity of the heap.
	private int[] doubleCapacity()
	{
		int[] newCap = new int[heap.length * 2];
		
		for (int i = 0; i < heap.length; i++)
			newCap[i] = heap[i];
		
		return newCap;
	}	//end doubleCapacity
	
	/** Checks if this heap is empty.
		@return True if heap is empty.*/
	public boolean isEmpty()
	{
		return lastIndex < 1;
	}	//end isEmpty
	
	public int getSize()
	{
		return lastIndex;
	}	//end getSize;
	
	//Clear all entries from heap.
	public void clear()
	{
		checkInitialization();
		while (lastIndex > -1)
		{
			heap[lastIndex] = 0;
			lastIndex--;
		}	//end while
		
		lastIndex = 0;
	}	//end clear
	
	// Throws an exception of this object is not initialized.
	private void checkInitialization()
	{
		if(!initialized)
			throw new IllegalStateException("Heap not initialized.");
	} //end checkInitialization
	
	//Uses reheap method to create a heap.
	public MaxHeap(int[] entries)
	{
		this(entries.length);	//Call other constructor
		
		//Copy given array to data field
		for (int index = 0; index < entries.length; index++)
		{
			heap[index + 1] = entries[index];
			
			lastIndex++;
		}		
		//Create heap
		for (int rootIndex = lastIndex / 2; rootIndex > 0; rootIndex--)
		{
			reheap(rootIndex);
		}		
	}	//end heap

	//Gets the first ten numbers in the heap and returns them.
	public String topTen()
	{
		String result = "";
		
		for(int i = 1; i <= 10; i++)
		{
			result = result + Integer.toString(heap[i]) + ",";
		}
		
		return result;
	}	//end firstTen
	
	//Removes first ten roots in the heap and returns the next ten.
	public String removeTop()
	{
		for(int j = 0; j < 10; j++)
		{
			removeMax();
		}
		
		return topTen();
	}	//end removeTen
	
	//Returns number of swaps in the called method.
	public int getSwaps() 
	{
		return numberOfSwaps;
	}	//end getSwaps
}
