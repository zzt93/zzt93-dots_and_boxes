package aiModel;

public class Main {
	Player aiPlayer = new Player(true, "ai");
	Player manPlayer = new Player(false, "man");
	GameStateM game = new GameStateM(aiPlayer, manPlayer);
	public void move() {
		if (aiPlayer.isTurn) {
			
		}else {
			
		}
	}
	public void setFirstHand() {//make change depend on checkbox in setting
		aiPlayer.isTurn = false;
		manPlayer.isTurn = true;
	}
	public static void main(String[] args) {
		Main main = new Main();
		while (!main.isend()) {
			
		}
	}
	public boolean isend () {
		if (game.end(manPlayer, aiPlayer)) {
			return true;
		}else {
			return false;
		}
	}
}
