package utils.datastructures;

import java.util.Iterator;

import utils.datastructures.B_stackWithGeneric.Node;

public class C_stackWithIterator<Item> implements Iterable<Item>
{
	/*
	 
	 	It is a stack, which is kind of a container, so we need to implements the Iterable.
	 	
	 A Iterable Interface has a method of iterator() that returns a iterator.
	 A Iterator Interface has the following 2 methods:
	 	1. hasNext
	 	2. next
	 
	 	That's it.
	 
	 So, first we need to implements the Iterable interface, so we can just create a method of iterator that returns a instance of a class
	that implements the Iterator interface.
	
	Done.
	 
	 */
	
	@Override
	public Iterator<Item> iterator() 
	{
		// TODO Auto-generated method stub
		return new IteratorGenerator();
	}
	private class IteratorGenerator implements Iterator
	{
		private Node current = first;
		@Override
		public boolean hasNext() {
			
			return (current != null);
		}
		@Override
		public Item next() 
		{
			Node ret = current;
			current = current.next;
			return ret.value;
		}
	}
	
	class Node
	{
		Item value;
		Node next;
	}
	
	private Node first = null;
	
	public void push(Item n)
	{
		Node oldfirst = first;
			 first = new Node();
			 first.value = n;
			 first.next = oldfirst;
	}
	public Item pop()
	{
		Node ret = first;
		first = first.next;
		System.out.print(ret.value);
		return ret.value;
	}
	
	public static void test()
	{
		C_stackWithIterator<Integer> a = new C_stackWithIterator<Integer>();
		a.push(1);
		a.push(2);
		a.push(3);
		a.push(4);
		a.push(5);
		Iterator i = a.iterator();
		while (i.hasNext())
		{
			System.out.println(i.next());
		}
	}
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		test();
	}
}
