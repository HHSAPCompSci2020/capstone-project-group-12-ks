import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

/**
 * Main Class that initiates the Game "Legend of Kenjiro"
 * @author Kinjal
 * @version 1.0.0
 */
public class Main extends JFrame {
	/**
	 * Main method creates window
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Welcome to the Dungeon");
		
		GameBoard board = new GameBoard(50,50,750,550);
		Graphics g = board.getGraphics();
		
		
		while(true) {
			
			board.clear();
			if(board.refreshGame()) {
				break;
			}
			board.paintComponent(g);
			
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		g.setColor(Color.black);
		g.fillRect(0, 0, 750, 550);
		g.setColor(Color.red);
		g.drawString("GAME OVER", 335, 275);
		
//		Main window = new Main(); 
//		window.setTitle("Legends of Kenjiro: The Infinite Dungeon");
//		window.setBounds(100, 100, 500, 400);
//		window.setResizable(true);
//		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		window.setVisible(true);
	}
	
}
