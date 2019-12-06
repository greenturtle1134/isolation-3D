package isolation;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Application {

	public static void main(String[] args) {
		int n = Integer.parseInt(JOptionPane.showInputDialog("Enter size of game board (cubical)."));
		JFrame frame = new JFrame(n+"x"+n+"x"+n+" Isolation Game");
		JPanel panel = new JPanel();
		Game game = new Game(n, panel);
		frame.setContentPane(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
	}

}
