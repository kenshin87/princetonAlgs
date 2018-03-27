/*
	This package implements the dynamic connectivity problem.
	
	In order to model the problem, we can use a 1d array or a 2d matrix. 
We will implement the algorithm using both.

	The first method is that we could use the first method, that is a 1d array. What we can do is:
	
		1. label all the n points that we want to study, from 0 to n-1;
		2. Create an array of n slot, and then initialize each slot with the value of its index. This can be illustrated like following:
		 	val    0   1   2   3   4   5   6   7   8
		 	index [0] [1] [2] [3] [4] [5] [6] [7] [8] 

	There are many approach to solve the problem. Including QF, 
	
	QF: The first method that we will use is called QF. The idea is that we can set the value of the connected slots as the same. That is, 
if there are slots with indexes of p and q, then, if the slots are connected, then index[p] == index[q].
	In this case after initializing all the slots, we can begin to read the connection from a file. For example, here we get the information that the following
spots are connected:

		index[1] and index[3]
		index[4] and index[3]
		index[7] and index[4]
		
	when we get the first info, we just set array[3] of array[1], so the picture will be 
		
		 	val    0   1   2   1   4   5   6   7   8
		 	index [0] [1] [2] [3] [4] [5] [6] [7] [8] 

	then we get the second info, and we iterate the array, and set all the values that identical to array[3] to array[4]

		 	val    0   4   2   4   4   5   6   7   8
			index [0] [1] [2] [3] [4] [5] [6] [7] [8] 
			
	then again we get the last info, so we set all the slots with value array[4](which is 4) to array[7]:

		 	val    0   7   2   7   7   5   6   7   8
			index [0] [1] [2] [3] [4] [5] [6] [7] [8] 	
	By this way, finally we can get a modified array according to the connectivity, and when we want to know whether 2 slots are connected, we can just check if
			array[p] == array[q].
			
	QU: The above method quickly finds whether 2 points are connected. However, looking into the code, we know that when we add a connection, we need to iterate
for n times. This means that it is a O^2 algrithm. Here we introduce another algorithm, which has a more faster way of union 2 points.

    First we need to change the strategy of the find method. Previously when calling find(p), we just returned the value of index[p], but considering the fact that:
    
    	Of all the connected points, there MUST BE a spot p with the value of p.
    
     Why, since nomatter how the values of the connected spot change, it must be one of the index of the spot, so we can change the find method as:
     
     int find(int p)
     {
     	//previously
     	// return array[p];
     	
     	if (p == array[p])
     	{
     		return p;
     	}
     	else
     	{
     		return find(array[p]);
     	}
     }
     
     finally, we will get to the spot that with its value identical to its index.
    
    With this new find method, now we can come up with a new union method. if we want to connect p and q, then we can just first find the root of each, and then
assign:

	void union()
	{
		int proot = find(p);
		int qroot = find(q);
		if (proot != qroot)
		{
			array[proot] = qroot;
		}
	}
	
	That is, by this way, the 2 batches will have the only root with the index of qroot.
	
*/

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

//import static utils.print.Print.print;
//import static utils.print.Print.print2DArray;



public class Percolation 
{
   private WeightedQuickUnionUF 	wuf;
   private int 						dimention;
   private int 						numOfOpen = 0;
   private int[][] 					gridMatrix;
   private int[]                    adjacentIndex = new int[]{-1, -1};
   
   public Percolation(int n)                // create n-by-n grid, with all sites blocked
   {
	   // initialize a new wuf
	   dimention = n;
	   gridMatrix = new int[n][n];
	   for(int i = 0; i < n; i++)
	   {
		   for(int j = 0; j < n; j++)
		   {
			   gridMatrix[i][j] = 0;
		   }
	   }
	   wuf = new WeightedQuickUnionUF(dimention * dimention + 2);
   }
   
