/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scopa2;

import Players.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author gregor
 */
public class Scopa2 {

	/**
	 * @param args the command line arguments
	 */
//	static Scanner in = new Scanner(System.in);
	static Scanner in;
	static PrintStream out;


	static Player player1;
	static Player player2;
	static int gameCount ;
	
	public static void main(String[] args) {

		String filename = "ScopaTest";
		if(args.length>0)
			filename = args[0];
			try
			{
				in = new Scanner(new FileReader(filename));
				out = new PrintStream(new FileOutputStream("output"+ new SimpleDateFormat("ddMMyyyyHHmm").format(new Date()) + ".txt"));
			}
			catch(FileNotFoundException e)
			{
				e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
			}





		int count = 1;
		while(in.hasNext())
		{
		player1= getPlayer();
		player2= getPlayer();
		gameCount = getGameCount();
		out.println("Game " + count);
		run();
			count++;
		}
	}
	
	static void run()
	{
		MainTable t = new MainTable();
		t.simulate(player1, player2,gameCount);
		out.println(player1);
		out.println(player2);
		out.flush();
	}

	private static int getGameCount() {
		//System.out.println("Number of games to simulate:");
		return in.nextInt();
	}
	
	static Player getPlayer()
	{
		//System.out.println("Enter the type of player:");
		//System.out.println("\t1:Random\n\t2:RuleBased\n\t3:MinMax");
		do{
			switch(in.nextInt())
			{
				case 1:
					return new RandomPlayer("Random");
				case 2:
					return new RuleBasedPlayer("RuleBased");
				case 3:
					return new MinMaxEndGame("MinMax");
				case 4:
					return new Interactive("Human");
				case 5:
				{
					int temp = in.nextInt();
					return new RandomMCTS("randomMCTS "+temp, temp);
				}
				case 6:
				{
					int temp = in.nextInt();
					return new RandomMCTS("RuleBasedMCTS " + temp, temp);
				}
				default:

			}
		}
		while(true);
			
		
		
	}
}
