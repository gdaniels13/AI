/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Players;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import CardManagement.*;

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
	public int rateMove(Move move) {
		return 0;
		
		}

	@Override
	public Move makeMove(CardHolder myCards, CardHolder centerCards, Deck d, CardHolder opponentCards) {
		ArrayList<Move> moves = getValidMoves(myCards.cards, centerCards.cards);
		return  moves.get(r.nextInt(moves.size()));

	}

	private Move mangle(GameState gs, boolean first)
	{
		if(first)
		{
			return makeMove(gs.player1hand,gs.centerCards,gs.deck,gs.player2hand);
		}
		else
		{
			return makeMove(gs.player2hand,gs.centerCards,gs.deck,gs.player1hand);
		}
	}

	@Override
	public Move makeMove(GameState gs, boolean first)
	{
		return mangle(gs,first);
	}

}
