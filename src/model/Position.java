package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Position implements Comparable<Position> {

	public final int x, y;
	public static final int MAX = 4;
	public static final int MIN = 0;

	public Position(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "Pos " + x + ", "+ y ;
	}

	@Override
	public int compareTo(Position o) {
		return value() - o.value();
	}

	private int value() {
		return y * 4 + x;
	}

	public boolean isCrossCell() {
		return ((x % 2 == 0 && y % 2 == 0) || (x % 2 != 0 && y % 2 != 0));
	}

	boolean isGoodPosition(int x, int y) {
		return (x >= MIN) && (x <= MAX) && (y >= MIN) && (y <= MAX);
	}

	public List<Position> getAdjPosition() {
		// TODO get All adj position of this position
		List<Position> adjPositions = new ArrayList<Position>();

		if (isCrossCell()) {
			for (int i = x - 1; i <= x + 1; i++) {
				for (int j = y - 1; j <= y + 1; j++) {
					if (isGoodPosition(i, j))
						adjPositions.add(new Position(i, j));
				}
			}
		} else {
			for (int i = x - 1; i <= x + 1; i++) {
				for (int j = y - 1; j <= y + 1; j++) {
					if (((i == x) || (j == y)) && isGoodPosition(i, j))
						adjPositions.add(new Position(i, j));
				}
			}
		}
		return adjPositions;
	}

	public List<Position> getAdjMovablePositions(List<Position> pieces) {
		// TODO get All adj move position of this position
		List<Position> positionAdj = getAdjPosition();
		return positionAdj.stream().filter(p -> !pieces.contains(p)).collect(Collectors.toList());
	}

	public List<Position> getJumpPositions(List<Position> adjEnemyPositions, List<Position> positions) {
		// TODO find all cross position available
		List<Position> jump = new LinkedList<>();
		int deX, deY;
		for (Position p : adjEnemyPositions) {
			deX = p.x - x;
			deY = p.y - y;
			if (isGoodPosition(p.x + deX, p.y + deY) && !positions.contains(new Position(p.x + deX, p.y + deY))) {
				jump.add(new Position(p.x + deX, p.y + deY));
			}
		}
		return jump;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	public Position getJumpVictimPosition(Position jumpPosition) {
		int px = jumpPosition.x;
		int py = jumpPosition.y;
		int maxX = Math.max(x, px);
		// int minX = Math.min(x, px);
		int maxY = Math.max(y, py);
		// int minY = Math.min(y, py);
		//
		if (px == x) {
			return new Position(x, maxY - 1);
		}
		if (py == y) {
			return new Position(maxX - 1, y);
		}
		return new Position(maxX - 1, maxY - 1);
	}

}
