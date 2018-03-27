package utils.datastructures;

import static utils.print.Print.print1DArray;
import static utils.print.Print.print1DArrayGeneric;
import static utils.print.Print.print1DArrayAttrGeneric;
import static utils.print.Print.println;

import java.util.Arrays;
import java.util.Comparator;


/*
 	Here we want to discuss the Array.sort of java. By default, it will try to sort something with the default order. This works for int[] or String[].
 	    See test1();
 	
 	However, when we want to sort something with our own order, or we want to sort something that doesn't have an order, then we need to implement the 
 Comparable interface.
 
 		See test2();
 */



public class D_Sort {

	private static class Element implements Comparable<Element>
	{
		String name;
		int    number;
		public static   Comparator<Element> SortByName = new ByName();
		public static  Comparator<Element> SortByNumber = new ByNumber();
		
		@Override
		public int compareTo(Element elePara) {
			
			     if (number <  elePara.number) return -1;
			else if (number > elePara.number)  return 1;   
			else							   return 0;
		}
		
		private static class ByName implements Comparator<Element>
		{
			public int compare(Element arg0, Element arg1) 
			{
				return arg0.name.compareTo(arg1.name);
			}	
		}
		
		private static class ByNumber implements Comparator<Element>
		{
			public int compare(Element arg0, Element arg1) 
			{
				return (arg0.number - arg1.number);
			}	
		}		
	}
	
	public D_Sort() 
    {
		// TODO Auto-generated constructor stub
	}

	
	public static void test1()
	{
		/*
			At the beginning, we can try to use Array.sort to sort an array, including int, String, etc.
		  
		  
		 */
		println("-----------------\nstart of test1.");
		
		Integer[] arr  = new Integer[] {6,4,3,1,0,-192};
		print1DArrayGeneric(arr, 6);
		Arrays.sort(arr);
		print1DArrayGeneric(arr, 6);
		
		
		String[] strArr = new String[] {"cat", "dog", "apple"};
		print1DArrayGeneric(strArr, 3);
		Arrays.sort(strArr);
		print1DArrayGeneric(strArr, 3);
		
	}
	public static void test2()
	{
		/*
				But when it comes to a user defined type or when we want to sort the things in our order, 
			then we need to implement the Comparable interface. In this example, we implements the Comparable<Element> interface and then add the 
			compareTo method in order for the Array.sort() to correctly sort the Element[] array by the "number" field.
			
			
			  	private static class Element implements Comparable<Element>
				{
					String name;
					int    number;
					
					@Override
					public int compareTo(Element elePara) 
					{
		 				return conditionalInt;
					}
				}
			
			And this is not enough, we might want to sort either by the "name" field or by the "number" field. In this case, we need to implement the 
		comparator interface. A comparator interface has the method of 
			compare(Item a, Item b) which returns an integer.
		
			In order to do this, 
		
		 */
		
		println("-----------------\nstart of test2.");
		
		String[] strArray = new String[] {"amy", "mary", "john", "ben"};
		int   [] intArray = new int   [] {4,3,2,1};
		Element[] eleArray= new Element[4];

		// Create an Element[] array.
	    for (int i = 0; i < 4; i++)
        {
			Element instanceOfElement = new Element();
                    instanceOfElement.name   = strArray[i];
                    instanceOfElement.number = intArray[i]; 
            eleArray[i] = instanceOfElement;
        }	
	    print1DArrayAttrGeneric(eleArray, 4, "number");
	    print1DArrayAttrGeneric(eleArray, 4, "name");
	    
	    Arrays.sort(eleArray);
	    print1DArrayAttrGeneric(eleArray, 4, "number");
	    print1DArrayAttrGeneric(eleArray, 4, "name");	    
	    
	    Arrays.sort(eleArray, Element.SortByName);
	    print1DArrayAttrGeneric(eleArray, 4, "number");
	    print1DArrayAttrGeneric(eleArray, 4, "name");	 	    
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
		test2();
		
	}

}
