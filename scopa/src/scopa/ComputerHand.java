

package scopa;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ComputerHand extends JPanel
{
	
	/////////////member Variables///////////////////
	private ArrayList<JLabel> cards, toShow;
	
	
	////////////////Constructor//////////////////
	public ComputerHand()
	{
		cards = new ArrayList<JLabel>(); 
		toShow = new ArrayList<JLabel>();
		setSize(400,180);
		setVisible(true);
		setLayout(new FlowLayout());
		setBackground(new Color(0,139,0));
		}
	
	//adds a label to the ListArray and updates the pane
	public void addCard(JLabel toAdd)
	{
		cards.add(toAdd);
		toShow.add(CardDeck.getBlankCard());
		updatePane();
	}
	
	
	//adds all the cards that are in the ListArray to the pane
	private void updatePane() 
	{
		removeAll();
		
		for(int i = 0; i<toShow.size(); ++i)
		{
			add(toShow.get(i));
		}
		
		revalidate();
		repaint();	
	}

	//removes a card at the specified index then updates the pane
	//then returns the specified card
	public JLabel removeCard(int index)
	{
		JLabel temp =  cards.remove(index);
		toShow.remove(index);
		updatePane();
		return temp;
	}
	
	public int getCardsLeft()
	{
		return cards.size();
	}
	
	public JLabel getCard(int index)
	{
		
		return cards.get(index);
	}

	public void reset() 
	{
		cards.clear();
		toShow.clear();
	}

}
