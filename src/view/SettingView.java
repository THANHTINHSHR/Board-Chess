package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.SettingController;
import model.SettingModel;

public class SettingView extends JFrame implements CustomPanel {
	private static final long serialVersionUID = 1L;
	private SettingController stController;
	private NamePlayersPanel setNamePN;
	private ColorPlayersPanel setColorPN;
	private WhoGoFirstPanel whoFirsePN;
	// private boolean p1GoFirst;
	// private String p1Name, p2Name;
	// private int p1Color, p2Color;
	private JButton saveBT, backToMenuBT, setDefaultBT;
	private JPanel support, sp0, sp1, sp2, bigSetting;
	private WelcomeFrame welcome;
	private Image image;

	public SettingView(SettingController settingControl, String imgPG, WelcomeFrame welcome) {
		// TODO Auto-generated constructor stub
		this.stController = settingControl;
		this.welcome = welcome;
		this.welcome.setVisible(false);
		image = new ImageIcon(imgPG).getImage();
		gui();
		createCompoment();

		addCompoment();

		makeTransparent();

		addButtonAction();
		loadSetting();
	}

	private void loadSetting() {
		// TODO Auto-generated method stub
		// setNamePN.setNameP1(stController.getSetting().getP1Name());
		setNamePN.setNameP1(stController.getSetting().getP1Name());
		setNamePN.setNameP2(stController.getSetting().getP2Name());
		//
		setColorPN.setP1Color(stController.getSetting().getP1Color());
		setColorPN.setP2Color(stController.getSetting().getP2Color());
		//
		whoFirsePN.setIsP1GoFirst(stController.getSetting().isP1GoFirst());
		repaint();
	}

	private void addButtonAction() {
		// TODO Auto-generated method stub
		setDefaultBT.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				stController.setDefaultSetting();
				loadSetting();
				// repaint();
			}
		});
		saveBT.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				stController.saveSetting(setNamePN.getNameP1(), setNamePN.getNameP2(), setColorPN.getP1Color(),
						setColorPN.getP2Color(), whoFirsePN.p1GoFirst());

				// repaint();
			}
		});
		backToMenuBT.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				welcome.setVisible(true);
			}
		});
	}

	private void gui() {
		setTitle("SETTING");
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(850, 575));
		setVisible(true);
		// setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);// beacuse want to back
		// to menu
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
	}

	@Override
	public void createCompoment() {
		// TODO Auto-generated method stub
		setNamePN = new NamePlayersPanel();
		setColorPN = new ColorPlayersPanel();
		whoFirsePN = new WhoGoFirstPanel();
		saveBT = new JButton("SAVE");
		setDefaultBT = new JButton("Set Default");
		backToMenuBT = new JButton("Back To Menu");
		//
		// bigSetting = new JPanel(new GridLayout(1, 2));
		// bigSetting = new JPanel() {
		// @Override
		// public void paintComponent(Graphics g) {
		// // TODO Auto-generated method stub
		// super.paintComponents(g);
		// g.drawImage(image, 0, 0, null);
		// }
		// };
		bigSetting = new ImagePanel(image);
		bigSetting.setLayout(new GridLayout(1, 2));
	}

	@Override
	public void addCompoment() {
		// TODO Auto-generated method stub
		setLayout(new GridLayout(1, 2));
		// setLayout(new BorderLayout());
		sp1 = new JPanel();
		sp1.setLayout(new GridLayout(2, 1));
		sp1.add(setNamePN);
		sp1.add(setColorPN);
		//
		// support = new JPanel(new FlowLayout());
		// support.setPreferredSize(new Dimension(150, 50));
		sp2 = new JPanel();
		sp2.setLayout(new GridLayout(2, 1));
		support = new JPanel(new FlowLayout());
		support.setPreferredSize(new Dimension(150, 50));
		support.add(setDefaultBT);
		support.add(backToMenuBT);
		support.add(saveBT);
		sp0 = new JPanel();
		sp0.setLayout(new GridLayout(2, 1));
		sp0.add(whoFirsePN);
		sp0.add(support);
		sp2.add(sp0);
		//
		bigSetting.add(sp1);
		bigSetting.add(sp2);
		add(bigSetting);
	}

	@Override
	public void makeTransparent() {
		// TODO Auto-generated method stub
		Color t = new Color(0, 0, 0, 0);

		support.setBackground(t);
		sp0.setBackground(t);
		sp1.setBackground(t);
		sp2.setBackground(t);

	}

	@Override
	public void setGroup() {
		// TODO Auto-generated method stub

	}

	// @Override
	// public void setDefault() {
	// // TODO Auto-generated method stub
	//
	// }
	@Override
	public void paintComponents(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponents(g);
		g.drawImage(image, 0, 0, null);
	}

	public static void main(String[] args) {
		// SettingView f = new SettingView(new SettingController(new SettingModel()),
		// "resource//ST1.jpeg",
		// new Welcome("resource\\tree.jpg", null));
		SettingView f = new SettingView(new SettingController(SettingModel.getInstance()), "resource\\WOOW1.png",
				new WelcomeFrame("resource\\tree.jpg", null));
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setResizable(true);
		// f.setLocationRelativeTo(null);
	}
}
