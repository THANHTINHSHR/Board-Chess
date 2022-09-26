	package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.BoardController;
import model.BoardModel;
import model.Flag;
import observer.Observer;

public class PlayScreen extends JFrame implements Observer<BoardController> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BoardView boardView;
	ControlPanel controlPanel;
	JButton restartBT, backToMenuBT, quitBT;
	JPanel buttonPanel, eastPanel, playScreenPanel;
	BoardController boardController;

	public PlayScreen() {

		//
		boardView = new BoardView(new BoardController(new BoardModel()), "resource//bigPS5.jpg");
		controlPanel = new ControlPanel(boardView);
		boardController = boardView.getController();
		//
		createCompoment();
		addButtonAction();
		boardController.registerObserver(this);

		//
		createPlayScreenPanel();
		add(playScreenPanel);
		// makePanelsTransparent();
		//
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
	}

	// private void makePanelsTransparent() {
	// TODO Auto-generated method stub
	// cant make error repaint!
	// boardView.setBackground(new Color(0, 0, 0, 0));
	// }

	private void createPlayScreenPanel() {
		// playScreenPanel = new ImagePanel("resource//bigPS5.jpg");
		playScreenPanel = new JPanel();
		playScreenPanel.setLayout(new BorderLayout());
		playScreenPanel.setPreferredSize(new Dimension(1130, 680));
		playScreenPanel.add(boardView, BorderLayout.CENTER);
		// add(controlPanel, BorderLayout.EAST);
		eastPanel = new JPanel();
		eastPanel.setLayout(new BorderLayout());
		eastPanel.add(controlPanel, BorderLayout.CENTER);
		eastPanel.add(buttonPanel, BorderLayout.SOUTH);
		playScreenPanel.add(eastPanel, BorderLayout.EAST);
	}

	private void createCompoment() {
		// // create button panel
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(restartBT = new JButton("Restart"));
		buttonPanel.add(backToMenuBT = new JButton("Back To Menu"));
		buttonPanel.add(quitBT = new JButton("Quit"));
		buttonPanel.setSize(new Dimension(300, 50));
	}

	private void addButtonAction() {
		restartBT.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO restart game
				newGame();
			}
		});
		backToMenuBT.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO back to welcome menu
				backTomenu();
			}

		});
		quitBT.addActionListener(e -> System.exit(0));
	}

	void newGame() {
		new PlayScreen();
		boardController.setStopGame(true);
		boardController.updateObservers();
		setVisible(false);
	}

	private void backTomenu() {
		// TODO Auto-generated method stub
		this.setVisible(false);
		new WelcomeFrame("resource\\tree.jpg", this, playScreenPanel);
	}

	public BoardController getBoardController() {
		return boardController;
	}

	@Override
	public void update(BoardController e) {
		// TODO Auto-generated method stub
		if (boardController.isStopGame())
			return;
		if (e.getNumberPieceSurvive(Flag.BLACK) <= 2) {
			int output = JOptionPane.showConfirmDialog(boardView, "White win, do you want to play a new game ?",
					"Alert", JOptionPane.YES_NO_OPTION);

			if (output == JOptionPane.YES_OPTION) {
				newGame();

			} else if (output == JOptionPane.NO_OPTION) {
				System.exit(0);
				// backTomenu();
			}
		}
		if (e.getTurnCounter() == 30) {
			int output = JOptionPane.showConfirmDialog(this, "Draw !, do you want to play a new game ?", "Alert",
					JOptionPane.YES_NO_OPTION);
			if (output == JOptionPane.YES_OPTION) {
				newGame();
			} else if (output == JOptionPane.NO_OPTION) {
				System.exit(0);
				// backTomenu();
			}
		}
		if (e.getNumberPieceSurvive(Flag.WHITE) <= 2) {
			int output = JOptionPane.showConfirmDialog(this, "Black win, do you want to play a new game ?", "Alert",
					JOptionPane.YES_NO_OPTION);
			if (output == JOptionPane.YES_OPTION) {
				newGame();
			} else if (output == JOptionPane.NO_OPTION) {
				System.exit(0);
				// backTomenu();
			}
		}
	}

	// public static void main(String[] args) {
	// PlayScreen ps = new PlayScreen();
	// }

}