package uk.ac.sheffield.aca14ame;

import java.util.*;

/*
 * Knight.java  	1.1 01/04/2015
 *
 */

/**
* Knight.java 
*
* Concrete class to represent a knight
*
* @version 1.1 1 April 2015
*
* @author Afam Ezechukwu  (amezechukwu1@sheffield.ac.uk)
*/

public class Knight extends Piece {
	
	public Knight (int ix, int iy, int c, Board b) {
	    super(PieceCode.KNIGHT, ix, iy, c, b);
	  }
	
	// method implements abstract method in Piece class
	public ArrayList<Move> availableMoves() {
	    if (getColour()==PieceCode.WHITE) return whiteKnight();
	    else return blackKnight();
	}
	
	// method to return Vector of legal moves for a white knight
	private ArrayList<Move> whiteKnight() {
		  int x = getX();
		  int y = getY();
		  
		  //create a new vector to store legal moves
		  ArrayList<Move> v = new ArrayList<Move>();
		  
		  // set up m to refer to a Move object
		  Move m = null;
		  
		  //legal moves
		  rules(x,y,m,v);
		  
		  if (v.isEmpty()) return null;
		  return v;
	  }
	  
	// method to return Vector of legal moves for a black knight
	  private ArrayList<Move> blackKnight() {
		  int x = getX();
		  int y = getY();
		  
		//create a new vector to store legal moves
		  ArrayList<Move> v = new ArrayList<Move>();
		  
		  // set up m to refer to a Move object
		  Move m = null;
		  
		  rules(x,y,m,v);

		  if (v.isEmpty()) return null;
		  return v;  
	  }
	  
	  //method that gives all legal moves for a knight
	  private void rules(int x, int y, Move m, ArrayList<Move> v){
		  //first legal move is from x,y to x+1,y+2
		  if(!getBoard().outOfRange(x+1, y+2))
			  
			  //if x+1,y+2 is unoccupied
			  if (!getBoard().occupied(x+1, y+2))  {
				  m = new Move(this, x,y,x+1,y+2,false);
				  v.add(m);
			  }
		  
		//if x+1,y+2 is occupied by a piece different to it's colour
			  else if (getBoard().occupied(x+1, y+2) && getBoard().getPiece(x+1, y+2).getColour()!=this.getColour()) {
				  m = new Move(this, x,y,x+1,y+2,true);
				  v.add(m);
			  }
		//second legal move is from x,y to x+1,y-2
		  if(!getBoard().outOfRange(x+1, y-2))
			  
			//if x+1,y-2 is unoccupied
			  if (!getBoard().occupied(x+1, y-2))  {
				  m = new Move(this, x,y,x+1,y-2,false);
			      v.add(m);
			  }
		  
		//if x+1,y-2 is occupied by a piece different to it's colour
			  else if (getBoard().occupied(x+1, y-2) && getBoard().getPiece(x+1, y-2).getColour() !=this.getColour()) {
				  m = new Move(this, x,y,x+1,y-2,true);
			      v.add(m);
			  }
		//third legal move is from x,y to x+2,y+1
		  if(!getBoard().outOfRange(x+2, y+1))
			  
			//if x+2,y+1 is unoccupied
		      if (!getBoard().occupied(x+2, y+1)) { 
				  m = new Move(this, x,y,x+2,y+1,false);
			      v.add(m);
			  }
		  
		//if x+2,y+1 is occupied by a piece different to it's colour
		      else if (getBoard().occupied(x+2, y+1) && getBoard().getPiece(x+2, y+1).getColour() !=this.getColour()) {
		    	  m = new Move(this, x,y,x+2,y+1,true);
			      v.add(m);
		      }
		//fourth legal move is from x,y to x+2,y-1
		  if(!getBoard().outOfRange(x+2, y-1))
			  
			//if x+2, y-1 is unoccupied
		  	  if(!getBoard().occupied(x+2, y-1)) { 
				  m = new Move(this, x,y,x+2,y-1,false);
			      v.add(m);
			  }
		  
		//if x+2, y-1 is occupied by a piece different to it's colour
		  	  else if (getBoard().occupied(x+2, y-1) && getBoard().getPiece(x+2, y-1).getColour() !=this.getColour()) {
		  		  m = new Move(this, x,y,x+2,y-1,true);
			      v.add(m);
		  	  }
	  
		//fifth legal move is from x,y to x-1,y+2
		  if(!getBoard().outOfRange(x-1, y+2))
			//if x-1,y+2 is unoccupied
			  if(!getBoard().occupied(x-1, y+2)) { 
				  m = new Move(this, x,y,x-1,y+2,false);
			      v.add(m);
			  }
		//if x-1,y+2 is occupied by a piece different to it's colour
			  else if (getBoard().occupied(x-1, y+2) && getBoard().getPiece(x-1, y+2).getColour() !=this.getColour()) {
				  m = new Move(this, x,y,x-1,y+2,true);
			      v.add(m);
			  }
		//sixth legal move is from x,y to x-1,y-2
		 if(!getBoard().outOfRange(x-1, y-2))
			 
			//if x-1.y-2 is unoccupied
			 if(!getBoard().occupied(x-1, y-2)) { 
				  m = new Move(this, x,y,x-1,y-2,false);
			      v.add(m);
			  }
		//if x-1.y-2 is occupied by a piece different to it's colour
			 else if (getBoard().occupied(x-1, y-2) && getBoard().getPiece(x-1, y-2).getColour() !=this.getColour()) {
			  	  m = new Move(this, x,y,x-1,y-2,true);
			  	  v.add(m);
			 }
		//seventh legal move is from x,y to x-2,y+1
		 if(!getBoard().outOfRange(x-2, y+1))
			 
			//if x-2,y+1 is unoccupied
			 if(!getBoard().occupied(x-2, y+1)) { 
				  m = new Move(this, x,y,x-2,y+1,false);
			      v.add(m);
			  	}
		 
		//if x-2,y+1 is occupied by a piece different to it's colour
			 else if (getBoard().occupied(x-2, y+1) && getBoard().getPiece(x-2, y+1).getColour() !=this.getColour()) {
			     m = new Move(this, x,y,x-2,y+1,true);
		         v.add(m);
			 }
		//eighth legal move is from x,y to x-2,y-1
		 if(!getBoard().outOfRange(x-2, y-1))
			 
			//if x-2,y-1 is unoccupied
			 if(!getBoard().occupied(x-2, y-1)) { 
				  m = new Move(this, x,y,x-2,y-1,false);
			      v.add(m);
			  }
		 
		//if x-2,y-1 is occupied by a piece different to it's colour
			 else if (getBoard().occupied(x-2, y-1) && getBoard().getPiece(x-2, y-1).getColour() !=this.getColour()) {
			     m = new Move(this, x,y,x-2,y-1,true);
		         v.add(m);
		  }
	  }
}
