package isolation;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

public class Game {

	private final int n;
	private JPanel[][][] cells;
	private State[][][] states;
	private JLabel output;
	private int x1, y1, z1, x2, y2, z2;
	private boolean isX = false;
	private ArrayList<String> log;
	private static final int[][] VECTORS;
	static {
		int[][] vector_temp = new int[27][3];
		int count = 0;
		for(int i=-1; i<=1; i++) {
			for(int j=-1; j<=1; j++) {
				for(int k=-1; k<=1; k++) {
					vector_temp[count][0] = i;
					vector_temp[count][1] = j;
					vector_temp[count][2] = k;
					count++;
				}
			}
		}
		VECTORS = vector_temp;
	}

	public int getN() {
		return n;
	}
	
	public Game(int n, Container panel) {
		this.n = n;
		this.cells = new JPanel[n][n][n];
		this.states = new State[n][n][n];
		for(int k = 0; k<n; k++) {
			for(int j = 0; j<n; j++) {
				for(int i = 0; i<n; i++) {
					states[i][j][k] = State.MOVE;
				}
			}
			panel.add(new GridPanel(this, k));
		}
		output = new JLabel("O's move (blue)");
		output.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Status"));
		output.setPreferredSize(new Dimension(150, 40));
		panel.add(output);
		
		log = new ArrayList<String>();
	}

	public JPanel[][][] getCellArray() {
		return cells;
	}

	public State[][][] getStateArray() {
		return states;
	}

	public enum State {
		EMPTY, MOVE, PLAYERONE, PLAYERTWO, FILLED
	}
	
	public void onClick(int x, int y, int z) {
		if(states[x][y][z].equals(State.MOVE)) {
			String result = " moves to ("+x+", "+y+", "+z+")";
			if(isX) {
				isX = false;
				states[x][y][z] = State.PLAYERTWO;
				cells[x][y][z].setBackground(Color.RED);
				if(log.size()>=2) {
					states[x2][y2][z2] = State.FILLED;
					cells[x2][y2][z2].setBackground(Color.BLACK);
				}
				cells[x2][y2][z2].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				x2 = x;
				y2 = y;
				z2 = z;
				x = x1;
				y = y1;
				z = z1;
				log.add("X"+result);
				output.setText("O's move (blue)");
			}
			else {
				isX = true;
				states[x][y][z] = State.PLAYERONE;
				cells[x][y][z].setBackground(Color.BLUE);
				if(log.size()>=2) {
					states[x1][y1][z1] = State.FILLED;
					cells[x1][y1][z1].setBackground(Color.BLACK);
				}
				cells[x1][y1][z1].setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
				x1 = x;
				y1 = y;
				z1 = z;
				x = x2;
				y = y2;
				z = z2;
				log.add("O"+result);
				output.setText("X's move (red)");
			}
			if(log.size()>=2) {
				for(int k = 0; k<n; k++) {
					for(int j = 0; j<n; j++) {
						for(int i = 0; i<n; i++) {
							if(states[i][j][k] == State.MOVE) {
								states[i][j][k] = State.EMPTY;
								cells[i][j][k].setBackground(Color.WHITE);
							}
						}
					}
				}
				cells[x][y][z].setBorder(BorderFactory.createDashedBorder(Color.WHITE));
				boolean alive = false;
				for(int[] v : VECTORS) {
					int dx = x;
					int dy = y;
					int dz = z;
					while(true) {
						dx += v[0];
						dy += v[1];
						dz += v[2];
						if(dx<0||dy<0||dz<0||dx>=n||dy>=n||dz>=n||states[dx][dy][dz]!=State.EMPTY) {
							break;
						}
						states[dx][dy][dz] = State.MOVE;
						cells[dx][dy][dz].setBackground(Color.YELLOW);
						alive = true;
					}
				}
				if(!alive) {
					if(isX) {
						output.setText("O wins!");
					}
					else {
						output.setText("X wins!");
					}
				}
			}
		}
	}

	public boolean isX() {
		return isX;
	}
}
