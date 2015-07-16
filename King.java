package uk.ac.sheffield.aca14ame;

import java.util.*;

/*
 * King.java  	1.1 01/04/2015
 *
 */

/**
* King.java
*
* Concrete class to represent a king
*
* @version 1.1 1 April 2015
*
* @author Afam Ezechukwu  (amezechukwu1@sheffield.ac.uk)
*/

public class King extends Piece {
	
	public King (int ix, int iy, int c, Board b) {
	    super(PieceCode.KING, ix, iy, c, b);
	  }
	
	// method implements abstract method in Piece class
	public ArrayList<Move> availableMoves() {
	    if (getColour()==PieceCode.WHITE) return whiteKing();
	    else return blackKing();
	 }
	
	// method to return Vector of legal moves for a white king
	private ArrayList<Move> whiteKing() {
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
	
	// method to return Vector of legal moves for a black king
	private ArrayList<Move> blackKing() {
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
	
	//method that gives all legal moves for a king
	private void rules(int x, int y, Move m, ArrayList<Move> v) {
		//first legal move is to go from x,y to x,y+1
	    if(!getBoard().outOfRange(x, y+1))
	    	
	    	  //if x,y+1 is unoccupied
			  if (!getBoard().occupied(x, y+1))  {
				  m = new Move(this, x,y,x,y+1,false);
				  v.add(m);
			  }
	    	  
	    	//if x,y+1 is occupied by a black/white piece
			  else if (getBoard().occupied(x, y+1) && getBoard().getPiece(x, y+1).getColour()!=this.getColour()) {
				  m = new Move(this, x,y,x,y+1,true);
				  v.add(m);
			  }
	    
	  //second legal move is to go from x,y to x,y-1
	    if(!getBoard().outOfRange(x, y-1))
	    	
	    	//if x,y-1 is unoccupied
			  if (!getBoard().occupied(x, y-1))  {
				  m = new Move(this, x,y,x,y-1,false);
				  v.add(m);
			  }
	    	//if x,y-1 is occupied by a black/white piece
			  else if (getBoard().occupied(x, y-1) && getBoard().getPiece(x, y-1).getColour()!=this.getColour()) {
				  m = new Move(this, x,y,x,y-1,true);
				  v.add(m);
			  }
	    
	  //third legal move is to go from x,y to x+1,y
	    if(!getBoard().outOfRange(x+1, y))
	    	
	    	//if x+1,y is unoccupied
			  if (!getBoard().occupied(x+1, y))  {
				  m = new Move(this, x,y,x+1,y,false);
				  v.add(m);
			  }
	        //if x+1,y is occupied by a black/white piece
			  else if (getBoard().occupied(x+1, y) && getBoard().getPiece(x+1, y).getColour()!=this.getColour()) {
				  m = new Move(this, x,y,x+1,y,true);
				  v.add(m);
			  }
	    
	  //fourth legal move is to go from x,y to x-1,y
	    if(!getBoard().outOfRange(x-1, y))
	    	
	    	//if x-1,y is unoccupied
			  if (!getBoard().occupied(x-1, y))  {
				  m = new Move(this, x,y,x-1,y,false);
				  v.add(m);
			  }
	    	//if x-1,y is occupied by a black/white piece
			  else if (getBoard().occupied(x-1, y) && getBoard().getPiece(x-1, y).getColour()!=this.getColour()) {
				  m = new Move(this, x,y,x-1,y,true);
				  v.add(m);
			  }
	    
	  //fifth legal move is to go from x,y to x+1,y+1
	    if(!getBoard().outOfRange(x+1, y+1))
	    	
	    	//if x+1,y+1 is unoccupied
			  if (!getBoard().occupied(x+1, y+1))  {
				  m = new Move(this, x,y,x+1,y+1,false);
				  v.add(m);
			  }
	    	//if x+1,y+1 is occupied by a black/white piece
			  else if (getBoard().occupied(x+1, y+1) && getBoard().getPiece(x+1, y+1).getColour()!=this.getColour()) {
				  m = new Move(this, x,y,x+1,y+1,true);
				  v.add(m);
			  }
	    
	  //sixth legal move is to go from x,y to x-1,y-1
	    if(!getBoard().outOfRange(x-1, y-1))
	    	
	    	//if x-1,y-1 is unoccupied
			  if (!getBoard().occupied(x-1, y-1))  {
				  m = new Move(this, x,y,x-1,y-1,false);
				  v.add(m);
			  }
	    	//if x-1,y-1 is occupied by a black/white piece
			  else if (getBoard().occupied(x-1, y-1) && getBoard().getPiece(x-1, y-1).getColour()!=this.getColour()) {
				  m = new Move(this, x,y,x-1,y-1,true);
				  v.add(m);
			  }
	    
	  //seventh legal move is to go from x,y to x+1,y-1
	    if(!getBoard().outOfRange(x+1, y-1))
	    	
	    	//if x+1,y-1 is unoccupied
			  if (!getBoard().occupied(x+1, y-1))  {
				  m = new Move(this, x,y,x+1,y-1,false);
				  v.add(m);
			  }
	    	//if x+1,y-1 is occupied by a black/white piece
			  else if (getBoard().occupied(x+1, y-1) && getBoard().getPiece(x+1, y-1).getColour()!=this.getColour()) {
				  m = new Move(this, x,y,x+1,y-1,true);
				  v.add(m);
			  }
	    
	  //eight legal move is to go from x,y to x-1,y+1
	    if(!getBoard().outOfRange(x-1, y+1))
	    	
	    	//if x-1,y+1 is unoccupied
			  if (!getBoard().occupied(x-1, y+1))  {
				  m = new Move(this, x,y,x-1,y+1,false);
				  v.add(m);
			  }
	    	//if x-1,y+1 is occupied by a black/white piece
			  else if (getBoard().occupied(x-1, y+1) && getBoard().getPiece(x-1, y+1).getColour()!=this.getColour()) {
				  m = new Move(this, x,y,x-1,y+1,true);
				  v.add(m);
			  }
	}
}
