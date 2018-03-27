import java.util.Iterator;



public class Board 
{
		private int[][] goalBoard;
		private int[][] board;
		private int boardDimention;
		private Board[] neighboringBoard;
		
		private class IterableClass implements Iterable<Board>
		{
			@Override
			public Iterator<Board> iterator() {
				// TODO Auto-generated method stub
				return new IteratorClass();
			}
		}
		
		private class IteratorClass implements Iterator<Board>
		{
			private int lengthOfIteratorObj = 0;
			
			public boolean hasNext() 
			{
				if (lengthOfIteratorObj >= neighboringBoard.length)
				{
					return false;
				}
				else if (lengthOfIteratorObj < neighboringBoard.length)
				{
					if (neighboringBoard[lengthOfIteratorObj] != null)
					{
						return true;
					}
					else if (neighboringBoard[lengthOfIteratorObj] != null)
					{
						lengthOfIteratorObj++;
						return hasNext();
					}
				}
				return false;
			}

			@Override
			public Board next() {
				if (hasNext())
				{
					return neighboringBoard[lengthOfIteratorObj++];
				}
				else
				{
					throw new NullPointerException();
				}
			}
		}
		
		private void swap(int[][] arrayPara, int rowF, int colF, int rowS, int colS)
		{
			int temp = arrayPara[rowF][colF];
			arrayPara[rowF][colF] = arrayPara[rowS][colS];
			arrayPara[rowS][colS] = temp;
		}
		
		
		
		private int[] findIndex(int[][] arrayPara, int numberPara)
		{
	        for (int i = 0; i < boardDimention; i++)
	        {
	        	for (int j = 0; j < boardDimention; j++)
	        	{
	        		//System.out.print(i + " " + j + "\n");
	        		if (numberPara == arrayPara[i][j])
	        		{
	        			
	        			return new int[] {i, j};
	        		}
	        	}
	        }
	        throw new NullPointerException();
		}

		private void initializeGoalBoard()
		{
	    	goalBoard =  new int[boardDimention][boardDimention];
	        int n = 1;
	        for (int i = 0; i < boardDimention; i++)
	        {
	        	for (int j = 0; j < boardDimention; j++)
	        	{
	        		goalBoard[i][j] = n;
	        		n++;    		
	        	}
	        }
	        goalBoard[boardDimention-1][boardDimention-1] = 0;
	        
;
		}
		
		private void initializeNeighbor()
		{
			if (neighboringBoard != null)
			{
				return;
			}
			else if (neighboringBoard == null)
			{
				neighboringBoard = new Board[4];
				for (int i = 0; i < 4; i++)
				{
					neighboringBoard[i] = null;
				}
				
		        int[] indexArray = findIndex(board, 0);
		        
		        int row = indexArray[0];
		        int col = indexArray[1];
		        
		        int numberOfNeighbor = 0;
		       
		        if (indexArray[0] > 0                ) numberOfNeighbor += 1;
		        if (indexArray[0] < boardDimention -1) numberOfNeighbor += 1;
		        if (indexArray[1] > 0                ) numberOfNeighbor += 1;
		        if (indexArray[1] < boardDimention -1) numberOfNeighbor += 1;
		        	

		        
		        int[][] precessorBoard = new int[boardDimention][boardDimention];
		        
		        for (int i = 0; i < boardDimention; i++)
		        {
		        	for (int j = 0; j < boardDimention; j++)
		        	{
		        		precessorBoard[i][j] = board[i][j];
		        	}
		        }
		        
		        //System.out.println("entering neighbour" + indexArray[0] + " " + indexArray[1]);
		        
		        int i = 0;
	        	if (indexArray[0] > 0) 
	        	{
	        		swap(precessorBoard, row, col, row - 1, col);
	        		neighboringBoard[i] = new Board(precessorBoard);
	        		swap(precessorBoard, row, col, row - 1, col);
	        		i++;
	        	}
	        	if (indexArray[0] < boardDimention -1) 
	        	{
	        		swap(precessorBoard, row, col, row + 1, col);
	        		neighboringBoard[i] = new Board(precessorBoard);
	        		swap(precessorBoard, row, col, row + 1, col);
	        		i++;
	        	}
	        	if (indexArray[1] > 0) 
	        	{
	        		swap(precessorBoard, row, col, row, col - 1);
	        		neighboringBoard[i] = new Board(precessorBoard);
	        		swap(precessorBoard, row, col, row, col - 1);
	        		i++;
	        	}
	        	if (indexArray[1] < boardDimention -1) 
	        	{
	        		swap(precessorBoard, row, col, row, col + 1);
	        		neighboringBoard[i] = new Board(precessorBoard);
	        		swap(precessorBoard, row, col, row, col + 1);
	        		i++;
	        	}
			}

        	//System.out.println(neighboringBoard[0] == null);
        	//System.out.println(neighboringBoard[1] == null);
        	//System.out.println(neighboringBoard[2] == null);
        	//System.out.println(neighboringBoard[3] == null);
        	
        	//System.out.println("quit neighbour");
        	
		}
		
	    public Board(int[][] blocks)           // construct a board from an n-by-n array of blocks
	                                           // (where blocks[i][j] = block in row i, column j)
	    {
	    	boardDimention = blocks.length;
	    	board = new int[boardDimention][boardDimention];
	    	for (int i = 0; i < boardDimention; i++)
	    	{
	    		for (int j= 0; j < boardDimention; j++)
	    		{
	    			board[i][j] = blocks[i][j];
	    		}
	    	}
	    	initializeGoalBoard();   	
	    }
	    	    
