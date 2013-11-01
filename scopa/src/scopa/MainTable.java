package scopa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;




public class MainTable extends JFrame
{
	private HumanHand humanHand;
	private ComputerHand computerHand;
	private CenterBoard centerBoard;
	private CardDeck cardDeck;
	private CapturedCards computerCaptured, humanCaptured;
	
	private JButton playB, discardB;
	private JPanel lower, upper, statusBar;
	private JPanel mainPane, playerLastMove, computerLastMove;
	
	private JLabel cardsLeftL, computerPointsL, humanPointsL;
	private String cardsLeftS = "Cards Left: ", computerPointsS = "Computer Points: ", humanPointsS = "Human Points: ";
	private int cardsLeftI, computerPointsI,humanPointsI, round;

	private Object tookLastCard;
	private static int WIDTH = 900, HEIGHT = 700;
	
	private JMenu help, menu;
	private JMenuBar menubar;
	private JMenuItem showMemory, showReadme, reset, exit, showLog, showRules;
	private GameSummary gameSummary;
	
	////////////////constructor///////////
	public MainTable() 
	{
		String humanName ="";
		
		mainPane = new JPanel();
		mainPane.setLayout(new GridLayout(3,1));
		humanHand= new HumanHand();
		
		computerHand = new ComputerHand();
		computerCaptured = new CapturedCards("Computer");
		centerBoard = new CenterBoard();
		
		cardDeck = new CardDeck();
		
		setSize(WIDTH,HEIGHT);
		setLayout(new BorderLayout());
		createMenu();
		
		setVisible(true);
		setBackground(new Color(0,139,0));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		firstDeal();
		
		initializeUpper();

		setTitle("Scopa");
		mainPane.add(centerBoard);
		add(mainPane);
		
		setLocationRelativeTo(null);
		initializeLower();

		try
		{
			humanName = JOptionPane.showInputDialog(mainPane,"Enter Your Name" );
		}
		catch (java.lang.NullPointerException e) {
			System.out.print("e");
			System.exit(0);
		}
		
		if(humanName==null||humanName.compareTo("")==0) humanName = "Player";
		
		humanPointsS = humanName + "'s Points: ";
		humanCaptured= new CapturedCards(humanName);
		initializeStatusBar();
		gameSummary = new GameSummary(1, computerCaptured, humanCaptured);
		gameSummary.setVisible(false);
		
	}
	
	
	private void initializeStatusBar()
	{
		statusBar = new JPanel();
		statusBar.setLayout(new FlowLayout());
		add(statusBar, BorderLayout.SOUTH);
		computerPointsL = new JLabel();
		
		humanPointsL = new JLabel();
		
		cardsLeftL = new JLabel();
		
		statusBar.add(humanPointsL);
		statusBar.add(computerPointsL);
		statusBar.add(cardsLeftL);
		
		computerPointsI = 0;
		humanPointsI = 0;
		round =0;
		
		updateLabels();	
	}
	
	
	private void initializeLower()
	{
		lower = new JPanel();
		JPanel lowerLeft = new JPanel();
		lowerLeft.setLayout(new FlowLayout());
		lower.setLayout(new BorderLayout());
		lower.setBackground(new Color(0,139,0));
		lowerLeft.setBackground(new Color(0,139,0));
		
		
		lowerLeft.add(humanHand);
		lowerLeft.setVisible(true);
		JPanel minibutton = new JPanel();
		minibutton.setLayout(new GridLayout(2,1));
		
		playB = new JButton("Play");
		playB.addActionListener(new ButtonHandler());
		
		discardB = new JButton("Discard");
		discardB.addActionListener(new ButtonHandler());
		
		initializePlayerLastMove();
		minibutton.add(playB);
		minibutton.add(discardB);
		lowerLeft.add(minibutton);
		lower.add(lowerLeft, BorderLayout.WEST);
		lower.add(playerLastMove, BorderLayout.EAST);
		
		mainPane.add(lower);
		revalidate();
		repaint();
		
		
	}
	
	private void initializePlayerLastMove()
	{
		playerLastMove = new JPanel();
		playerLastMove.setBackground(new Color(0,139,0));
	}
	
	
	private void initializeUpper()
	{
		upper = new JPanel();
		upper.setLayout(new BorderLayout());
		
		//upper.setBackground(new Color(0,139,0));
		
		upper.add(computerHand, BorderLayout.WEST);
		upper.setVisible(true);
		
		//computerHand.setLocation(WIDTH/2-200,30);
		
		computerLastMove = new JPanel();
		computerLastMove.setLayout(new FlowLayout());	
		computerLastMove.setBackground(new Color(0,139,0));
		upper.add(computerLastMove, BorderLayout.EAST);
		upper.setBackground(new Color(0,139,0));
		mainPane.add(upper);
		
		revalidate();
		repaint();
	
	}
	