   private boolean hasUpAndSet(int row, int col)
   {
	   if (row > 1)
	   {
		   adjacentIndex[0] = row - 1;
		   adjacentIndex[1] = col;
		   connectEach(row, col);
		   return true;
	   }
	   return false;
   }
   private boolean hasDownAndSet(int row, int col)
   {
	   if (row < dimention)
	   {
		   adjacentIndex[0] = row + 1;
		   adjacentIndex[1] = col;
		   connectEach(row, col);
		   return true;
	   }
	   return false;
   }
   private boolean hasLeftAndSet(int row, int col)
   {
	   if (col > 1)
	   {
		   adjacentIndex[0] = row;
		   adjacentIndex[1] = col - 1;
		   connectEach(row, col);
		   return true;
	   }
	   return false;
   }
   private boolean hasRightAndSet(int row, int col)
   {
	   if (col < dimention)
	   {
		   adjacentIndex[0] = row;
		   adjacentIndex[1] = col + 1;
		   connectEach(row, col);
		   return true;
	   }
	   return false;
   }
   
   private int transferMatrixToArray(int row, int col)
   {
	   return (row - 1) * dimention + col;
   }
   
   private void connectEach(int row, int col)
   {
	   if (isOpen(adjacentIndex[0], adjacentIndex[1]))
	   {
		   int i = transferMatrixToArray(row, col);
		   int j = transferMatrixToArray(adjacentIndex[0], adjacentIndex[1]);
		   wuf.union(i, j);
	   }
   }
   
   private void connectAll(int row, int col)
   {
	   hasUpAndSet(row, col);
	   hasDownAndSet(row, col);      
	   hasLeftAndSet(row, col);
	   hasRightAndSet(row, col);
   }
   
   
   public    void open(int row, int col)    // open site (row, col) if it is not open already
   {   
	   // Open means that we need to:
	   //	1. Change the value in the gridMatrix to 1.
	   //   2. check the adjacent points of the point, to see whether we need to connect it.
	   if (gridMatrix[row - 1][col - 1] == 0)
	   {
		   gridMatrix[row - 1][col - 1] = 1;
		   numOfOpen += 1;
		   connectAll(row, col);
		   if (row == 1)
		   {
			   int i = transferMatrixToArray(row, col);
			   wuf.union(0, i);
		   }
		   if (row == dimention)
		   {
			   int i = transferMatrixToArray(row, col);
			   wuf.union(i, dimention * dimention + 1);
		   }
	   }
   }
   public boolean isOpen(int row, int col)  // is site (row, col) open?
   {
	   return (gridMatrix[row - 1][col - 1] == 1);
   }
   public boolean isFull(int row, int col)  // is site (row, col) full?
   {
	   
	   if (isOpen(row, col))
	   {
		   int i = transferMatrixToArray(row, col);
		   //print("isFull: row--" + row + "col--" + col + "--" + wuf.find(i));
		   return (wuf.find(i) == wuf.find(0) );		   
	   }
	   return false;
   }
   public     int numberOfOpenSites()       // number of open sites
   {
	   return numOfOpen;
   }
   public boolean percolates()              // does the system percolate?
   {
	   //print(wuf.find(0) + "..." + wuf.find(dimention * dimention + 1));
	   return (wuf.find(0) == wuf.find(dimention * dimention + 1));
   }
   
/*   public static void debugTest1()
   {
	   Percolation p = new Percolation(10);
	   
	   p.open(3, 2);
	   print("isOpen " + p.isOpen(3, 2));
	   print("isFull " + p.isFull(3, 2));
	   print(p.wuf.find(2));
	   print(p.wuf.find(12));
	   print(p.wuf.find(22));
	   PercolationVisualizer.draw(p, 10);
	   
	   p.open(2, 2);
	   print("isOpen " + p.isOpen(2, 2));
	   print("isFull " + p.isFull(2, 2));
	   print(p.wuf.find(2));
	   print(p.wuf.find(12));
	   print(p.wuf.find(22));
	   PercolationVisualizer.draw(p, 10);
	   
	   p.open(1, 2);
	   print("isOpen " + p.isOpen(1,2));
	   print("isFull " + p.isFull(1, 2));
	   print(p.wuf.find(2));
	   print(p.wuf.find(12));
	   print(p.wuf.find(22));
	   
	   p.wuf.union(0, 1);
	   print(p.wuf.find(1));
	   PercolationVisualizer.draw(p, 10);
	   print2DArray(p.gridMatrix, 10, 10);
   }
*/
   public static void main(String[] args)   // test client (optional)
   {
	   //Percolation.debugTest1();
	   InteractivePercolationVisualizer.main(new String[] {"10"});
   }

}
