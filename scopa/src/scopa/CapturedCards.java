package scopa;

import java.awt.Container;
import java.awt.FlowLayout;
import java.security.AllPermission;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class CapturedCards
{
	private ArrayList<JLabel> cards;
	private int numScopa;
	private String name;
	
	public void clearCards()
	{
		cards.clear();
	}
	
	public CapturedCards(String N)
	{
		cards = new ArrayList<JLabel>();
		name = N;
		numScopa = 0;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getNumCards()
	{
		return cards.size();
	}
	
	public int getCardPoint()
	{
		if (getNumCards()>20)
			return 1;
		
		return 0;
	}
	
	public int getNumSoldi()
	{
		System.out.println(name);
		int num = 0;
		for(int i =0; i<cards.size(); ++i)
		{
			if(cards.get(i).getName().charAt(0) =='m')
			{
				++num;
				System.out.println(cards.get(i).getName());
			}
		}
		return num;
	}
	
	public int getSoldiPoint()
	{
		if(getNumSoldi() >5)
			return 1;
		
		return 0;
	}
	
	public void addCard(JLabel toAdd)
	{
		cards.add(toAdd);
	}

	public int getPoints() 
	{
		return getBelloPoint()+ getSevensPoint() + getSoldiPoint() + getCardPoint() + numScopa;
	}

	public boolean haveSetteBello() 
	{		
		for(int i = 0; i < cards.size(); ++i)
		{
			if(cards.get(i).getName().charAt(1) == '7' 
					&& cards.get(i).getName().charAt(0) =='m')
			{
				System.out.println("i found sette bello");
				return true;
				
			}
		}
		return false;
	}

	public int getBelloPoint()
	{
		if (haveSetteBello())
			return 1;
		
		return 0;
	}
	
	public JLabel getlastadded()
	{
		return cards.get(cards.size()-1);
	}

	public int getNumSevens() 
	{
		int numSevens=0;
		
		for( int i =0; i<cards.size(); ++i)
		{
			if(cards.get(i).getName().charAt(1) =='7')
			{
				numSevens++;
			}
		}
		return numSevens;
	}
	
	public int getSevensPoint()
	{
		if(getNumSevens()>2)
			return 1;
		
		return 0;
	}

	public void addScopa()
	{
		numScopa++;	
	}

	public int getScopaPoints()
	{
		return numScopa;
	}
	public void addCardVector(ArrayList<JLabel> removeSelected) 
	{
		cards.addAll( removeSelected);
	}
	
	public void displayCards()
	{
		JFrame cardstoDisplay = new JFrame();
		cardstoDisplay.setVisible(true);
		cardstoDisplay.setTitle(name);
		Container temp = cardstoDisplay.getContentPane();
		temp.setLayout(new FlowLayout());
		cardstoDisplay.setSize(500,300);
		for(int i =0; i<cards.size();++i)
		{
			temp.add(cards.get(i));
		}
		
	}

	public void reset() 
	{
		cards.clear();
	}
	
}