	private void updateLabels() 
	{
		computerPointsL.setText(computerPointsS + computerPointsI);
		humanPointsL.setText(humanPointsS + humanPointsI);
		cardsLeftL.setText(cardsLeftS + cardDeck.cardsLeft());
		
	}

	private void endRound()
	{
		if(tookLastCard == humanHand)
		{
			humanCaptured.addCardVector(centerBoard.removeAllCards());
		}
		else
		{
			computerCaptured.addCardVector(centerBoard.removeAllCards());
		}
		computerPointsI += computerCaptured.getPoints();
		humanPointsI += humanCaptured.getPoints();
		updateLabels();
		gameSummary = new GameSummary(round,computerCaptured, humanCaptured);
		gameSummary.setVisible(true);
		
		gameSummary.getOKButton().addActionListener(new ButtonHandler());
		
	}

	public void firstDeal()
	{
		deal();
		for(int i =0; i<4; ++i)
		{
			centerBoard.addCard(cardDeck.getRandCard());
		}
	}
	
	public void deal()
 	{
 		for(int i = 0; i<3; i++)
 		{
 			humanHand.addCard(cardDeck.getRandCard());
 			computerHand.addCard(cardDeck.getRandCard());
 		}
 	}
	
	private void makeHumanMove()
	{
		JLabel humCard = humanHand.removeSelected();
		playerLastMove.removeAll();
		playerLastMove.add(humCard);
		humanCaptured.addCard(humanHand.removeSelected());
		ArrayList<JLabel> cenCard = centerBoard.removeSelectedCards();
		
		for(int i = 0; i< cenCard.size(); ++i)
		{
			playerLastMove.add(cenCard.get(i));
		}
		
		humanCaptured.addCardVector(cenCard);

		if (centerBoard.getCardsLeft() == 0)
		{
			humanCaptured.addScopa();
		}
	}
	
	private void makeComputerMove()
	{
		
		//check scopa
		int sumCenterCards = centerBoard.getTotalCardValue();
		if(sumCenterCards<=10)
		{
			for(int i =0; i<computerHand.getCardsLeft(); ++i)
			{
				if(sumCenterCards == CardDeck.getCardValue(computerHand.getCard(i)))
				{
 					computerLastMove.removeAll();

					JLabel temp = computerHand.removeCard(i);
					computerCaptured.addCard(temp);
					computerLastMove.add(temp);
					
					ArrayList<JLabel> tempV = centerBoard.removeAllCards();
					
					for(int k = 0; k<tempV.size();++k) 
					{
						computerLastMove.add(tempV.get(k));
					}
					
					computerCaptured.addCardVector(tempV);
					
					tookLastCard = computerCaptured;
					computerCaptured.addScopa();
					return;
				}
			}
		}
 		
 		
 		//check sette bello
 		//check for 7
 		//check for soldi
 		//check for maxnumberCards
 		
 		//check for single cards
 		
 		for(int i =0; i<computerHand.getCardsLeft(); i++)
 		{
 			for(int j = 0; j<centerBoard.getCardsLeft() ; j++)
 			{
 				if(CardDeck.getCardValue(computerHand.getCard(i)) == CardDeck.getCardValue(centerBoard.getCard(j)))
 				{
 					computerLastMove.removeAll();
 					JLabel temp = computerHand.removeCard(i);
 					temp.setVisible(true);
 					computerCaptured.addCard(temp);
 					computerLastMove.add(temp);
 					temp = centerBoard.removeCard(j);
 					temp.setVisible(true);
 					computerCaptured.addCard(temp);
 					computerLastMove.add(temp);
 					tookLastCard = computerCaptured;
 					return;
 				}
 			}
 		}
 		
 		//discard lowest card
 		int lowest = 0;
 		for(int i=0; i<computerHand.getCardsLeft(); ++i)
 		{
 			if(CardDeck.getCardValue(computerHand.getCard(lowest))>cardDeck.getCardValue(computerHand.getCard(i)))
 			{
 				lowest = i;
 			}
 		}
 		
 		System.out.println("computer has discarded " + computerHand.getCard(lowest));
 		centerBoard.addCard(computerHand.removeCard(lowest));

	}
	
