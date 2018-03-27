//import static utils.print.Print.println;
//import static utils.print.Print.print;

import java.util.Arrays;

public class BruteCollinearPoints {

	/*
	    [a1, a2, a3, a4, a5, a6, a7, a8, a9, a10]
	*/
	
    private int nOfSeg;
    private LineSegment[] collinearSeg = null;
    
    private class Node
    {
    	LineSegment nodeLineSeg;
    	Node next;
    }

	private Node printQuartic(Point[] pointsArray, int a)
	{
		nOfSeg = 0;
		Node first = null;
		Node pointer = null;

		/*
		     The following offers a method of iterating all the combination of C_n^k.
		 */
		for (int i = 0; i < a; i++)
		{
			for (int j = i + 1; j < a; j++)
			{
				for (int k = j + 1; k < a; k++)
				{
					for (int l = k + 1; l < a; l++)
					{
						double fstSlp = pointsArray[i].slopeTo(pointsArray[j]);
						double secSlp = pointsArray[j].slopeTo(pointsArray[k]);
						double trdSlp = pointsArray[k].slopeTo(pointsArray[l]);
						
						if ( (fstSlp == secSlp) && (secSlp == trdSlp))
						{
//							print(pointsArray[i]);
//							print(pointsArray[j]);
//							print(pointsArray[k]);
//							println(pointsArray[l]);
//							
//							println(fstSlp);
//							println(secSlp);
//							println(trdSlp);
							if (first == null)
							{
								first = new Node();
								first.nodeLineSeg = new LineSegment(pointsArray[i], pointsArray[l]);
								pointer = first;
							}
							else
							{
								pointer.next = new Node();
								pointer.nodeLineSeg = new LineSegment(pointsArray[i], pointsArray[l]);
								pointer = pointer.next;
							}
							nOfSeg++;
						}
					}
				}
			}
		}
		return first;
	}
    
    public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
    {
        if (points == null)
        {
            throw new java.lang.IllegalArgumentException();          
        }
        
        // Sort the points in order for the line seg to be sorted.
        Arrays.sort(points);
        
        Node first = printQuartic(points, points.length);
        
        collinearSeg = new LineSegment[nOfSeg];
        
        int index = 0;
        while (first != null)
        {
        	collinearSeg[index] = first.nodeLineSeg;
        	first = first.next;
        }
    }
    
    public int numberOfSegments()        // the number of line segments
    {
        return nOfSeg;
    }
    public LineSegment[] segments()                // the line segments
    {
    	return collinearSeg;
    }	
    
//    public static void test()
//    {
//    	Point[] ps = new Point[7];
//    	ps[0] = new Point(0, 0);
//    	ps[3] = new Point(1, 1);
//    	ps[2] = new Point(2, 2);
//    	ps[1] = new Point(3, 3);
//    	ps[4] = new Point(-1, 1);
//    	ps[5] = new Point(-2, 2);
//    	ps[6] = new Point(-3, 3);
//    	BruteCollinearPoints b = new BruteCollinearPoints(ps);
//    	
//    	println(b.nOfSeg);
//    }
    
    public static void main(String[] args)
    {
//    	test();
    }
}

