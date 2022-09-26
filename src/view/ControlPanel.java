package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.BoardController;
import model.ClockThread;

public class ControlPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel buttonPanel, clockAndTurnPanel, anonymousPanel;
	StatusPanel statusPanel;
	BoardView boardView;
	BoardController boardController;
	TurnLabel turnLabel;
	// ClockCountDownLabel clock;
	ClockCountDownLabel clock;
	ClockThread clockThread;

	public ControlPanel(BoardView boardView) {
		this.boardView = boardView;
		this.boardController = boardView.getController();

		setBorder(BorderFactory.createLineBorder(java.awt.Color.GRAY, 10));
		setLayout(new BorderLayout());
		createCompoment();
		addObservers();
		// addButtonAction();
		setPreferredSize(new Dimension(438, 600));
	}

	private void createCompoment() {
		// create turn panel and clock
		clockAndTurnPanel = new JPanel();
		clockAndTurnPanel.setLayout(new GridLayout(2, 1));

		turnLabel = new TurnLabel();
		clockAndTurnPanel.add(turnLabel);
		turnLabel.setHorizontalAlignment(JLabel.CENTER);
		// clock = new ClockCountDownLabel();
		clockThread = new ClockThread();
		clock = new ClockCountDownLabel();
		clockAndTurnPanel.add(clock);
		clock.setHorizontalAlignment(JLabel.CENTER);
		// create status panel
		statusPanel = new StatusPanel();
		// create anonymous panel
		anonymousPanel = new AnonymousPanel("resource//hd1.PNG");
		// anonymousPanel = new AnonymousPanel("resource//HS2.png");
		// add panels
		JPanel clockTurnStatusPanel = new JPanel();
		clockTurnStatusPanel.setLayout(new BorderLayout());
		clockTurnStatusPanel.add(clockAndTurnPanel, BorderLayout.NORTH);
		clockTurnStatusPanel.add(statusPanel, BorderLayout.CENTER);

		JPanel supBigPanel = new JPanel();
		supBigPanel.setLayout(new GridLayout(2, 1));
		supBigPanel.add(clockTurnStatusPanel);
		supBigPanel.add(anonymousPanel);

		add(supBigPanel, BorderLayout.CENTER);
		// add(buttonPanel, BorderLayout.SOUTH);

	}

	private void addObservers() {
		boardController.registerObserver(turnLabel);
		boardController.registerObserver(statusPanel);
		boardController.registerObserver(clockThread);
		clockThread.registerObserver(clock);
		clockThread.registerObserver(boardController);

	}

	// public static void main(String[] args) {
	// JFrame f = new JFrame("status");
	// f.add(new ControlPanel(new BoardView(new BoardController(new Board()))));
	// f.setVisible(true);
	// f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	// // f.setSize(454, 700);
	// f.pack();
	// // f.setResizable(false);
	// f.setLocationRelativeTo(null);
	// }

}
