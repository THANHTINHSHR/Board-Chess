package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class BoardModel {

	public List<Piece> pieces;

	public BoardModel(List<Piece> pieces) {
		super();
		this.pieces = pieces;
	}

	public BoardModel() {
		loadNewPieces();
	}

	void loadNewPieces() {
		pieces = new ArrayList<>();
		pieces.add(new Piece(Flag.WHITE, new Position(0, 0)));
		pieces.add(new Piece(Flag.WHITE, new Position(0, 1)));
		pieces.add(new Piece(Flag.WHITE, new Position(0, 2)));
		pieces.add(new Piece(Flag.WHITE, new Position(1, 0)));
		pieces.add(new Piece(Flag.WHITE, new Position(2, 0)));
		pieces.add(new Piece(Flag.WHITE, new Position(3, 0)));
		pieces.add(new Piece(Flag.WHITE, new Position(4, 0)));
		pieces.add(new Piece(Flag.WHITE, new Position(4, 1)));
		//
		pieces.add(new Piece(Flag.BLACK, new Position(0, 3)));
		pieces.add(new Piece(Flag.BLACK, new Position(0, 4)));
		pieces.add(new Piece(Flag.BLACK, new Position(1, 4)));
		pieces.add(new Piece(Flag.BLACK, new Position(2, 4)));
		pieces.add(new Piece(Flag.BLACK, new Position(3, 4)));
		pieces.add(new Piece(Flag.BLACK, new Position(4, 4)));
		pieces.add(new Piece(Flag.BLACK, new Position(4, 3)));
		pieces.add(new Piece(Flag.BLACK, new Position(4, 2)));
		//
	}

	private List<Piece> getPairsEnemys(Flag flag, Position position) {
		// TODO get pairs of enemys adjacent position;
		Piece virtualPiece = new Piece(flag, position);
		List<Position> adjEnemyPositions = virtualPiece.getAdjEnemyPositions(pieces);
		HashSet<Position> enemyHasPairs = new HashSet<Position>();
		Position virtualPartner;
		for (Position adjEPos : adjEnemyPositions) {
			virtualPartner = new Position(adjEPos.x + 2, adjEPos.y + 2);
			if (adjEnemyPositions.contains(virtualPartner)) {
				enemyHasPairs.add(adjEPos);
				enemyHasPairs.add(virtualPartner);
			}
			virtualPartner = new Position(adjEPos.x + 2, adjEPos.y - 2);
			if (adjEnemyPositions.contains(virtualPartner)) {
				enemyHasPairs.add(adjEPos);
				enemyHasPairs.add(virtualPartner);
			}
			virtualPartner = new Position(adjEPos.x, adjEPos.y + 2);
			if (adjEPos.x == position.x && adjEnemyPositions.contains(virtualPartner)) {
				enemyHasPairs.add(adjEPos);
				enemyHasPairs.add(virtualPartner);
			}
			virtualPartner = new Position(adjEPos.x + 2, adjEPos.y);
			if (adjEPos.y == position.y && adjEnemyPositions.contains(virtualPartner)) {
				enemyHasPairs.add(adjEPos);
				enemyHasPairs.add(virtualPartner);
			}

		}
		return pieces.stream().filter(p -> enemyHasPairs.contains(p.position)).collect(Collectors.toList());
	}

	public List<Piece> attack(Piece selectedChess, Position position) {
		List<Piece> result = new ArrayList<>();
		// TODO check if is jump attack the return a victim jump .
		Position victimJumpPos;
		boolean isJumpAttack = selectedChess.getJumpPositions(pieces).contains(position);
		if (isJumpAttack) {
//			System.out.println("is a jump attack");
			victimJumpPos = selectedChess.getVictimJumpPosition(position);
			Piece jumpVictim = new Piece(selectedChess.flag.getEnemy(), victimJumpPos);
			result.add(jumpVictim);
			return result;
		}
		// TODO check if is burden attack then return victim jumps
		List<Piece> pairsEnemys = getPairsEnemys(selectedChess.flag, position);
		boolean isBurdenAttack = !pairsEnemys.isEmpty();
		if (isBurdenAttack) {
//			System.out.println("is a burden attack");
			return pairsEnemys;
		}
		// TODO else is a simple move
//		System.out.println("is a simple move");
		return null;

	}

	public void removePieces(List<Piece> pieceVictims) {
		// TODO if is a simple attack then do notthing
		if (pieceVictims == null) {
			return;
		}
		// TODO if is a jump attack then remove one piece
		if (pieceVictims.size() == 1) {
			Piece pieceVictim = pieceVictims.get(0);
			int index = 0;
			for (int i = 0; i < pieces.size(); i++) {
				if (pieces.get(i).equals(pieceVictim)) {
					index = i;
					break;
				}
			}
			pieces.remove(index);
			return;
		} // TODO if is a burden attack then remove pieces
		pieces = pieces.stream().filter(p -> !pieceVictims.contains(p)).collect(Collectors.toList());
	}

	public int getNumberPieceSurvive(Flag flag) {
		// TODO get Number Piece Survive
		return (int) pieces.stream().filter(p -> p.flag == flag).count();

	}

}