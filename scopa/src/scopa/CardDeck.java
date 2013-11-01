package scopa;

/*Greg Daniels
 *A00798340
 * 
 * this class is a deck of cards
 * reads cards in from the file ScopaCards
 */


import java.awt.Container;
import java.awt.FlowLayout;
import java.lang.Character.Subset;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class CardDeck
{
	private ArrayList<JLabel> deck;
	private String[] cardNames;
	
	//initializs the deck
	public CardDeck() 
	{	
		initializeCardNames();
		initializeDeck();
	}
	
	//initializes the string cardNames to the fileNames of the images
	private void initializeCardNames() {
		cardNames = new String[40];
		
		for(int i = 0; i<10; ++i)
		{
			cardNames[i] = "s"+(i+1)+".jpg";
			cardNames[i+10] = "b"+(i+1)+".jpg";
			cardNames[i+20] = "m"+(i+1)+".jpg";
			cardNames[i+30] = "c"+(i+1)+".jpg";
		}
	}

	private void initializeDeck() 
	{
		deck = new ArrayList<JLabel>(40);

		
		JLabel temp;
		for(int i = 0; i<40; ++i)
		{
			temp =new JLabel(new ImageIcon("ScopaCards/" + cardNames[i]));
			deck.add(temp);
			deck.get(i).setName(cardNames[i]);
		}		
	}

	//resets the deck
	public void resetDeck()
	{
		deck.removeAll(deck);
		initializeDeck();
	}
	
	//removes the card at index i and returns it
	public JLabel getCard( int i)
	{
		JLabel temp = deck.get(i);
		deck.remove(i);
		return deck.get(i);
	}
	
	//returns a random card and deletes that card from the deck;
	public JLabel getRandCard()
	{
		int rand = (int)(Math.random()*deck.size());
		
		JLabel temp = deck.get(rand);
		
		deck.remove(rand);
		return temp;
	}

	//returns the number of cards left
	public int cardsLeft()
	{
		return deck.size();
	}
	
	public static JLabel getBlankCard()
	{
		JLabel temp = new JLabel(new ImageIcon("scopaCards/blank.jpg"));
		
		return temp;
	}
	
 	public static int getCardValue(JLabel card)
 	{
 		String cardName = card.getName();
 		 cardName=(String) cardName.subSequence(1, 3);
 		return (int)Double.parseDouble(cardName);
 	}
	
 	
	public void displayCards()
	{
		JFrame cardstoDisplay = new JFrame();
		cardstoDisplay.setVisible(true);
		cardstoDisplay.setTitle("all cards");
		Container temp = cardstoDisplay.getContentPane();
		temp.setLayout(new FlowLayout());
		cardstoDisplay.setSize(500,300);
		for(int i =0; i<deck.size();++i)
		{
			//deck.get(i).setText(cardNames[i]);
			temp.add(deck.get(i));
		}
		
	}
}
