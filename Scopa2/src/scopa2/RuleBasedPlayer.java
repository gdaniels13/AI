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
public class RuleBasedPlayer extends Player
{

	public RuleBasedPlayer(String name) {
		super(name);
	}

	
	@Override
	public Move makeMove(ArrayList<Card> myCards, ArrayList<Card> centerCards, Deck d, int opponentCardCount) 
	{
		ArrayList<Move> moves = getValidMoves(myCards, centerCards);
		
		Move max = moves.get(0);
		for (Move move : moves) {
			if(isScopa(move,centerCards))
				move.value+=200;
			if(max.value < move.value)
			{
				max = move;
			}
		}
		return max;
	}

	//seven +1
	//sette bello +5
	//soldi +1 per soldi
	//cards +1 per card

	
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
