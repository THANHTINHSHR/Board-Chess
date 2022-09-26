package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class NamePlayersPanel extends JPanel implements CustomPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel playerW, playerB;
	JTextField tFP1, tFP2;
	JPanel sp1, sp2;

	public NamePlayersPanel() {
		// TODO Auto-generated constructor stub
		createCompoment();
		makeTransparent();
		addCompoment();
		// setDefault();
	}

	// limit the number of characters in JTextField! ( from Stack Overflow)
	private class JTextFieldLimit extends PlainDocument {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private int limit;

		private JTextFieldLimit(int limit) {
			super();
			this.limit = limit;
		}

		public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
			if (str == null)
				return;

			if ((getLength() + str.length()) <= limit) {
				super.insertString(offset, str, attr);
			}
		}
	}

	// @Override
	// public void setDefault() {
	// // TODO Auto-generated method stub
	// tFP1.setText("Player 1");
	// tFP2.setText("Player 2");
	// }

	@Override
	public void createCompoment() {
		// TODO Auto-generated method stub
		playerW = new JLabel("PLAYER 1 : ");
		playerB = new JLabel("PLAYER 2 : ");
		playerW.setFont(new Font("", Font.BOLD, 20));
		playerB.setFont(new Font("", Font.BOLD, 20));
		//
		// tFP1 = new JTextField("", 15);
		// tFP2 = new JTextField("", 15);
		tFP1 = new JTextField(new JTextFieldLimit(7), "", 15);
		tFP2 = new JTextField(new JTextFieldLimit(7), "", 15);
		tFP1.setFont(new Font("", Font.BOLD, 20));
		tFP2.setFont(new Font("", Font.BOLD, 20));
		//
		sp1 = new JPanel();
		sp2 = new JPanel();
	}

	@Override
	public void makeTransparent() {
		// TODO Auto-generated method stub
		sp1.setBackground(new Color(0, 0, 0, 0));
		sp2.setBackground(new Color(0, 0, 0, 0));
		setBackground(new Color(0, 0, 0, 0));

	}

	public String getNameP1() {
		return tFP1.getText();
	}

	public String getNameP2() {
		return tFP2.getText();
	}

	public void setNameP1(String name) {
		tFP1.setText(name);
	}

	public void setNameP2(String name) {
		tFP2.setText(name);
	}

	public void addCompoment() {
		// TODO Auto-generated method stub

		sp1.add(playerW);
		sp1.add(tFP1);
		//
		sp2.add(playerB);
		sp2.add(tFP2);
		//
		setLayout(new GridLayout(2, 1));
		add(sp1);
		add(sp2);
	}

	// public static void main(String[] args) {
	// JFrame f = new JFrame("frame");
	// f.add(new NamePalyersPanel());
	// // f.setPreferredSize(new Dimension(715, 575));
	// f.setVisible(true);
	// f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	// f.pack();
	// // f.setResizable(false);
	// f.setLocationRelativeTo(null);
	// }

	@Override
	public void setGroup() {
		// TODO Auto-generated method stub

	}
}
