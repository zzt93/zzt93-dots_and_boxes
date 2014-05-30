package aiMABFullMOrder4;

public class Player {
	int score;
	boolean isTurn;
	
	public Player(boolean is) {
		score = 0;
		isTurn = is;
	}

	public void setTurn(boolean b) {
		isTurn = b;
		
	}
}
