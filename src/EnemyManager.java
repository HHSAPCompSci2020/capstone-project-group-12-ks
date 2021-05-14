import java.awt.Graphics;
import java.util.ArrayList;

public class EnemyManager {
	
	ArrayList<Enemy> enemies;
	
	
	public EnemyManager() {
		enemies = new ArrayList<Enemy>();
		enemies.add(new Enemy(150,150));
	}
	
	public void drawAll(Graphics g) {
		for(int i=0;i<enemies.size();i++) {
			enemies.get(i).draw(g);
		}
	}
	
	public void moveAll() {
		for(int i=0;i<enemies.size();i++) {
			enemies.get(i).move();
		}
	}
	
	public void createNewEnemy() {
		
	}

}
