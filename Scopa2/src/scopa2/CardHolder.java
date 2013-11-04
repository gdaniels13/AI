/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scopa2;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Greg Daniels gdaniels13@gmail.com A00798340
 */
public class CardHolder extends JPanel {

	public ArrayList<Card> cards;
	private ArrayList<JLabel> cardLabels;
	private ArrayList<Boolean> selected;
	boolean ShowCards;
	boolean Selectable;
	boolean MultipleSelect;
	boolean GUI;

	//Constructor
	CardHolder() {
		GUI = false;
		cards = new ArrayList<>();
		selected = new ArrayList<>();
		setVisible(true);
		setSize(cards.size() * 90, 160);
		setBackground(new Color(0, 139, 0));
		MultipleSelect = false;
		ShowCards = true;
		Selectable = true;
	}

	public void remove(Card c) {
		cards.remove(c);
		updateDisplay();
	}

	public void addCard(Card c) {
		cards.add(c);
		
		updateDisplay();
	}

	public void updateDisplay() {
		if(!GUI) return;
		removeAll();
		setLayout(new GridLayout(1, cards.size()));
		int y = getLocation().y;
		setLocation((MainTable.WIDTH / 2) - (cards.size() * 100 / 2), y);
		setSize(cards.size() * 100, 165);
		//selected = new ArrayList<>();
		cardLabels = new ArrayList<>();

		if (ShowCards) {
			for (Card card : cards) {
				JLabel t = new JLabel(new ImageIcon(card.name));
				t.setVisible(true);

				if (Selectable) {
					t.addMouseListener(mouseAdapter);
					//selected.add(false);
					cardLabels.add(t);
					
				}

				add(t);
			}
		} else {
			for (Card card : cards) {
				JLabel t = new JLabel(new ImageIcon("Cards/blank.jpg"));
				t.setVisible(true);
				add(t);
			}

		}
	}
	private MouseAdapter mouseAdapter = new MouseAdapter() {
		@Override
		public void mousePressed(MouseEvent e) {
			int tempIndex;
			if (Selectable) {
				JLabel temp = (JLabel) e.getSource();
				tempIndex = cardLabels.indexOf(temp);

				if (cards.get(tempIndex).selected) {
					temp.setBorder(null);
					cards.get(tempIndex).selected = false;
				} 
				else {
					temp.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 5, true));
					cards.get(tempIndex).selected = true;
//					selected.set(tempIndex, true);
				}
			if(!MultipleSelect)
			{
				for (int i = 0; i < cards.size(); i++) {
					if(cards.get(i).selected && i != tempIndex)
					{
						cards.get(i).selected = false;
						cardLabels.get(i).setBorder(null);
					}
				}
			}
				
			super.mouseClicked(e);
			System.out.println(selected);
			}
		}
	};
}
