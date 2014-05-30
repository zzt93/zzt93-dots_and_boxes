package aiG;

public class Player {
	public Player(boolean is) {
		isTurn = is;
	}

	int score = 0;
	boolean isTurn;
	public void setTurn(boolean is) {
		isTurn = is;
	}
}
