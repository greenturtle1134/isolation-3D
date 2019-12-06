package isolation;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class GridPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public static final int CELL_SIZE = 50;
	JPanel[][] cells;
	Game game;

	public GridPanel(Game game, int k) {
		int n = game.getN();
		JPanel[][][] cells = game.getCellArray();
		this.setPreferredSize(new Dimension(n*CELL_SIZE, n*CELL_SIZE));
		this.setLayout(new GridLayout(n, n));
		for(int i = 0; i<n; i++) {
			for(int j = 0; j<n; j++) {
				cells[i][j][k] = new JPanel();
				cells[i][j][k].setBackground(Color.YELLOW);
				cells[i][j][k].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				this.add(cells[i][j][k]);
				MouseCellListener listener = new MouseCellListener(game, cells[i][j][k], i, j, k);
				cells[i][j][k].addMouseListener(listener);
				cells[i][j][k].addMouseMotionListener(listener);
			}
		}
		this.setBorder(BorderFactory.createTitledBorder("Level "+k));
		this.validate();
	}
}
