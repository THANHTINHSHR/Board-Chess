package view;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton restartBT, backToMenuBT, quitBT;

	public ButtonPanel() {
		setLayout(new FlowLayout());
		add(restartBT = new JButton("Restart"));
		// restartBT.addActionListener(this);
		add(backToMenuBT = new JButton("Back To Menu"));
		// backToMenuBT.addActionListener(this);
		add(quitBT = new JButton("Quit"));
		setSize(new Dimension(300, 50));
		// setPreferredSize(new Dimension(300, 50));
	}

//	public static void main(String[] args) {
//		JFrame frame = new JFrame("button panel");
//		frame.setVisible(true);
//		frame.setSize(100, 100);
//		frame.setLayout(new BorderLayout());
//		frame.setLocationRelativeTo(null);
//		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		frame.add(new ButtonPanel());
//		frame.pack();
//	}
}
