/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package scopa2;

import java.awt.Color;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *Greg Daniels
 *gdaniels13@gmail.com
 *A00798340
 */
public class MainTable extends JFrame {
	public static int WIDTH = 900, HEIGHT = 700;
	
	CardHolder hand1, hand2, centerBoard;
	Player player1, player2;
	JPanel main;
	Player lastToTake;
	Deck deck;
	
	
	
	public MainTable() {
		
		main = new JPanel();
		main.setLayout(null);
		
		setTitle("Scopa");
		setSize(WIDTH, HEIGHT);
		main.setSize(WIDTH, HEIGHT);
		
		setBackground(new Color(0,139,0));
		main.setBackground(new Color(0,139,0));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		hand1  = new CardHolder();
		hand2 = new CardHolder();
		centerBoard = new CardHolder();

		hand1.setLocation(0, 0);
		hand1.ShowCards = true;
		hand2.setLocation(0, 500);
		hand2.ShowCards = true;
		hand2.Selectable = true;
		
		centerBoard.setLocation(0,250);
		centerBoard.ShowCards = true;
		centerBoard.MultipleSelect= true;
	
		player1 = new RandomPlayer("player1");
		player2 = new RuleBasedPlayer("player2");
		
//		main.add(hand1);
//		main.add(hand2);
//		main.add(centerBoard);
	//	add(main);
	//	setVisible(true);
//		main.setVisible(true);
				
	}
	
	public void startGame()
	{
		deal();
		while(deck.deck.size()>0){
			if(hand1.cards.isEmpty())
			{
				newHand();
			}
			processMove(player1.makeMove(hand1.cards, centerBoard.cards, deck, hand2.cards.size()),player1, hand1);
			processMove(player2.makeMove(hand2.cards, centerBoard.cards, deck, hand1.cards.size()),player2, hand2);
		}
		
		while(hand2.cards.size()>0)
		{
			processMove(player1.makeMove(hand1.cards, centerBoard.cards, deck, hand2.cards.size()),player1,hand1);
			processMove(player2.makeMove(hand2.cards, centerBoard.cards, deck, hand1.cards.size()),player2, hand2);
		}
		
		finishGame();
		
		//System.out.println(player1.cc);
		//System.out.println(player2.cc);
		
	}

	
	public void processMove(Move move, Player player, CardHolder hand)
	{
		if(move.centerCards == null)
		{
			hand.remove(move.myCard);
			centerBoard.addCard(move.myCard);
			return;
		}
		lastToTake = player;
		hand.remove(move.myCard);
		
		for(Card card : move.centerCards)
		{
			centerBoard.remove(card);
		}
		
		if(centerBoard.cards.isEmpty())
		{
			player.cc.addScopa();
		}
		
		player.cc.addCard(move.myCard);
		player.cc.addCardVector(move.centerCards);
		repaint();
		validate();
		repaint();
	}
	
	private void finishGame() {
		 lastToTake.cc.addCardVector(centerBoard.cards);
	}
	
	public void newHand()
	{
		for(int i = 0; i<3; ++i)
		{
			hand1.addCard(deck.nextCard());
		}

		for(int i = 0; i<3; ++i)
		{
			hand2.addCard(deck.nextCard());
		}
		
	}
	
	public void deal()
	{
		deck = new Deck();
		deck.shuffle();
		
		for(int i = 0; i<3; ++i)
		{
			hand1.addCard(deck.nextCard());
		}

		for(int i = 0; i<3; ++i)
		{
			hand2.addCard(deck.nextCard());
		}
		
		for(int i = 0; i<4; ++i)
		{
			centerBoard.addCard(deck.nextCard());
		}
		

	}
	
	public void simulate(Player player1, Player player2, int numGames)
	{
		this.player1 = player1;
		this.player2 = player2;
		for(int i =0; i<numGames; ++i)
		{
			startGame();
			player1.updateScore();
			player2.updateScore();
			
			player1.clear();
			player2.clear();
		}
		
		System.out.println("Player1 score: " + player1.totalScore);
		System.out.println("Player2 score: " + player2.totalScore);
	}
	
	
	
}
