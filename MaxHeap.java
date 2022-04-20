public final class MaxHeap<T extends Comparable<? super T>> implements MaxHeapInterface<T>
{
    private T[] heap;       //Array of heap entries
    private int lastIndex;  //Index of last entry
    private static final int DEFAULT_CAPACITY = 25;
    private int swaps;

    public MaxHeap()
    {
        this(DEFAULT_CAPACITY); //call next constructor
    } //end default constructor

    public MaxHeap(int initialCapacity)
    {
        //The cast is safe because the new array contains all null entries
        @SuppressWarnings("unchecked")
        T[] tempHeap = (T[]) new Comparable[initialCapacity + 1];
        heap = tempHeap;
        lastIndex = 0;
        swaps = 0;
    } //end constructor

    public void reheap(int rootIndex)
    {
        boolean done = false;
        T orphan = heap[rootIndex];
        int leftChildIndex = 2 * rootIndex;

        while(!done && (leftChildIndex <= lastIndex))
        {
            int largerChildIndex = leftChildIndex;
            int rightChildIndex = leftChildIndex + 1;

            if((rightChildIndex <= lastIndex) &&
                heap[rightChildIndex].compareTo(heap[largerChildIndex]) > 0)
            {
                largerChildIndex = rightChildIndex;
                swaps++;
            } //end if

            if(orphan.compareTo(heap[largerChildIndex]) < 0)
            {
                heap[rootIndex] = heap[largerChildIndex];
                swaps++;
                rootIndex = largerChildIndex;
                leftChildIndex = 2 * rootIndex;
            }
            else
            {
                done = true;
            }
        } //end while
        
        heap[rootIndex] = orphan;
        swaps++;
    } //end reheap

    public void add(T newEntry)
    {
        int newIndex = lastIndex + 1;
        int parentIndex = newIndex / 2;
        while((parentIndex > 0) && newEntry.compareTo(heap[parentIndex]) > 0)
        {
            heap[newIndex] = heap[parentIndex];
            swaps++;
            newIndex = parentIndex;
            parentIndex = newIndex / 2;
        } //end while
        heap[newIndex] = newEntry;
        swaps++;
        lastIndex++;
    } //end add

    public T removeMax()
    {
        T root = null;

        if(!isEmpty())
        {
            root = heap[1];
            heap[1] = heap[lastIndex];
            lastIndex--;
            reheap(1);
        } //end if

        return root;
    } //end removeMax

    public T getMax()
    {
        T root = null;
        if(!isEmpty())
        {
            root = heap[1];
        }
        return root;
    } //end getMax

    public boolean isEmpty()
    {
        return lastIndex < 1;
    } //end isEmpty

    public int getSize()
    {
        return lastIndex;
    } //end getSize

    public void clear()
    {
        while(lastIndex < -1)
        {
            heap[lastIndex] = null;
            lastIndex--;
        } //end while
        lastIndex = 0;
    } //end clear

}
