package Players;

import CardManagement.*;

import java.util.ArrayList;
import java.util.Random;


public class RuleBasedMCTS extends  Player
{

	private CapturedCards player1cc, player2cc;

	public RuleBasedMCTS(String name, int playouts)
	{
		super(name);
		this.playouts = playouts;
	}

	@Override
	public Move makeMove(CardHolder myCards, CardHolder centerCards, Deck d, CardHolder opponentCards)
	{
		return null;
	}

	private void backupGS(GameState gs){

		gs.player1.cc = new CapturedCards(player1cc);
		gs.player2.cc = new CapturedCards(player2cc);
	}

	private void setupGS(GameState gs)
	{
		player1cc = gs.player1.cc;
		player2cc = gs.player2.cc;
	}


	private void restoreGS(GameState gs){
		gs.player1.cc = player1cc;
		gs.player2.cc = player2cc;

	}


	@Override
	public Move makeMove(GameState gs, boolean player1)
	{
		setupGS(gs);
		ArrayList<Move> moves;
		if(player1)
		{
			moves = getValidMoves(gs.player1hand.cards, gs.centerCards.cards);
		}
		else
		{
			moves = getValidMoves(gs.player2hand.cards,gs.centerCards.cards);
		}

		int[] ratings = new int[moves.size()];
		for(int i = 0; i<ratings.length; ++i)
		{
			ratings[i] = 0;
		}

		int count = 0;
		for(Move move : moves)
		{
			for(int i = 0; i< playouts; ++i)
			{
				backupGS(gs);
				ratings[count]+=  rollOut(gs, player1, move);
			}
			count++;
		}

		int max = 0;
		for(int i = 0; i<ratings.length; ++i){
			if(ratings[max]<ratings[i])
			{
				max = i;
			}
		}

		restoreGS(gs);
		return moves.get(max);
	}

	private void applyMove(GameState newGS, Move move, boolean player1)
	{
		Player player;
		CardHolder pCards;
		if(player1)
		{
			player = newGS.player1;
			pCards = newGS.player1hand;
		}
		else
		{
			pCards = newGS.player2hand;
			player = newGS.player2;
		}

		if(move.centerCards ==null){

			newGS.centerCards.addCard(move.myCard);
		}
		else{
			newGS.lastToTake = player;
			newGS.centerCards.remove(move.centerCards);
			player.cc.addCard(move.myCard);
			player.cc.addCardVector(move.centerCards);
			if(newGS.centerCards.cards.isEmpty())
			{
				player.cc.addScopa();
			}
		}
		pCards.remove(move.myCard);

	}

	private int rollOut(GameState gs, boolean player1, Move move)
	{
		GameState newGS = new GameState(gs);
		newGS.deck.shuffle();
		applyMove(newGS, move, player1);

		int turns = turnsLeft(newGS);
		Move randomMove;

		for(int i = 0; i<turns; ++i )
		{
			if(player1)
			{
				if(newGS.player1hand.cards.isEmpty()){
					newHand(newGS);
				}
				randomMove = getBestMove(newGS, newGS.player1hand);
				applyMove(newGS, randomMove, true);
				randomMove = getBestMove(newGS, newGS.player2hand);
				applyMove(newGS, randomMove, false);

			}
			else
			{
				if(newGS.player2hand.cards.isEmpty()){
					newHand(newGS);
				}
				randomMove = getBestMove(newGS, newGS.player2hand);
				applyMove(newGS, randomMove, false);
				randomMove = getBestMove(newGS, newGS.player1hand);
				applyMove(newGS, randomMove, true);
			}
		}

		newGS.lastToTake.cc.addCardVector(gs.centerCards.cards);

		if(player1)
		{
			return newGS.player1.cc.getPoints();
		}
		else
		{
			return newGS.player2.cc.getPoints();
		}
	}

	private void newHand(GameState gs)
	{
		for(int i = 0; i<3; ++i){
			gs.player1hand.addCard(gs.deck.nextCard());
			gs.player2hand.addCard(gs.deck.nextCard());
		}
	}

	private Move getBestMove(GameState newGS, CardHolder player1hand)
	{
		ArrayList<Move> m = getValidMoves(player1hand.cards,newGS.centerCards.cards);
		int r;
		Move best = m.get(0);
		if(m.size() == 0)
		{
			r = 0;
		}
		else
		{
			best = m.get(0);
			rateMove(best);
			for(Move move:m)
			{
				if(best.value < rateMove(move))
				{
					best = move;
				}
			}
		}
		return best;
	}

	private int turnsLeft(GameState gs)
	{
		int turns = gs.deck.cardsLeft() /2;

		turns += Math.min(gs.player1hand.cards.size(), gs.player2hand.cards.size());
		return turns;
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
}

