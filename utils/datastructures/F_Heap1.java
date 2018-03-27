package utils.datastructures;
import static utils.print.Print.print1DArray;

     /*
      A heap is an array that represents a full binary tree. At the beginning, we first consider the following 2 questions.
      
      
A         		0
	      	1		2
	      3   4   5   6
	     7 8
	      
	      
B	            1
	        2		3
	      4   5   6   7
	     8 
    
    1. whether we represent the array from 0 or from 1?  
      
    Here we consider whether we represent the array from 0 or from 1.
       
    If it is from 0, then it is like what A is showing. In this case, we need to check until 
the element 3, this is the last leaf with son nodes. and it has the index of 3. It appears 
that from length(9) to get 3 is a little bit more calculation.

	Another way is to calculate from 1, in particular, we create an array without using its
first slot. In this case, the second slot is with index 1 which stores the first element. 
Here we can see that the element 4, which also has the index of 4, is the last leaf with 
son nodes, and the length of the array is 10, it makes it easier to get 4 from 10.

	In conclusion, we use a 1 based index to represent an heap.

-----------------------------------------

    2. implementation:
    
    So, now we begin to start the implementation.
    
    a. receiving an inputArray of length n;
    b. create a new array of length n + 1, and store the original array from newArray[1] -- newArray[n]. In this case, we 
have the following 2 properties:

 		1. the index of the k element is k;
 		2. the leftson of the k element is 2k, and the rightson of the k element is 2k+1
 	
 	It means that, the nth element of the inputArray is newArray[n], 
 	and if n is odd:
 	
 		then the tree is like this:
 	            1
	        2		3
	      4   5   6   7
	     8 9
	    the last element that has son nodes is 4, which is n / 2 or (n-1)/2
	and if n is even:
 		then the tree is like this:
 	            1
	        2		3
	      4   5   6   7
	     8 
	    the last element that has son nodes is 4, which is n / 2 
	    
	with the above analysis, all the node start from n/2 + 1 are sorted heap node, so if we want 
to make the array as sorted heap, we can just start from n/1 and then recursively heapify the 
node, until we reach node 1.
 	
	*/

import static utils.print.Print.println;

public class F_Heap1 
{
	private int[] heapArray;
	private int numberOfElements;
	
	// Create a new array of length + 1
	public F_Heap1(int[] intArray) 
	{
		numberOfElements = intArray.length;
		heapArray = new int[intArray.length + 1];
		for (int i = 0; i < intArray.length; i++)
		{
			heapArray[i + 1] = intArray[i];
		}
		print1DArray(heapArray, 9);
	}

	// Check whether the heap is sorted.
	public boolean isSortedHeap()
	{
		for (int i = 1; i <= numberOfElements / 2; i++)
		{
			if (!isSortedNode(i))
			{
				return false;
			}
		}
		return true;
	}
	
	// check whether a node has a left son
	public boolean hasLeft(int index)
	{
		return (index * 2) <= numberOfElements;
	}

	// check whether a node has a right son
	public boolean hasRight(int index)
	{
		return (index * 2 + 1) <= numberOfElements;
	}	
	


	// check whether a node is a sorted node
	public boolean isSortedNode(int index)
	{
		if (hasLeft(index))
		{
			if ( heapArray[index] < heapArray[index * 2 ])   return false;
		}
		if (hasRight(index))
		{
			if ( heapArray[index] < heapArray[index * 2 +1])  return false;
		}
		return true;
	}
	
	// make the node as a sorted node
	public void heapify(int index)
	{
		int biggestIndex = index;
        int biggestValue = heapArray[index];
		
		if (hasLeft(index))
		{
			if (heapArray[biggestIndex] < heapArray[index * 2])
			{
				biggestIndex = index * 2;
				biggestValue = heapArray[index * 2];				
			}
		}
		if (hasRight(index))
		{
			if (heapArray[biggestIndex] < heapArray[index * 2 + 1])
			{
				biggestIndex = index * 2 + 1;
				biggestValue = heapArray[index * 2 + 1];				
			}
		}
		swap(heapArray, index, biggestIndex);
		if (!isSortedNode(biggestIndex))
		{
			heapify(biggestIndex);
		}
		
	}
	
	// swap an array
	public void swap(int[] array, int i, int j)
	{
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	public int popMax()
	{
		if (numberOfElements < 1)
		{
			throw new NullPointerException();
		}
		int max = heapArray[1];
		heapArray[1] = heapArray[numberOfElements];
		numberOfElements--;
		heapify(1);
		return max;
	}
	
	// print an heap to an sorted array.
	public int[] toArray()
	{
		int[] returnedArray = new int[numberOfElements];
		
		int i = 0;
		while (numberOfElements > 0)
		{
			returnedArray[i] = 	popMax();
			i++;
		}
		return returnedArray;
	}
	
	public static void main(String[] args) 
	{
		int [] intArray = new int[] {1, 2, 3, 4, 5, 6, 7, 8};
		
		//print1DArray(intArray, 8);
		
		F_Heap1 h = new F_Heap1(intArray);
		        h.heapify(4);
		print1DArray( h.heapArray, 9);
		        h.heapify(3);
		        print1DArray( h.heapArray, 9);        
		        h.heapify(2);
		        print1DArray( h.heapArray, 9);   
		        h.heapify(1);
		        print1DArray( h.heapArray, 9);  
		        
		int[] returnedArray = h.toArray();
	    print1DArray(returnedArray, 8);
		        
		// TODO Auto-generated method stub

	}
}
