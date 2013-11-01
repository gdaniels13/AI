/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package scopa2;

import java.util.ArrayList;

/**
 *Greg Daniels
 *gdaniels13@gmail.com
 *A00798340
 */
public class Tester {

	
	
	
	public static void main(String[] args)
	{
		RuleBasedPlayer t = new RuleBasedPlayer("tester");
		
		Deck deck = new Deck();
		
		deck.shuffle();
		
		ArrayList<Card> myHand = new ArrayList<>();
		ArrayList<Card> centerBoard= new ArrayList<>();
		
		for(int i = 0; i<5; ++i)
		{
			centerBoard.add(deck.nextCard());
		}
		
		
		for(int i =0; i<3; ++i)
		{
			myHand.add(deck.nextCard());
		}
		
		
		ArrayList<Move> moves = t.getValidMoves(myHand, centerBoard);
		
		System.out.println("My Hand: " + myHand);
		System.out.println("center : " + centerBoard);
		
		for (Move move : moves) {
			System.out.println(move);
		}
	}
	
}
