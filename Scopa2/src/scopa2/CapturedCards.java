package scopa2;

import java.util.ArrayList;



public class CapturedCards
{
	private int soldiCount;
	private int sevenCount;
	private int setteBello;
	private int numScopa;
	private int numCards;
	private String name;
	
	public void clearCards()
	{
		numCards = 0;
	}

	public CapturedCards(String name) {
		this.soldiCount = 0;
		this.sevenCount = 0;
		this.setteBello = 0;
		this.numScopa = 0;
		this.numCards = 0;
		this.name = name;
	}
		
	public String getName()
	{
		return name;
	}
	
	public int getNumCards()
	{
		return numCards;
	}
	
	public int getCardPoint()
	{
		if (numCards>20)
			return 1;
		else
			return 0;
	}
	
	public int getNumSoldi()
	{
		return soldiCount;
	}
	
	public int getSoldiPoint()
	{
		if(soldiCount >5)
			return 1;
		return 0;
	}
	
	public void addCard(Card toAdd)
	{
		numCards++;
		if(toAdd.suit == Card.SUIT.SOLDI)
		{
			soldiCount++;
			if(toAdd.value ==7)
			{
				sevenCount++;
				setteBello++;
			}
		}
		else if(toAdd.value ==7)
		{
			sevenCount++;
		}
	}

	public int getPoints() 
	{
		return getBelloPoint()+ getSevensPoint() + getSoldiPoint() + getCardPoint() + numScopa;
	}


	public int getBelloPoint()
	{
		return setteBello;
	}
	

	public int getNumSevens() 
	{
		return sevenCount;
	}
	
	public int getSevensPoint()
	{
		if(sevenCount>2)
			return 1;
		
		return 0;
	}

	@Override
	public String toString() {
		return "CapturedCards{"+ "Scord=" + getPoints() + ", soldiCount=" + soldiCount + ", sevenCount=" + sevenCount + ", setteBello=" + setteBello + ", numScopa=" + numScopa + ", numCards=" + numCards + ", name=" + name + '}';
	}

	public void addScopa()
	{
		numScopa++;	
	}

	public int getScopaPoints()
	{
		return numScopa;
	}
	
	public void addCardVector(ArrayList<Card> removeSelected) 
	{
		for (Card card : removeSelected) {
			addCard(card);
		}
	}
	
	public void reset() 
	{
		setteBello = 0;
		sevenCount = 0;
		numCards = 0;
		soldiCount = 0;
		numScopa = 0;
	}

}
