package uk.ac.sheffield.aca14ame;

import java.util.*;

/*
 * Rook.java  	1.1 07/04/2015
 *
 */

/**
* Rook.java 
*
* Concrete class to represent a rook
*
* @version 1.1 7 April 2015
*
* @author Afam Ezechukwu  (amezechukwu1@sheffield.ac.uk)
*/

public class Rook extends Piece {
	
	public Rook (int ix, int iy, int c, Board b) {
	    super(PieceCode.ROOK, ix, iy, c, b);
	  }
	
	// method implements abstract method in Piece class
	public ArrayList<Move> availableMoves() {
	    if (getColour()==PieceCode.WHITE) return whiteRook();
	    else return blackRook();
	}
	
	// method to return Vector of legal moves for a white rook
	private ArrayList<Move> whiteRook() {
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
	  
	  // method to return Vector of legal moves for a black rook
	  private ArrayList<Move> blackRook() {
		    int x = getX();
		    int y = getY();
		    
		    ArrayList<Move> v = new ArrayList<Move>();
		      
		    // set up m to refer to a Move object  
		    Move m = null;
		    
		    //legal moves
		    rules(x,y,m,v);
		    
		    if (v.isEmpty()) return null;
		    return v;
	  }
	  
	  //method that gives all moves for a rook
	  private void rules(int x, int y, Move m, ArrayList<Move> v ){
		//first legal move from x,y to x,y+i
		  for(int i = 1; i<8; i++) {
			  if(!getBoard().outOfRange(x, y+i))
				  //if x,y+i is unoccupied
				  if(!getBoard().occupied(x, y+i)) {
					  m = new Move(this, x,y,x,y+i,false);
					  v.add(m);
		
				  }
				  else 
					  //if x,y+i is occupied with a piece of a different colour
					  if(getBoard().occupied(x, y+i) && getBoard().getPiece(x, y+i).getColour()!=this.getColour()) {
					  m = new Move(this, x,y,x,y+i,true);
					  v.add(m);
					  break;
				  }  
					  else
						  break;
		  }
	    
		  //second legal move is to go from x,y-i
	    for(int i = 1; i<8; i++) {
	    	
	    		//if x,y-i is unoccupied
			  if(!getBoard().outOfRange(x, y-i))
				  if(!getBoard().occupied(x, y-i)) {
					  m = new Move(this, x,y,x,y-i,false);
					  v.add(m);

				  }
				  else
					  //if x,y-i is unoccupied
					  if(getBoard().occupied(x, y-i) && getBoard().getPiece(x, y-i).getColour()!=this.getColour()) {
					  m = new Move(this, x,y,x,y-i,true);
					  v.add(m);
					  break;
				  }
					  else
						  break;
		  }
	    
	    //third legal move is to go from x,y to x+i,y
	    for(int i = 1; i<8; i++) {
	    	
	    		//if x+i,y is unoccupied
			  if(!getBoard().outOfRange(x+i, y))
				  if(!getBoard().occupied(x+i, y)) {
					  m = new Move(this, x,y,x+i,y,false);
					  v.add(m);
				  }
				  else 
					  //if x+i,y is occupied by a black/white piece
					  if(getBoard().occupied(x+i, y) && getBoard().getPiece(x+i, y).getColour()!=this.getColour()) {
					  m = new Move(this, x,y,x+i,y,true);
					  v.add(m);
					  break;
				  } 
					  else
						  break;
		  }
	    //fourth legal move is to go from x,y to x-i,y
	    for(int i = 1; i<8; i++) {
	    	
	    		//if x-i,y is unoccupied
			  if(!getBoard().outOfRange(x-i, y))
				  if(!getBoard().occupied(x-i, y)) {
					  m = new Move(this, x,y,x-i,y,false);
					  v.add(m);
	
				  }
				  else
					  //if x-i,y is occupied by a white/black piece
					  if(getBoard().occupied(x-i, y) && getBoard().getPiece(x-i, y).getColour()!=this.getColour()) {
					  m = new Move(this, x,y,x-i,y,true);
					  v.add(m);
					  break;
				  }  
					  else
						  break;
		  }
	  }
}
