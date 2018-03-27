package utils.datastructures;

import static utils.print.Print.print;
import static utils.print.Print.printLL;
import static utils.print.Print.println;
public class LinkedListNode {

	public int value;
	public LinkedListNode next;
	
	LinkedListNode()
	{
		
	}
	LinkedListNode(int value)
	{
		this.value = value;
	}
	
	public static LinkedListNode newLL()
	{
		// Generate a new LinkedList of length 10, step 10, from 0 - 90; 
		LinkedListNode head = null;
		LinkedListNode pointer = null;
		// TODO Auto-generated method stub
		for (int i = 0; i < 10; i++)
		{
			if (head == null)
			{
				head = new LinkedListNode(i);
				pointer = head;
			}
			else
			{
				pointer.next = new  LinkedListNode(10 * i);
				pointer = pointer.next;
			}
		}
		return head;		
	}
	
	public static void testLinkedList()
	{
		LinkedListNode head = newLL();
		printLL(head);		
	}
	
	public static LinkedListNode reverseLinkedList(LinkedListNode node)
	{
		/*
		 	a -- b -- c
		 	assuming that we have the above linked list. In order to reverse a linked list, What we need to 
		 do is as following:
		 	0. we have a key position of b, which is the next of the first element a.
		 	1. set b.next to a;
		 	2. in this case, we need another variable to keep tract of node c, else the linkedlist will lose.
		 	3. after setting b.next to a, now we change the key position to the original next of b, which is c.
		 we forever loop this circle, until we reach the end of the linked list.
		 	4. finally, we need to set a.next to null, since it is the end of the linked list.
		 */
		
		// at the very beginning we create 3 Node to store the positions.
		LinkedListNode first = node;
		LinkedListNode keyElement = node.next;
		LinkedListNode third = null;
		if (keyElement != null)
		{
			third = keyElement.next;
		}
		first.next = null;
		while (keyElement != null)
		{
			// set keyElement.next to first
			keyElement.next = first;
			// then move before
			first = keyElement;
			// then move keyElement
			keyElement = third;
			if (third!= null)
			{   // then move third
				third = third.next;	
			}
		}
		return first;
	}
	public static void testreverseLinkedList()
	{
		LinkedListNode head = newLL();
		//printLL(head);		
		print('\n');
		head = reverseLinkedList(head);
		printLL(head);	
	}	
	
	public static void main(String[] args) 
	{
		//testLinkedList();
		//testreverseLinkedList();
	}
}
