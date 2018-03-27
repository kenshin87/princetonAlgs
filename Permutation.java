import edu.princeton.cs.algs4.StdIn;

public class Permutation 
{

	public static void main(String[] args) 
	{
            
            RandomizedQueue<String> r = new RandomizedQueue<String>();
            while (!StdIn.isEmpty())
            {
                r.enqueue(StdIn.readString());
            }
            
            int number = Integer.parseInt(args[0]);
            while (number>0)
            {
                System.out.println(r.sample());
                number--;
            }
	}
	
}
