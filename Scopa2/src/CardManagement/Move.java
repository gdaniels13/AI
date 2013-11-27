/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CardManagement;

import Players.Player;
import java.util.ArrayList;

/**
 *Greg Daniels
 *gdaniels13@gmail.com
 *A00798340
 */
public class Move {
	public Card myCard;
	public ArrayList<Card> centerCards;
	public int value;

	public Move()
	{
		              centerCards = new ArrayList<>()  ;
	}

	public Move(Card myCard, ArrayList<Card> centerCards, Player p) {
		this.myCard = myCard;
		this.centerCards = centerCards;
		value = p.rateMove(this);
	}	
	
	public Move(Card myCard, ArrayList<Card> centerCards) {
		this.myCard = myCard;
		this.centerCards = centerCards;
	}	
	
	public Card getMyCard() {
		return myCard;
	}

	public ArrayList<Card> getCenterCards() {
		return centerCards;
	}

	public void setMyCard(Card myCard) {
		this.myCard = myCard;
	}

	public void setCenterCards(ArrayList<Card> centerCards) {
		this.centerCards = centerCards;
	}

	@Override
	public String toString() {
		return "Move{" + "myCard=" + myCard + ", centerCards=" + centerCards + '}';
	}


	public boolean equals(Object o)
	{
		Move t = (Move) o;

		if(myCard != t.myCard)   return false;
		if(centerCards == null && t.centerCards == null) return true;
		if(centerCards == null || t.centerCards == null) return false;
		if(centerCards.size() != t.centerCards.size()) return false;
		for(Card c:centerCards)
		{
			if(!t.centerCards.contains(c)) return false;
		}

		return true;
	}


}
