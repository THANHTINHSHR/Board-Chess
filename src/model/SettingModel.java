package model;

import java.util.ArrayList;
import java.util.List;

import observer.Observable;
import observer.Observer;

public class SettingModel implements Observable<SettingModel> {
	private boolean p1GoFirst;
	private String p1Name, p2Name;
	private int p1Color, p2Color;
	private List<String> sourceChess;
	private List<Observer<SettingModel>> observers;
	// static
	public static String p1Co, p2Co, p1N, p2N;
	public static boolean p1First;
	private static SettingModel uniqueInstance;

	private SettingModel() {
		// TODO Auto-generated constructor stub
		observers = new ArrayList<>();

		createSource();
		setDefault();
		// p1Co = getP1Co();
		// p2Co = getP2Co();
	}

	public static SettingModel getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new SettingModel();
		}
		return uniqueInstance;
	}

	// public SettingModel(boolean p1GoFirst, String p1Name, String p2Name, int
	// p1Color, int p2Color) {
	// super();
	// this.p1GoFirst = p1GoFirst;
	// this.p1Name = p1Name;
	// this.p2Name = p2Name;
	// this.p1Color = p1Color;
	// this.p2Color = p2Color;
	// // setDefault();
	// observers = new ArrayList<>();
	// createSource();
	// }

	public void setDefault() {
		// TODO Auto-generated method stub
		p1GoFirst = false;
		p1Name = "P1";
		p2Name = "P2";
		p1Color = 1;
		p2Color = 0;
		//
		p1Co = getP1Co();
		p2Co = getP2Co();
		p1N = p1Name;
		p2N = p2Name;
		p1First = p1GoFirst;
		System.out.println("setDefault button click! getp1Color :" + p1Color);

	}

	public boolean isP1GoFirst() {
		return p1GoFirst;
	}

	public void setP1GoFirst(boolean p1GoFirst) {
		this.p1GoFirst = p1GoFirst;
		p1First = p1GoFirst;

	}

	public String getP1Name() {
		return p1Name;
	}

	public void setP1Name(String p1Name) {
		this.p1Name = p1Name;
		p1N = p1Name;
	}

	public String getP2Name() {
		return p2Name;
	}

	public void setP2Name(String p2Name) {
		this.p2Name = p2Name;
		p2N = p2Name;
	}

	public int getP1Color() {

		return p1Color;
	}

	public void setP1Color(int p1Color) {
		// System.out.println("save button click! getp1Color :" + p1Color);
		this.p1Color = p1Color;
		p1Co = getP1Co();
	}

	public int getP2Color() {
		return p2Color;
	}

	public void setP2Color(int p2Color) {
		this.p2Color = p2Color;
		p2Co = getP2Co();
	}

	private String getP1Co() {
		return sourceChess.get(p1Color);
	}

	private String getP2Co() {
		return sourceChess.get(p2Color);
	}

	private void createSource() {
		// TODO Auto-generated method stub
		sourceChess = new ArrayList<>();
		sourceChess.add("resource//A.png");
		sourceChess.add("resource//B.png");
		sourceChess.add("resource//C.png");
		sourceChess.add("resource//D.png");

	}

	@Override
	public void registerObserver(Observer<SettingModel> o) {
		// TODO Auto-generated method stub
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer<SettingModel> o) {
		// TODO Auto-generated method stub
		observers.remove(o);
	}

	@Override
	public void updateObservers() {
		// TODO Auto-generated method stub
		observers.forEach(o -> o.update(this));
	}
}
