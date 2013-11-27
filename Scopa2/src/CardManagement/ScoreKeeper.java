/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CardManagement;

/**
 *Greg Daniels
 *gdaniels13@gmail.com
 *A00798340
 */
public class ScoreKeeper {
	public int totalScore;
	public int scopa;
	public int sevens;
	public int setteBello;
	public int soldi;
	public int cards;

	public ScoreKeeper() {
		totalScore=0;
		scopa=0;
		sevens=0;
		setteBello=0;
		soldi=0;
		cards=0;
	}

	public ScoreKeeper(ScoreKeeper sc)
	{
		this.totalScore = sc.totalScore;
		this.scopa = sc.scopa;
		this.sevens = sc.sevens;
		this.setteBello = sc.setteBello;
		this.soldi = sc.soldi;
		this.cards = sc.cards;
	}

	public void update(CapturedCards c)
	{
		scopa +=c.getScopaPoints();
		sevens +=c.getSevensPoint();
		setteBello+=c.getBelloPoint();
		soldi +=c.getSoldiPoint();
		cards +=c.getCardPoint();
		totalScore+=c.getPoints();
	}

	@Override
	public String toString() {
		return " totalScore: " + totalScore + ", scopa=" + scopa + ", sevens=" + sevens + ", setteBello=" + setteBello + ", soldi=" + soldi + ", cards=" + cards + '}';
	}

	
	
	
}
