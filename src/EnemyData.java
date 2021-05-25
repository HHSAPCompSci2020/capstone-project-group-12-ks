/**
 * Holds data for the enemy in the database
 * @author kinjal
 *
 */
public class EnemyData{
	
	/**
	 * constructs EnemyData Object
	 */
	EnemyData(){
		
	}
	public double x, y;
	public double health;
	
	/**
	 * getter for x coord
	 * @return x the x coord of the enemy
	 */
	public double getX(){
		return x;
	}
	/**
	 * getter for y coord
	 * @return y the y coord of the enemy
	 */
	public double getY(){
		return y;
	}
	/**
	 * Gets health in data
	 * @return health
	 */
	public double getHealth() {
		return health;
	}
}