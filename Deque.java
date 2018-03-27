import java.util.Iterator;
import java.util.NoSuchElementException;
public class Deque<Item> implements Iterable<Item> 
{
	/*
	 
	This is a Deque class, it means that we need to implement addFirst, addLast, removeFirst, removeLast, 
in this case, we must use a double way linkedList. 
    
    Since it is a double way linkedList, so when we implements the above method, we need to take the node.next and node.first into consideration.
    In general：
    
    1. addFirst:
    	when (hasFirst)
    		addFirst and set prev, next
    	      (!hasFirst)
			addFirst and set last as first.
		when (hasLast)
			addLast and set prev, next
			  (!hasLast)
			add last and set first as last.
	    Pay attention to the following codes to see how a better organized idea can lead to a cleaner code.
    
	// messy implementation
    public void addFirst(Item item)          // add the item to the front
    {
    	if (item == null)
    	{
    		throw new IllegalArgumentException();
    	}
    	
    	Node oldFirst = first;
    	
	    	first = new Node();
	    	first.value = item;

    	first.next = oldFirst;
    	if (oldFirst != null)
    	{
    		oldFirst.prev = first;	
    	}
    	if (last == null)
    	{
    		last = first;
    	}
    	sizeOfQueue++;
    }
    
  public void addLast(Item item)           // add the item to the end
  {
  	if (item == null)
  	{
  		throw new IllegalArgumentException();
  	}
  	
  	 Node oldLast = last;
  	 
  	 last = new Node();
  	 last.value = item;
  	 
  	 if (oldLast != null)
  	 {
  		 oldLast.next = last;
  		 last.prev = oldLast;
  	 }
  	 
  	 if (first == null)
   	 {
   		 first = last;
   	 }
  	 sizeOfQueue++;
  }
    
  public Item removeFirst()                // remove and return the item from the front
  {
  	if (sizeOfQueue == 0)
  	{
  		throw new NoSuchElementException();
  	}
  		
  	Node ret = first;
  	
  	     
  	first = first.next;
  	if (first != null)
  	{
  		first.prev = null;
  	}
  	else if (first == null)
  	{
  		last = null;
  	}
  	
  	ret.next = null;
  	sizeOfQueue--;
  	return ret.value;
  }

    public Item removeLast()                 // remove and return the item from the end
    {
    	if (sizeOfQueue == 0)
    	{
    		throw new NoSuchElementException();
    	}
    	
    	Node ret = last;
    	     last = last.prev;
    	     
    	if (last == null)
    	{
    		first = null;
    	}
    	else if (last != null)
    	{
    		last.next = null;
    	}
    	
    	ret.prev = null;
    	sizeOfQueue--;
    	return ret.value;
    }
    
    // better implementation
    public void addFirst(Item item)
    {
    	if (item == null)
    	{
    		throw new IllegalArgumentException();
    	}
    	
    	Node added = new Node();
    		added.value = item;
    	
    	if (first == null)
    	{
    		first = last = added;
    	}
    	else if (first != null)
    	{
    		first.prev = added;
    		added.next = first;
    		first = first.prev;
    	}
    	sizeOfQueue++;
    }
    
    public void addLast(Item item)
    {
    	if (item == null)
    	{
    		throw new IllegalArgumentException();
    	}
    	
    	Node added = new Node();
    		added.value = item;
    		
    	if (last == null)
    	{
    		first = last = added;
    	}
    	else if (last != null)
    	{
    		last.next = added;
    		added.prev = last;
    		last = last.next;
    	}
    	sizeOfQueue++;
    }

    public Item removeFirst()
    {
    	if (sizeOfQueue == 0)
    	{
    		throw new NoSuchElementException();
    	}
    		
    	Node ret = first;
    	
    	if (first.next == null)
    	{
    		first = last = null;
    	}
    	else if (first.next != null)
    	{
    		first = first.next;
    		first.prev = null;
    		ret.next = null;
    	}
    	sizeOfQueue--;
    	
    	return ret.value;
    }
    
    public Item removeLast()
    {
    	if (sizeOfQueue == 0)
    	{
    		throw new NoSuchElementException();
    	}
    	Node ret = last;
    	
    	if (last.prev == null)
    	{
    		first = last = null;
    	}
    	else if (last.prev != null)
    	{
    		last = last.prev;
    		last.next = null;
    		ret.prev = null;
    	}
    	sizeOfQueue--;
    	return ret.value;
    }
	 */
	private Node first;
	private Node last;
	private int sizeOfQueue;
	
	private class Node
	{
		Item value;
		Node prev;
		Node next;
	}

    public Deque()                           // construct an empty deque
    {
    	first = last = null;
    }
    public boolean isEmpty()                 // is the deque empty?
    {
    	return (sizeOfQueue == 0);
    }
    public int size()                        // return the number of items on the deque
    {
    	return sizeOfQueue;
    }

    public void addFirst(Item item)
    {
    	if (item == null)
    	{
    		throw new IllegalArgumentException();
    	}
    	
    	Node added = new Node();
    		added.value = item;
    	
    	if (first == null)
    	{
    		first = last = added;
    	}
    	else if (first != null)
    	{
    		first.prev = added;
    		added.next = first;
    		first = first.prev;
    	}
    	sizeOfQueue++;
    }
    
    public void addLast(Item item)
    {
    	if (item == null)
    	{
    		throw new IllegalArgumentException();
    	}
    	
    	Node added = new Node();
    		added.value = item;
    		
    	if (last == null)
    	{
    		first = last = added;
    	}
    	else if (last != null)
    	{
    		last.next = added;
    		added.prev = last;
    		last = last.next;
    	}
    	sizeOfQueue++;
    }

    public Item removeFirst()
    {
    	if (sizeOfQueue == 0)
    	{
    		throw new NoSuchElementException();
    	}
    		
    	Node ret = first;
    	
    	if (first.next == null)
    	{
    		first = last = null;
    	}
    	else if (first.next != null)
    	{
    		first = first.next;
    		first.prev = null;
    		ret.next = null;
    	}
    	sizeOfQueue--;
    	
    	return ret.value;
    }
    
    public Item removeLast()
    {
    	if (sizeOfQueue == 0)
    	{
    		throw new NoSuchElementException();
    	}
    	Node ret = last;
    	
    	if (last.prev == null)
    	{
    		first = last = null;
    	}
    	else if (last.prev != null)
    	{
    		last = last.prev;
    		last.next = null;
    		ret.prev = null;
    	}
    	sizeOfQueue--;
    	return ret.value;
    }

    public Iterator<Item> iterator()         // return an iterator over items in order from front to end
    {
    	return new QueueIterator();
    }
    
    private class QueueIterator implements Iterator<Item>
    {
    	private Node pointer = first;

    	@Override
    	public void remove()
    	{
    		throw new UnsupportedOperationException();
    	}
		@Override
		public boolean hasNext() {
			return (pointer != null);
		}

		@Override
		public Item next() {
			if (pointer == null)
			{
				throw new NoSuchElementException();
			}
					
			Node p = pointer;
			pointer = pointer.next;
			return  p.value;
		}
    }
    
//    private static void test()
//    {
//    	Deque<Integer> d = new Deque<Integer>();
//    	
//    	for (int i = 0; i<100; i++)
//    	{
//    		d.addFirst(i);
//    	}
//    	
//
//
//    	System.out.println(d.first);
//    	System.out.println(d.size());
//    	System.out.println(d.isEmpty());
//
//    }
    
    public static void main(String[] args) 
    {
    	//test();
    }

}
