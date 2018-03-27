import java.util.Comparator;
//import static utils.print.Print.print;
//import static utils.print.Print.println;
import java.util.Iterator;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class Solver 
{
	private Board imageBoard;
	private Board initialBoard;
	private int boardDimension;
	private ComparableBoard answer = null;
	private boolean canSolve;
	private Stack<Board> qOfAnswers = null;
	private int moves = -1;
	
	private class IterableClass implements Iterable<Board>
	{
		private Iterator<Board> it = qOfAnswers.iterator();
		@Override
		public Iterator<Board> iterator() {
			// TODO Auto-generated method stub
			return it;
		}
	}
    public Iterable<Board> solution()      // sequence of boards in a shortest solution; null if unsolvable
    {
    	return new IterableClass();
    }
    
	
	// This wrap a board into a Comparable class. Also it store the previous node.
	private static class ComparableBoard implements Comparable<ComparableBoard>
	{
		ComparableBoard previous = null;
		public int wastedMoves = 0;
		public Board board;
		public ComparableBoard(Board boardPara)
		{
			board = boardPara;
		}
		
		public int compareTo(ComparableBoard comPara) 
		{
			if (getDistance() < comPara.getDistance())
			{
				return -1;	
			}
			else if (getDistance() > comPara.getDistance())
			{
				return 1;
			}
			else
			{
				return 0;
			}
		}
		public int getDistance()
		{
			return board.manhattan() +  board.hamming() + wastedMoves;
		}
	}
    
	// Get the twin board.
	private void initializeImageBoard()
    {
        imageBoard = initialBoard.twin();
    }
	/*
	
	 This solves the board. Since either of the original or twin can be solved, so here we
	Implements 2 MinPQ to simutaneously run the A* algs.
	
	 
	*/
	private void solving()
	{
		
		ComparableBoard curComparable   = new ComparableBoard(initialBoard);
		MinPQ<ComparableBoard> minHeap = new MinPQ<ComparableBoard>();
		minHeap.insert(curComparable);

		ComparableBoard curImageComparable   = new ComparableBoard(imageBoard);
		MinPQ<ComparableBoard> minImageHeap = new MinPQ<ComparableBoard>();
		minImageHeap.insert(curImageComparable);		
		
    	while (true)
    	{
    		ComparableBoard minComparable = minHeap.delMin();
    		ComparableBoard minImageComparable = minImageHeap.delMin();
    		

        		if (minComparable.board.isGoal() ) 
        		{
        			answer = minComparable;
        			canSolve = true;
        			break;
        		}
        		else if ( minImageComparable.board.isGoal())
        		{
        			canSolve = false;
        			break;
        		}
        		else
        		{
                	Iterable<Board> ib = minComparable.board.neighbors();
                	Iterator<Board> it = ib.iterator();
                	int moves = minComparable.wastedMoves + 1;
        	
                	Iterable<Board> ibImage = minImageComparable.board.neighbors();
                	Iterator<Board> itImage = ibImage.iterator();
                	int movesImage = minImageComparable.wastedMoves + 1;              	
                	
            		
                	while (it.hasNext())
                	{
                		
                		Board newBoard = it.next();
                		ComparableBoard newCompare = new ComparableBoard(newBoard);
                		newCompare.wastedMoves += moves;
                		newCompare.previous = minComparable;
                		minHeap.insert(newCompare);
                	}  
                	
                	while (itImage.hasNext())
                	{
                		Board newImageBoard = itImage.next();
                		ComparableBoard newImageCompare = new ComparableBoard(newImageBoard);
                		newImageCompare.wastedMoves += movesImage;
                		newImageCompare.previous = minImageComparable;
                		minImageHeap.insert(newImageCompare);
                	}   
        		}
    	}
	}
	
	private void initializeIterator()
	{
		if (qOfAnswers == null)
		{
			qOfAnswers = new Stack<Board>();
		}
		if (isSolvable())
		{
			while (answer != null)
			{
				qOfAnswers.push(answer.board);
				moves++;
				answer = answer.previous;
			}
		}
	}
	
    public Solver(Board initial)           // find a solution to the initial board (using the A* algorithm)
    {
    	initialBoard =initial;       
    	boardDimension = initial.dimension();
    	initializeImageBoard();
    	solving();
    	initializeIterator();
        
    
    }
    
    public boolean isSolvable()            // is the initial board solvable?
    {
    	return canSolve;
    }
    
    public int moves()                     // min number of moves to solve initial board; -1 if unsolvable
    {
    	return moves;
    }
    
    public static void main(String[] args) // solve a slider puzzle (given below)
    {
//    	Board b = new Board(new int[][] {{0, 1, 2},  {4,5,3}, {7,8,6}});
//    	
//    	Solver s = new Solver(b);
//    	
//    	System.out.println(s.minHeap.size());
//    	testSolver();
    	//testComparableBoard();
    }
}

/*
	    At the very beginning we need to discuss A* algorithm.
	    
	    Considering that we are now standing at the source, and we want to find a path to the destination. In
	order to simplify the problem, we slice the map into right rectangles. in this case, we can travel the map,
	box by box until we reach/cannot reach the destination. 
	
	    For example, if we are at box A, it has neighbours of An1, An2, etc, then we can continue to 
	check the neighbors of An1, An2, that is "neighbours of the neighbours", and "neighbours of the neighbours
	of the neighbors", by this way, we can reach any box that reachable from the start. Since we can reach any
	reachable box, it means that now we can determine whether we can reach the destination or not.
	
	there are following features of this problem:
	
		1. At any box, we have neighboring boxes.
		2. At any box, we can count the least steps that we need to use to reach the destination.
	
	    So, first we need an algorithm to get the neighbors, also we need an algorithm to count the least steps
	that we need to reach the destination.
	
	    That's all about A* algorithm.
*/

//public static void testComparableBoard()  
//{
//	
//	Board b1 = new Board(new int[][] {{0, 1, 2},  {4,5,3}, {7,8,6}});
//	Board b2 = new Board(new int[][] {{0, 1, 2},  {4,5,3}, {8,7,6}});
//	Board b3 = new Board(new int[][] {{0, 1, 2},  {4,3,5}, {7,8,6}});
//	Board b4 = new Board(new int[][] {{2, 1, 0},  {4,5,3}, {7,8,6}});
//
//	
//	ComparableBoard c1 = new ComparableBoard(b1);
//	ComparableBoard c2 = new ComparableBoard(b2);
//	ComparableBoard c3 = new ComparableBoard(b3);
//	ComparableBoard c4 = new ComparableBoard(b4);
//	
//	print(b1.toString());
//	print(b1.stringifyGoal());
//	println(b1.hamming());
//	println(b1.manhattan());
//	
//	print(c1.getDistance());
//	print(c2.getDistance());
//	print(c3.getDistance());
//	print(c4.getDistance());
//}
//public static void testSolver()
//{
//	//Board b = new Board(new int[][] {{1, 2, 3},  {4,5,6}, {7,0,8}});
//	Board b = new Board(new int[][] {{0, 1, 2, },  {5,4,3}, {6,8,7}});
//	Solver s = new Solver(b);
//	
//	Iterable ib = s.solution();
//	Iterator it = ib.iterator();
//	
//	
//	
//	while (it.hasNext())
//	{
//		println(it.next());
//	}
//	
//}

