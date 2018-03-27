package utils.print;
import static utils.print.Print.println;

import java.lang.reflect.Field;

import utils.datastructures.LinkedListNode;


public class Print{

	public final static String print(Object objArg)
	{
		System.out.print(objArg);
		return objArg.toString();
	}
	
	public final static String println(Object objArg)
	{
		System.out.println(objArg);
		return objArg.toString();
	}
	
	public final static String returnStr()
	{
		Print.print("haha");
		return "haha";
	}
	
	public final static String printMain(Object objArg)
	{
		System.out.print("static void main of ");
		System.out.println(objArg);
		return objArg.toString();
	}

	public final static void print2DArray(int[][] intArray, int a, int b)
	{
		for (int i = 0; i < a; i++)
		{
			for (int j = 0; j < b; j++)
			{
				System.out.print(intArray[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public final static void print1DArray(int[] intArray, int a)
	{
		for (int i = 0; i < a; i++)
		{
				System.out.print(intArray[i] + " ");
		}
		System.out.println();
	}
	
	public final static<Item> void print1DArrayGeneric(Item[] itemArray, int a)
	{
		for (int i = 0; i < a; i++)
		{
				System.out.print(itemArray[i] + " ");
		}
		System.out.println();
	}
	
	public final static void printBinomial(int[] intArray, int a)
	{
		for (int i = 0; i < a; i++)
		{
				for (int j = i + 1; j < a; j++)
				{
					
					System.out.print(intArray[i]);
					System.out.print("&");
					System.out.print(intArray[j]);
					System.out.print(" ");
				}
		}
		System.out.println();
	}
	
	public final static void printCubic(int[] intArray, int a)
	{
		int n = 0;
		for (int i = 0; i < a; i++)
		{
				for (int j = i + 1; j < a; j++)
				{
					for (int k = j + 1; k < a; k++)
					{
						n++;
						System.out.print(intArray[i]);
						System.out.print("&");
						System.out.print(intArray[j]);
						System.out.print("&");
						System.out.print(intArray[k]);
						System.out.print(" ");
					}
				}
		}
		System.out.println();
		System.out.println(n);
	}
	
	public final static void printQuartic(int[] intArray, int a)
	{
		int n = 0;
		for (int i = 0; i < a; i++)
		{
				for (int j = i + 1; j < a; j++)
				{
					for (int k = j + 1; k < a; k++)
					{
						for (int l = k + 1; l < a; l++)
						{
							n++;
							System.out.print(intArray[i]);
							System.out.print("&");
							System.out.print(intArray[j]);
							System.out.print("&");
							System.out.print(intArray[k]);
							System.out.print("&");
							System.out.print(intArray[l]);
							System.out.print(" ");
						}
					}
				}
		}
		System.out.println();
		System.out.println(n);
	}
	
	
	public final static<Item> void print1DArrayAttrGeneric(Item[] itemArray, int a, String attr)
	{
		for (int i = 0; i < a; i++)
		{
			Item e = itemArray[i];
			Field field = null;
			
			try 
			{
				field = e.getClass().getDeclaredField(attr);
				field.setAccessible(true);
			} 
			catch (NoSuchFieldException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			catch (SecurityException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}    		
			
			Object obj = null;
			
			try {
				obj = field.get(e);
			} catch (IllegalArgumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.print(obj + "  ");
			
		}
		System.out.println();
	}
	
	public final static void printCon(Object objArg)
	{
		System.out.print("constructor of ");
		System.out.println(objArg);
	}	
	
	public final static int sum1DArray(int[] intArray, int a)
	{
		int sum = 0;
		for (int i = 0; i < a; i++)
		{
				sum += intArray[i];
		}
		return sum;
	}
	
	public final static double mean1DArray(int[] intArray, int a)
	{
		int sum = 0;
		for (int i = 0; i < a; i++)
		{
				sum += intArray[i];
		}
		return ((double)sum) / a;
	}
	public final static void printLL(LinkedListNode nodePara)
	{
		while (nodePara != null)
		{
			print(nodePara.value + "-->");
			nodePara = nodePara.next;
		}
	}
	
	public static void main(String[] args) 
	{
		printQuartic(new int[]{0,1,2,3,4,5, 6}, 7);
	}

	
	
}
