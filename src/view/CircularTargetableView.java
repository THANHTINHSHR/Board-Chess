package view;

import java.awt.*;

import model.Position;

public abstract class CircularTargetableView {

    protected double distanceFrom(Position position, Point point) {
        return point.distance(position.x * BoardView.SPACE + BoardView.GAP,
                position.y * BoardView.SPACE + BoardView.GAP);
    }

    public boolean isTargeted(Point point) {
        return distanceFrom(getPosition(), point) <= getRadius();
    }

    public abstract Color getColor();

    public void paint(Graphics g) {
        g.setColor(getColor());
    }

    public abstract int getRadius();

    public abstract Position getPosition();
}
