package uk.ac.sheffield.aca14ame;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;

/*
 * GraphicDisplay.java  	1.1 15/05/2015
 *
 */
 
/**
* GraphicDisplay.java 
*
* Implements an interface which specifies what a text or graphical display needs to do
*
* @version 1.1 15 May 2015
*
* @author Afam Ezechukwu (amezechukwu1@sheffield.ac.uk)
*/


public class GraphicDisplay extends JFrame implements Display{
	
	private static final long serialVersionUID = 1L;
	private static final int BOARD_SIZE=8;
	private JPanel grid = new JPanel(new GridLayout(BOARD_SIZE,BOARD_SIZE));
	private JPanel[][] squares = new JPanel[BOARD_SIZE][BOARD_SIZE];
	
	//Returns JPanel 
	public JPanel getGrid(){
		return grid;
	}
	
	//Returns JPanel from the array of JPanels
	public JPanel getSquares(int x, int y){
		return squares[x][y];
	}
	
	//Method which prints squares on board(panel). Each square itself is a panel
	public void printSquares(){
		for(int x=0; x<8; x++)
			for(int y=0; y<8; y++){
				int h = x+y;
				squares[x][y] = new JPanel(new BorderLayout());
				grid.add(squares[x][y]);
				if (h%2==0)
					squares[x][y].setBackground(Color.WHITE);
				else
					squares[x][y].setBackground(new Color(213,176,53));
			}
	}
	
	//Method returns a JLabel of a piece based on it's character
	public JLabel chessPiece(Piece piece){
		JLabel label;
		if (piece == null)
			label = new JLabel();
		else {
		switch(piece.getChar()){
			case 'P' : label = new JLabel("\u265F");
			break;
			case 'p' : label = new JLabel("\u2659");
			break;
			case 'N' : label = new JLabel("\u265E");
			break;
			case 'n' : label = new JLabel("\u2658");
			break;
			case 'B' : label = new JLabel("\u265D");
			break;
			case 'b' : label = new JLabel("\u2657");
			break;
			case 'R' : label = new JLabel("\u265C");
			break;
			case 'r' : label = new JLabel("\u2656");
			break;
			case 'Q' : label = new JLabel("\u265B");
			break;
			case 'q' : label = new JLabel("\u2655");
			break;
			case 'K' : label = new JLabel("\u265A");
			break;
			case 'k' : label = new JLabel("\u2654");
			break;
			default : label = new JLabel();
		}
		}
		
		//This sets the font of the label before it's returned
		label.setFont(new Font("Sans-Serif", Font.PLAIN, 50));
		return label;
	}
	
	//method implements void method in Display class
	public void showPiecesOnBoard(Piece[][] data) {	
		for(int x=0; x<8; x++)
			for(int y=0; y<8; y++){
					this.squares[x][y].add(this.chessPiece(data[x][y]), BorderLayout.CENTER);
					this.squares[x][y].validate();
			}
	}
}