	public boolean isValidMove()
	{
		if(humanHand.getSelectedValue()==centerBoard.getSelectedValue())
		{
			if(centerBoard.getNumSelected() >1)
			{
				for(int j = 0; j<centerBoard.getCardsLeft() ; j++)
	 			{
	 				if(humanHand.getSelectedValue() == CardDeck.getCardValue(centerBoard.getCard(j)))
	 				{
	 					return false;
	 				}
	 			}
			}
			return true;
		}
		return false;
	}

	
	private class ButtonHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			Object temp = e.getSource();
			
			if(temp == gameSummary.getOKButton())
			{
				cardDeck.resetDeck();
				humanCaptured.clearCards();
				computerCaptured.clearCards();
				playerLastMove.removeAll();
				computerLastMove.removeAll();
				firstDeal();
			}
			if(temp == playB)
			{
				if(humanHand.getSelected() != humanHand.HOLDERLABEL && isValidMove())
				{
					makeHumanMove();
					tookLastCard = humanHand;
					makeComputerMove();
				}
				else
				{
					JOptionPane.showMessageDialog(mainPane, "Invalid Move \n press ALT 1 for help.");
				}
			}
			else if (temp == discardB)
			{
				if(humanHand.getSelected() == HumanHand.HOLDERLABEL)
				{
					JOptionPane.showMessageDialog(mainPane, "Invalid Move \n press ALT 1 for help.");
				}
				else
				{

						
						for (int i =0; i<centerBoard.getCardsLeft(); ++i)
						{
							if(humanHand.getSelectedValue()== CardDeck.getCardValue(centerBoard.getCard(i)))
							{	
								JOptionPane.showMessageDialog(mainPane, "Invalid Move \n press F1 for help.");
								return;
							}
						}
						
					JLabel templ = humanHand.removeSelected();
					centerBoard.addCard(templ);
					makeComputerMove();
				}
			}
			
			if(cardDeck.cardsLeft() ==0 && computerHand.getCardsLeft()==0)
			{
				endRound();
			}
			else if(computerHand.getCardsLeft()==0)
			{	
				deal();
				updateLabels();
			}
		}	
		
	}
	
	
	//creates the menu
	private void createMenu()
	{       
        help = new JMenu("Help");
        showReadme = new JMenuItem("Show Readme");
        showReadme.setMnemonic(KeyEvent.VK_F1);
        help.add(showReadme);
                
            
		showMemory = new JCheckBoxMenuItem("Memory");
		reset = new JMenuItem("Reset Game");
		
		exit = new JMenuItem("Exit");
		showLog = new JCheckBoxMenuItem("Show Log");
		
		showRules = new JMenuItem("How to Play");
		//showRules.setMnemonic(KeyEvent.VK_0);
		
		showRules.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		
		menu = new JMenu("Menu");
		menubar = new JMenuBar();
		
		help.add(showRules);
		//menu.add(showMemory);
		//menu.add(showLog);
		menu.add(reset);
		menu.add(exit);
		
                
		showLog.addActionListener(new menuHandler());
		showMemory.addActionListener(new menuHandler());
		reset.addActionListener(new menuHandler());
		exit.addActionListener(new menuHandler());
		showReadme.addActionListener(new menuHandler());
		showRules.addActionListener(new menuHandler());
	
		menubar.add(menu);
        menubar.add(help);
		
		setJMenuBar(menubar);
	}
        
        
	//handles events corresponding to the menus
	private class menuHandler implements ActionListener
	{
	
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			Object temp =  e.getSource();
			
			if(temp == exit)
			{
				System.exit(0);
			}
				
			if (temp == reset)
			{
				cardDeck.resetDeck();
				computerCaptured.reset();
				computerHand.reset();
				computerPointsI = 0;
				centerBoard.removeAllCards();
				humanHand.reset();
				humanCaptured.reset();
				humanPointsI = 0;
				computerLastMove.removeAll();
				playerLastMove.removeAll();
				centerBoard.removeAllCards();
				firstDeal();
			}
	        else if (temp == showReadme)
	        {
				try 
				{
					new FileOpener("README.txt", "About");
				} 
				catch (FileNotFoundException e1) 
				{
		        	JOptionPane.showMessageDialog(mainPane, "README not found");
		        }
	            
	        }		
	        else if (temp == showRules)
	        {
				try 
				{
					new FileOpener("RULES.txt", "How To Play");
				} 
				catch (FileNotFoundException e1) 
				{
		        	JOptionPane.showMessageDialog(mainPane, "RULES.txt not found");
		        }
	         
	        }		
		}
	
	}

	
	public void cheaterFunction()
	{
		for(int i = 0; i<15; ++i)
		{
			humanCaptured.addCard(cardDeck.getRandCard());
			computerCaptured.addCard(cardDeck.getRandCard());
		}
	}
	
	
	
	
	public static void main(String[] args) 
	{
		new MainTable();
	}

}
