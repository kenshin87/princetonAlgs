package utils.random;
import static utils.print.Print.print;
import static utils.print.Print.print1DArray;

public class Random {

	static java.util.Random systemRandom = new java.util.Random();

	
	public static int getInt()
	{
		return systemRandom.nextInt();
	}

	public static int getInt(int max)
	{
		return systemRandom.nextInt(max);
	}
	
	public static int getInt(int min, int max)
	{
		return systemRandom.nextInt(max - min + 1) + min;
	}
	
	public static void fisherShuffle(int[] array, int size)
	{
		// This is a fisher algorithm that finally transfer the array to a new random sequence.
		for (int i = size - 1; i > 0; i--)
		{
			int ramdomNumber = getInt(i + 1);
			int temp = array[ramdomNumber];
			array[ramdomNumber] = array[i];
			array[i] = temp;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int array[] = new int[] {1,2,3,4,5,6,7,8};
		print1DArray(array, 8);
		fisherShuffle(array, 8);
		print1DArray(array, 8);
	}

}
