/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CardManagement;

import javax.swing.ImageIcon;

/**
 *Greg Daniels
 *gdaniels13@gmail.com
 *A00798340
 */


public class Card implements  Comparable
{



	public enum SUIT{
		SOLDI("soldi",0),
		COPPE("coppe",10),
		SPADE("spade",20),
		BASTONE("bastone",30);

		String name;
		int value;
		SUIT(String n, int value)
		{
			this.name = n;
			this.value = value;
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

	public int score()
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

	public boolean equals(Object o)
	{
		Card c = (Card) o;
		if(this.compareTo(c)==0)
			return true;
		return false;
	}
	@Override
	public int compareTo(Object o)
	{
		int t = suit.value + this.value;
		Card c = (Card)o;
		int s = c.value + c.suit.value;
		return t-s;
	}

}
