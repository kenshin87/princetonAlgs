import static utils.print.Print.println;
import static utils.print.Print.print1DArrayGeneric;
import java.util.Arrays;
import java.util.Comparator;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {

	private Node head = null;
	private Node pointer = null;
	
    private int nOfSeg;
    private LineSegment[] collinearSeg = null;
    
    private class Node
    {
    	LineSegment nodeLineSeg;
    	Node next;
    	double distance;
    	Point p = null;
    	Point q = null;

    	public double getSlope()
    	{
    		return p.slopeTo(q);
    	}
    	
    }
    

    private void addNode(Node NodePara)
    {
		if (head == null)
		{
			head = NodePara;
			pointer = head;
		}
		else
		{
			
			pointer.next = NodePara;
			pointer = pointer.next;
		}
    }
    
//    private void addNode(Node nodePara)
//    {
//		if (head == null)
//		{
//			head = nodePara;
//			pointer = head;
//		}
//		else
//		{
//			println("else");
//			pointer = head;
//			while (pointer != null)
//			{
//				if (nodePara.getSlope() == pointer.getSlope())
//				{
//					if (nodePara.getSlope() == nodePara.p.slopeTo(pointer.p))
//					{
//						if (nodePara.p.compareTo(pointer.p) == -1)
//						{
//							pointer.p = nodePara.p;
//							pointer.q = nodePara.q;
//							pointer.nodeLineSeg = nodePara.nodeLineSeg;
//							break;
//						}
//						else
//						{
//							// do nothing
//						}
//					}
//				}
//				if (pointer.next != null)
//				{
//				    pointer = pointer.next;
//				}
//				else if (pointer.next == null)
//				{
//					break;
//				}
//			}
//			pointer.next = nodePara;
//			pointer = pointer.next;	
//		}
//    }
//    
    public FastCollinearPoints(Point[] points)     // finds all line segments containing 4 or more points
    {
    	nOfSeg = 0;
    	
        if (points == null)
        {
            throw new java.lang.IllegalArgumentException();          
        }
        // Sort the points in order for the line seg to be sorted.
        Arrays.sort(points);
        
//        print1DArrayGeneric(points, 7);
        
        // Then we try call the following in order to get all the result.
        // At this point, we have the linkedlist head that stores all the LineSegments.
        runResult(points, points.length); 
        
        // Then we transfer all the nodes into the array.
        collinearSeg = new LineSegment[nOfSeg];
        getLineSegmentArray();
    }
    
    private void runResult(Point[] pointsArray, int lengthOfPointsArray)
    {
    	for (int i = 0; i < lengthOfPointsArray; i++)
    	{
    		getLineForP(pointsArray, lengthOfPointsArray, i);
    	}
    	getLineForP(pointsArray, lengthOfPointsArray, 0);
    }
    

   
    private void getLineForP(Point[] pointsArray, int lengthOfPointsArray, int index)
    {
    	Point originalPoint = pointsArray[index];
    	Arrays.sort(pointsArray, originalPoint.slopeOrder());
    	
    	Point[] restArray = Arrays.copyOfRange(pointsArray, 1, lengthOfPointsArray);
    	//print1DArrayGeneric(restArray, 6);
    	
    	int newLength = restArray.length;
		int startIndex = 0;
		int endIndex  = 0;
		double slopeStorage;
		
        slopeStorage = originalPoint.slopeTo(restArray[startIndex]);
        
        for (int i = 0; i < newLength; i++)
		{
        	
			if (originalPoint.slopeTo(restArray[i]) != slopeStorage)
			{
				if (endIndex - startIndex >= 3)
				{
					
					    Point[] sortedArray = Arrays.copyOfRange(restArray, startIndex, endIndex); 
					    Arrays.sort(sortedArray);
					    
					    
					
						Node oneNode = new Node();
						Point rightPoint = null;
					     if  (originalPoint.compareTo(sortedArray[0]) == -1 )
					     {
					    	 rightPoint = originalPoint;
					    	 oneNode.nodeLineSeg = new LineSegment(rightPoint, sortedArray[sortedArray.length-1]);
					     }
					     else
					     {
					    	 rightPoint = restArray[startIndex];
					    	 oneNode.nodeLineSeg = new LineSegment(rightPoint, sortedArray[sortedArray.length-1]);
					     }
						 
						 oneNode.p = rightPoint;
						 oneNode.q = restArray[endIndex - 1];
					
					addNode(oneNode);
					nOfSeg++;
				}
				else if (endIndex - startIndex < 3)
				{
					// Nothing going on here.
				}
				startIndex = endIndex;
				slopeStorage = originalPoint.slopeTo((restArray[endIndex]));
			}
			else if (originalPoint.slopeTo(restArray[i]) == slopeStorage)
			{
				// do nothing;
			}
			endIndex++;
		}
		if (endIndex - startIndex >= 3)
		{
			Node oneNode = new Node();
			Point rightPoint = null;
		     if  (originalPoint.compareTo(restArray[startIndex]) == -1 )
		     {
		    	 rightPoint = originalPoint;
		    	 oneNode.nodeLineSeg = new LineSegment(rightPoint, restArray[endIndex - 1]);
		     }
		     else
		     {
		    	 rightPoint = restArray[startIndex];
		    	 oneNode.nodeLineSeg = new LineSegment(rightPoint, restArray[endIndex - 1]);
		     }
			 
			 oneNode.p = rightPoint;
			 oneNode.q = restArray[endIndex - 1];
			 
		    addNode(oneNode);
		    nOfSeg++;
		}
    }
    
	   public           int numberOfSegments()        // the number of line segments
	   {
		   return nOfSeg;
	   }
	   public LineSegment[] segments()                // the line segments
	   {
		   return collinearSeg;
	   }
	   
	    private void getLineSegmentArray()
	    {
	    	// Transfer all the nodes into the array.
	        pointer = head;
	        int index = 0;
	        while (pointer != null)
	        {
	        	collinearSeg[index] = pointer.nodeLineSeg;
	        	index++;
	        	pointer = pointer.next;
	        }
	    }
	   
	    public static void test()
	    {
	    	Point[] ps = new Point[7];
	    	ps[6] = new Point(0, 0);
	    	ps[5] = new Point(1, 1);
	    	ps[4] = new Point(2, 2);
	    	ps[3] = new Point(3, 3);
	    	ps[2] = new Point(4, 4);
	    	ps[1] = new Point(5, 5);
	    	ps[0] = new Point(6, 6);
	    	
	    	
	    	FastCollinearPoints b = new FastCollinearPoints(ps);
	    	
	    	
	    	printSlope(b.collinearSeg);
	    	println(b.nOfSeg);
	    }
	    
	    public static void printSlope(LineSegment[] ls)
	    {
	    	for (int i = 0; i < ls.length; i++) 
	    	{
	    		System.out.print(ls[i]); 
	    		System.out.print("  " ); 
	    	}
	    	System.out.println("  ");
	    }	    
	    
	    public static void main(String[] args)
	    {
	    	test();
//	        // read the n points from a file
//	        In in = new In("/home/kenshin/Downloads/collinear/equidistant.txt");
//	        int n = in.readInt();
//	        Point[] points = new Point[n];
//	        for (int i = 0; i < n; i++) {
//	            int x = in.readInt();
//	            int y = in.readInt();
//	            points[i] = new Point(x, y);
//	        }
//
//	        // draw the points
//	        StdDraw.enableDoubleBuffering();
//	        StdDraw.setXscale(0, 32768);
//	        StdDraw.setYscale(0, 32768);
//	        for (Point p : points) {
//	            p.draw();
//	        }
//	        StdDraw.show();
//
//	        // print and draw the line segments
//	        FastCollinearPoints collinear = new FastCollinearPoints(points);
//	        for (LineSegment segment : collinear.segments()) {
//	            StdOut.println(segment);
//	            segment.draw();
//	        }
//	        StdDraw.show();
	    	
	    }
	}

