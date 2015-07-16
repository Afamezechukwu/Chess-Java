package uk.ac.sheffield.aca14ame;
import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/*
 * HumanPlayer.java  	1.2 15/05/2015
 *
 */

/**
* HumanPlayer.java 
*
* Class which extends Player to represent a Human Player
* This class makes it possible to play a game between two HumanPlayers
* @version 1.2 15 May 2015
*
* @author Afamefuna Ezechukwu  (ameiechukwu1@sheffield.ac.uk)
*/
public class HumanPlayer extends Player{
	
	private boolean move = false, whitePlayer = true, mouseClicked = true;
	private Piece a, opponentPiece;
	private Pieces w = new Pieces(getBoard(),1), bl = new Pieces(getBoard(),0);
	private String name, nameOfOpponent;
	private Point from, to;
	private int i,j;
	
	public HumanPlayer(String n, Pieces p, Board b, Player o) {
		super(n, p, b, o);
	}
	
	public void askName(){			//Method asks for name of player and opponent
		name = JOptionPane.showInputDialog("Please enter your given name: ");
		nameOfOpponent = JOptionPane.showInputDialog("Please enter the given name of your opponent: ");
	}
	
	public String getName(){	//method gets the name of player
		return name;
	}
	
	public String getNameOfOpponent(){	//method which gets the name of opponent
		return nameOfOpponent;
	}
	
	public Pieces getPieces(Piece p){	//Method which returns pieces based on the piece(colour).
		if (p.getColour() == 0)
			return bl;
		else
			return w;
	}

	public boolean makeMove() {				//This method removes piece from one square and repaints it to another square.
		removeAndRepaint(to.x, to.y);		//Refer to Player class
		removeAndRepaint(from.x, from.y);
		this.getGraphics().getSquares(to.x, to.y).add(getGraphics().chessPiece(opponentPiece), BorderLayout.CENTER);
		this.getGraphics().getSquares(to.x, to.y).validate(); //Refer to GraphicDisplay and Player classes
		return true;
	}

	public void mouseClicked(MouseEvent e) {
		if (mouseClicked){
			Object panelSource = e.getSource();	//panelSource refers to the Object on which Mouse Event initially occurred
			JPanel panel = (JPanel)panelSource;
			int x = (panel.getX()/59);	//Divided by 59 because of the width and height of the grid set in Chess.java
			int y = (panel.getY()/59);
			this.move = !this.move;
			if (this.move){
				from = new Point(y,x);
				a = getBoard().getPiece(from.x, from.y);	//Get the piece at square clicked on
				if (a == null)
					this.move = !this.move;
				else if (a != null && this.whitePlayer && a.getColour() == 0)
					this.move = !this.move;
				else if (a != null && !this.whitePlayer && a.getColour() == 1)
					this.move = !this.move;
				if(this.move)
					this.indicateSelected(false, from);	//Refer to Player.java
			}
			else {
				to = new Point(y,x);
				if (!from.equals(to)){	//Condition which checks squares clicked on are different
					if (a != null)
						if (this.valid(from,to,i,j)){	//Refer to Player.java
							opponentPiece = getBoard().getPiece(to.x, to.y);
							opponentPiece = a;
							a = null;
							this.makeMove();
							
							//makes black then white
							this.whitePlayer = !this.whitePlayer;	//This helps to create turn-by-turn game
							
							if (!getBoard().occupied(to.x, to.y)) {		//move Piece on Board if the square to move to is not occupied
								getBoard().getPiece(from.x, from.y).setPosition(to.x, to.y);	
								getBoard().setPosition(to.x, to.y,getBoard().getPiece(from.x, from.y));	
								getBoard().remove(from.x, from.y);
							}
							else {
								if (getBoard().getPiece(to.x, to.y).getColour() 
										!= getBoard().getPiece(from.x, from.y).getColour()){	//if the colours are different
									char king = getBoard().getPiece(to.x, to.y).getChar();		//get the char
									if ((king == 'k') || (king == 'K')){						//End game if the char is a king
										if (king == 'k')
											JOptionPane.showMessageDialog(getGraphics(), "Game Over ! "+this.getNameOfOpponent()+" won", "Message", JOptionPane.PLAIN_MESSAGE);
										else
											JOptionPane.showMessageDialog(getGraphics(), "Game Over ! "+this.getName()+" won", "Message", JOptionPane.PLAIN_MESSAGE);
										System.exit(0);
									}
									
									//indicate if a piece was lost by a player
									if (getBoard().getPiece(to.x, to.y).getColour()==w.getPiece(0).getColour())
										JOptionPane.showMessageDialog(getGraphics(), this.getName()+" just lost a piece", "Message", JOptionPane.PLAIN_MESSAGE);
									else if (getBoard().getPiece(to.x, to.y).getColour()==bl.getPiece(0).getColour())
										JOptionPane.showMessageDialog(getGraphics(), this.getNameOfOpponent()+" just lost a piece", "Message", JOptionPane.PLAIN_MESSAGE);
									this.getPieces(getBoard().getPiece(to.x, to.y)).delete(getBoard().getPiece(to.x, to.y));
									
								}
								getBoard().getPiece(from.x, from.y).setPosition(to.x, to.y);	
								getBoard().setPosition(to.x, to.y,getBoard().getPiece(from.x, from.y));	
								getBoard().remove(from.x, from.y);
							}
						}
						else{
							JOptionPane.showMessageDialog(getGraphics(), "Invalid Move.", "Warning", JOptionPane.ERROR_MESSAGE);
							this.indicateSelected(true, from);
						}
				}
				else
					this.indicateSelected(true, from);	//Refer to Player.java
			}
		}
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
}
