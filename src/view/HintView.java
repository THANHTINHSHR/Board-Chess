package view;

import java.awt.*;

import model.Position;

public class HintView extends CircularTargetableView {

	public final Position position;

	public HintView(Position position) {
		this.position = position;
	}

	@Override
	public Color getColor() {
		return Color.GRAY;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		int r = getRadius();
		int x = position.x * BoardView.SPACE - (r - BoardView.GAP);
		int y = position.y * BoardView.SPACE - (r - BoardView.GAP);
		g.drawOval(x, y, r * 2, r * 2);

	}

	@Override
	public int getRadius() {
		return 40;
	}

	@Override
	public Position getPosition() {
		return position;
	}

	@Override
	public String toString() {
		return position.toString();
	}
}
