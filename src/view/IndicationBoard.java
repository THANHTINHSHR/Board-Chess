package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class IndicationBoard extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JScrollPane scrollP;

	public IndicationBoard(String indiPath) {
		// TODO Auto-generated constructor stub
		// setPreferredSize(new Dimension(700, 600));
		ImageIcon img = new ImageIcon(indiPath);
		JLabel lb = new JLabel(img);
		scrollP = new JScrollPane(lb, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollP.setPreferredSize(new Dimension(700, 500));
		add(scrollP, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		JFrame f = new JFrame("Scroll");
		f.add(new IndicationBoard("resource//HSF.PNG"));
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// f.setPreferredSize(new Dimension(700, 700));
		f.pack();

		f.setLocationRelativeTo(null);
	}
}