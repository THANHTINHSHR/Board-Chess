package model;

import java.util.ArrayList;
import java.util.List;

import controller.BoardController;
import observer.Observable;
import observer.Observer;

public class ClockThread implements Observable<ClockThread>, Observer<BoardController> {
	private Flag turn;
	// int sec;
	final static int LIMIT = 30;
	List<Observer<ClockThread>> observers;
	public TimeRunner timeRunner;

	class TimeRunner implements Runnable {
		boolean alive = true;
		int s = LIMIT;

		@Override
		public void run() {
			while (alive && s >= 0) {
				updateObservers();
				s--;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (s == -1) {
				turn = turn.getEnemy();
				updateObservers();
				// System.out.println("do update count down s = : " + s);
			}
		}

		public int getSec() {
			// TODO Auto-generated method stub
			return s;
		}
	}

	public ClockThread() {
		observers = new ArrayList<>();
		// turn = Flag.BLACK;
		turn = (SettingModel.p1First) ? Flag.WHITE : Flag.BLACK;
		System.out.println("thread think : " + turn);
		refresh();
	}

	public int getSec() {
		return timeRunner.s;
	}

	public Flag getTurn() {
		return turn;
	}

	public void setTurn(Flag turn) {
		this.turn = turn;
	}

	void refresh() {
		if (timeRunner != null)
			timeRunner.alive = false;
		timeRunner = new TimeRunner();
		new Thread(timeRunner).start();
	}

	@Override
	public void update(BoardController e) {
		if (e.isStopGame()) {
			new Thread(timeRunner).stop();
			return;
		}
		if (turn != e.getTurn()) {
			turn = e.getTurn();
			// System.out.println("next turn(clock think) " + turn);
			refresh();
		}

	}

	@Override
	public void registerObserver(Observer<ClockThread> o) {
		observers.add(o);

	}

	@Override
	public void removeObserver(Observer<ClockThread> o) {
		// TODO Auto-generated method stub
		observers.remove(o);
	}

	@Override
	public void updateObservers() {
		// TODO Auto-generated method stub
		observers.forEach(o -> o.update(this));
	}

}
