import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

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
	private Image imgLeftEnemy,imgRightEnemy;
	private boolean loaded;
	private Image bossImageLeft, bossImageRight;
	private boolean nextIsBoss;


	/**
	 * constructor of EnemyManager Class (used to create a EnemyManager object)
	 */
	public EnemyManager() {
		loaded = false;
		enemies = new ArrayList<Enemy>();
		refs = new ArrayList<DatabaseReference>();
		indeces = new ArrayList<Integer>();
		disconnectors = new ArrayList<OnDisconnect>();


		imgLeftEnemy = new ImageIcon("Images/position1forenemy").getImage();
		try {
			imgLeftEnemy = ImageIO.read(getClass().getClassLoader().getResource("Images/position1forenemy.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		imgRightEnemy = new ImageIcon("Images/position2forenemy").getImage();
		try {
			imgRightEnemy = ImageIO.read(getClass().getClassLoader().getResource("Images/position2forenemy.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		bossImageLeft = new ImageIcon("Images/Bossposition1.png").getImage();
		try {
			bossImageLeft = ImageIO.read(getClass().getClassLoader().getResource("Images/Bossposition1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		bossImageRight = new ImageIcon("Images/Bossposition2.png").getImage();
		try {
			bossImageRight = ImageIO.read(getClass().getClassLoader().getResource("Images/Bossposition2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loaded = true;
		nextIsBoss=false;
	}
	/**
	 * Expresses the all the Enemy Objects contained within this object graphically
	 * @param g graphics for the pixel grid you wish to draw this object on
	 */
	public void drawAll(Graphics g, double x, double y) {
		for(int i=0;i<enemies.size();i++) {
			if(enemies.get(i) == null) continue;
			if(enemies.get(i) instanceof Boss) {
				enemies.get(i).draw(g, bossImageLeft, bossImageRight, x, y);
			}
			else {
				enemies.get(i).draw(g,imgLeftEnemy,imgRightEnemy, x, y);
			}
		}

	}

	/**
	 * Calls the move method for every Enemy Object contained within this object
	 */
	public void moveAll(int x, int y) {
		if(!loaded) {
			return;
		}
		for(int i : indeces) {
			//System.out.println(indeces);
			//System.out.println(enemies);
			//System.out.println(refs);
			//			if(enemies==null) {
			//				System.out.println("ENEMIES NULLLLLL");
			//				return;
			//			}
			if(enemies.get(i) == null) {
				//System.out.println("e");
				continue;
			}

			enemies.get(i).move(x,y);
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
	/**
	 * Moves an enemy of index i
	 * @param i
	 * @param x player loc
	 * @param y player loc
	 */
	public void move(int i,int x, int y ) {
		if(enemies.get(i) == null) {
			return;
		}
		enemies.get(i).move(x,y);
		EnemyData data = new EnemyData();
		data.x = enemies.get(i).getX();
		data.y = enemies.get(i).getY();
		data.health = enemies.get(i).getHealth();
		refs.get(i).setValueAsync(data);
		if(enemies.get(i).getHealth()<= 0) {
			refs.get(i).removeValueAsync();
		}

	}
	/**
	 * checks all enemies for collisions with other
	 * @param other object to check collisions with
	 */
	public void collide(Collidable other) {
		for(int i = 0; i < refs.size(); i++) {
			if(enemies.get(i) == null) {
				continue;
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
		while(!loaded) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		//		indeces.clear();
		//		this.enemies.clear();
		//		refs.clear();
		for(int i = 0;  i < enemies/*to be changed*/; i++) {
			DatabaseReference newRef;
			if(refs == null) {
				newRef = enemiesRef.child(0+"");
			} else {
				newRef = enemiesRef.child(refs.size()+"");
			}
			disconnectors.add(newRef.onDisconnect());
			disconnectors.get(disconnectors.size()-1).removeValueAsync();
			EnemyData data = new EnemyData();
			data.x = 150;
			data.y = 150;
			data.health = 150;
			indeces.add(refs.size());
			newRef.setValueAsync(data);
			try {
				Thread.sleep(700);//delay to add enemy to database
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	/**
	 * spawns boss
	 * @param room the room to spawn in
	 */
	public void spawnRoomEnemiesForBossRoom(BossRoom room) {
	
		while(!loaded) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		System.out.println("IN BOSS ROOMMMMMMM");
		nextIsBoss=true;

		//		indeces.clear();
		//		this.enemies.clear();
		//		refs.clear();

		Boss enemy = room.getRoomBoss();

		DatabaseReference newRef;
		if(refs == null) {
			newRef = enemiesRef.child(0+"");
		} else {
			newRef = enemiesRef.child(refs.size()+"");
		}
		disconnectors.add(newRef.onDisconnect());
		disconnectors.get(disconnectors.size()-1).removeValueAsync();
		EnemyData data = new EnemyData();
		data.x = enemy.getX();
		data.y = enemy.getY();
		data.health = enemy.getHealth();
		indeces.add(refs.size());
		newRef.setValueAsync(data);
		try {
			Thread.sleep(700);//delay to add enemy to database
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

	}


	public void setReference(DatabaseReference ref) {
		enemiesRef = ref;
	}

	@Override
	public void onCancelled(DatabaseError arg0) {
		// TODO Auto-generatd method stub

	}
	/**
	 * runs when something is added to database
	 * @param arg0 the updated data
	 */
	@Override
	public void onChildAdded(DataSnapshot arg0, String arg1) {
		if(nextIsBoss) {
			loaded = false;
			EnemyData e = arg0.getValue(EnemyData.class);
			int index = Integer.parseInt(arg0.getKey());
			enemies.add(null);
			Boss enemy = new Boss(e.getX(),e.getY(),new HealthBar(2000,2000,Color.blue));
			enemy.reduceHealth(2000-e.getHealth());
			enemies.set(index, enemy);
			refs.add(null);
			refs.set(index, arg0.getRef());
			loaded = true;
			
			return;
		}
		loaded = false;
		EnemyData e = arg0.getValue(EnemyData.class);
		int index = Integer.parseInt(arg0.getKey());
		enemies.add(null);
		Enemy enemy = new Enemy(e.getX(), e.getY(),new HealthBar(150,150,Color.red));
		enemy.reduceHealth(150-e.getHealth());
		enemies.set(index, enemy);
		refs.add(null);
		refs.set(index, arg0.getRef());
		loaded = true;

	}


	/**
	 * Runs when database is updated
	 */
	@Override
	public void onChildChanged(DataSnapshot arg0, String arg1) {
		
		//System.out.println("d");
		if(nextIsBoss) {
			EnemyData e = arg0.getValue(EnemyData.class);
			Boss enemy = new Boss(e.getX(),e.getY(),new HealthBar(2000,2000,Color.blue));
			enemy.reduceHealth(2000-e.getHealth());
			enemies.set(Integer.parseInt(arg0.getKey()), enemy);
			
			return;
		}
		EnemyData e = arg0.getValue(EnemyData.class);
		Enemy enemy = new Enemy(e.getX(), e.getY(),new HealthBar(150,150,Color.red));
		enemy.reduceHealth(150-e.getHealth());
		enemies.set(Integer.parseInt(arg0.getKey()), enemy);

	}

	@Override
	public void onChildMoved(DataSnapshot arg0, String arg1) {
		// TODO Auto-generated method stub

	}
	/**
	 * Runs when something is removed from the database
	 */
	@Override
	public void onChildRemoved(DataSnapshot arg0) {
		enemies.set(Integer.parseInt(arg0.getKey()), null);

	}

}
