package CardManagement;

import Players.*;

/**
 * Created with IntelliJ IDEA.
 * User: gregor
 * Date: 11/23/13
 * Time: 12:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameState
{
	public  Player player1;
	public  Player player2;
	public  Player lastToTake;
	public  CardHolder player1hand;
	public  CardHolder player2hand;
	public  CardHolder centerCards;
	public  Deck deck;
	public  Move lastMove;

	public GameState(){};


	public GameState(GameState gs)
	{
		this.player1 =  gs.player1;
		this.player2 =  gs.player2;
		this.lastToTake = gs.lastToTake;
		this.player1hand =  new CardHolder(gs.player1hand);
		this.player2hand = new CardHolder(gs.player2hand);
		this.centerCards = new CardHolder(gs.centerCards);
		this.deck = new Deck(gs.deck);
		this.lastMove = gs.lastMove;
	}

}
