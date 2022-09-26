package view;

import java.awt.Dimension;

public class AnonymousPanel extends ImagePanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AnonymousPanel(String image) {
		super(image);
		setBackground(java.awt.Color.DARK_GRAY);
		setSize(4500, 400);
		setPreferredSize(new Dimension(400, 400));
	}

	// public static void main(String[] args) {
	// JFrame frame = new JFrame("anoymous panel");
	// frame.setVisible(true);
	// // frame.setSize(500, 500);
	// frame.setLayout(new BorderLayout());
	// frame.add(new AnonymousPanel("image/eye.jpg"));
	// frame.setLocationRelativeTo(null);
	// frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	// frame.pack();
	// }
}
