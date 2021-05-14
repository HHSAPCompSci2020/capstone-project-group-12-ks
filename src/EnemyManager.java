import java.awt.Graphics;
import java.util.ArrayList;

/**
 * The EnemyManager Class represents all the enemies on the grid
 * this class is intended to be used with a data structure using Rooms
 * @author srikrishna
 * @version 1.0.0
 */
public class EnemyManager {
	
	private ArrayList<Enemy> enemies;
	
	/**
	 * constructor of EnemyManager Class (used to create a EnemyManager object)
	 */
	public EnemyManager() {
		enemies = new ArrayList<Enemy>();
		enemies.add(new Enemy(150,150));
	}
	
	/**
	 * Expresses the all the Enemy Objects contained within this object graphically
	 * @param g graphics for the pixel grid you wish to draw this object on
	 */
	public void drawAll(Graphics g) {
		for(int i=0;i<enemies.size();i++) {
			enemies.get(i).draw(g);
		}
	}
	
	/**
	 * Calles the move method for every Enemy Object contained within this object
	 */
	public void moveAll() {
		for(int i=0;i<enemies.size();i++) {
			enemies.get(i).move();
		}
	}
	
	/**
	 * Used to create a new Enemy that will be managed by this class
	 */
	public void createNewEnemy() {
		
	}

}
