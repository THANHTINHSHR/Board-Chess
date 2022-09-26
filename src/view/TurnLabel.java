package view;

import java.awt.Font;

import javax.swing.JLabel;

import controller.BoardController;
import observer.Observer;

public class TurnLabel extends JLabel implements Observer<BoardController> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int turn;

	public TurnLabel() {
		setText("Turn: " + turn + "/ 30");
		setFont(new Font(("Turn: " + turn + "/ 30"), Font.BOLD, 30));
	}

	@Override
	public void update(BoardController e) {
		setText("Turn: " + e.getTurnCounter() + " /30");

	}

}
