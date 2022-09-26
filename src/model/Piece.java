package model;

import java.util.List;
import java.util.stream.Collectors;

public class Piece {

	public Position position;

	public final Flag flag;

	public boolean selected;

	public Piece(Flag flag, Position position) {
		super();
		this.position = position;
		this.flag = flag;
	}

	public List<Position> getAdjMovablePositions(List<Piece> pieces) {
		return position.getAdjMovablePositions(pieces.stream().map(Piece::getPosition).collect(Collectors.toList()));
	}

	public List<Position> getJumpPositions(List<Piece> pieces) {
		return position.getJumpPositions(getAdjEnemyPositions(pieces),
				pieces.stream().map(Piece::getPosition).collect(Collectors.toList()));
	}

	public List<Position> getAdjEnemyPositions(List<Piece> pieces) {
		return pieces.stream().filter(p -> isEnemy(p)).collect(Collectors.toList()).stream().map(Piece::getPosition)
				.collect(Collectors.toList()).stream().filter(p -> getAdjPosition().contains(p))
				.collect(Collectors.toList());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((flag == null) ? 0 : flag.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		return result;
	}

	public boolean equals(Piece piece) {
		return position.x == piece.position.x && position.y == piece.position.y && flag == piece.flag;

	}

	@Override
	public String toString() {
		return "Piece " + position + ", " + flag;
	}

	public List<Position> getAdjPosition() {
		return position.getAdjPosition();
	}

	public List<Position> getMovablePositions(List<Piece> pieces) {
		List<Position> adj = getAdjMovablePositions(pieces);
		List<Position> jumb = getJumpPositions(pieces);
		adj.addAll(jumb);
		return adj;

	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public boolean isEnemy(Piece piece) {
		return flag.getEnemy() != piece.flag.getEnemy();
	}

	public Position getVictimJumpPosition(Position jumpPosition) {
		return position.getJumpVictimPosition(jumpPosition);
	}

	// public static void main(String[] args) {
	// Piece p1 = new Piece(Flag.BLACK, new Position(2, 7));
	// Piece p2 = new Piece(Flag.BLACK, new Position(2, 7));
	// System.out.println("p1 is enemy p2 :" + p1.isEnemy(p2));
	// }

}
