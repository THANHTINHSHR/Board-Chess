package view;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.BoardController;
import model.BoardModel;
import model.Piece;

public class BoardView extends JPanel {
	/**
	 *
	 */
	private static final long serialVersionUID = -442107658327021217L;
	public static final int GAP = 40;
	public static final int SPACE = 150;
	public static final int HEIGHT = 600;
	public static final int NUMBER_LINE = 5;

	final BoardController controller;

	public BoardController getController() {
		return controller;
	}

	List<PieceView> pieceViews;
	private List<HintView> hintViews;
	private Image image;

	public BoardView(BoardController controller, String pathBG) {
		super();
		this.controller = controller;
		pieceViews = controller.getChesses().stream().map(PieceView::new).collect(Collectors.toList());
		updateHintViews();
		int size = HEIGHT + GAP * 2;
		setPreferredSize(new Dimension(size, size));
		addMouseListener();
		image = new ImageIcon(pathBG).getImage();
	}

	private void updateHintViews() {
		hintViews = controller.getMovablePositions().stream().map(HintView::new).collect(Collectors.toList());

	}

	private void addMouseListener() {
		addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				Point p = e.getPoint();
				// PieceView cv = getTargeted(chessViews, p);
				// HintView h = getTargeted(hintViews, p);

				//
				PieceView cv = getTargeted(pieceViews, p);

				if (cv != null && (cv.piece.flag == controller.getTurn())) {
					controller.select(cv.piece);
					update();
					return;
				}

				HintView h = getTargeted(hintViews, p);
				if (h != null) {
					// kill first move after
					List<Piece> pieceVictims = controller.attack(h.position);
					controller.removePieces(pieceVictims);
					removeChessViews(pieceVictims);
					controller.move(h.position);
					update();
					return;
				}
				if (controller.isSelecting()) {
					controller.deselect();
					update();
					return;
				}
			}
			// end turn!
		});
	}

	private void removeChessViews(List<Piece> pieceVictims) {
		// TODO if is a simple attack then do notthing
		if (pieceVictims == null) {
			return;
		}
		// TODO if is a jump attack then remove one pieceView
		if (pieceVictims.size() == 1) {
			Piece pieceVictim = pieceVictims.get(0);
			PieceView pieceViewVictim = null;
			for (PieceView pw : pieceViews) {
				if (pw.piece.equals(pieceVictim)) {
					pieceViewVictim = pw;
					break;
				}
			}
			pieceViews.remove(pieceViewVictim);
			return;
		}
		// TODO if is a burden attack then remove pieces
		pieceViews = pieceViews.stream().filter(pw -> !pieceVictims.contains(pw.piece)).collect(Collectors.toList());
		return;

	}

	private void update() {
		updateHintViews();
		repaint();
	}

	private <T extends CircularTargetableView> T getTargeted(List<T> ts, Point p) {
		return ts.stream().filter(t -> t.isTargeted(p)).findFirst().orElse(null);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		g.drawImage(image, 0, 0, null);
		paintLines(g);
		pieceViews.forEach(c -> c.paint(g2));
		hintViews.forEach(h -> h.paint(g2));
	}

	private void paintLines(Graphics g) {
		for (int i = 0, x1 = 0, x2 = HEIGHT, y1 = 0, y2 = 0; i < NUMBER_LINE; i++) {
			g.drawLine(x1 + GAP, y1 + GAP, x2 + GAP, y2 + GAP);
			y1 += SPACE;
			y2 += SPACE;
		}
		for (int i = 0, x1 = 0, x2 = 0, y1 = 0, y2 = HEIGHT; i < NUMBER_LINE; i++) {
			g.drawLine(x1 + GAP, y1 + GAP, x2 + GAP, y2 + GAP);
			x1 += SPACE;
			x2 += SPACE;
		}
		g.drawLine(2 * SPACE + GAP, GAP, GAP, 2 * SPACE + GAP);
		g.drawLine(GAP, 2 * SPACE + GAP, 2 * SPACE + GAP, HEIGHT + GAP);
		g.drawLine(2 * SPACE + GAP, GAP, HEIGHT + GAP, 2 * SPACE + GAP);
		g.drawLine(2 * SPACE + GAP, HEIGHT + GAP, HEIGHT + GAP, 2 * SPACE + GAP);
		g.drawLine(GAP, GAP, HEIGHT + GAP, HEIGHT + GAP);
		g.drawLine(HEIGHT + GAP, GAP, GAP, HEIGHT + GAP);
		for (int i = 150; i < HEIGHT; i += 150) {
			for (int j = 150; j < HEIGHT; j += 150) {
				g.fillOval(i - 5 + GAP, j - 5 + GAP, 10, 10);
			}
		}
	}

	public static void main(String[] args) {
		JFrame f = new JFrame("status");
		f.add(new BoardView(new BoardController(new BoardModel()), "resource//bigPS5.jpg"));
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.pack();
		f.setLocationRelativeTo(null);
	}
}
