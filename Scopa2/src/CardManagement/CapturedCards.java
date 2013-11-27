package CardManagement;

import java.util.ArrayList;



public class CapturedCards
{
	ArrayList<Card> cards;
	private int soldiCount;
	private int sevenCount;
	private int setteBello;
	private int numScopa;
	private int numCards;
	public CapturedCards(CapturedCards cc)
	{
		this.cards = new ArrayList<>(cc.cards);
		this.soldiCount = cc.soldiCount;
		this.sevenCount = cc.sevenCount;
		this.setteBello = cc.setteBello;
		this.numScopa = cc.numScopa;
		this.numCards = cc.numCards;
		this.name = cc.name;
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
		cards.add(toAdd);
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

	public void addCardVector(ArrayList<Card> removeSelected)
	{
		if(removeSelected==null)
			return;
		for (Card card : removeSelected) {
			addCard(card);
		}
	}

	public void removeCard( Card toAdd)
	{
		cards.remove(toAdd);
		numCards--;
		if(toAdd.suit == Card.SUIT.SOLDI)
		{
			soldiCount--;
			if(toAdd.value ==7)
			{
				sevenCount--;
				setteBello--;
			}
		}
		else if(toAdd.value ==7)
		{
			sevenCount--;
		}
	}


	public void removeCardVector(ArrayList<Card> removeSelected)
	{
		if(removeSelected==null)
			return;
		for (Card card : removeSelected) {
			removeCard(card );
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
		return "CapturedCards{"+ "Score=" + getPoints() + ", soldiCount="
				+ soldiCount + ", sevenCount=" + sevenCount + ", setteBello="
				+ setteBello + ", numScopa=" + numScopa + ", numCards="
				+ numCards + ", name=" + name + '}';
	}

	public void addScopa()
	{
		numScopa++;
	}

	public void removeScopa()
	{
		numScopa--;
	}

	public int getScopaPoints()
	{
		return numScopa;
	}

	public void printCards()
	{
		for(Card c : cards)
		{
			System.out.println(c);
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


	private String name;

	public CapturedCards(String name)
	{
		cards = new ArrayList<>();
		this.soldiCount = 0;
		this.sevenCount = 0;
		this.setteBello = 0;
		this.numScopa = 0;
		this.numCards = 0;
		this.name = name;
	}

}