	    public int dimension()                 // board dimension n
	    {
	    	return boardDimention;
	    }
	    public int hamming()                   // number of blocks out of place
	    {
	        int result = 0;
	        for (int i = 0; i < boardDimention; i++)
	        {
	        	for (int j = 0; j < boardDimention; j++)
	        		if (board[i][j] != goalBoard[i][j] && board[i][j] != 0)
	        		{
	        			result += 1;
	        		}
	        }
	        return result;
	    }
	    public int manhattan()                 // sum of Manhattan distances between blocks and goal
	    {
	        int result = 0;
	        for (int i = 0; i < boardDimention; i++)
	        {
	        	for (int j = 0; j < boardDimention; j++)
	        	{
	        		if (board[i][j] != goalBoard[i][j] && board[i][j] != 0)
	        		{
	        			int number = board[i][j];
	        			int[] indexes = findIndex(goalBoard, number);
	        			int diff = Math.abs(indexes[0] - i) + Math.abs(indexes[1] - j);
	        			result = result + diff;
	        		}
	        	}
	        }
	        return result;	                 
	    }
	    public boolean isGoal()                // is this board the goal board?
	    {

	        int result = 0;
	        for (int i = 0; i < boardDimention; i++)
	        {
	        	for (int j = 0; j < boardDimention; j++)
	        	{
	        		if (board[i][j] != goalBoard[i][j])
	        		{
	        			return false;
	        		}
	        	}
	        }
	        return true;
	    }
	    public Board twin()                    // a board that is obtained by exchanging any pair of blocks
	    {
	    	int[][] newBlocks = new int[boardDimention][boardDimention];
	    	for (int i = 0; i < boardDimention; i++)
	    	{
	    		for (int j= 0; j < boardDimention; j++)
	    		{
	    			newBlocks[i][j] = board[i][j];
	    		}
	    	}
	    	if (newBlocks[0][0] != 0 && newBlocks[0][1] != 0)
	    	{
	    		int temp = newBlocks[0][0];
	    		newBlocks[0][0] = newBlocks[0][1];
	    		newBlocks[0][1] = temp;
	    	}
	    	else
	    	{
	    		int temp = newBlocks[1][0];
	    		newBlocks[1][0] = newBlocks[1][1];
	    		newBlocks[1][1] = temp;
	    	}
	    	return new Board(newBlocks);
	    }
	    public boolean equals(Object y)        // does this board equal y?
	    {
	    	return board.toString().equals(y.toString());
	    }

		public Iterable<Board> neighbors()     // all neighboring boards
	    {
			initializeNeighbor();	
			return new IterableClass();
	    }
	    
	    public String toString()               // string representation of this board (in the output format specified below)
	    {
	    	String s = new String();
	    	for (int i = 0 ; i < boardDimention; i++)
	    	{
	    		for (int j = 0 ; j < boardDimention; j++)
	    		{
	    			s += (board[i][j] + " ");	
	    		}
	    		s += "\n";
	    		
	    	}
	    	s += "\n";
	    	return s;
	    }
	    
//	    public String stringifyGoal()               
//	    {
//	    	String s = new String();
//	    	for (int i = 0 ; i < boardDimention; i++)
//	    	{
//	    		for (int j = 0 ; j < boardDimention; j++)
//	    		{
//	    			s += (goalBoard[i][j] + " ");	
//	    		}
//	    		s += "\n";
//	    		
//	    	}
//	    	s += "\n";
//	    	return s;
//	    }
	    
//	    public static void testIterator()
//	    {
//	    	Board bo1 = new Board(new int[][] {{0, 1, 2},  {4,5,3}, {7,8,6}});
//	    	      
//	    	Iterable ib = bo1.neighbors();
//	    	Iterator it = ib.iterator();
//	    	while (it.hasNext())
//	    	{
//	    		System.out.println(it.next().getClass());
//	    	}
//	    	
//	    }

//	    public static void testManhattan()
//	    {
//	    	Board bo1 = new Board(new int[][] {{1, 2, 3},  {4,5,6}, {0,7,8}});
//	    	System.out.println(bo1.toString());
//	    	System.out.println(bo1.manhattan());
//	    	System.out.println(bo1.hamming());
//	    }
//	    
	    
	    public static void main(String[] args) // unit tests (not graded)
	    {

	    	
//	    	Board bo = new Board(new int[][] {{1,2},  {3,4}});
//	    	Board co = new Board(new int[][] {{1,2},  {3,4}});
//	    	System.out.println(bo.toString().equals(co.toString()));
//	    	//System.out.println(a[0] == b[0]);
//	    	//testBoard();
//	    	//testMinPQ();
//	    	//testIterator();
//	    	//testManhattan();
//	    	Board b = new Board(new int[][] {{0, 1, 2},  {4,5,3}, {7,8,6}});
//	    	//Board b = new Board(new int[][] {{1, 2, 3},  {4,5,6}, {7,8,0}});
//	    	System.out.println(b);
//	    	System.out.println(b.twin());
//	    	
//	    	Iterable<Board> ib = b.neighbors();
//	    	Iterator<Board> it = ib.iterator();
//	    	
//	    	while (it.hasNext())
//	    	{
//	    		System.out.println(it.next());
//	    	}
//	    	
	    	
	    }
}
