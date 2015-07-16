package uk.ac.sheffield.aca14ame;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * AggressivePlayer.java  	1.1 15/05/2015
 *
 *
 */

/**
* AgressivePlayer.java 
*
* Class which extends Player to represent an Aggressive Player
* This class makes it possible to play a game between a Human and AggressivePlayer
*
* @version 1.1 15 May 2015
*
* @author Afamefuna Ezechukwu  (amezechukwu1@sheffield.ac.uk)
*/

public class AggressivePlayer extends Player{
	//Variables
	private boolean move = false, whitePlayer = true, mouseClicked = true;
	private Point fromSquare, toSquare;
	private Piece a, opponentPiece, aggressivePiece;
	private int testI,testJ, i, j, x1, x2, y1, y2;
	private Pieces w = new Pieces(getBoard(),1), bl = new Pieces(getBoard(),0);
	private List<Move> moves = new ArrayList<Move>();
	private List<Piece> piece = new ArrayList<Piece>();
	private List<Integer> integer = new ArrayList<Integer>();
	private String name;
	
	public AggressivePlayer(String n, Pieces p, Board b, Player o) {	//Constructor
		super(n, p, b, o);
	}
	
	public void askName(){ 												// Method which asks for name of player.
		name = JOptionPane.showInputDialog("Please enter your given name: ");
	}
	
	public String getName(){											//Method which gets the name of the player.
		return name;
	}

	public List<Move> findMove(boolean test) {			//This method returns an array list of all the moves with a true or false test.
		List<Move> testMoves = new ArrayList<Move>();
		for (Move move : moves){
			if (move.getTest() == test){ // if the test for move is equal to the test in the parameter then add this move to the list
				testMoves.add(move);
			}
		}
		return testMoves;
	}
	
	public int pieceValue(Piece valueOfPiece) {		//Method which assigns a value to each piece based on its character
		int i;
		switch(valueOfPiece.getChar()){
			case 'P': case 'p': i=1;     break;
			case 'N': case 'n': i = 3;   break;
		    case 'B': case 'b': i = 3;   break;
		    case 'R': case 'r': i = 5;   break;
		    case 'Q': case 'q': i = 9;   break;
		    case 'K': case 'k': i = 10;  break;
		    default: i = 0;
		}
		return i;	   // This method returns the integer value of the piece
	}
	
	public Pieces getPieces(Piece p){	//Method which returns pieces based on the piece(colour).
		if (p.getColour() == 0)
			return bl;	//Black pieces
		else
			return w;	//White pieces
	}
	
	public boolean makeMove() {						//This method removes piece from one square and repaints it to another square.
		removeAndRepaint(toSquare.x, toSquare.y);
		removeAndRepaint(fromSquare.x, fromSquare.y);	//Refer to Player class for removeAndRepaint method
		this.getGraphics().getSquares(toSquare.x, toSquare.y).add(getGraphics().chessPiece(opponentPiece), BorderLayout.CENTER);
		this.getGraphics().getSquares(toSquare.x, toSquare.y).validate();
		return true;
	}
	
	public void aggressiveMove(){
		int range = (bl.getNumPieces());	//range is the number of pieces left for Aggressive Player, who is black.
		
		//get available moves to list
		for (int r=0; r<range; r++){			//Go through all the pieces left for Aggressive Player
			aggressivePiece = bl.getPiece(r);
			while(aggressivePiece.availableMoves()!=null) {
				for (Move m : aggressivePiece.availableMoves()){	//Go through all the available moves for the piece chosen in the current loop
					if (m != null){
						moves.add(m);	// add all the available moves for that piece to the array list of moves
					}
				}
				break;
			}
		}
		// if all moves are false i.e no piece to take, then make random move
		if (findMove(false).size() == moves.size()){
			System.out.println();
			Random r = new Random();
			Move random = moves.get(r.nextInt(moves.size())); // Make random move using the size of the array list
			x1=random.getX(); y1=random.getY(); 
			x2=random.getI(); y2=random.getJ();
			aggressivePiece = getBoard().getPiece(x1, y1);
		}
		else{
			for(Move trueMoves : findMove(true)){	//Go through all the true moves in the array list
				//possible to- coordinates
				testI = trueMoves.getI();
				testJ = trueMoves.getJ();
				Piece b = getBoard().getPiece(testI, testJ);	//Get the piece occupied in the square it wants to move to
				piece.add(b);									//Add this piece to array list of Piece
				integer.add(pieceValue(piece.get(piece.size()-1)));	//And add it's integer value to array list of integer
			}
			int highestValue = Collections.max(integer);		//Get the max value from the array list of integers
			int index = integer.indexOf(highestValue);			//Get the index of max value in the array list of integers
			Move aggressiveMove = findMove(true).get(index);	// The move would then be the index of all the possible true moves
			x1=aggressiveMove.getX(); y1=aggressiveMove.getY(); 
			x2=aggressiveMove.getI(); y2=aggressiveMove.getJ();
			aggressivePiece = getBoard().getPiece(x1, y1);
		}
		
		opponentPiece = getBoard().getPiece(x2, y2);
		opponentPiece = aggressivePiece;
		aggressivePiece = null;
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		removeAndRepaint(x2,y2);
		removeAndRepaint(x1,y1);
		this.getGraphics().getSquares(x2, y2).add(getGraphics().chessPiece(opponentPiece), BorderLayout.CENTER);
		this.getGraphics().getSquares(x2, y2).validate();
		moveChessPiece(x1, y1, x2, y2, this.getName(), "Aggressive Player", w, bl, mouseClicked); // Refer to Player.java for moveChessPiece method
		moves.clear();
		piece.clear();
		integer.clear();	//clear all the lists when done
	}
	
	public void mouseClicked(MouseEvent e) {
		if (mouseClicked){
			Object panelSource = e.getSource();	//panelSource refers to the Object on which Mouse Event initially occurred
			JPanel panel = (JPanel)panelSource;
			int x = (panel.getX()/59);		//Divided by 59 because of the width and height of the grid set in Chess.java
			int y = (panel.getY()/59);
			this.move = !this.move;
			if (this.move){
				fromSquare = new Point(y,x);
				a = getBoard().getPiece(fromSquare.x, fromSquare.y); 	//Get the piece at square clicked on
				if (a == null)
					this.move = !this.move;
				else if (a != null && this.whitePlayer && a.getColour() == 0)
					this.move = !this.move;
				else if (a != null && !this.whitePlayer && a.getColour() == 1)
					this.move = !this.move;
				if(this.move)
					this.indicateSelected(false, fromSquare);	//Refer to Player.java for indicatedSelected method
			}
			else {
				toSquare = new Point(y,x);
				if (!fromSquare.equals(toSquare)){	//Condition which checks squares clicked on are different
					if (a != null)
						if (this.valid(fromSquare, toSquare, i, j)){						//Refer to Player.java for valid method
							opponentPiece = getBoard().getPiece(toSquare.x, toSquare.y);
							opponentPiece = a;
							a = null;
							this.makeMove();
							
							// Refer to Player.java for moveChessPiece method
							moveChessPiece(fromSquare.x, fromSquare.y, toSquare.x, toSquare.y, this.getName(), "Aggressive player", w, bl, mouseClicked);
							aggressiveMove();
						}
						else{
							JOptionPane.showMessageDialog(getGraphics(), "Invalid Move.", "Warning", JOptionPane.ERROR_MESSAGE);
							this.indicateSelected(true, fromSquare);
						}
				}
				else
					this.indicateSelected(true, fromSquare);
			}
		}
	}

	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
}
