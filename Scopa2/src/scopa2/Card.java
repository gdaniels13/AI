/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package scopa2;

import javax.swing.ImageIcon;

/**
 *Greg Daniels
 *gdaniels13@gmail.com
 *A00798340
 */


public class Card {

	public enum SUIT{
		SOLDI("soldi"),
		COPPE("coppe"),
		SPADE("spade"),
		BASTONE("bastone");

		String name;

		SUIT(String n)
		{
			this.name = n;
		}

		public String toString()
		{
			return name;
		}
	}

	public SUIT suit;
	public int value;
	public String name;
	public boolean selected;
	public Card(String card, int value, SUIT suit) {
		this.name = "Cards/" + card;
		this.value = value;
		this.suit = suit;
		selected = false;
	}

	int score()
	{
		int t = 1;
		if(suit == SUIT.SOLDI)
		{
			t++;
		}
		if(value ==7)
		{
			t+=2;
		}
		
		if(t ==3)
		{
			t+=10;
		}
		
		return t;		
	}
	
	@Override
	public String toString() {
		return " "+ value+  " of " + suit ;
	}
	
	
	
}
