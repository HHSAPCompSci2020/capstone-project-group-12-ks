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
	/**
	 * gets swing boolean 
	 * @return swing boolean 
	 */
	public boolean getSwing() {
		return swing;
	}
	
	/**
	 * gets currentHealth (double)
	 * @return currentHealth (double)
	 */
	public double getHealth() {
		return health;
	}
	/**
	 * gets left boolean 
	 * @return left boolean 
	 */
	public boolean getLeft() {
		return left;
	}
	/**
	 * gets right boolean 
	 * @return right boolean 
	 */
	public boolean getRight() {
		return right;
	}
	/**
	 * gets up boolean 
	 * @return up boolean 
	 */
	public boolean getUp() {
		return up;
	}
	/**
	 * gets down boolean 
	 * @return down boolean 
	 */
	public boolean getDown() {
		return down;
	}
}