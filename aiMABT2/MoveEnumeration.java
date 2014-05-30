package aiMABT2;

import java.util.LinkedList;

public class MoveEnumeration {
	int[][] stateDu = new int [11][11];
	LinkedList<Move> move = new LinkedList<Move>();//debug
	
	public MoveEnumeration(int[][] a) {
		// TODO Auto-generated constructor stub
		stateDu = a;
		for (int i = 0; i < 11; i+=2) {
			for (int j = 1; j < 11; j+=2) {
				if (stateDu[i][j] == 0) {
					move.add(new Move(i, j));
				}
			}
		}
		for (int i = 1; i < 11; i+=2) {
			for (int j = 0; j < 11; j+=2) {
				if (stateDu[i][j] == 0) {
					move.add(new Move(i, j));
				}
			}
		}
	}
	public boolean moreMoves() {
		
		if (move.isEmpty()) {
			return false;
		}
		return true;
	}

	public Move nextMove() {//debug
		return move.poll();
	}

}
