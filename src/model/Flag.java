package model;

public enum Flag {

	BLACK, WHITE;
	public Flag getEnemy() {
		if (this == WHITE)
			return BLACK;
		return WHITE;
	}
	
}
