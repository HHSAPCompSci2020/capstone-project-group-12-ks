import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.OnDisconnect;

/**
 * This is the GameBoard Class the manages the Game "Legends of Kenjiro" as a whole
 * by tying all the other classes together 
 * @author srikrishna + kinjal(multiplayer)
 * @version 1.0.0
 */
public class GameBoard extends JPanel implements ChildEventListener{


	private ArrayList<Player> players;
	private Player p1;
	private Image up1, up2, up3, down1, down2, down3, left1, left2,left3, right1,right2, right3;
	private EnemyManager enemies;
	
	private Room currentRoom;
	private BufferedImage bImage;
	private Graphics bufferedG;
	private JFrame frame;
	
	//Database fields
	private PlayerData currentPost;
	private DatabaseReference playersRef;
	private DatabaseReference playerRef;
	private OnDisconnect disconnector;
	private DatabaseReference enemyRef;
	private boolean loaded;
	//Database Fields
	
	private Stack<Room> rooms;
	private int storyCount;
	private Image imgWeapon;




	/**
	 * Creates a GameBoard Object
	 * Creates connection with database and adds player to database
	 * @param x X coordinate where the window that should be created
	 * @param y Y coordinate where the window that should be created
	 * @param w width of window that should be created
	 * @param h height of window that should be created
	 */
	public GameBoard(int x, int y, int w, int h) {
		loaded = false;
		players = new ArrayList<Player>();
		enemies = new EnemyManager();

		up1 = new ImageIcon("Images/position10.png").getImage();
		try {
			up1 = ImageIO.read(getClass().getClassLoader().getResource("Images/position10.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		up2 = new ImageIcon("Images/position11.png").getImage();
		try {
			up2 = ImageIO.read(getClass().getClassLoader().getResource("Images/position11.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		up3 = new ImageIcon("Images/position12.png").getImage();
		try {
			up3 = ImageIO.read(getClass().getClassLoader().getResource("Images/position12.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		left1 = new ImageIcon("Images/position4.png").getImage();
		try {
			left1 = ImageIO.read(getClass().getClassLoader().getResource("Images/position4.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		left2 = new ImageIcon("Images/position5.png").getImage();
		try {
			left2 = ImageIO.read(getClass().getClassLoader().getResource("Images/position5.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		left3 = new ImageIcon("Images/position6.png").getImage();
		try {
			left3 = ImageIO.read(getClass().getClassLoader().getResource("Images/position6.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		right1 = new ImageIcon("Images/position7.png").getImage();
		try {
			right1 = ImageIO.read(getClass().getClassLoader().getResource("Images/position7.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		right2 = new ImageIcon("Images/position8.png").getImage();
		try {
			right2 = ImageIO.read(getClass().getClassLoader().getResource("Images/position8.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		right3 = new ImageIcon("Images/position9.png").getImage();
		try {
			right3 = ImageIO.read(getClass().getClassLoader().getResource("Images/position9.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		down1 = new ImageIcon("Images/position1.png").getImage();
		try {
			down1 = ImageIO.read(getClass().getClassLoader().getResource("Images/position1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		down2 = new ImageIcon("Images/position2.png").getImage();
		try {
			down2 = ImageIO.read(getClass().getClassLoader().getResource("Images/position2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		down3 = new ImageIcon("Images/position3.png").getImage();
		try {
			down3 = ImageIO.read(getClass().getClassLoader().getResource("Images/position3.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		//Firebase Setup
		FileInputStream refreshToken;
		try {

			refreshToken = new FileInputStream("resources/Key.json");

			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(refreshToken))
					.setDatabaseUrl("https://fir-78638-default-rtdb.firebaseio.com/")
					.build();

			FirebaseApp.initializeApp(options);
			DatabaseReference database = FirebaseDatabase.getInstance().getReference();
			playersRef = database.child("Players");
			enemyRef = database.child("Enemies");
			enemyRef.addChildEventListener(enemies);

			playersRef.addChildEventListener(this);
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		try {
			Thread.sleep(7000);//5 sec loading time
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		loaded = true;
		enemies.setReference(enemyRef);
		currentPost = null;

		//Window Setup


		frame=new JFrame("MyDrawingBoard");
		frame.setBounds(x, y,0,0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setPreferredSize(new Dimension(w,h));
		frame.add(this);
		frame.pack();
		frame.setResizable(false);

		frame.setVisible(true);

		bImage=new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
		bufferedG=bImage.getGraphics();
		((Graphics2D)bufferedG).setRenderingHint(RenderingHints.KEY_ANTIALIASING
				,RenderingHints.VALUE_ANTIALIAS_ON);

		bufferedG.setColor(Color.white);
		bufferedG.drawRect(x, y,w, h);


		

		rooms = new Stack<Room>();
		



		playerRef = playersRef.child(players.size()+"");
		disconnector = playerRef.onDisconnect();
		disconnector.removeValueAsync();


		currentPost = new PlayerData();
		currentPost.x = 200;
		currentPost.y = 200;
		currentPost.health = 150;
		currentPost.down = true;
		
		playerRef.setValueAsync(currentPost);
		try {
			Thread.sleep(700);//delay to add player to database
		} catch (InterruptedException e) {
			e.printStackTrace();
		}



		

		rooms.add(new BossRoom("src/scripts/BossRoom1.txt","Room ","Images/RoomOption2.png", w, h));
		rooms.add(new EnemyRoom("src/scripts/Room2.txt","Room ","Images/RoomOption1.png", w, h,1));
		rooms.add(new EnemyRoom("src/scripts/Room1.txt","Room ","Images/RoomOption1.png", w, h,1));
	
		

		int temp=0;
		for(int i =0;i<players.size();i++) {
			if(players.get(i)!=null) {
				temp =i;
				break;
			}	

		}
	
		currentRoom = rooms.pop();
		
		if(currentRoom instanceof BossRoom && Integer.parseInt(playerRef.getKey())==temp) {
			enemies.spawnRoomEnemiesForBossRoom((BossRoom)currentRoom);
		}
		if(currentRoom instanceof EnemyRoom) {
			enemies.spawnRoomEnemies(((EnemyRoom) currentRoom).getOriginalNumberOfEnemies());}


		p1 = new Player(250,250,50,50);



		frame.addKeyListener(p1);
		frame.addMouseListener(currentRoom);
		storyCount=0;

		imgWeapon = new ImageIcon("Images/weapon2.png").getImage();
		try {
			imgWeapon = ImageIO.read(getClass().getClassLoader().getResource("Images/weapon2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}




	}

	/**
	 * This refreshes the game and activates all the objects this class holds
	 * Updates database with new location of player
	 * (intended to be called continously for the game to run)
	 */
	public boolean refreshGame() {

		if(currentRoom.isStoryActive()) {
			currentRoom.draw(bufferedG);
			p1.draw(bufferedG, up1, up2, up3, down1, down2, down3, left1,
					left2, left3, right1, right2, right3,imgWeapon);
			Player p;
			for(int i =0;i<players.size();i++) {
				p = players.get(i);
				if(p != null) {
					p.draw(bufferedG, up1, up2, up3, down1, down2, down3, left1,
							left2, left3, right1, right2, right3,imgWeapon);
					if(i != Integer.parseInt(playerRef.getKey())) {
						
					}
				}
			}
			if(storyCount==0) {
				for(int i =0;i<players.size();i++) {
					p = players.get(i);
					if(p != null) {
						frame.removeKeyListener(p);
					}
				}
				frame.removeKeyListener(p1);
				storyCount++;
			}

			return false;
		}
		else if(!currentRoom.isStoryActive() && storyCount!=0) {
			Player p;
			for(int i =0;i<players.size();i++) {
				p = players.get(i);
				if(p != null) {
					frame.addKeyListener(p1);
				}
			}
			frame.addKeyListener(p1);
			storyCount=0;
		}


	
		if(!loaded) {
			return true;
		}
		currentRoom.draw(bufferedG);
		Player p;
		for(int i =0;i<players.size();i++) {
			p = players.get(i);
			if(p != null) {
				p.draw(bufferedG, up1, up2, up3, down1, down2, down3, left1,
						left2, left3, right1, right2, right3, imgWeapon);
				if(i != Integer.parseInt(playerRef.getKey())) {
					p1.collisionCheck(p);
				}
			}
		}

		p1.draw(getBufferedGraphics(), up1, up2, up3, down1, down2, down3, left1,
				left2, left3, right1, right2, right3, imgWeapon);
		Weapon temp=p1.getWeapon();
		enemies.collide(currentRoom);
		enemies.collide(temp);
		enemies.collide(p1);
		boolean collided = currentRoom.collisionCheck(p1);
		if(!collided)
			p1.move();
		if(p1.getHealth()<=0) {
			playerRef.removeValueAsync();
			return true;


		}
		PlayerData data = new PlayerData();
		data.x = p1.getX();
		data.y = p1.getY();
		data.up = p1.getUp();
		data.down = p1.getDown();
		data.left = p1.getLeft();
		data.right = p1.getRight();
		data.health = p1.getHealth();
		data.swing = p1.getSwing();
		playerRef.setValueAsync(data);


	
		enemies.moveAll(p1.getX(), p1.getY());


		
		enemies.drawAll(bufferedG, p1.getX(), p1.getY());
		if(currentRoom instanceof EnemyRoom) {
			((EnemyRoom) currentRoom).reduceEnemiesLeft(enemies);
		}


		if(currentRoom.checkWhetherCanMoveToNextRoom()) {
			if(currentRoom instanceof EnemyRoom) {
				if(p1.getHitbox().intersects(((EnemyRoom) currentRoom).getHitboxForNextRoomPlate())){
					currentRoom = rooms.pop();
					frame.addMouseListener(currentRoom);
					if(currentRoom instanceof BossRoom) {

						int temp1=0;
						for(int i =0;i<players.size();i++) {
							if(players.get(i)!=null) {
								temp1 =i;
								break;
							}	

						}
						System.out.println(temp);
						if(currentRoom instanceof BossRoom && Integer.parseInt(playerRef.getKey())==temp1)
							enemies.spawnRoomEnemiesForBossRoom((BossRoom)currentRoom);

						p1.setX(550);
						p1.setY(100);
					}
					else if(currentRoom instanceof EnemyRoom) {
						enemies.spawnRoomEnemies(((EnemyRoom) currentRoom).getOriginalNumberOfEnemies());
						p1.setX(550);
						p1.setY(100);
					}

			
				}
			}
			else if(currentRoom instanceof BossRoom) {
				if(p1.getHitbox().intersects(((EnemyRoom) currentRoom).getHitboxForNextRoomPlate())){
					currentRoom = rooms.pop();
					frame.addMouseListener(currentRoom);
					
					int temp1=0;
					for(int i =0;i<players.size();i++) {
						if(players.get(i)!=null) {
							temp1 =i;
							break;
						}	

					}
					
					if(currentRoom instanceof EnemyRoom) {
						enemies.spawnRoomEnemies(((EnemyRoom) currentRoom).getOriginalNumberOfEnemies());
					}

					p1.setX(550);
					p1.setY(100);
				
				}
			}
			
		}

		repaint();
		return false;
	}

	/**
	 * Clears the BufferedImage this object contains within itself by painting it white
	 * if the BufferedImage is painted white so will the window because the Image is the
	 * representation of the window
	 */
	public void clear() {
		for(int i=0;i<getWidth();i++) {
			for(int j=0;j<getHeight();j++) {
				bImage.setRGB(i, j, 0xffffffff);
			}
		}
	}

	/**
	 * returns the graphics which are used to draw on this classes BufferedImage
	 * @return Graphics for the Buffered image in this object
	 */
	public Graphics getBufferedGraphics() {
		return bufferedG;
	}


	/**
	 * important method that redraws everything on the screen by redrawing the BufferedImage on the jpanel (which is this class)
	 */
	public void paintComponent(Graphics g) {

		g.drawImage(bImage, 0, 0, this);



	}


	@Override
	public void onCancelled(DatabaseError arg0) {

	}


	/**
	 * Runs when new players are added to the database
	 * @param arg0 the current state of the database
	 */
	@Override
	public void onChildAdded(DataSnapshot arg0, String arg1) {
		PlayerData data = arg0.getValue(PlayerData.class);
		players.add(null);
		Player p = new Player((int)data.getX(), (int)data.getY(), 50, 50);
		p.setUp(data.getUp());
		p.setDown(data.getDown());
		p.setLeft(data.getLeft());
		p.setRight(data.getRight());
		p.setHealth(data.getHealth());
		if(data.getSwing() == true) {
			p.setSwing();
		}
		players.set(Integer.parseInt(arg0.getKey()), p);
	}


	/**
	 * runs when the database is updated
	 * @param arg0 the current state of the database
	 */
	@Override
	public void onChildChanged(DataSnapshot arg0, String arg1) {
		PlayerData data = arg0.getValue(PlayerData.class);
		Player p = new Player((int)data.getX(), (int)data.getY(), 50, 50);
		p.setUp(data.getUp());
		p.setDown(data.getDown());
		p.setLeft(data.getLeft());
		p.setRight(data.getRight());
		p.setHealth(data.getHealth());
		if(data.getSwing() == true) {
			p.setSwing();
		}
		players.set(Integer.parseInt(arg0.getKey()), p);
	}


	@Override
	public void onChildMoved(DataSnapshot arg0, String arg1) {

	}


	/**
	 * Removes player data from players data structure using arg0
	 */
	@Override
	public void onChildRemoved(DataSnapshot arg0) {
		PlayerData data = arg0.getValue(PlayerData.class);
		players.set(Integer.parseInt(arg0.getKey()),null);
	}



}
