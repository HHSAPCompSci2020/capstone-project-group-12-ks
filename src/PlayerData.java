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
}