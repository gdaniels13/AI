/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CardManagement;

import java.util.ArrayList;
import javax.lang.model.type.TypeKind;
import javax.swing.JPanel;

/**
 * Greg Daniels gdaniels13@gmail.com A00798340
 */
public class CardHolder {

	public ArrayList<Card> cards;

	//Constructor
	public CardHolder() {
		cards = new ArrayList<>();
	}

	public CardHolder(CardHolder cards)
	{
		this.cards = new ArrayList<>(cards.cards);
	}

	public void remove(Card c) {
		cards.remove(c);
	}

	public void remove(ArrayList<Card> t) {
		cards.removeAll(t);
	}

	public void addCard(Card c) {
		cards.add(c);
	}
	public void addCard(ArrayList<Card> t) {
		cards.addAll(t);
	}

	@Override
	public String toString()
	{
		StringBuilder t = new StringBuilder();
		for(Card c : cards)
		{
			t.append(c);
			t.append("\n");
		}

		return t.toString();
	}
}
