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
public class HumanPlayer extends Player{

	public HumanPlayer(String name) {
		super(name);
	}

	
	@Override
	public Move makeMove(ArrayList<Card> myCards, ArrayList<Card> centerCards, Deck d, int opponentCardCount) {
		return null;
	}

	
	@Override
	public int rateMove(Move move) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
	
}
