package utils.datastructures;

import static utils.print.Print.print1DArrayGeneric;

import edu.princeton.cs.algs4.StdOut;

public class G_HeapComparable {

	Comparable[] heapArray; 
	int sizeOfArray;
	
	
	public G_HeapComparable(Comparable[] pq) {
		// TODO Auto-generated constructor stub
		sizeOfArray = pq.length;
		heapArray = new Comparable[sizeOfArray + 1];
	    for (int i = 0; i < sizeOfArray; i++)
        {
            heapArray[i + 1] = pq[i];      
        }
	    print1DArrayGeneric(heapArray, sizeOfArray + 1);
	}

	public static void sort(Comparable[] pq)
	{
		G_HeapComparable g = new G_HeapComparable(pq);

		g.heapify(4);
		g.heapify(3);
		g.heapify(2);
		g.heapify(1);
		g.pself();
		g.sort();
		
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
	
	public boolean hasLeft(int indexPara)
	{
		return (indexPara * 2) <= sizeOfArray;
	}
	
	public boolean hasRight(int indexPara)
	{
		return (indexPara * 2 + 1) <= sizeOfArray;
	}
	private boolean less(int i, int j)
	{
		return heapArray[i].compareTo(heapArray[j]) < 0;
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
		Comparable temp = heapArray[i];
        heapArray[i] = heapArray[j];
        heapArray[j] = temp;
    }
    
    public void pself()
    {
        for (int i = 0; i < sizeOfArray; i++)
        {
            System.out.print(heapArray[i + 1] + " ");
        }
        System.out.println("\n"); 
    }
    
	public static void test()
	{
		String[] s = new String[] {"A", "B", "C", "D", "E", "F", "G", "H"};
		
		sort(s);
		
	}
    // print array to standard output
    private static void show(Comparable[] pq) {
        for (int i = 0; i < pq.length; i++) {
            StdOut.println(pq[i]);
        }
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}

}
