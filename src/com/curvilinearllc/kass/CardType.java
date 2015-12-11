/*
 * Assignment	A06 RockPaperScissors
 * Program		CardType
 * Author		Dandy H. Taylor
 * Date			Nov 28, 2015
 *
 */

package com.curvilinearllc.kass;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * This enum represents the type of cards that you can have in a
 * Rock, Paper, Scissors game. 
 * 
 * @author Dandy H. Taylor
 *
 */
public enum CardType
{
	  ROCK("/resources/rock.png")
	, PAPER("/resources/paper.png")
	, SCISSORS("/resources/scissors.png");
	
	private ImageIcon icon;
	
	/**
	 * This is the constructor method for the CardType enum. It takes a
	 * String for the image path it will use to set the image of the card.
	 * 
	 * @param imagePath A String that represents the path to the image for the card
	 * 
	 */
	private CardType(String imagePath)
	{
		try
		{
			Image img = ImageIO.read(getClass().getResource(imagePath));
			this.icon = new ImageIcon(img);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}

	/**
	 * This method returns an icon of ImageIcon representing the image that will
	 * be displayed for the card.
	 * 
	 * @return Returns an ImageIcon for the card type
	 */
	public ImageIcon getIcon()
	{
		return this.icon;
	}
	
	/**
	 * This method returns a string indicating the winner of the game. The method
	 * evaluates the dealer's and user's selections to determine which player is
	 * the winner.
	 * 
	 * @param dealerSelection A CardType representing the dealer's selection
	 * @param playerSelection A CardType representing the user's selection
	 * 
	 * @return Returns a String of the outcome. Possible values are player, dealer, or draw
	 * 
	 */
	public static String getWinner(CardType dealerSelection, CardType playerSelection)
	{
		if (dealerSelection.equals(playerSelection))
			return "draw";
		
		switch(dealerSelection)
		{
			case ROCK:
				if (playerSelection.equals(CardType.PAPER))
					return "player";
				else
					return "dealer";
			case PAPER:
				if (playerSelection.equals(CardType.SCISSORS))
					return "player";
				else
					return "dealer";
			case SCISSORS:
				if (playerSelection.equals(CardType.ROCK))
					return "player";
				else
					return "dealer";
		}

		return null;
		
	}

}
