/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package scopa2;

import CardManagement.Move;
import CardManagement.Deck;
import CardManagement.Card;
import Players.RuleBasedPlayer;
import java.util.ArrayList;

/**
 *Greg Daniels
 *gdaniels13@gmail.com
 *A00798340
 */
public class Tester {

	
	public static void main(String args[])
	{
//		testMoves();
//		testValidMoves();
		testDeck();
	}



	public static void testMoves()
	{
		Deck deck = new Deck();
		deck.shuffle();

		Move m1 = new Move();
		Move m2 = new Move();

		Card t = deck.nextCard();

		m1.myCard = t;
		m2.myCard = deck.nextCard();

		System.out.println( " false" + m1.equals(m2) + m2.equals(m1) );

		m1.myCard = m2.myCard;

		System.out.println( " true" + m1.equals(m2) + m2.equals(m1) );

		m1.centerCards.add(deck.nextCard());
		m1.centerCards.add(deck.nextCard());
		m1.centerCards.add(deck.nextCard());
		m1.centerCards.add(deck.nextCard());

		System.out.println( " false" + m1.equals(m2) + m2.equals(m1) );

		for(Card c : m1.centerCards)
		{
			m2.centerCards.add(c);
		}

		System.out.println( " true" + m1.equals(m2) + m2.equals(m1) );

		m2.centerCards.add(deck.nextCard());
		System.out.println( " false" + m1.equals(m2) + m2.equals(m1));
		System.out.println(m2 + "\n" + m1);
	}


	public static void testDeck()
	{
		Deck deck = new Deck();

		deck.shuffle();
		for(int i = 0; i<40; ++i)
		{
			System.out.println(deck.nextCard());
		}
	}


	public static void testValidMoves()
	{
		RuleBasedPlayer t = new RuleBasedPlayer("tester");
		
		Deck deck = new Deck();
		
		deck.shuffle();
		
		ArrayList<Card> myHand = new ArrayList<>();
		ArrayList<Card> centerBoard= new ArrayList<>();

		for(int i = 0; i<5; ++i)
		{
			centerBoard.add(deck.nextCard());
		}


		for(int i =0; i<3; ++i)
		{
			myHand.add(deck.nextCard());
		}
		
		
		ArrayList<Move> moves = t.getValidMoves(myHand, centerBoard);

		System.out.println("My Hand: " + myHand);
		System.out.println("center : " + centerBoard);

		for (Move move : moves) {
			System.out.println(move);
		}
	}
	
}
