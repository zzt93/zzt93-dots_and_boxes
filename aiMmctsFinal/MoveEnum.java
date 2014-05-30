package aiMmctsFinal;

import java.util.LinkedList;



public class MoveEnum {
	int[][] stateDu = new int [11][11];
	LinkedList<Move> move = new LinkedList<Move>();
	
	public MoveEnum(int[][] a) {
		// TODO Auto-generated constructor stub
		boolean exit = false;
		stateDu = a;
		for (int i = 0; i < 11 && !exit; i++) {
			for (int j = ((i%2)==0?1:0) ; j < 11 && !exit; j+=2) {
				//add the fourth side
				if ( i == 0 && stateDu[i+1][j] == 4 && stateDu[i][j] == 0 ) {

					move.add(new Move(i, j));
					exit = true;
				}else if (i == 10 && stateDu[i-1][j] == 4 && stateDu[i][j] == 0) {

					move.add(new Move(i, j));
					exit = true;
				}else if ((i%2) == 0 && i >= 2 && i <= 8 && (stateDu[i-1][j] == 4 || stateDu[i+1][j] == 4) && stateDu[i][j] == 0) {

					move.add(new Move(i, j));
					exit = true;
				}else if ( j == 0 && stateDu[i][j+1] == 4 && stateDu[i][j] == 0 ) {

					move.add(new Move(i, j));
					exit = true;
				}else if (j == 10 && stateDu[i][j-1] == 4 && stateDu[i][j] == 0) {

					move.add(new Move(i, j));
					exit = true;
				}else if ((j%2) == 0 && j >= 2 && j <= 8  && (stateDu[i][j-1] == 4 || stateDu[i][j+1] == 4) && stateDu[i][j] == 0) {

					move.add(new Move(i, j));
					exit = true;
				}
				
			}
		}
		for (int i = 0; i < 11 && !exit; i++) {
			for (int j = ((i%2)==0?1:0); j < 11 && ! exit; j+=2) {
				//add the second and 
				if ( i == 0 && stateDu[i+1][j] <= 2 && stateDu[i][j] == 0 ) {

					move.add(new Move(i, j));
					exit = true;
				}else if (i == 10 && stateDu[i-1][j] <= 2 && stateDu[i][j] == 0) {

					move.add(new Move(i, j));
					exit = true;
				}else if ((i%2) == 0 && i >= 2 && i <= 8 && stateDu[i-1][j] <= 2 && stateDu[i+1][j] <= 2 && stateDu[i][j] == 0) {

					move.add(new Move(i, j));
					exit = true;
				}else if ( j == 0 && stateDu[i][j+1] <= 2 && stateDu[i][j] == 0 ) {

					move.add(new Move(i, j));
					exit = true;
				}else if (j == 10 && stateDu[i][j-1] <= 2 && stateDu[i][j] == 0) {

					move.add(new Move(i, j));
					exit = true;
				}else if ((j%2) == 0 && j >= 2 && j <= 8 && stateDu[i][j-1] <= 2 && stateDu[i][j+1] <= 2 && stateDu[i][j] == 0) {

					move.add(new Move(i, j));
					exit = true;
				}
			}
		}
		
		for (int i = 0; i < 11 && !exit; i++) {
			for (int j = ((i%2==0)?1:0); j < 11 && !exit; j+=2) {
				if (stateDu[i][j] == 0) {
					move.add(new Move(i, j));
					exit = true;
				}
			}
		}
		
	}
	public boolean moreMoves() {
		//move.trimToSize();
		if (move.isEmpty()) {
			return false;
		}
		return true;
	}

	public Move nextMove() {//debug
		//move.trimToSize();
		return move.poll();
	}

}
