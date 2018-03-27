package utils.datastructures;
import edu.princeton.cs.algs4.Queue;
import static utils.print.Print.print1DArray;

import java.util.Iterator;
public class AgainHeap {

	int [] heapArray;
	int sizeOfArray;
	
	public AgainHeap(int[] intArray) 
	{
		sizeOfArray = intArray.length;
		heapArray = new int[sizeOfArray + 1] ;
	    for (int i = 0; i < sizeOfArray; i++)
        {
            heapArray[i + 1] = intArray[i];      
        }
	    print1DArray(heapArray, sizeOfArray + 1);
		// TODO Auto-generated constructor stub
	}
	
	public boolean hasLeft(int indexPara)
	{
		return (indexPara * 2) <= sizeOfArray;
	}
	
	public boolean hasRight(int indexPara)
	{
		return (indexPara * 2 + 1) <= sizeOfArray;
	}
	
	private boolean less(int indexParaA, int indexParaB)
	{
		return heapArray[indexParaA] < heapArray[indexParaB];
	}
	
	private void heapify(int indexPara)
	{
		int maxIndex = indexPara;
		
		if (hasLeft(indexPara))
		{
		    if (less(indexPara, indexPara * 2))
            {
                maxIndex = indexPara * 2;
            }
		}
        if (hasRight(indexPara))
        {
            if (less(maxIndex, indexPara * 2 + 1))
            {
                maxIndex = indexPara * 2 + 1;
            }
        }
        if (indexPara != maxIndex)
        {
            swap(indexPara, maxIndex);
            heapify(maxIndex);
        }
	}
	
	private void swap(int i, int j)
    {
        int temp = heapArray[i];
        heapArray[i] = heapArray[j];
        heapArray[j] = temp;
    }
    
	public void sort()
	{
		while (sizeOfArray > 0)
		{
			System.out.print("max: " + heapArray[1] + " ");
		    swap(1, sizeOfArray);
		    sizeOfArray--;
		    heapify(1);
		    
		}
		System.out.println("\n"); 
		
	}
	
    public void pself()
    {
        for (int i = 0; i < sizeOfArray; i++)
        {
            System.out.print(heapArray[i + 1] + " ");
        }
        System.out.println("\n"); 
    }
    public static  void testHeapify()
    {
        AgainHeap h = new AgainHeap(new int[]{1,2,3,4,5,6,7,8});
        h.heapify(4);
        h.heapify(3);
        h.heapify(2);
        h.heapify(1);
        
        h.sort();
  
    } 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queue<Integer> q = new Queue<Integer>();
		q.enqueue(1);
		q.enqueue(1);
		q.enqueue(1);
		
		Iterator<Integer> i = q.iterator();
		while (i.hasNext())
		{
			System.out.println(i.next());
		}
		
        //testHeapify();
        //AgainHeap h = new AgainHeap(new int[]{1,2,3,4,5,6,7,8});
	}

}
