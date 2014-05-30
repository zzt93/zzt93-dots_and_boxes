package aiModel;

public class Player {
	int score ;
	String name;
	boolean isTurn;
	public  Player(boolean is, String s) {
		score = 0;
		isTurn = is;
		name = s;
	}
	public void setTurn(boolean is) {
		isTurn = is;
	}
	
}
