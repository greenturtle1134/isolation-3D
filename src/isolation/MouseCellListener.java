package isolation;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import isolation.Game.State;

public class MouseCellListener implements MouseListener, MouseMotionListener {
	private Game game;
	private JPanel cell;
	private int x, y, z;
	
	public MouseCellListener(Game game, JPanel cell, int x, int y, int z) {
		this.game = game;
		this.cell = cell;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		game.onClick(x, y, z);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(game.getStateArray()[x][y][z] == State.MOVE) {
			if(game.isX()) {
				cell.setBackground(Color.RED);
			}
			else {
				cell.setBackground(Color.BLUE);
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(game.getStateArray()[x][y][z] == State.MOVE) {
			cell.setBackground(Color.YELLOW);
		}
	}
	
	//A bunch of do-nothing methods

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

}
