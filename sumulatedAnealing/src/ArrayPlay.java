
import java.util.Scanner;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *Greg Daniels
 *gdaniels13@gmail.com
 *A00798340
 */
public class ArrayPlay {

	static int[] array;
	static Scanner console = new Scanner(System.in);
	
	
	
	public static void main(String [] args)
	{
		array = new int[5];
		
		for(int i = 0; i<array.length; ++i)
		{
			array[i] = console.nextInt();
		}
		
		for(int i = 0; i<array.length; ++i)
		{
			System.out.println(array[i]);
		}		
	}
	
	
}
