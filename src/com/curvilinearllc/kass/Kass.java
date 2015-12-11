/*
 * Assignment	A06 RockPaperScissors
 * Program		RockPaperScissors
 * Author		Dandy H. Taylor
 * Date			Nov 28, 2015
 *
 */

package com.curvilinearllc.kass;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * This is the main class of the Rock Paper Scissors (Rochambeau) application.
 * It creates the interface and manages the majority of the logic, other than
 * the actual evaluation of the cards.
 * 
 * @author Dandy H. Taylor
 *
 */
@SuppressWarnings("serial")
public class Kass extends JFrame
{
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rock;
	private JRadioButton paper;
	private JRadioButton scissors;
	private JButton shoot;
	
	private JLabel lblDWins;
	private JLabel lblDLosses;
	private JLabel lblDDraws;
	
	private JLabel lblPWins;
	private JLabel lblPLosses;
	private JLabel lblPDraws;
	
	private JLabel lblOutcome;
	
	private JButton dealerCard;
	private JButton playerCard;
	
	private Random num = new Random();
	
	private int dealerWins = 0;
	private int dealerLosses = 0;
	private int dealerDraws = 0;
	
	private int playerWins = 0;
	private int playerLosses = 0;
	private int playerDraws = 0;
	
	private Color background = new Color(37,91,101);
	private Color panelBackground = new Color(3, 44, 52);
	private Color sidePanel = new Color(17, 68, 78);
	private Color defaultText = new Color(246, 220, 163);
	private Color titleText = new Color(202, 171, 101);
	
	/**
	 * This is the main entry point method to the application.
	 * 
	 * @param args	A String array representing the command line arguments passed to the game
	 * 
	 */
	public static void main(String[] args)
	{
		new Kass().run();

	}

