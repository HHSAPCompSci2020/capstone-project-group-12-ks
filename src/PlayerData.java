/**
 * Holds data for the player in the database
 * @author kinjal
 *
 */
public class PlayerData{
	
	PlayerData(){
		
	}
	public double x, y;
	public boolean swing;
	public double health;
	public boolean left,right,up,down;
	
	/**
	 * getter for x coord
	 * @return x the x coord of the player
	 */
	public double getX(){
		return x;
	}
	/**
	 * getter for y coord
	 * @return y the y coord of the player
	 */
	public double getY(){
		return y;
	}
	
	public boolean getSwing() {
		return swing;
	}
	
	public double getHealth() {
		return health;
	}
	
	public boolean getLeft() {
		return left;
	}
	
	public boolean getRight() {
		return right;
	}
	
	public boolean getUp() {
		return up;
	}
	
	public boolean getDown() {
		return down;
	}
}