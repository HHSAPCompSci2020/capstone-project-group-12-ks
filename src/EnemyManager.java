import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.OnDisconnect;

/**
 * The EnemyManager Class represents all the enemies on the grid
 * this class is intended to be used with a data structure using Rooms
 * @author kinjal
 * @version 1.0.0
 */
public class EnemyManager implements ChildEventListener {
	
	private ArrayList<Enemy> enemies;
	private ArrayList<DatabaseReference> refs;
	private ArrayList<Integer> indeces;
	private ArrayList<OnDisconnect>  disconnectors;
	private DatabaseReference enemiesRef;
	
	/**
	 * constructor of EnemyManager Class (used to create a EnemyManager object)
	 */
	public EnemyManager() {
		enemies = new ArrayList<Enemy>();
		refs = new ArrayList<DatabaseReference>();
		indeces = new ArrayList<Integer>();
		disconnectors = new ArrayList<OnDisconnect>();
	}
	
	/**
	 * Expresses the all the Enemy Objects contained within this object graphically
	 * @param g graphics for the pixel grid you wish to draw this object on
	 */
	public void drawAll(Graphics g) {
		for(int i=0;i<enemies.size();i++) {
			if(enemies.get(i) != null)
			enemies.get(i).draw(g);
		}
		
	}
	
	/**
	 * Calles the move method for every Enemy Object contained within this object
	 */
	public void moveAll() {
		for(int i : indeces) {
			if(enemies.get(i) == null) {
				return;
			}
			enemies.get(i).move();
			EnemyData data = new EnemyData();
			data.x = enemies.get(i).getX();
			data.y = enemies.get(i).getY();
			data.health = enemies.get(i).getHealth();
			refs.get(i).setValueAsync(data);
			if(enemies.get(i).getHealth()<= 0) {
				refs.get(i).removeValueAsync();
			}
		}
	}
	
	public void move(int i) {
		enemies.get(i).move();
		EnemyData data = new EnemyData();
		data.x = enemies.get(i).getX();
		data.y = enemies.get(i).getY();
		refs.get(i).setValueAsync(data);
//		
	}
	
	public void collide(Collidable other) {
		for(int i = 0; i < refs.size(); i++) {
			if(enemies.get(i) == null) {
				return;
			}
			if(other.collisionCheck(enemies.get(i))) {
				EnemyData data = new EnemyData();
				data.x = enemies.get(i).getX();
				data.y = enemies.get(i).getY();
				data.health = enemies.get(i).getHealth();
				refs.get(i).setValueAsync(data);
			}
		}
	}
	
	public Enemy get(int i) {
		return enemies.get(i);
	}
	
	public int size() {
		return enemies.size();
	}
	
	/**
	 * Used to create a new Enemy that will be managed by this class
	 */
	public void spawnRoomEnemies(int enemies) {
		indeces.clear();
		this.enemies.clear();
		refs.clear();
		for(int i = 0;  i < enemies/*to be changed*/; i++) {
			DatabaseReference newRef;
			if(refs == null) {
				newRef = enemiesRef.child(0+"");
			} else {
				newRef = enemiesRef.child(refs.size()+"");
			}
			EnemyData data = new EnemyData();
			data.x = 150;
			data.y = 150;
			data.health = 150;
			indeces.add(refs.size());
			newRef.setValueAsync(data);
			try {
				Thread.sleep(500);//delay to add enemy to database
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void setReference(DatabaseReference ref) {
		enemiesRef = ref;
	}

	@Override
	public void onCancelled(DatabaseError arg0) {
		// TODO Auto-generatd method stub
		
	}

	@Override
	public void onChildAdded(DataSnapshot arg0, String arg1) {
		EnemyData e = arg0.getValue(EnemyData.class);
		int index = Integer.parseInt(arg0.getKey());
		enemies.add(null);
		Enemy enemy = new Enemy(e.getX(), e.getY(),new HealthBar(150,150,Color.red));
		enemy.reduceHealth(150-e.getHealth());
		enemies.set(index, enemy);
		refs.add(null);
		refs.set(index, arg0.getRef());
		if(indeces.contains(index)) {
			System.out.println(index);
			disconnectors.add(refs.get(index).onDisconnect());
			for(OnDisconnect d : disconnectors) {
				d.removeValueAsync();
			}
			
		}
		
	}
	
	

	@Override
	public void onChildChanged(DataSnapshot arg0, String arg1) {
		EnemyData e = arg0.getValue(EnemyData.class);
		Enemy enemy = new Enemy(e.getX(), e.getY(),new HealthBar(150,150,Color.red));
		enemy.reduceHealth(150-e.getHealth());
		enemies.set(Integer.parseInt(arg0.getKey()), enemy);
		
	}

	@Override
	public void onChildMoved(DataSnapshot arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onChildRemoved(DataSnapshot arg0) {
		enemies.set(Integer.parseInt(arg0.getKey()), null);
		
	}

}
