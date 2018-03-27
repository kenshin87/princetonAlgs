import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;
import java.util.NoSuchElementException;
//import static utils.print.Print.print1DArray;

/*
 	Now we need to implement a randomizedQueue datastructure. The requirements are as following:
 		
 		enqueue or dequeue should be randomized, and amortized O(1);
 		a randomized iterator, and iterator.next() should be O(1);
 		
 	With the requirements, of course the idea of a linkedlist should be deprecated, since a linkedList won't be able to offer O(1) randomized iterator speed.
The only choice can just be using an array like implementation.
 	
 	
 	
 
 
 
 
 
 */

public class RandomizedQueue<Item> implements Iterable<Item> 
{
    private Item[] a;         // array of items
    private int n;            // number of elements on stack
    
    public RandomizedQueue() 
    {
        a = (Item[]) new Object[2];
        n = 0;
    }

    public boolean isEmpty() 
    {
        return n == 0;
    }

    public int size() 
    {
        return n;
    }

    private void resize(int capacity)
    {
        assert capacity >= n;
        // textbook implementation
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }
    
    public void enqueue(Item item) 
    {
        if (n == a.length) 
    	{
    		resize(2 * a.length);
    	}
        a[n++] = item;                       
    }

    public Item dequeue() 
    {
        if (isEmpty()) 
        {
        	throw new NoSuchElementException("Stack underflow");
        }

        int index = StdRandom.uniform(n);
        Item item = a[index];
        a[index] = null;                              // to avoid loitering
        a[index] = a[n-1];
        a[n-1]   = null;
        n--;
        if (n > 0 && n == a.length/4) resize(a.length/2);
        return item;
    }
    public Item sample()
    {
        if (isEmpty()) 
        {
        	throw new NoSuchElementException("Stack underflow");
        }
    	return a[StdRandom.uniform(n)];
    }
    public Iterator<Item> iterator() 
    {
        return new RandomizedQueueIterator();
    }
    private class RandomizedQueueIterator implements Iterator<Item> 
    {
        private int[] iteratorArray = new int[n];
        private int hasNextAmount;

        public RandomizedQueueIterator() 
        {
        	hasNextAmount = n;
            for (int i = 0; i > n; i++)
            {
                iteratorArray[i] = i;
            }          
            shuffleArray();
        }
        
        private void shuffleArray()
        {
            for (int j = iteratorArray.length - 1; j > 0; j--)
            {
                int randomIndex = StdRandom.uniform(j + 1);
                // Simple swap
                int a = iteratorArray[randomIndex];
                iteratorArray[randomIndex] = iteratorArray[j];
                iteratorArray[j] = a;
            }
        }

        public boolean hasNext() {
            return hasNextAmount > 0;
        }
        public void remove() 
        {
            throw new UnsupportedOperationException();
        }
        public Item next() 
        {
            if (!hasNext()) 
            {
            	throw new NoSuchElementException();
            }
            int i = iteratorArray[--hasNextAmount];
            System.out.print(i);
            return  a[i] ;
        }
    }
    public static void test()
    {
    	RandomizedQueue<Integer> r = new RandomizedQueue<Integer>();

        for (int i = 0; i < 10; i++)
        {
            r.enqueue(i);     
        }

    	Iterator<Integer> i = r.iterator();

    	while (i.hasNext())
    	{
    		System.out.print(i.next());
    	}
    }
    public static void main(String[] args)
    {
    	test();
    }
}
