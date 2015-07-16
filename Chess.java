package uk.ac.sheffield.aca14ame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

/*
 * Chess.java  	1.2 15/05/2015
 *
 */

/**
* Chess.java 
*
* A class which runs the Chess game
*
* @version 1.2 15 May 2015
*
* @author Afamefuna Ezechukwu  (amezechukwu1@sheffield.ac.uk)
*/

public class Chess {
	
	public static final String[] PLAYER = {"Aggressive Player", "Random Player", "Human Player"};	//array of Strings
	
	public static void main(String[] args){
		
		String firstInstruction = "\tPlease note that the player who is asked for their name first would be assigned "
				+ "to the white pieces and the opponent to the black pieces";
		
		String secondInstruction = "\tIn this chess game there is no checking, check mate, en passe, castling and pawn promotion.\n\n "
				+ "You win the game by taking the King";
		
		JOptionPane.showMessageDialog(null, firstInstruction, "Instructions", JOptionPane.PLAIN_MESSAGE);
		JOptionPane.showMessageDialog(null, secondInstruction, "Instructions", JOptionPane.PLAIN_MESSAGE);
		
		Board board = new Board();		//Board
		
		Player one = null;		//Player which is null
		
		String choice = (String)JOptionPane.showInputDialog(null, 
		        "Choose a player to play against",
		        "Choose Opponent",
		        JOptionPane.QUESTION_MESSAGE, 
		        null, 
		        PLAYER, 
		        PLAYER[0]);					//Option to choose player to play against
		
		if (choice == PLAYER[0]){
			one =  new AggressivePlayer("",null,board,null);	//Pieces and opponent (Aggressive Player) have already been set in AgressivePlayer class
			one.askName();
			JOptionPane.showMessageDialog(null, 
					one.getName()+" is white and Aggressive Player is black. \n"+one.getName()+" starts.", 
					"Instructions", 
					JOptionPane.PLAIN_MESSAGE);
		}
		else if (choice == PLAYER[1]){
			one = new RandomPlayer("", null, board, null);		//Pieces and opponent (Random Player) have already been set in RandomPlayer class
			one.askName();
			JOptionPane.showMessageDialog(null, 
					one.getName()+" is white and Random Player is black. \n"+one.getName()+" starts.", 
					"Instructions", 
					JOptionPane.PLAIN_MESSAGE);
		}
		else if (choice == PLAYER[2]){
			one = new HumanPlayer("", null, board, null);		//Pieces and opponent (Human Player) have already been set in HumanPlayer class
			one.askName();
			JOptionPane.showMessageDialog(null, 
					one.getName()+" is white and "+((HumanPlayer) one).getNameOfOpponent()+" is black. \n"+one.getName()+" starts.", 
					"Instructions", 
					JOptionPane.PLAIN_MESSAGE);
		}

		GraphicDisplay a = one.getGraphics();		//getGraphics() returns Graphic Display, refer to GraphicDisplay and Player classes.
		
		a.getContentPane();	//Container
		a.setSize(600,525); // size of frame
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenDimensions = toolkit.getScreenSize();
		a.setLocation(new Point(screenDimensions.width/4,screenDimensions.height/4));	//Location on screen
		a.setBackground(new Color(200, 200, 200)); //Background colour
		a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		a.setTitle("Java Chess Game"); // Title of frame
		a.setResizable(false);  // Not resizable
		
		a.getContentPane().setLayout(null);	//Layout of container
		
		a.getGrid().setBounds(3, 3, 480, 480);				//Refer to GraphicDisplay for getGrid() method.
		a.getGrid().setBackground(new Color(255,255,255));
		Border blackline = BorderFactory.createLineBorder(Color.black);
		a.getGrid().setBorder(blackline);
		a.getContentPane().add(a.getGrid());
		
		a.printSquares();			//Refer to GraphicDisplay class for printSquares method.
		one.addMouseListener();		//Refer to Player class for this method

		a.showPiecesOnBoard(board.getData());	//Refer to GraphicDisplay class for this method
		a.setVisible(true);	//set Visible
	}
}
