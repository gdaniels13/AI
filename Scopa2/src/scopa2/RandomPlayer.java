/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package scopa2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *Greg Daniels
 *gdaniels13@gmail.com
 *A00798340
 */
public class RandomPlayer extends Player {

	Random r;
	public RandomPlayer(String name) {
		super(name);
		r = new Random();
	}

	
	@Override
	public Move makeMove(ArrayList<Card> myCards, ArrayList<Card> centerCards, Deck d, int opponentCardCount) {
		ArrayList<Move> moves = getValidMoves(myCards, centerCards);
		//Collections.shuffle(moves);
		//return moves.get(0);
		return  moves.get(r.nextInt(moves.size()));
	}

	@Override
	public int rateMove(Move move) {
		return 0;
		
		}

}
