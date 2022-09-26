package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class HowToPlayFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IndicationBoard instruction;
	// private Welcome welcome;
	private JButton backToWelcome;

	// JButton back;
	public HowToPlayFrame(WelcomeFrame welcome, String pathIndi) {
		// TODO Auto-generated constructor stub
		this.instruction = new IndicationBoard(pathIndi);
		// this.welcome = welcome;
		welcome.setVisible(false);
		this.backToWelcome = new JButton("Back To Welcome");

		setTitle("HOW TO PLAY");
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(715, 575));
		setVisible(true);
		// setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);// beacuse want to back
		// to menu
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setResizable(false);
		setLocationRelativeTo(null);

		add(backToWelcome, BorderLayout.NORTH);
		add(instruction, BorderLayout.CENTER);

		backToWelcome.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				welcome.setVisible(true);
			}
		});
	}

	public static void main(String[] args) {
		new HowToPlayFrame(new WelcomeFrame("resource//tree.jpg"), "resource//HSF.PNG");
	}

}
