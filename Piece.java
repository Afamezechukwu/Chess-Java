package uk.ac.sheffield.aca14ame;

import java.awt.Point;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/*
 * Player.java  	1.2 15/05/2015
 *
 * Copyright (c) University of Sheffield 2015
 */

/**
* Player.java 
*
* Abstract class to represent a chess player
*
* @version 1.2 15 May 2015
*
* @author Richard Clayton  (r.h.clayton@sheffield.ac.uk), Steve Maddock (s.c.maddock@sheffield.ac.uk), Afam Ezechukwu (amezechukwu1@sheffield.ac.uk)
*/
public abstract class Player implements MouseListener{

  public static final int BLACK = 0;
  public static final int WHITE = 1;
  private String name;
  private Pieces pieces;
  private Board board;
  private Player opponent;
  private GraphicDisplay graphics = new GraphicDisplay();

  public Player (String n, Pieces p, Board b, Player o) {
    name = n;
    pieces = p;
    board = b;
    opponent = o;
  }

  public Board getBoard() {
    return board;
  }

  public Player getOpponent() {
    return opponent;
  }
  
  //Method to get Graphics Display
  public GraphicDisplay getGraphics(){
	return graphics;
  }
  
  //This method adds MouseListener to all the squares created in printSquares() of GraphicDisplay
  public void addMouseListener(){
	for (int x=0; x<8; x++)
		for (int y=0; y<8; y++){
				graphics.getSquares(x, y).addMouseListener(this);
		}
  }
  
  /* 
   * Boolean method which checks if the move made from one square to another is valid
   * It returns true if the move is valid
   */
  public boolean valid(Point fromSquare, Point toSquare, int i, int j){
	boolean valid = false;
	if (getBoard().getPiece(fromSquare.x, fromSquare.y).availableMoves()==null)
		valid = false;
	else {
		for (Move m : getBoard().getPiece(fromSquare.x, fromSquare.y).availableMoves()){
			if (m != null){
				//get the possible to-coordinates
				i = m.getI();
				j = m.getJ();	
			}
			//break out of for loop when to-coordinates are the same as in Move m
			if (toSquare.x == i && toSquare.y == j){
				valid = true;
				break;
			}
		}
	}
	return valid;
  }
  
  //Method shows the piece(label) that we selected(or clicked on)
  public void indicateSelected(boolean selected, Point from){
		for(int i=0; i<getGraphics().getSquares(from.x, from.y).getComponentCount(); i++)
			if(getGraphics().getSquares(from.x, from.y).getComponent(i).getClass().toString().indexOf("JLabel") > -1) {
				JLabel label = (JLabel)getGraphics().getSquares(from.x, from.y).getComponent(i);
				label.setEnabled(selected);
			}
  }
  
  /*
   * This method moves a Piece on the Board from one position to another and changes the state of mouseClicked
   * It's also through this method that the game ends
   */
  public void moveChessPiece(int x1, int y1, int x2, int y2, String white, String black, Pieces w, Pieces bl, boolean mouseClicked){
	  //if the position to move to is not occupied
	  if (!getBoard().occupied(x2, y2)) {
			getBoard().getPiece(x1, y1).setPosition(x2, y2);	
			getBoard().setPosition(x2, y2, getBoard().getPiece(x1, y1));	
			getBoard().remove(x1, y1);
			mouseClicked = !mouseClicked;
		}
		else {
			//If the piece at from position has a different colour to the piece at another position on Board
			if (getBoard().getPiece(x2, y2).getColour() 
					!= getBoard().getPiece(x1, y1).getColour()){ 
				char king = getBoard().getPiece(x2, y2).getChar();	//Get the character of the piece
				if ((king == 'k') || (king == 'K')){	//If the piece is a king, end the game and tell us who won
					if (king == 'k')
						JOptionPane.showMessageDialog(getGraphics(), "Game Over ! "+black+" won", "Message", JOptionPane.PLAIN_MESSAGE);
					else
						JOptionPane.showMessageDialog(getGraphics(), "Game Over ! "+white+" won", "Message", JOptionPane.PLAIN_MESSAGE);
					System.exit(0);
				}
				//Tells who lose a piece based on the names in the parameters
				if (getBoard().getPiece(x2, y2).getColour()==w.getPiece(0).getColour())
					JOptionPane.showMessageDialog(getGraphics(), white+" just lost a piece", "Message", JOptionPane.PLAIN_MESSAGE);
				else if (getBoard().getPiece(x2, y2).getColour()==bl.getPiece(0).getColour())
					JOptionPane.showMessageDialog(getGraphics(), black+" just lost a piece", "Message", JOptionPane.PLAIN_MESSAGE);
				this.getPieces(getBoard().getPiece(x2, y2)).delete(getBoard().getPiece(x2, y2));	
			}
			getBoard().getPiece(x1, y1).setPosition(x2, y2);	
			getBoard().setPosition(x2, y2, getBoard().getPiece(x1, y1));	
			getBoard().remove(x1, y1);
			mouseClicked = !mouseClicked;
		}
  }
  
  //This method removes a piece(label) from a square(panel) and repaints it to another square
  public void removeAndRepaint(int x, int y){
	  for(int i = 0; i < this.getGraphics().getSquares(x, y).getComponentCount(); i++)
          if(this.getGraphics().getSquares(x, y).getComponent(i).getClass().toString().indexOf("JLabel") > -1){
                this.getGraphics().getSquares(x, y).remove(i);
                this.getGraphics().getSquares(x, y).repaint();
          }
  }
  
  public void setOpponent(Player p) {
    opponent = p;
  }

  public Pieces getPieces() {
    return pieces;
  }
  
  public abstract Pieces getPieces(Piece p);
  
  //Abstract methods which I added
  public abstract boolean makeMove();
  public abstract void askName();
  public abstract String getName();
 
  public void deletePiece(Piece p) {
    pieces.delete(p);
  }

  public String toString() {
    return name;
  }
}
