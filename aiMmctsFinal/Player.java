package aiMmctsFinal;

public class Player {
	int score;
	boolean isTurn;
	
	public Player(boolean is) {
		score = 0;
		isTurn = is;
	}
	public Player(boolean is, int s) {
		score = s;
		isTurn = is;
	}

	public void setTurn(boolean b) {
		isTurn = b;
		
	}
}
