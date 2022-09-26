package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.BoardController;
import model.Flag;
import model.SettingModel;
import observer.Observer;

public class StatusPanel extends JPanel implements Observer<BoardController> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel chessCountLB1, previousTurnLB1, chessCountLB2, previousTurnLB2, turnLB;
	JLabel pl1, pl2, cs, pt;
	JPanel statusPanel, table, supPanel;
	Image image;

	public StatusPanel() {
		gui();
		setPreferredSize(new Dimension(410, 300));
	}

	void gui() {
		setLayout(new BorderLayout());
		// boardPanel = new BoardView();
		//
		statusPanel = new JPanel();
		table = new JPanel(new GridLayout(3, 3));
		table.setBorder(BorderFactory.createLineBorder(java.awt.Color.BLACK, 5));
		//
		table.add(new JLabel(" "));

		// table.add(pl1 = new JLabel(" Player 1 ", JLabel.CENTER));
		// table.add(pl2 = new JLabel(" Player 2 ", JLabel.CENTER));
		table.add(pl1 = new JLabel(SettingModel.p1N, JLabel.CENTER));
		table.add(pl2 = new JLabel(SettingModel.p2N, JLabel.CENTER));
		table.add(cs = new JLabel("Chess Survive", JLabel.CENTER));
		table.add(chessCountLB1 = new JLabel("8", JLabel.CENTER));
		table.add(chessCountLB2 = new JLabel("8", JLabel.CENTER));
		table.add(pt = new JLabel("Previous Turn", JLabel.CENTER));
		table.add(previousTurnLB1 = new JLabel("null", JLabel.CENTER));
		table.add(previousTurnLB2 = new JLabel("null", JLabel.CENTER));
		// font
		pl1.setFont(new Font("", Font.BOLD, 25));
		pl2.setFont(new Font("", Font.BOLD, 25));
		chessCountLB1.setFont(new Font("", Font.BOLD, 30));
		chessCountLB2.setFont(new Font("", Font.BOLD, 30));
		cs.setFont(new Font("", Font.BOLD, 20));
		pt.setFont(new Font("", Font.BOLD, 20));
		previousTurnLB1.setFont(new Font("", Font.BOLD, 15));
		previousTurnLB2.setFont(new Font("", Font.BOLD, 15));
		//
		statusPanel.setLayout(new BorderLayout());
		supPanel = new JPanel();
		supPanel.setLayout(new BorderLayout());
		supPanel.add(table, BorderLayout.CENTER);
		//
		statusPanel.add(supPanel, BorderLayout.CENTER);
		//
		add(statusPanel, BorderLayout.EAST);

	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);

	}

	@Override
	public void update(BoardController e) {
		chessCountLB1.setText(e.getNumberPieceSurvive(Flag.WHITE) + "");
		chessCountLB2.setText(e.getNumberPieceSurvive(Flag.BLACK) + "");
		// pl1.setBackground((e.getTurn() == Flag.BLACK) ? Color.RED : Color.BLACK);
		// pl2.setForeground((e.getTurn() == Flag.BLACK) ? Color.RED : Color.BLACK);
		if (e.getTurn() == Flag.BLACK) {
			pl2.setForeground(Color.RED);
			pl1.setForeground(Color.BLACK);
		} else {
			pl1.setForeground(Color.RED);
			pl2.setForeground(Color.BLACK);
		}
		previousTurnLB1.setText(e.getPreTurnP1());
		previousTurnLB2.setText(e.getPreTurnP2());

	}

	public static void main(String[] args) {
		JFrame f = new JFrame("status");
		f.add(new StatusPanel());
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.pack();
	}
}
