package scopa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameSummary extends JFrame
{
	private Container mainPane;
	private JPanel computerPane, humanPane;
	private CapturedCards computerHand, humanHand;
	public JButton OK;
	
	private JLabel[] computerL, humanL;
	private String[] labelStrings = {"Name: ", "Cards: ", "Sevens: ", "Soldi: ", "Sette Bello: ", "Scopa: "};
	
	
	public GameSummary(int round, CapturedCards cHand, CapturedCards hHand)
	{
		mainPane = new JPanel();
		getContentPane().setLayout(new BorderLayout());
		setSize(270,150);
		mainPane.setVisible(true);
		mainPane.setLayout(new GridLayout(1,2));
		setTitle("bob");
		mainPane.setBackground(Color.green);
		humanHand = hHand;
		computerHand = cHand;
		
		OK = new JButton("Click to Start the next Round");
		OK.addActionListener(new okHandler());
		initializeComputerPane();
		initializeHumanPane();
		
		getContentPane().add(mainPane);
		getContentPane().add(OK,BorderLayout.SOUTH);

		setLocationRelativeTo(null);
		revalidate();
		repaint();
		
	}

	public JButton getOKButton()
	{
		return OK;
	}
	
	
	private class okHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			setVisible(false);
		}
		
	}

	private void initializeHumanPane() 
	{
		humanPane = new JPanel();
		humanPane.setLayout(new GridLayout(6,1));
		humanPane.setVisible(true);
		
		intitializeHumanLabels();
		updateHumanLabels();
		mainPane.add(humanPane);
	}


	private void updateHumanLabels() 
	{
		humanL[0].setText(labelStrings[0] + humanHand.getName());
		humanL[1].setText(labelStrings[1] + humanHand.getCardPoint() );
		humanL[2].setText(labelStrings[2] + humanHand.getSevensPoint() );
		humanL[3].setText(labelStrings[3] + humanHand.getSoldiPoint() );
		humanL[4].setText(labelStrings[4] + humanHand.getBelloPoint() );
		humanL[5].setText(labelStrings[5] + humanHand.getScopaPoints() );

	}


	private void intitializeHumanLabels() 
	{
		humanL = new JLabel[6];
		for(int i = 0; i<6; ++i)
		{
			humanL[i] = new JLabel(labelStrings[i]);
			
			humanPane.add(humanL[i]);
		}
		humanL[0].setBackground(Color.blue);
	}


	private void initializeComputerPane() 
	{
		computerPane = new JPanel();
		computerPane.setLayout(new GridLayout(6,1));
		
		intitializeComputerLabels();
		updateComputerLabels();
		mainPane.add(computerPane);		
	}


	private void updateComputerLabels() 
	{
		computerL[0].setText(computerHand.getName());
		computerL[1].setText(labelStrings[1] + computerHand.getCardPoint() );
		computerL[2].setText(labelStrings[2] + computerHand.getSevensPoint() );
		computerL[3].setText(labelStrings[3] + computerHand.getSoldiPoint() );
		computerL[4].setText(labelStrings[4] + computerHand.getBelloPoint() );		
		computerL[5].setText(labelStrings[5] + computerHand.getScopaPoints() );

	}


	private void intitializeComputerLabels() 
	{
		computerL = new JLabel[6];
		for(int i = 0; i<6; ++i)
		{
			computerL[i] = new JLabel();
			computerPane.add(computerL[i]);
		}
	}

}
