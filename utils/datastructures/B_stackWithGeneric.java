package utils.datastructures;

import utils.datastructures.A_stack.Node;

public class B_stackWithGeneric<Item> 
{
	/*
	 
	    So, we used a linkedList to store a stack. The basic component of the linkedlist is linkedNode. There are 2 field in
	a node, that is 
		Node
		{
			int;
			Node;
		}
	This is not practical since we never want to create 100 types of linkedListNode for a given type, so this is why
    Generic type comes.
    
    	The syntax of generic is to put a <AbstractType> at the definition of the stack definition, and when we create a new object
    of the type, we use a <realType> in order to tell the compiler that we are now using the type to create a new container, see the following example:
	 
	 */
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
		B_stackWithGeneric<Integer> a = new B_stackWithGeneric<Integer>();
		a.push(1);
		a.push(2);
		a.push(3);
		a.push(4);
		a.push(5);
		a.pop();
		a.pop();
		a.pop();
		a.pop();
		
		B_stackWithGeneric<Character> b = new B_stackWithGeneric<Character>();
		b.push('a');
		b.push('b');
		b.push('c');
		b.push('d');
		b.push('e');
		b.pop();
		b.pop();
		b.pop();
		b.pop();
	}
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		test();
	}
	
}
