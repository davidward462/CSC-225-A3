/* 
 * CSC 225 - Assignment 3
 * Name: David Ward
 * Student number: V00920409
 */
 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;

public class ArrayMatch {
	
	
	/*
	 * compare
	 * Purpose: Determine if the two given arrays are equal
	 * Parameters: int[] a, int[] b - the two arrays
	 * Returns: boolean - true if the elements in both arrays are the same
	 * Preconditions: a and b have the same number of elements
	 */
	public static boolean equ(int[] a, int[] b)
	{
		int size = a.length;
		for(int i = 0; i<size; i++)
		{
			if(a[i] != b[i])
			{
				return false;
			}
		}
		return true;
	}
	
	//FOR TESTING
	public static void printArray(String text, int[] x)
	{
		System.out.print(text + " ");
		for(int i = 0; i<x.length; i++)
		{
			System.out.print(x[i]+", ");
		}
		System.out.println();
	}
	
	//FOR TESTING
	public static void pInt (int x)
	{
		System.out.println(x);
	}
	
	//FOR TESTING
	public static void ln ()
	{
		System.out.println();
	}
	
	//FOR TESTING
	public static void out (String s)
	{
		System.out.println(s);
	}
	
	/*
	 * match
	 * Purpose: Determine if the two given arrays 'match'
	 * Parameters: int[] a, int[] b - the two arrays
	 * Returns: boolean - true if arrays 'match', false otherwise
	 * Preconditions: a and b have the same number of elements
	 */
	public static boolean match(int[] a, int[] b) {
		
		// TODO complete the implementation
		boolean ret = false;
		int newSize = a.length/2;
		if (a.length%2 == 0)//size is even
		{
			//split (works as far as I can tell)
			int[] a1 = new int[newSize];
			int[] a2 = new int[newSize];
			int[] b1 = new int[newSize];
			int[] b2 = new int[newSize];
			
			java.lang.System.arraycopy(a, 0, a1, 0, newSize); 
			java.lang.System.arraycopy(a, newSize, a2, 0, newSize); 
			java.lang.System.arraycopy(b, 0, b1, 0, newSize); 
			java.lang.System.arraycopy(b, newSize, b2, 0, newSize); 
			/*
			printArray("a1:",a1);
			printArray("a2",a2);
			printArray("b1:",b1);
			printArray("b2",b2);
			*/
			//   first halves and 2nd halves        1st of a and both of b     2nd of a and both of b
			if(((equ(a1,b1))&&(equ(a2,b2))) || ((equ(a1,b1))&&(equ(a1,b2))) || ((equ(a2,b1))&&(equ(a2,b2))))
			{
				ret = true;
				return ret;
			}
			ret = match(a1,b1);
			
		}
		else//size is not even 
		{
			ret = equ(a,b);		
		}
		
		return ret;
	}

	/*
	 * fillArray
	 * Purpose: Fills arrays with contents read from Scanner
	 * Parameters: int[] x, Scanner fileReader
	 * Returns: nothing
	 */
	public static void fillArray(int[] x, Scanner fileReader) throws NoSuchElementException {
		Scanner f = new Scanner(fileReader.nextLine());
		for (int i = 0; i < x.length; i++) {
			x[i] = f.nextInt();
		}
	}
		
	/*
	 * a3Setup
	 * Purpose: Initializes the input arrays for Assignment 3 match detection
	 *          by reading data from the text file named fname
	 * Parameters: String fname - name of the file containig input data
	 * Returns: nothing
	 */
	public static void a3Setup(String fname) {
		Scanner fileReader = null;
		int[] A = null;
		int[] B = null;
		
		try {
			fileReader = new Scanner(new File(fname));
		} catch (FileNotFoundException e) {
			System.out.println("Error finding input file");
			e.printStackTrace();
			return;
		}
		
		try {
			int size = Integer.parseInt(fileReader.nextLine());
			A = new int[size];
			B = new int[size];
			fillArray(A, fileReader);
			fillArray(B, fileReader);
		} catch (NoSuchElementException e) {
			System.out.println("Error reading input file data");
			e.printStackTrace();
		}
		
		if (match(A,B)) {
			System.out.println("match found");
		} else {
			System.out.println("no matches");
		}
	}
	
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Incorrect usage, should be:");
			System.out.println("java MysteryArray filename.txt");
			return;
		}
		a3Setup(args[0]);
	}
}