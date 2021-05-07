import javax.swing.JFrame;

/**
 * 
 * @author Kinjal
 *
 */
public class Main extends JFrame {
	/**
	 * Main method creates window
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Welcome to the Dungeon");
		Main window = new Main(); 
		window.setTitle("Legends of Kenjiro: The Infinte Dungeon");
		window.setBounds(100, 100, 500, 400);
		window.setResizable(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
}
