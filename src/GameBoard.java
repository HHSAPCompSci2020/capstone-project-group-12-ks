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

public class GameBoard extends JPanel implements ChildEventListener{

	private Image backGroundImage;
	private ArrayList<Player> players;
	private EnemyManager enemies;
	private GameBoard board;
	private ArrayList<String> storySubtitles; 	//Will contain string that have the file path to text documents (that hv subs)
	private ArrayList<Room> rooms;
	private Room currentRoom;
	private BufferedImage bImage;
	private Graphics bufferedG;
	private JFrame frame;
	//Database fields
	private Post currentPost;
	private DatabaseReference postsRef;
	private DatabaseReference playerRef;
	
	

	
	public GameBoard(int x, int y, int w, int h) {
		
		//Firebase Setup
		FileInputStream refreshToken;
		try {

			refreshToken = new FileInputStream("/Users/kinjal/Desktop/AnimationDemoProcessing/Key.json");

			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(refreshToken))
					.setDatabaseUrl("https://fir-78638-default-rtdb.firebaseio.com/")
					.build();

			FirebaseApp.initializeApp(options);
			DatabaseReference database = FirebaseDatabase.getInstance().getReference();
			postsRef = database.child("Players");

			postsRef.addChildEventListener(this);

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
		currentPost = null;
		
		//Window Setup
		
		frame=new JFrame("MyDrawingBoard");
		frame.setBounds(x, y,0,0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setPreferredSize(new Dimension(w,h));
		frame.add(this);
		frame.pack();

		frame.setVisible(true);

		bImage=new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
		bufferedG=bImage.getGraphics();
		((Graphics2D)bufferedG).setRenderingHint(RenderingHints.KEY_ANTIALIASING
				,RenderingHints.VALUE_ANTIALIAS_ON);

		bufferedG.setColor(Color.white);
		bufferedG.drawRect(x, y,w, h);
		
		
		backGroundImage = null;
		players = new ArrayList<Player>();
		rooms = new ArrayList<Room>();
		storySubtitles = new ArrayList<String>();
		enemies = new EnemyManager();
		currentRoom = new Room();
		
		
		playerRef = postsRef.child(players.size()+"");
		currentPost = new Post();
		currentPost.x = 200;
		currentPost.y = 200;
		playerRef.setValueAsync(currentPost);
//		players.add(new Player(200,200));
		
		
		//frame.addKeyListener(this);
		for(int i =0;i<players.size();i++) {
		frame.addKeyListener(players.get(i));
		}
		
	}
	
	
	public void refreshGame() {
		Player p;
		for(int i =0;i<players.size();i++) {
			p = players.get(i);
			p.move();
			p.draw(bufferedG, null);
		}
		
		enemies.moveAll();
		enemies.drawAll(bufferedG);
	}
	
	public void clear() {
		for(int i=0;i<getWidth();i++) {
			for(int j=0;j<getHeight();j++) {
				bImage.setRGB(i, j, 0xffffffff);
			}
		}
	}
	
	
	public Graphics getBufferedGraphics() {
		return bufferedG;
	}
	
	public void paintComponent(Graphics g) {
		
		g.drawImage(bImage, 0, 0, this);
		
		
		
	}


	@Override
	public void onCancelled(DatabaseError arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onChildAdded(DataSnapshot arg0, String arg1) {
		Post post = arg0.getValue(Post.class);
		players.add(null);
//		player.set(Integer.parseInt(arg0.getKey()), Player())
//		postsRef.ge
	}


	@Override
	public void onChildChanged(DataSnapshot arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onChildMoved(DataSnapshot arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onChildRemoved(DataSnapshot arg0) {
		// TODO Auto-generated method stub
		
	}


	
}
