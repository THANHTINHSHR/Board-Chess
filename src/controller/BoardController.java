package controller;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


import model.BoardModel;
import model.ClockThread;
import model.Flag;
import model.Piece;
import model.Position;
import model.SettingModel;
import observer.Observable;
import observer.Observer;

public class BoardController implements Observable<BoardController>, Observer<ClockThread> {

	final BoardModel board;
	private Piece selectedChess;
	private Flag turn;
	private List<Position> movablePositions;
	private int turnCounter;
	private List<Observer<BoardController>> observers;
	private boolean isStopGame;
	private String preTurnP1;
	private String preTurnP2;

	public BoardController(BoardModel board) {
		super();
		this.board = board;
		movablePositions = Collections.emptyList();
		//
		// turn = Flag.BLACK;
		turn = (SettingModel.p1First) ? Flag.WHITE : Flag.BLACK;
		turnCounter = 0;
		observers = new LinkedList<Observer<BoardController>>();
		isStopGame = false;
		preTurnP1 = null;
		preTurnP2 = null;
		//
	}

	public String getPreTurnP1() {
		return preTurnP1;
	}

	public void setPreTurnP1(String preTurnP1) {
		this.preTurnP1 = preTurnP1;
	}

	public String getPreTurnP2() {
		return preTurnP2;
	}

	public void setPreTurnP2(String preTurnP2) {
		this.preTurnP2 = preTurnP2;
	}

	public boolean isStopGame() {
		return isStopGame;
	}

	public void setStopGame(boolean isStopGame) {
		this.isStopGame = isStopGame;
	}

	public Flag getTurn() {
		return turn;
	}

	public void setTurn(Flag turn) {
		this.turn = turn;

	}

	public int getTurnCounter() {
		return turnCounter;
	}

	public List<Position> getMovablePositions() {
		return movablePositions;
	}

	public List<Piece> getChesses() {
		return board.pieces;
	}

	public void select(Piece chess) {
		if (isSelecting())
			deselect();
		selectedChess = chess;
		selectedChess.selected = true;
		movablePositions = selectedChess.getMovablePositions(getChesses());
	}

	public void deselect() {
		selectedChess.selected = false;
		selectedChess = null;
		movablePositions = Collections.emptyList();
	}

	public boolean isSelecting() {
		return selectedChess != null;
	}

	public void updatePreTurn(Piece selectedChess, Position nextPosition) {
		Flag flag = selectedChess.flag;
		int sx = selectedChess.position.x;
		int sy = selectedChess.position.y;
		int px = nextPosition.x;
		int py = nextPosition.y;
		if (flag == Flag.WHITE) {
			preTurnP1 = "[" + sx + "," + sy + "]" + " -> [" + px + ", " + py + "]";
		} else {
			preTurnP2 = "[" + sx + "," + sy + "]" + " -> [" + px + "," + py + "]";
		}
	}

	public void move(Position position) {
		selectedChess.position = position;
		deselect();
	}

	public List<Piece> attack(Position pos) {
		List<Piece> pieceVictims = board.attack(selectedChess, pos);
		Flag f = (pieceVictims == null) ? turn.getEnemy() : turn;
		if (f != turn) {
			turnCounter++;
			turn = f;
			updatePreTurn(selectedChess, pos);
			// endTime = false;
		}
		// System.out.println("Next Turn : " + turn);
		// System.out.println(turnCounter);
		updateObservers();
		return pieceVictims;

	}

	public void removePieces(List<Piece> pieceVictims) {
		board.removePieces(pieceVictims);

	}

	public int getNumberPieceSurvive(Flag flag) {
		return board.getNumberPieceSurvive(flag);

	}

	@Override
	public void registerObserver(Observer<BoardController> o) {
		observers.add(o);

	}

	@Override
	public void removeObserver(Observer<BoardController> o) {
		observers.remove(o);
	}

	@Override
	public void updateObservers() {
		observers.forEach(o -> o.update(this));
	}

	@Override
	public void update(ClockThread e) {
		// TODO Auto-generated method stub
		if (e.getSec() < 0) {
			// System.out.println("sec controll from thread " + e.getSec());
			turn = e.getTurn();
			turnCounter++;
			updateObservers();
			// System.out.println("turn ( board think :" + turn);
		}

	}

}
