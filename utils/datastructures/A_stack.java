package utils.datastructures;

public class A_stack {

	/*
	     This shows a simple implementation of a stack. The idea is to use a linkedlist to store
	  the stack. In order to do this, what we need to do is:
	  	
	     1. private Node first;
	  	 2. The new integer that pushed into the stack will be the new first.
	  	 3. When doing the pop(), we just set first = first.next and then return it.
	  
	 */
	
	class Node
	{
		int value;
		Node next;
	}
	
	private Node first = null;
	
	public void push(int n)
	{
		Node oldfirst = first;
			 first = new Node();
			 first.value = n;
			 first.next = oldfirst;
	}
	public int pop()
	{
		Node ret = first;
		first = first.next;
		System.out.print(ret.value);
		return ret.value;
	}
	
	public static void test()
	{
		A_stack a = new A_stack();
		a.push(1);
		a.push(2);
		a.push(3);
		a.push(4);
		a.push(5);
		a.pop();
		a.pop();
		a.pop();
		a.pop();
	}
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		test();
	}

}
