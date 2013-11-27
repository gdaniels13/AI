/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CardManagement;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *Greg Daniels
 *gdaniels13@gmail.com
 *A00798340
 */
public class Deck {
	public ArrayList<Card> deck;

	//default
	public Deck() {
		initializeDeck();
	}

	
	public Deck(ArrayList<Card> deck) {
		this.deck = new ArrayList<>(deck);
	}

	//copy
	public Deck(Deck d) {
		this.deck = new ArrayList<>(d.deck);
	}
		
	private void initializeDeck() {
		deck = new ArrayList<Card>(40);
		for(int i = 0; i<10; ++i)
		{
			deck.add(new Card("s"+(i+1)+".jpg", i+1, Card.SUIT.SPADE)); 
			deck.add(new Card("b"+(i+1)+".jpg", i+1, Card.SUIT.BASTONE)); 
			deck.add(new Card("m"+(i+1)+".jpg", i+1, Card.SUIT.SOLDI)); 
			deck.add(new Card("c"+(i+1)+".jpg", i+1, Card.SUIT.COPPE)); 
		}
	}
	
	//resets the deck
	public void reset()
	{
		deck.removeAll(deck);
		initializeDeck();
	}
	
	public void clear()
	{
		deck.removeAll(deck);
	}
	
	public void shuffle()
	{
		Collections.shuffle(deck);
	}
	
	public Card nextCard()
	{
		Card t = deck.get(0);
		deck.remove(0);
		return t;
	}
	
	//returns the number of cards left
	public int cardsLeft()
	{
		return deck.size();
	}
	
	public Card getRandCard()
	{
		int rand = (int)(Math.random()*deck.size());
		
		Card temp = deck.get(rand);
		
		deck.remove(rand);
		return temp;
	}
}
