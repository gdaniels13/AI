/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package scopa2;

import CardManagement.*;
import Players.Player;

import java.util.ArrayList;

/**
 *Greg Daniels
 *gdaniels13@gmail.com
 *A00798340
 */
public class MainTable {
	
	GameState gs;
	public static Move lastMove;

	
	public MainTable() {
		gs = new GameState();
		gs.player1hand  = new CardHolder();
		gs.player2hand = new CardHolder();
		gs.centerCards = new CardHolder();
	}
	
	public void startGame()
	{
		deal();

		for(int i = 0; i<18; ++i)
		{
			if(gs.player1hand.cards.isEmpty())
			{
				newHand();
			}
			processMove(gs.player1.makeMove(gs,true), gs.player1, gs.player1hand);
			processMove(gs.player2.makeMove(gs,false),gs.player2,gs.player2hand);
		//	processMove(gs.player1.makeMove(gs.player1hand, gs.centerCards, gs.deck, gs.player2hand), gs.player1, gs.player1hand);
		//	processMove(gs.player2.makeMove(gs.player2hand, gs.centerCards, gs.deck, gs.player1hand),gs.player2, gs.player2hand);
		}


		gs.lastToTake.cc.addCardVector(gs.centerCards.cards);
		gs.centerCards.cards.clear();

		if(gs.player1.cc.getNumCards() + gs.player2.cc.getNumCards() !=40)
		{
			gs.player1.cc.printCards();
			gs.player2.cc.printCards();
			gs.centerCards.cards.clear();
		}
	}


	public void processMove(Move move, Player player, CardHolder hand)
	{
		lastMove = move;
		if(move.centerCards == null)
		{
			hand.remove(move.myCard);
			gs.centerCards.addCard(move.myCard);
			return;
		}

		gs.lastToTake = player;
		hand.remove(move.myCard);
		

		gs.centerCards.remove(move.centerCards);

		if(gs.centerCards.cards.isEmpty())
		{
			player.cc.addScopa();
		}
		
		player.cc.addCard(move.myCard);
		player.cc.addCardVector(move.centerCards);
	}
	
	private void finishGame() {
		gs.lastToTake.cc.addCardVector(gs.centerCards.cards);
	}

	public void newHand()
	{
//		System.out.println(gs.deck.cardsLeft());
		for(int i = 0; i<3; ++i)
		{
			gs.player1hand.addCard(gs.deck.nextCard());
		}

		for(int i = 0; i<3; ++i)
		{
			gs.player2hand.addCard(gs.deck.nextCard());
		}
		
	}
	
	public void deal()
	{
		gs.deck = new Deck();
		gs.deck.shuffle();
		for(int i = 0; i<3; ++i)
		{
			gs.player1hand.addCard(gs.deck.nextCard());
		}

		for(int i = 0; i<3; ++i)
		{
			gs.player2hand.addCard(gs.deck.nextCard());
		}


		for(int i = 0; i<4; ++i)
		{
			gs.centerCards.addCard(gs.deck.nextCard());
		}

	}
	
	public void simulate(Player player1, Player player2, int numGames)
	{
		this.gs.player1 = player1;
		this.gs.player2 = player2;
		player1.opponentCC = player2.cc;
		player2.opponentCC = player1.cc;

		Player t;
		for(int i =0; i<numGames; ++i)
		{
			startGame();
			player1.updateScore();
			player2.updateScore();

			player1.clear();
			player2.clear();
			t = player1;
			player1 = player2;
			player2 = t;
		}
	}
}
