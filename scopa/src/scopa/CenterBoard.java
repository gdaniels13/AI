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

public class CenterBoard extends JPanel
{
	
	/////////////member Variables///////////////////
	private ArrayList<JLabel> cards;
	private ArrayList<Boolean> isSelected;
	JLabel humanSelectedCard;
	
	
	private MouseAdapter mouseAdapter = new MouseAdapter() {
		@Override
		public void mousePressed(MouseEvent e) 
		{
			JLabel temp = (JLabel) e.getSource();
			int tempIndex = cards.indexOf(temp);
		
			if(isSelected.get(tempIndex))
			{
				temp.setBorder(null);
				isSelected.set(tempIndex, false);
			}
			else
			{
			temp.setBorder(BorderFactory.createLineBorder(Color.YELLOW,5,true));
			isSelected.set(tempIndex ,true);
			}
			System.out.println("you Clicked on a card total value of selected cards is " + getSelectedValue());
			super.mouseClicked(e);
		}

	};
	
	////////////////Constructor//////////////////
	public CenterBoard()
	{
		cards = new ArrayList<JLabel>(); 
		isSelected = new ArrayList<Boolean>();
		
		setSize(400,160);
		setVisible(true);
		setBackground(new Color(0,139,0));
	}
	
	//adds a label to the ArrayList and updates the pane
	public void addCard(JLabel toAdd)
	{
		cards.add(toAdd);
		isSelected.add(false);
		toAdd.addMouseListener(mouseAdapter);
		toAdd.setVisible(true);
		updatePane();
	}
	
	
	//adds all the cards that are in the ArrayList to the pane
	private void updatePane() 
	{
		removeAll();
		
		for(int i =0; i<cards.size(); ++i)
		{
			add(cards.get(i));
		}
		
		revalidate();
		repaint();	
	}

	//removes a card at the specified index then updates the pane
	//then returns the specified card
	public JLabel removeCard(int index)
	{
		JLabel temp =  cards.remove(index);
		updatePane();
		temp.removeMouseListener(mouseAdapter);
		return temp;
	}
	
	public ArrayList<JLabel> removeSelectedCards()
	{
		ArrayList<JLabel> toReturn = new ArrayList<JLabel>();
		for(int i = cards.size()-1; i>=0; --i)
		{
			if(isSelected.get(i))
			{
				isSelected.remove(i);
				JLabel temp = cards.remove(i);
				temp.removeMouseListener(mouseAdapter);
				temp.setBorder(null);
				toReturn.add(temp);
			}
		}
		updatePane();
		return toReturn;
	}
	
	public int getSelectedValue() 
	{
		int temp=0;
		
		for(int i = 0; i<cards.size(); ++i)
		{
			if(isSelected.get(i))
		
			{
				temp += CardDeck.getCardValue(cards.get(i));
			}
		}	
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
	
	public ArrayList<JLabel> removeAllCards()
	{
		ArrayList<JLabel> toReturn = new ArrayList<JLabel>();
		for(int i = cards.size()-1; i>=0; --i)
		{

				isSelected.remove(i);
				JLabel temp = cards.remove(i);
				temp.removeMouseListener(mouseAdapter);
				temp.setBorder(null);
				toReturn.add(temp);
			
		}
		updatePane();
		return toReturn;
	}
	
	public int getNumSelected()
	{
		int count = 0;
		
		for (int i = 0; i<isSelected.size(); ++i)
		{
			if(isSelected.get(i))
			{
				++count;
			}
		}
		
		return count;
	}

	public int getTotalCardValue()
	{
		int value = 0; 
		
		for (int i =0; i<cards.size(); ++i)
		{
			value += CardDeck.getCardValue(cards.get(i));
		}
		
		return value;
	}
}
