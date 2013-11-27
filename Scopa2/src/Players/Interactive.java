package Players;

import CardManagement.*;
import scopa2.MainTable;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Interactive extends  Player
{
	public Interactive(String name)
	{
		super(name);
	}

	@Override
	public Move makeMove(CardHolder myCards, CardHolder centerCards, Deck d, CardHolder opponentCards)
	{
		return displayGameState(myCards,centerCards, d.cardsLeft(), opponentCards.cards.size());
	}

	public Move displayGameState(CardHolder myCards, CardHolder centerCards,  int deckCardCount,int opponentCardCount)
	{
		System.out.println("****************************************************************************");

		System.out.println("Your Opponents last move was:");
		if(MainTable.lastMove !=null)
		if(MainTable.lastMove.centerCards == null){
			System.out.println("\t Discard " + MainTable.lastMove.myCard);
		}
		else
		{
			System.out.println( "\t Use " + MainTable.lastMove.myCard + " to get:");
			for(Card c: MainTable.lastMove.centerCards){
				System.out.println("\t\t" + c);
			}
		}


		System.out.printf("There are %d cards left in the deck\n your opponent has %d cards in his hand\n", deckCardCount, opponentCardCount);
		System.out.println("**********Center Cards************");

		for(Card c:centerCards.cards){
			System.out.println(c);
		}

		System.out.println("***********Your Cards*************");

		for(Card c:myCards.cards){
			System.out.println(c);
		}

		System.out.println("*********Moves Available**********");
		ArrayList<Move> moves =  getValidMoves(myCards.cards, centerCards.cards);

		for(int i = 0 ;i<moves.size(); ++i){
			Move curMove = moves.get(i);

			if(curMove.centerCards == null){
				System.out.println(i + "\t Discard " + curMove.myCard);
			}
			else
			{
				System.out.println(i + "\t Use " + curMove.myCard + " to get:");
				for(Card c: curMove.centerCards){
					System.out.println("\t\t" + c);
				}
			}
		}
		System.out.print("Please Enter your move of choice:");
		Scanner in = new Scanner(System.in);
		int next = in.nextInt();
		return moves.get(next);
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
		return 0;
	}
}
