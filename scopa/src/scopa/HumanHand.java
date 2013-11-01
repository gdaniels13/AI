package scopa;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HumanHand extends JPanel
{
	
	/////////////member Variables///////////////////
	private Vector<JLabel> cards;
	
	private JLabel humanSelectedCard;
	public static  JLabel HOLDERLABEL = new JLabel();
	private JPanel mainPane;
	private String name;

	private MouseAdapter mouseAdapter = new MouseAdapter() {

		private void doMouseClick(MouseEvent e) {
			JLabel temp = (JLabel) e.getSource();
			humanSelectedCard.setBorder(null);
			humanSelectedCard = temp;
			humanSelectedCard.setBorder(BorderFactory.createLineBorder(Color.yellow, 5, true));
			
			super.mouseClicked(e);
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
		doMouseClick(e);
		}
	};
	
	////////////////Constructor//////////////////
	public HumanHand()
	{
		mainPane = this;
		cards = new Vector<JLabel>(); 
		humanSelectedCard = new JLabel();
	
		
		mainPane.setSize(400,180);
		mainPane.setVisible(true);
		mainPane.setBackground(new Color(0,139,0));
		
		setSize(400,220);
		setVisible(true);
		setBackground(new Color(0,139,0));
		setLayout(new GridLayout(1,2));
		
		
		HOLDERLABEL.setName("000000");
	}
	
	//adds a label to the vector and updates the pane
	public void addCard(JLabel toAdd)
	{
		cards.add(toAdd);
		toAdd.addMouseListener(mouseAdapter);
		updatePane();
	}

	public void reset() 
	{
		cards.clear();
	}
	
	//adds all the cards that are in the vector to the pane
	private void updatePane() 
	{
		mainPane.removeAll();
		
		for(int i =0; i<cards.size(); ++i)
		{
			mainPane.add(cards.get(i));
		}
		
		mainPane.revalidate();
		mainPane.repaint();	
	}

	public JLabel removeSelected()
	{
		cards.remove(humanSelectedCard);
		humanSelectedCard.removeMouseListener(mouseAdapter);
		JLabel temp = humanSelectedCard;
		temp.setBorder(null);
		humanSelectedCard = HOLDERLABEL;
		updatePane();
		return temp;
	}
	
	public int getSelectedValue()
	{
		return CardDeck.getCardValue(humanSelectedCard);
	}
	public JLabel getSelected()
	{
		return humanSelectedCard;
	}
}
