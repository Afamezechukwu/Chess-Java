package uk.ac.sheffield.aca14ame;
import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/*
 * RandomPlayer.java  	1.1 15/05/2015
 *
 */

/**
* RandomPlayer.java 
*
* Class which extends Player to represent a Random Player
* This class makes it possible to play a game between a Human and RandomPlayer
* 
* @version 1.1 15 May 2015
*
* @author Afamefuna Ezechukwu  (ameiechukwu1@sheffield.ac.uk)
*/

public class RandomPlayer extends Player implements MouseListener{
	
	private boolean move = false, whitePlayer = true, mouseClicked = true;
	private Point fromSquare, toSquare;
	private Piece a, opponentPiece, randomPiece;
	private Pieces w = new Pieces(getBoard(),1), bl = new Pieces(getBoard(),0);
	private int x1, x2, y1, y2, i, j;
	private String name;

	public RandomPlayer(String n, Pieces p, Board b, Player o) {	//Constructor
		super(n, p, b, o);
	}
	
	public void askName(){											// Method which asks for name of player.
		name = JOptionPane.showInputDialog("Please enter your given name: ");
	}
	
	public String getName(){									//Method which gets the name of the player.
		return name;
	}

	public Pieces getPieces(Piece p){							//Method which returns pieces based on the piece(colour).
		if (p.getColour() == 0)
			return bl;
		else
			return w;
	}
	
	public boolean makeMove() {									//This method removes piece from one square and repaints it to another square.
		removeAndRepaint(toSquare.x, toSquare.y);
		removeAndRepaint(fromSquare.x, fromSquare.y);			//Refer to Player.java
		this.getGraphics().getSquares(toSquare.x, toSquare.y).add(getGraphics().chessPiece(opponentPiece), BorderLayout.CENTER);
		this.getGraphics().getSquares(toSquare.x, toSquare.y).validate();	//Refer to GraphicDisplay 
		return true;
	}
	
	public void randomMove(){
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		do {
			int range = (bl.getNumPieces());		//range is the number of pieces of random player, who is black
			int randomNum = (int)(Math.random()*range);	//randomNum is a random number from 0 to range
			randomPiece = bl.getPiece(randomNum);		//randomPiece is the piece chosen due to generated random number
		}
		while (randomPiece.availableMoves()==null);		//Until there are available moves for the piece
		Random r = new Random();
		
		//Get a random available move from the list of available moves for randomPiece
		Move random = randomPiece.availableMoves().get(r.nextInt(randomPiece.availableMoves().size()));
		x1=random.getX(); y1=random.getY(); x2=random.getI(); y2=random.getJ();
		
		opponentPiece = getBoard().getPiece(x2, y2);
		opponentPiece = randomPiece;
		randomPiece = null;
		
		removeAndRepaint(x2,y2);		//Refer to Player.java
		removeAndRepaint(x1,y1);
		this.getGraphics().getSquares(x2, y2).add(getGraphics().chessPiece(opponentPiece), BorderLayout.CENTER);
		this.getGraphics().getSquares(x2, y2).validate();
		moveChessPiece(x1, y1, x2, y2, this.getName(), "Random Player", w, bl, mouseClicked);
	}
	
	public void mouseClicked(MouseEvent e) {
		if (mouseClicked){
			Object panelSource = e.getSource();		//panelSource refers to the Object on which Mouse Event initially occurred
			JPanel panel = (JPanel)panelSource;	
			int x = (panel.getX()/59);		//Divided by 59 because of the width and height of the grid set in Chess.java
			int y = (panel.getY()/59);
			this.move = !this.move;
			if (this.move){
				fromSquare = new Point(y,x);
				a = getBoard().getPiece(fromSquare.x, fromSquare.y);		//Get the piece at square clicked on
				if (a == null)
					this.move = !this.move;
				else if (a != null && this.whitePlayer && a.getColour() == 0)
					this.move = !this.move;
				else if (a != null && !this.whitePlayer && a.getColour() == 1)
					this.move = !this.move;
				if(this.move)
					this.indicateSelected(false,fromSquare);		//Refer to Player.java for indicatedSelected method
			}
			else {
				toSquare = new Point(y,x);
				if (!fromSquare.equals(toSquare)){		//Condition which checks squares clicked on are different
					if (a != null)
						if (this.valid(fromSquare, toSquare, i, j)){		//Refer to Player.java for valid method
							opponentPiece = getBoard().getPiece(toSquare.x, toSquare.y);
							opponentPiece = a;
							a = null;
							this.makeMove();
							
							// Refer to Player.java for moveChessPiece method
							moveChessPiece(fromSquare.x, fromSquare.y, toSquare.x, toSquare.y, this.getName(), "Random player", w, bl, mouseClicked);
							randomMove();
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
