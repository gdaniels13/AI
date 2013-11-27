/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Players;

import java.util.ArrayList;

import CardManagement.*;

/**
 *Greg Daniels
 *gdaniels13@gmail.com
 *A00798340
 */
public class RuleBasedPlayer extends Player
{

	public RuleBasedPlayer(String name) {
		super(name);
	}

	
	@Override
	public Move makeMove(CardHolder myCards, CardHolder centerCards, Deck d, CardHolder opponentCards) {

		ArrayList<Move> moves = getValidMoves(myCards.cards, centerCards.cards);
		
		Move max = moves.get(0);
		for (Move move : moves) {
			if(isScopa(move,centerCards.cards))
				move.value+=200;
			if(max.value < move.value)
			{
				max = move;
			}
		}
		return max;
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


	@Override
	public int rateMove(Move move)
	{
		int score = 0;
		score+=move.myCard.score();
		
		if(move.centerCards != null)
		for (Card card : move.centerCards) {
			score+=card.score();		
		}
		move.value = score;
		return score;
	}

	private boolean isScopa(Move move, ArrayList<Card> centerCards) {
		if(move.centerCards == null) 
			return false;
		
		for (Card card : centerCards) {
			if(!move.centerCards.contains(card))
				return false;
		}
			
		return true;
	}
}
