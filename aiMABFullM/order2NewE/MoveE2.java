package aiMABFullM.order2NewE;

import java.util.LinkedList;


public class MoveE2 {
	LinkedList<Move> move = new LinkedList<Move>();
	
	public MoveE2(int[][] a) {
		// TODO Auto-generated constructor stub
		
		for (int i = 0; i < 11 ; i++) {
			for (int j = ((i%2)==0?1:0) ; j < 11 ; j+=2) {
				//add the fourth side
				if ( i == 0 && a[i+1][j] == 4 && a[i][j] == 0 ) {
					
					move.add(new Move(i, j));
					a[i][j] = -2;
				}else if (i == 10 && a[i-1][j] == 4 && a[i][j] == 0) {

					move.add(new Move(i, j));
					a[i][j] = -2;
				}else if ((i%2) == 0 && i >= 2 && i <= 8 && (a[i-1][j] == 4 || a[i+1][j] == 4) && a[i][j] == 0) {

					move.add(new Move(i, j));
					a[i][j] = -2;
				}else if ( j == 0 && a[i][j+1] == 4 && a[i][j] == 0 ) {

					move.add(new Move(i, j));
					a[i][j] = -2;
				}else if (j == 10 && a[i][j-1] == 4 && a[i][j] == 0) {

					move.add(new Move(i, j));
					a[i][j] = -2;
				}else if ((j%2) == 0 && j >= 2 && j <= 8  && (a[i][j-1] == 4 || a[i][j+1] == 4) && a[i][j] == 0) {

					move.add(new Move(i, j));
					a[i][j] = -2;
				}
				
			}
		}
		
		for (int i = 0; i < 11 ; i++) {
			for (int j = ((i%2==0)?1:0); j < 11; j+=2) {
				if (a[i][j] == 0) {
					move.add(new Move(i, j));
					a[i][j] = -2;
				}
				if (a[i][j] == -2) {
					a[i][j] = 0;
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

	public Move nextMoveAi() {//debug
		//move.trimToSize();
		return move.poll();
	}
	/**
	 * this move is useless for the man should also make good move to lower the 
	 * score of ai
	 */
	/*
	public Move nextMoveMan() {//debug
		//move.trimToSize();
		return move.pollLast();
	}*/
}