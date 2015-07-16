package uk.ac.sheffield.aca14ame;

import java.util.*;

/*
 * Bishop.java  	1.1 01/04/2015
 *
 */

/**
* Bishop.java 
*
* Concrete class to represent a bishop
*
* @version 1.1 1 April 2015
*
* @author Afam Ezechukwu  (amezechukwu1@sheffield.ac.uk)
*/

public class Bishop extends Piece {
	
	// constructor
	public Bishop (int ix, int iy, int c, Board b) {
	    super(PieceCode.BISHOP, ix, iy, c, b);
	}
	
	// method implements abstract method in Piece class
	public ArrayList<Move> availableMoves() {
		    if (getColour()==PieceCode.WHITE) return whiteBishop();
		    else return blackBishop();
	}
	
	//method to return Vector of legal moves for white bishop
	private ArrayList<Move> whiteBishop() {
		
			 int x = getX();
			 int y = getY();
			 
			 //create a new vector to store legal moves
			 ArrayList<Move> v = new ArrayList<Move>();
			 
			// set up m to refer to a Move object
			 Move m = null;
			 
			 rulesForBishop(x,y,m,v);
			  
			  if (v.isEmpty()) return null;
			  return v;
	}
	
	// method to return Vector of legal moves for a black bishop
	private ArrayList<Move> blackBishop() {
		int x = getX();
		int y = getY();
		 
		//create a new vector to store legal moves
		ArrayList<Move> v = new ArrayList<Move>();
		 
		// set up m to refer to a Move object
		Move  m = null;
		
		rulesForBishop(x,y,m,v);
		if (v.isEmpty()) return null;
		  return v;
	}
	
	public void rulesForBishop(int x, int y, Move m,ArrayList<Move> v) {
		 
		 //first legal move is to go from x,y to a diagonal coordinate x+i,y+i
		 for(int i = 1; i<8; i++) {
			 
			 //if x+i,y+i is unoccupied
			 if(!getBoard().outOfRange(x+i, y+i))
				 if(!getBoard().occupied(x+i, y+i)) {
					 m = new Move(this, x,y,x+i,y+i,false);
					 v.add(m);
					  
				  }
				  else 
					  //if x+i, y+i is occupied by a white/black piece
					  if(getBoard().occupied(x+i, y+i) && getBoard().getPiece(x+i, y+i).getColour()!=this.getColour()) {
					  m = new Move(this, x,y,x+i,y+i,true);
					  v.add(m);
					  break;
				  }  
					  else
						  break;
		  }
		  
		//second legal move is to go from x,y to a diagonal coordinate x-i,y-i
		  for(int i = 1; i<8; i++) {
			  
			//if x-i,y-i is unoccupied
			  if(!getBoard().outOfRange(x-i, y-i))
				  if(!getBoard().occupied(x-i, y-i)) {
					  m = new Move(this, x,y,x-i,y-i,false);
					  v.add(m);  
				  }
				  else
					 //if x-i, y-i is occupied by a white/black piece
					  if(getBoard().occupied(x-i, y-i) && getBoard().getPiece(x-i, y-i).getColour()!=this.getColour()) {
					  m = new Move(this, x,y,x-i,y-i,true);
					  v.add(m);
					  break;
				  }  
					  else
						  break;
		  }
		  
		//third legal move is to go from x,y to a diagonal coordinate x+i,y-i
		  for(int i = 1; i<8; i++) {
			//if x+i,y-i is unoccupied
			  if(!getBoard().outOfRange(x+i, y-i))
				  if(!getBoard().occupied(x+i, y-i)) {
					  m = new Move(this, x,y,x+i,y-i,false);
					  v.add(m);  
				  }
				  else
					 //if x+i, y-i is occupied by a white/black piece
					  if(getBoard().occupied(x+i, y-i) && getBoard().getPiece(x+i, y-i).getColour()!=this.getColour()) {
					  m = new Move(this, x,y,x+i,y-i,true);
					  v.add(m);
					  break;
				  } 
					  else
						  break;
		  }
		  
		//fourth legal move is to go from x,y to a diagonal coordinate x-i,y+i
		  for(int i = 1; i<8; i++) {
			//if x-i,y+i is unoccupied
			  if(!getBoard().outOfRange(x-i, y+i))
				  if(!getBoard().occupied(x-i, y+i)) {
					  m = new Move(this, x,y,x-i,y+i,false);
					  v.add(m);	 
				  }
				  else
					 //if x-i, y+i is occupied by a white/black piece
					  if(getBoard().occupied(x-i, y+i) && getBoard().getPiece(x-i, y+i).getColour()!=this.getColour()) {
					  m = new Move(this, x,y,x-i,y+i,true);
					  v.add(m);
					  break;
				  }
					  else
						  break;
		  }  
	}
}
