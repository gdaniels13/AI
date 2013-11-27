/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Players;

import java.util.ArrayList;

import CardManagement.*;


/**
 * Greg Daniels
 * gdaniels13@gmail.com
 * A00798340
 */
public class MinMaxEndGame extends Player
{

	public MinMaxEndGame(String name)
	{
		super(name);
	}


	@Override
	public Move makeMove(CardHolder myCards, CardHolder centerCards, Deck d, CardHolder opponentCards)
	{
		if(d.deck.isEmpty())
		{
			return endGame(myCards, centerCards, opponentCards);
		}


		ArrayList<Move> moves = getValidMoves(myCards.cards, centerCards.cards);
		Move max = moves.get(0);
		for(Move move : moves)
		{
			if(isScopa(move, centerCards.cards))
			{
				move.value += 200;
			}
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

	private boolean gameOver(CardHolder myCards, CardHolder opponentCards)
	{
		if(myCards.cards.isEmpty() && opponentCards.cards.isEmpty())
		{
			return true;
		}
		return false;
	}

	private Move endGame(CardHolder myCards, CardHolder centerCards, CardHolder opponentCards)
	{

		return maxMove(myCards, centerCards, opponentCards, null);
	}

	private Move maxMove(CardHolder myCards, CardHolder centerCards, CardHolder opponentCards, Move m)
	{
		if(gameOver(myCards, opponentCards))
		{
			m.value = cc.getPoints();
			return m;
		}
		else
		{
			ArrayList<Move> moves = getValidMoves(myCards.cards, centerCards.cards);

			Move max = moves.get(0);
			max.value = Integer.MIN_VALUE;

			for(Move move : moves)
			{
				makeMove(move, myCards, centerCards, cc);
				move.value = minMove(opponentCards, centerCards, myCards, move).value;
				if(move.value > max.value)
				{
					max = move;
				}
				undoMove(move, myCards, centerCards, cc);
			}
			return max;
		}
	}

	private Move minMove(CardHolder myCards, CardHolder centerCards, CardHolder opponentCards, Move m)
	{
		if(gameOver(myCards, opponentCards))
		{
			m.value = opponentCC.getPoints();
			return m;
		}
		else
		{
			ArrayList<Move> moves = getValidMoves(myCards.cards, centerCards.cards);

			Move min = moves.get(0);
			min.value = Integer.MAX_VALUE;

			for(Move move : moves)
			{
				makeMove(move, myCards, centerCards, opponentCC);
				move.value = maxMove(opponentCards, centerCards, myCards, move).value;
				if(move.value < min.value)
				{
					min = move;
				}
				undoMove(move, myCards, centerCards, opponentCC);
			}
			return min;
		}
	}


	private void makeMove(Move m, CardHolder playerHand, CardHolder centerCards, CapturedCards cc)
	{
		cc.addCard(m.myCard);
		if(m.centerCards != null)
		{
			cc.addCardVector(m.centerCards);
		}

		playerHand.remove(m.myCard);
		if(m.centerCards != null)
		{
			centerCards.remove(m.centerCards);
		}
		if(centerCards.cards.isEmpty())
		{
			cc.addScopa();
		}
	}

	private void undoMove(Move m, CardHolder playerHand, CardHolder centerCards, CapturedCards cc)
	{

		if(centerCards.cards.isEmpty())
		{
			cc.removeScopa();
		}

		playerHand.addCard(m.myCard);

		if(m.centerCards != null)
		{
			centerCards.addCard(m.centerCards);
		}

		cc.removeCard(m.myCard);
		if(m.centerCards != null)
		{
			cc.removeCardVector(m.centerCards);
		}
	}

	@Override
	public int rateMove(Move move)
	{
		int score = 0;
		score += move.myCard.score();

		if(move.centerCards != null)
		{
			for(Card card : move.centerCards)
			{
				score += card.score();
			}
		}
		move.value = score;
		return score;
	}

	private boolean isScopa(Move move, ArrayList<Card> centerCards)
	{
		if(move.centerCards == null)
		{
			return false;
		}

		for(Card card : centerCards)
		{
			if(!move.centerCards.contains(card))
			{
				return false;
			}
		}

		return true;
	}

}
