/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package scopa2;

import java.io.Console;
import java.util.ArrayList;

/**
 *Greg Daniels
 *gdaniels13@gmail.com
 *A00798340
 */
public class RandomPlayer extends Player {

	public RandomPlayer(String name) {
		super(name);
	}

	
	@Override
	public Move makeMove(ArrayList<Card> myCards, ArrayList<Card> centerCards, Deck d, int opponentCardCount) {
		ArrayList<Move> moves = getValidMoves(myCards, centerCards);
		
		Move myMove; 
		if(moves.isEmpty())
		{
			myMove = new Move(myCards.get((int)Math.random()*myCards.size()), null);
		}
		else
			myMove = moves.get((int)Math.random()*moves.size());
		
		System.out.println(name + " " + myMove);
		
		return myMove;
		
	}
	

}
