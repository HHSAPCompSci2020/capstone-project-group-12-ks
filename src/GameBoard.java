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

	private Image backGroundImage;
	private ArrayList<Player> players;
	private Player p1;
	private EnemyManager enemies;
	private GameBoard board;
	private ArrayList<String> storySubtitles; 	//Will contain string that have the file path to text documents (that hv subs)
	//private ArrayList<Room> rooms;
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
	
	private Stack<Room> rooms;


	/**
	 * Creates a GameBoard Object
	 * Creates connection with database and adds player to database
	 * @param x X coordinate where the window that should be created
	 * @param y Y coordinate where the window that should be created
	 * @param w width of window that should be created
	 * @param h height of window that should be created
	 */
	public GameBoard(int x, int y, int w, int h) {
		players = new ArrayList<Player>();
		enemies = new EnemyManager();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Thread.sleep(5000);//5 sec loading time
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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


		backGroundImage = null;
		
		rooms = new Stack<Room>();
		storySubtitles = new ArrayList<String>();

		

		playerRef = playersRef.child(players.size()+"");
		disconnector = playerRef.onDisconnect();
		disconnector.removeValueAsync();
		

		currentPost = new PlayerData();
		currentPost.x = 200;
		currentPost.y = 200;
//		newData.add(currentPost);
//		Post spawn = new Post();
//		spawn.players = newData;
		playerRef.setValueAsync(currentPost);
		try {
			Thread.sleep(700);//delay to add player to database
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		for(int i=10;i>0;i--) {
			rooms.add(new EnemyRoom("scripts/Room1.txt","Room "+i,"Images/RoomOption1.png", w, h));
		}
		currentRoom = rooms.pop();
		enemies.spawnRoomEnemies();

		p1 = new Player(200,200,50,50);



		frame.addKeyListener(p1);


	}

	/**
	 * This refreshes the game and activates all the objects this class holds
	 * Updates database with new location of player
	 * (intended to be called continously for the game to run)
	 */
	public void refreshGame() {
		currentRoom.draw(bufferedG);
		Player p;
		for(int i =0;i<players.size();i++) {
			p = players.get(i);
			if(p != null) {
				p.draw(bufferedG);
				if(i != Integer.parseInt(playerRef.getKey())) {
					p1.collisionCheck(p);
				}
			}
		}
		
		p1.draw(getBufferedGraphics());
		boolean collided = currentRoom.collisionCheck(p1);
		if(!collided)
		p1.move();
		PlayerData data = new PlayerData();
		data.x = p1.getX();
		data.y = p1.getY();
		data.yVel = p1.getYVel();
		data.xVel = p1.getXVel();
		playerRef.setValueAsync(data);
		
		
//		for(int i=0;i<enemies.size();i++) {
//			if(!currentRoom.collisionCheck(enemies.get(i))){
//				enemies.move(i);
//			}
//		}
		enemies.moveAll();
		Weapon temp=p1.getWeapon();
		enemies.collide(currentRoom);
		enemies.collide(temp);
//		for(int i=0;i<enemies.size();i++) {
//			if(temp.collisionCheck(enemies.get(i))){
//				enemies.get(i).reduceHealth(50);
//			}
//		}
		enemies.drawAll(bufferedG);
		
		repaint();
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
	 * important method that redraws everything on the screen by redrawing the BufferedImage 
	 */
	public void paintComponent(Graphics g) {

		g.drawImage(bImage, 0, 0, this);



	}


	@Override

	public void onCancelled(DatabaseError arg0) {
		// TODO Auto-generated method stub

	}


	@Override
	/**
	 * Runs when new players are added to the database
	 * @param arg0 the current state of the database
	 */
	public void onChildAdded(DataSnapshot arg0, String arg1) {
		PlayerData data = arg0.getValue(PlayerData.class);
		players.add(null);
		Player p = new Player((int)data.getX(), (int)data.getY(), 50, 50);
		p.setXSpeed(data.getxVel());
		p.setYSpeed(data.getyVel());
		players.set(Integer.parseInt(arg0.getKey()), p);
	}


	@Override
	/**
	 * runs when the database is updated
	 * @param arg0 the current state of the database
	 */
	public void onChildChanged(DataSnapshot arg0, String arg1) {
		PlayerData data = arg0.getValue(PlayerData.class);
		Player p = new Player((int)data.getX(), (int)data.getY(), 50, 50);
		p.setXSpeed(data.getxVel());
		p.setYSpeed(data.getyVel());
		players.set(Integer.parseInt(arg0.getKey()), p);
	}


	@Override
	public void onChildMoved(DataSnapshot arg0, String arg1) {
		// TODO Auto-generated method stub

	}


	@Override
	public void onChildRemoved(DataSnapshot arg0) {
		PlayerData data = arg0.getValue(PlayerData.class);
		players.set(Integer.parseInt(arg0.getKey()),null);
	}



}
