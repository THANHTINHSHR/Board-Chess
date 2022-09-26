package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import model.Flag;
import model.Piece;
import model.Position;
import model.SettingModel;

public class PieceView extends CircularTargetableView {

	@Override
	public String toString() {
		return "PW :  " + piece;
	}

	public final Piece piece;

	public PieceView(Piece piece) {
		super();
		this.piece = piece;
	}

	@Override
	public Color getColor() {
		switch (piece.flag) {
		case BLACK:
			return Color.BLACK;
		case WHITE:
			// return Color.GRAY;
			return Color.WHITE;
		}
		return null;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		// Image imgW = new ImageIcon("resource//B.png").getImage();
		// Image imgB = new ImageIcon("resource//A.png").getImage();
		Image imgW = new ImageIcon(SettingModel.p1Co).getImage();
		Image imgB = new ImageIcon(SettingModel.p2Co).getImage();
		Position p = piece.position;
		int r = getRadius();
		int x = p.x * BoardView.SPACE - (r - BoardView.GAP);
		int y = p.y * BoardView.SPACE - (r - BoardView.GAP);
		// g.fillOval(x, y, getRadius() * 2, getRadius() * 2);

		if (piece.flag == Flag.WHITE) {
			g.drawImage(imgW, x, y, null);
		} else {
			g.drawImage(imgB, x, y, null);
		}
	}

	@Override
	public int getRadius() {
		return piece.selected ? 40 : 30;
	}

	@Override
	public Position getPosition() {
		return piece.position;
	}
}
