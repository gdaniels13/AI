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
public abstract class Player {
	
	String name;
	CapturedCards cc;
	int totalScore;
	
	
	
	
	Player(String name)
	{
		cc = new CapturedCards(name);
		this.name = name;
	}
	
	public abstract Move makeMove(ArrayList<Card> myCards,ArrayList<Card> centerCards ,Deck d, int opponentCardCount);
	

	public ArrayList<Move> getValidMoves(ArrayList<Card> myCards,ArrayList<Card> centerCards)
	{
		ArrayList<Move> moves = new ArrayList<>();
		
		for(Card myCard: myCards)
		{
			generateMoves(myCard, new ArrayList<Card>(), centerCards, moves, 0);
		}
		
		for (Card card : myCards) {
			Move t = new Move(card, null);
			t.value = -rateMove(t);
			moves.add(t);
		}
		
		return moves;
	}

	private int sumCards(ArrayList<Card> c)
	{
		int sum = 0;
		for (Card card : c) {
			sum +=card.value;
		}
		return sum;
	}
	
	private void generateMoves(Card myCard,ArrayList<Card> CurrentMove, ArrayList<Card> centerCards,ArrayList<Move> moves, int moveSum) {
		if(moveSum>myCard.value)
		{
//			centerCards.add(CurrentMove.get(CurrentMove.size()-1));
//			CurrentMove.remove(CurrentMove.size()-1);
			return;
		}
		else if(moveSum == myCard.value)
		{
//			centerCards.add(CurrentMove.get(CurrentMove.size()-1));
			Move m = new Move(myCard, new ArrayList<>(CurrentMove));
			rateMove(m);
			moves.add(m);
//			CurrentMove.remove(CurrentMove.size()-1);
			return;
		}
		
		//we are under the value of MyCard
		for (Card card :centerCards) {
			if(!card.selected)
			{
				CurrentMove.add(card);
				card.selected = true;
				moveSum += card.value;
				generateMoves(myCard, CurrentMove, centerCards, moves, moveSum);
				moveSum -= card.value;
				CurrentMove.remove(card);
				card.selected = false;
			}
		}
	}
	
	public abstract int rateMove(Move move);
	
	public void clear()
	{
		cc = new CapturedCards(name);
	}
	
	public void updateScore()
	{
		totalScore +=cc.getPoints();
	}
	
}