	/**
	 * This is the constructor method for the Rock Paper Scissors application.
	 * The method sets up the sizes and default settings for the JFrame and
	 * adds all of the separate panel elements that make up the interface.
	 * 
	 */
	public Kass()
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 550, 450);
		this.getContentPane().setBackground(background);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(126, 35, 25));
		mainPanel.setLayout(new BorderLayout(0, 0));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		mainPanel.add(createTitle(), BorderLayout.NORTH);
		mainPanel.add(createSelectionPanel(), BorderLayout.WEST);
		mainPanel.add(createResultsPanel(), BorderLayout.EAST);
		mainPanel.add(createOutcomePanel(), BorderLayout.SOUTH);
		mainPanel.add(createCentralPanel(), BorderLayout.CENTER);

		this.getContentPane().add(mainPanel);
		
	}

	/**
	 * This method kicks off the application from main. It was created in
	 * order to isolate the running of the game from the main method in order
	 * to keep the main method as simple as possible.
	 * 
	 */
	public void run()
	{
		this.setVisible(true);
	}
	
	/**
	 * This method creates a JPanel object that is placed in the center of the
	 * interface. This panel holds the buttons that will represent the cards
	 * of the dealer and the player.
	 * 
	 * @return Returns a JPanel that contains a dealer card and a user card
	 * 
	 */
	private JPanel createCentralPanel()
	{
		JPanel centralPanel = new JPanel();
		centralPanel.setBorder(new EmptyBorder(0, 50, 0, 50));
		centralPanel.setPreferredSize(new Dimension(200, 250));
		centralPanel.setBackground(panelBackground);
		centralPanel.setLayout(new FlowLayout());
		
		dealerCard = new JButton("Dealer Card");
		dealerCard.setPreferredSize(new Dimension(125, 125));
		centralPanel.add(dealerCard);
		
		playerCard = new JButton("Player Card");
		playerCard.setPreferredSize(new Dimension(125, 125));
		centralPanel.add(playerCard);

		return centralPanel;

	}

	/**
	 * This method creates a JLabel object that contains information about the
	 * outcome of the game. It is updated as the game is played and a winner is
	 * declared.
	 * 
	 * @return Returns a JLabel containing information about the outcome of the game
	 * 
	 */
	private JLabel createOutcomePanel()
	{
		lblOutcome = new JLabel();
		lblOutcome.setFont(new Font("Arial", Font.BOLD, 20));
		lblOutcome.setForeground(new Color(202, 111, 101));
		lblOutcome.setBackground(background);
		lblOutcome.setOpaque(true);
		lblOutcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblOutcome.setBorder(BorderFactory.createMatteBorder(1, 3, 3, 3, titleText));
		lblOutcome.setPreferredSize(new Dimension(550, 65));
		
		return lblOutcome;
		
	}

	/**
	 * This method creates a JPanel object that is placed to the right side of the
	 * interface. It contains the results area for subsequent games, keeping track
	 * of the total wins, losses, and draws for both the dealer and the user.
	 * 
	 * @return Returns a JPanel that contains the dealer's and user's statistics in respective panels
	 * 
	 */
	private JPanel createResultsPanel()
	{
		JPanel resultPanel = new JPanel();
		resultPanel.setPreferredSize(new Dimension(120, 350));
		resultPanel.setBackground(sidePanel);
		resultPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 3, titleText));
		resultPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		resultPanel.add(createDealerResultPanel());
		resultPanel.add(createPlayerResultPanel());

		return resultPanel;

	}

	/**
	 * This method creates JPanel that holds the player's statistics for wins, losses,
	 * and draws. It is called by the createResultsPanel method as part of creating the
	 * results panel for the game.
	 * 
	 * @return Returns a JPanel containing the player's statistics
	 * 
	 */
	private JPanel createPlayerResultPanel()
	{
		JPanel playerResultPanel = new JPanel();
		playerResultPanel.setBackground(sidePanel);
		playerResultPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblPlayer = new JLabel("Player");
		lblPlayer.setPreferredSize(new Dimension(110,15));
		lblPlayer.setForeground(defaultText);
		lblPlayer.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
		
		lblPWins = new JLabel("Wins: 0");
		lblPWins.setPreferredSize(new Dimension(110,15));
		lblPWins.setForeground(defaultText);
		lblPWins.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
		
		lblPLosses = new JLabel("Losses: 0");
		lblPLosses.setPreferredSize(new Dimension(110,15));
		lblPLosses.setForeground(defaultText);
		lblPLosses.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
		
		lblPDraws = new JLabel("Draws: 0");
		lblPDraws.setPreferredSize(new Dimension(110,15));
		lblPDraws.setForeground(defaultText);
		lblPDraws.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
		
		playerResultPanel.add(lblPlayer);
		playerResultPanel.add(lblPWins);
		playerResultPanel.add(lblPLosses);
		playerResultPanel.add(lblPDraws);

		return playerResultPanel;
	}

	/**
	 * This method creates JPanel that holds the dealer's statistics for wins, losses,
	 * and draws. It is called by the createResultsPanel method as part of creating the
	 * results panel for the game.
	 * 
	 * @return Returns a JPanel containing the dealer's statistics
	 * 
	 */
	private JPanel createDealerResultPanel()
	{
		JPanel dealerResultPanel = new JPanel();
		dealerResultPanel.setBackground(sidePanel);
		dealerResultPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

 		JLabel lblDealer = new JLabel("Dealer");
		lblDealer.setPreferredSize(new Dimension(110,15));
		lblDealer.setForeground(defaultText);
		lblDealer.setBorder(BorderFactory.createEmptyBorder(5,10,0,0));
		
		lblDWins = new JLabel("Wins: 0");
		lblDWins.setPreferredSize(new Dimension(110,15));
		lblDWins.setForeground(defaultText);
		lblDWins.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
		
		lblDLosses = new JLabel("Losses: 0");
		lblDLosses.setPreferredSize(new Dimension(110,15));
		lblDLosses.setForeground(defaultText);
		lblDLosses.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
		
		lblDDraws = new JLabel("Draws: 0");
		lblDDraws.setPreferredSize(new Dimension(110,15));
		lblDDraws.setForeground(defaultText);
		lblDDraws.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));

		dealerResultPanel.add(lblDealer);
		dealerResultPanel.add(lblDWins);
		dealerResultPanel.add(lblDLosses);
		dealerResultPanel.add(lblDDraws);
		
		return dealerResultPanel;
	}

	/**
	 * This method creates a JPanel object that is placed to the left side of the
	 * interface. It contains the control area of the game, providing functionality
	 * for the user to select one of the options and call the game, or shoot.
	 * 
	 * @return Returns a JPanel that contains the controls for the game
	 */
	private JPanel createSelectionPanel()
	{
		JPanel selectionPanel = new JPanel();
		selectionPanel.setPreferredSize(new Dimension(120, 350));
		selectionPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		selectionPanel.setBorder(BorderFactory.createMatteBorder(0, 3, 0, 1, titleText));
		selectionPanel.setBackground(sidePanel);
		
		rock = new JRadioButton("Rock");
		rock.setForeground(defaultText);
		rock.setBackground(sidePanel);
		buttonGroup.add(rock);
		selectionPanel.add(rock);
		
		paper = new JRadioButton("Paper");
		paper.setForeground(defaultText);
		paper.setBackground(sidePanel);
		buttonGroup.add(paper);
		selectionPanel.add(paper);

		scissors = new JRadioButton("Scissors");
		scissors.setForeground(defaultText);
		scissors.setBackground(sidePanel);
		buttonGroup.add(scissors);
		selectionPanel.add(scissors);
		
		shoot = new JButton("Shoot");
		
		shoot.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				checkResults();
			}
		});
		
		selectionPanel.add(shoot);
		
		return selectionPanel;
	}

	/**
	 * This method checks the results of the game. It checks to make sure that a
	 * selection was made and then flips over the player and dealer cards. It then
	 * calls the CardType getWinner method to determine who one the game and calls
	 * methods to update the interface appropriately.
	 * 
	 */
	protected void checkResults()
	{
		if (buttonGroup.getSelection() == null)
			return;
		
		lblOutcome.setText(null);
		CardType playerSelection = flipPlayerCard();
		CardType dealerSelection = flipDealerCard();
		String result = CardType.getWinner(dealerSelection, playerSelection);
		
		switch (result)
		{
			case "draw":
				draw();
				break;
			case "dealer":
				dealerWin();
				break;
			case "player":
				playerWin();
				break;
		}

	}

	/**
	 * This method handles the logic in the case that the dealer and user 
	 * choose the same card. It updates the interface to indicate a draw and
	 * increments the draw counts for both the dealer and the player.
	 * 
	 */
	private void draw()
	{
		dealerDraws++;
		playerDraws++;
		lblDDraws.setText(String.format("Draws: %d", dealerDraws));
		lblPDraws.setText(String.format("Draws: %d", playerDraws));
		lblOutcome.setText("Draw!");
	}
	
	/**
	 * This method handles the logic in the case that the dealer wins the game.
	 * It updates the interface, indicating the dealer win and increments
	 * the dealer win count and the player loss count.
	 * 
	 */
	private void dealerWin()
	{
		dealerWins++;
		playerLosses++;
		lblDWins.setText(String.format("Wins: %d", dealerWins));
		lblPLosses.setText(String.format("Losses: %d", playerLosses));
		lblOutcome.setText("Dealer Wins!");
		
	}
	
	/**
	 * This method handles the logic in the case that the player wins the game.
	 * It updates the interface, indicating the player win and increments the
	 * player win count and the dealer loss count.
	 * 
	 */
	private void playerWin()
	{
		dealerLosses++;
		playerWins++;
		lblDLosses.setText(String.format("Losses: %d", dealerLosses));
		lblPWins.setText(String.format("Wins: %d", playerWins));
		lblOutcome.setText("Player Wins!");
		
	}
	
	/**
	 * This method is the basis for choosing and flipping a random card
	 * for the dealer. It uses a Random object to select a random number
	 * between 1 and 3 and chooses the corresponding card type to be
	 * displayed.
	 * 
	 * @return Returns a CardType to the caller representing the dealer's selected card
	 * 
	 */
	private CardType flipDealerCard()
	{

		int randomNumber = num.nextInt(3);
		
		dealerCard.setText(null);
		
		switch(randomNumber)
		{
			case 0:
				dealerCard.setIcon(CardType.ROCK.getIcon());
				return CardType.ROCK;
			case 1:
				dealerCard.setIcon(CardType.PAPER.getIcon());
				return CardType.PAPER;
			case 2:
				dealerCard.setIcon(CardType.SCISSORS.getIcon());
				return CardType.SCISSORS;

		}

		return null;
		
	}

	/**
	 * This method is the basis for flipping the player's chosen card.
	 * checks to see which card the player selected and gets the 
	 * appropriate icon to set on the card, as well as returning the
	 * player's card selection.
	 * 
	 * @return Returns a CardType to the caller representing the player's selected card
	 * 
	 */
	private CardType flipPlayerCard()
	{
		CardType selection = null;
		
		playerCard.setText(null);
		
		if (rock.isSelected())
		{
			playerCard.setIcon(CardType.ROCK.getIcon());;
			selection = CardType.ROCK;
		}
		
		if (paper.isSelected())
		{
			playerCard.setIcon(CardType.PAPER.getIcon());
			selection = CardType.PAPER;
		}
		
		if (scissors.isSelected())
		{
			playerCard.setIcon(CardType.SCISSORS.getIcon());
			selection = CardType.SCISSORS;
		}

		buttonGroup.clearSelection();
		
		return selection;
		
	}

	/**
	 * This method creates a JLabel containing the application's title.
	 * 
	 * @return Returns a JLabel with the application's title
	 * 
	 */
	private JLabel createTitle()
	{
		JLabel lblTitle = new JLabel("ROCHAMBEAU");
		lblTitle.setFont(new Font("GARAMOND", Font.BOLD, 28));
		lblTitle.setForeground(defaultText);
		lblTitle.setBackground(background);
		lblTitle.setOpaque(true);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBorder(BorderFactory.createMatteBorder(3, 3, 1, 3, titleText));
		lblTitle.setPreferredSize(new Dimension(550, 75));
		
		return lblTitle;

	}
	
}
