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
public class Move {
	Card myCard;
	ArrayList<Card> centerCards;
	int value;

	public Move(Card myCard, ArrayList<Card> centerCards, Player p) {
		this.myCard = myCard;
		this.centerCards = centerCards;
		int value = p.rateMove(this);
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
	
	
	
	

}
