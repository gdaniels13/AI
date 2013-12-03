
package Players;

import java.util.ArrayList;
import CardManagement.*;

/**
 *Greg Daniels
 *gdaniels13@gmail.com
 *A00798340
 */
public abstract class Player{
	
	public String name;
	public CapturedCards cc, opponentCC;
	public int playouts;

	public ScoreKeeper scoreKeeper;
	
	
	Player(String name)
	{
		scoreKeeper = new ScoreKeeper();
		cc = new CapturedCards(name);
		this.name = name;
	}

	Player(Player player)
	{
		this.name = player.name;
		this.cc = new CapturedCards(player.cc);
		this.opponentCC = new CapturedCards(player.opponentCC);
		this.scoreKeeper = new ScoreKeeper(player.scoreKeeper);

	}
	
	public abstract Move makeMove(CardHolder myCards,CardHolder centerCards ,Deck d, CardHolder opponentCards);


	public abstract Move makeMove(GameState gs, boolean first);


	public ArrayList<Move> getValidMoves(ArrayList<Card> myCards,ArrayList<Card> centerCards)
	{
		ArrayList<Move> moves = new ArrayList<>();
		
		for(Card myCard: myCards)
		{
			generateMoves(myCard, new ArrayList<Card>(), centerCards, moves, 0,0);
		}

		//removeDuplicates(moves);


		for (Card card : myCards) {
			Move t = new Move(card, null);
			t.value = -rateMove(t);
			moves.add(t);
		}
		return moves;
	}

	protected void removeDuplicates(ArrayList<Move> moves)
	{
		ArrayList<Move> temp = new ArrayList<>(moves);

		for(Move move : temp)
		{
			while(moves.remove(move));
			moves.add(move);
		}
	}

	private boolean hasDuplicate(Move m, ArrayList<Move> moves)  {


		return false;
	}
	
	private void generateMoves(Card myCard,ArrayList<Card> CurrentMove, ArrayList<Card> centerCards,ArrayList<Move> moves, int moveSum, int centerCardPos) {
		if(moveSum>myCard.value)
		{
			return;
		}
		else if(moveSum == myCard.value)
		{
			Move m = new Move(myCard, new ArrayList<>(CurrentMove));
			rateMove(m);
			moves.add(m);
			return;
		}

		for(int i = centerCardPos; i< centerCards.size(); ++i)
		{
			Card card = centerCards.get(i);
			if(!card.selected)
			{
				CurrentMove.add(card);
				card.selected = true;
				moveSum += card.value;
				generateMoves(myCard, CurrentMove, centerCards, moves, moveSum, i+1);
				moveSum -= card.value;
				CurrentMove.remove(card);
				card.selected = false;
			}



//		for (Card card :centerCards) {
//			if(!card.selected)
//			{
//				CurrentMove.add(card);
//				card.selected = true;
//				moveSum += card.value;
//				generateMoves(myCard, CurrentMove, centerCards, moves, moveSum);
//				moveSum -= card.value;
//				CurrentMove.remove(card);
//				card.selected = false;
//			}
		}
	}
	
	public abstract int rateMove(Move move);
	
	public void clear()
	{
		cc = new CapturedCards(name);
	}
	
	public void updateScore()
	{
		scoreKeeper.update(cc);
	}

	@Override
	public String toString() {
		return "Player{" + name + ", result: " + scoreKeeper + '}';
	}


}
