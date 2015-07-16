package uk.ac.sheffield.aca14ame;

/*
 * Move.java  	1.1 12/04/2015
 *
 */

/**
* Move.java 
*
* 
*
* @version 1.1 12 April 2015
*
* @author Afamefuna Ezechukwu  (amezechukwu1@sheffield.ac.uk)
*/

public class Move {
	private Piece a;
	private int fromX, fromY, toX, toY;
	private boolean test;
	
	//constructor
	public Move(Piece piece, int x, int y, int i, int j, boolean b) {
		a = piece;
		fromX = x;
		fromY = y;
		toX = i;
		toY = j;
		test=b;
	}
	
	public int getX() {
		return fromX;
	}
	
	public int getY() {
		return fromY;
	}
	
	//get x-to-coordinate
	public int getI() {
		return toX;
	}
	
	//get y-to-coordinate
	 public int getJ() {
		 return toY;
	 }
	 
	 public Piece getPiece() {
		 return a;
	 }
	 
	 public boolean getTest(){
		 return test;
	 }
	 
	 public String toString() {
		return a+" from "+fromX+","+fromY+" to "+toX+","+toY+" "+test;
		 
	 }
}
