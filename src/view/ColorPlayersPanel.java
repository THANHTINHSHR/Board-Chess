package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class ColorPlayersPanel extends JPanel implements CustomPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JRadioButton p1A, p1B, p1C, p1D, p2A, p2B, p2C, p2D;
	JLabel p1, p2;
	JPanel pcl1, pcl2, b1, b2;
	List<String> sourceChess;

	public ColorPlayersPanel() {
		// TODO Auto-generated constructor stub
		sourceChess = new ArrayList<>();
		createSource();
		createCompoment();
		setGroup();
		makeTransparent();
		addCompoment();
		// setDefault();
		setPreferredSize(new Dimension(500, 300));
		// setSize(200, 200);
	}
	// @Override
	// public void setDefault() {
	// // TODO Auto-generated method stub
	// p1B.setSelected(true);
	// p2A.setSelected(true);
	// }

	private void createSource() {
		// TODO Auto-generated method stub
		sourceChess.add("resource//A.png");
		sourceChess.add("resource//B.png");
		sourceChess.add("resource//C.png");
		sourceChess.add("resource//D.png");

	}

	@Override
	public void createCompoment() {
		// TODO Auto-generated method stub
		p1A = new JRadioButton();
		p1B = new JRadioButton();
		p1C = new JRadioButton();
		p1D = new JRadioButton();
		p2A = new JRadioButton();
		p2B = new JRadioButton();
		p2C = new JRadioButton();
		p2D = new JRadioButton();
		//
		p1 = new JLabel("PLAYER 1: ");
		p2 = new JLabel("PLAYER 2: ");
		p1.setFont(new Font("", Font.BOLD, 20));
		;
		p2.setFont(new Font("", Font.BOLD, 20));
		;
		//

		pcl1 = new JPanel(new GridLayout(2, 4, 20, 0));
		pcl2 = new JPanel(new GridLayout(2, 4, 20, 0));
		//
		b1 = new JPanel();
		b2 = new JPanel();

	}

	@Override
	public void setGroup() {
		// TODO Auto-generated method stub
		ButtonGroup gr1 = new ButtonGroup();

		gr1.add(p1A);
		gr1.add(p1B);
		gr1.add(p1C);
		gr1.add(p1D);
		//
		ButtonGroup gr2 = new ButtonGroup();
		gr2.add(p2A);
		gr2.add(p2B);
		gr2.add(p2C);
		gr2.add(p2D);
		//
	}

	@Override
	public void makeTransparent() {
		// TODO Auto-generated method stub
		Color t = new Color(0, 0, 0, 0);
		pcl1.setBackground(t);
		pcl2.setBackground(t);
		b1.setBackground(t);
		b2.setBackground(t);
		//
		// p1A.setOpaque(false);
		//
		// p1A.setBackground(new Color(15,84,100,5));
		// p1B.setBackground(t);
		// p1C.setBackground(t);
		// p1D.setBackground(t);
		// p2A.setBackground(t);
		// p2B.setBackground(t);
		// p2C.setBackground(t);
		// p2D.setBackground(t);
		//
		// p1A.setSelectedIcon(new ImageIcon("resource//A.png"));
		// p1B.setSelectedIcon(new ImageIcon("resource//B.png"));
		// p1C.setSelectedIcon(new ImageIcon("resource//C.png"));
		// p1D.setSelectedIcon(new ImageIcon("resource//D.png"));
		//
		// p1A.setIcon(new ImageIcon("resource//A.png"));
		// p1B.setIcon(new ImageIcon("resource//B.png"));
		// p1C.setIcon(new ImageIcon("resource//tra.png"));
		// p1D.setIcon(new ImageIcon("resource//tra.png"));
		setBackground(t);

	}

	@Override
	public void addCompoment() {
		// TODO Auto-generated method stub
		pcl1.add(new JLabel(new ImageIcon(sourceChess.get(0))));
		pcl1.add(new JLabel(new ImageIcon(sourceChess.get(1))));
		pcl1.add(new JLabel(new ImageIcon(sourceChess.get(2))));
		pcl1.add(new JLabel(new ImageIcon(sourceChess.get(3))));
		//
		pcl1.add(p1A);
		pcl1.add(p1B);
		pcl1.add(p1C);
		pcl1.add(p1D);
		p1A.setHorizontalAlignment(SwingConstants.CENTER);
		p1B.setHorizontalAlignment(SwingConstants.CENTER);
		p1C.setHorizontalAlignment(SwingConstants.CENTER);
		p1D.setHorizontalAlignment(SwingConstants.CENTER);
		//
		b1.add(p1);
		b1.add(pcl1);
		//
		pcl2.add(new JLabel(new ImageIcon(sourceChess.get(0))));
		pcl2.add(new JLabel(new ImageIcon(sourceChess.get(1))));
		pcl2.add(new JLabel(new ImageIcon(sourceChess.get(2))));
		pcl2.add(new JLabel(new ImageIcon(sourceChess.get(3))));
		//
		pcl2.add(p2A);
		pcl2.add(p2B);
		pcl2.add(p2C);
		pcl2.add(p2D);
		p2A.setHorizontalAlignment(SwingConstants.CENTER);
		p2B.setHorizontalAlignment(SwingConstants.CENTER);
		p2C.setHorizontalAlignment(SwingConstants.CENTER);
		p2D.setHorizontalAlignment(SwingConstants.CENTER);
		//
		b2.add(p2);
		b2.add(pcl2);
		//
		// setLayout(new GridLayout(2, 1));
		setLayout(new BorderLayout());
		add(b1, BorderLayout.NORTH);
		add(b2, BorderLayout.CENTER);

	}

	public int getP1Color() {
		if (p1A.isSelected())
			return 0;
		if (p1B.isSelected())
			return 1;
		if (p1C.isSelected())
			return 2;
		if (p1D.isSelected())
			return 3;
		return 1;
	}

	public int getP2Color() {
		if (p2A.isSelected())
			return 0;
		if (p2B.isSelected())
			return 1;
		if (p2C.isSelected())
			return 2;
		if (p2D.isSelected())
			return 3;
		return 0;
	}

	public void setP1Color(int c) {
		switch (c) {
		case 0:
			p1A.setSelected(true);
			return;
		case 1:
			p1B.setSelected(true);
			return;
		case 2:
			p1C.setSelected(true);
			return;
		case 3:
			p1D.setSelected(true);
			return;
		default:
			return;
		}
	}

	public void setP2Color(int c) {
		switch (c) {
		case 0:
			p2A.setSelected(true);
			return;
		case 1:
			p2B.setSelected(true);
			return;
		case 2:
			p2C.setSelected(true);
			return;
		case 3:
			p2D.setSelected(true);
			return;
		default:
			return;
		}
	}

	// public static void main(String[] args) {
	// JFrame f = new JFrame("frame");
	// f.setLayout(new BorderLayout());
	// // f.add(new NamePalyersPanel(), BorderLayout.NORTH);
	// f.add(new ColorPlayersPanel(), BorderLayout.CENTER);
	// // f.setPreferredSize(new Dimension(715, 575));
	// f.setVisible(true);
	// f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	// f.pack();
	// // f.setResizable(false);
	// f.setLocationRelativeTo(null);
	// }
}
