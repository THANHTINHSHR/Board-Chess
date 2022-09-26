package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

import model.ClockThread;
import observer.Observer;

public class ClockCountDownLabel extends JLabel implements Observer<ClockThread> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int sec = 0;

	public ClockCountDownLabel() {
		setFont(new Font("", Font.BOLD, 30));
		setText(sec + "");
	}

	@Override
	public void update(ClockThread e) {
		// TODO Auto-generated method stub
		if(e.getSec() >= 0 ) {
		setForeground((e.getSec() <= 5) ? Color.RED : Color.BLACK);
		setText(Integer.toString(e.getSec()));
		}
	}

}
