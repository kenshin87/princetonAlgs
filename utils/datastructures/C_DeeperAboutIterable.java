package utils.datastructures;

import java.util.Iterator;

/*
  	This is something deeper about Iterable and Iterator interface. 
  	
  	For the last example,  we show that by implementing the Iterable class into our class Stack, then 
we can have a  iterator() method for each Stack, it returns an iterator. After that, we can implement the 
private inner Iterator class which returns the iterator, in this case, we can have a functional 
iterator for the class.
 
 	In general, first we define a innerclass of IteratorClass implements Iterator, it has hasNext() and next() 
method. then we can define a iterator() function for the Stack class that returns the inner class, that's it. 
 
 
 	There are also some other cases that we are using other's class that we cannot just implement
 a new method to generate a iterator. In this way, we might have to use a second implementation. In this case, we might 
want to implement a getIterable method that shows the similar behavior that Stack that implements Iterable. In this case, 
We need to define another innerclass of IterableClass that has the same iterator() method that returns the identical
new IteratorClass();

    See following example: 

 */

public class C_DeeperAboutIterable implements Iterable
{
	int [] array = new int[] {1,2,3,4,5,6,7,8};
	int length = 8;
	
    private class IterableClass implements Iterable
    {
		@Override
		public Iterator iterator() {
			// TODO Auto-generated method stub
			return new IteratorClass();
		}
    }
	
	private class IteratorClass implements Iterator
	{
		private int indexOfIterator = 0;
		
		@Override
		public boolean hasNext() {
			if (indexOfIterator < length) return true; 
			else                return false;
		}

		@Override
		public Object next() 
		{
			if (indexOfIterator >= length)
			{
				throw new NullPointerException();
			}
			else
			{
				return array[indexOfIterator++];
			}
		}
		
	}
	
	public C_DeeperAboutIterable() 	{ }

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return new IteratorClass();
	}

	
	public Iterator getIterator()
	{
		return new IteratorClass();
	}
	
	public Iterable getIterable()
	{
		return new IterableClass();
	}
	
	public static void main(String[] args) 
	{
		C_DeeperAboutIterable c = new C_DeeperAboutIterable();
		Iterator i = c.iterator();
		
		while (i.hasNext())
		{
			System.out.print(i.next() + " ");
		}
		
		Iterable it = c.getIterable();
		Iterator newi = c.getIterator();
		
		while (newi.hasNext())
		{
			System.out.print(newi.next() + " ");
		}
	}


}
