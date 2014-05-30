package aiMABFullMOrder2ttNewE;

import java.util.LinkedList;


public class PartMove {
	LinkedList<Move> move = new LinkedList<Move>();
	LinkedList<Move> moveBad = new LinkedList<Move>();
	public PartMove(int[][] a) {
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
			for (int j = ((i%2)==0?1:0); j < 11 ; j+=2) {
				//add the second and 
				if ( i == 0 && a[i+1][j] <= 2 && a[i][j] == 0 ) {

					move.add(new Move(i, j));
					a[i][j] = -2;
				}else if (i == 10 && a[i-1][j] <= 2 && a[i][j] == 0) {

					move.add(new Move(i, j));
					a[i][j] = -2;
				}else if ((i%2) == 0 && i >= 2 && i <= 8 && a[i-1][j] <= 2 && a[i+1][j] <= 2 && a[i][j] == 0) {

					move.add(new Move(i, j));
					a[i][j] = -2;
				}else if ( j == 0 && a[i][j+1] <= 2 && a[i][j] == 0 ) {

					move.add(new Move(i, j));
					a[i][j] = -2;
				}else if (j == 10 && a[i][j-1] <= 2 && a[i][j] == 0) {

					move.add(new Move(i, j));
					a[i][j] = -2;
				}else if ((j%2) == 0 && j >= 2 && j <= 8 && a[i][j-1] <= 2 && a[i][j+1] <= 2 && a[i][j] == 0) {

					move.add(new Move(i, j));
					a[i][j] = -2;
				}
			}
		}
		
		for (int i = 0; i < 11 ; i++) {
			for (int j = ((i%2==0)?1:0); j < 11; j+=2) {
				if (a[i][j] == 0) {
					moveBad.add(new Move(i, j));
					a[i][j] = -2;
				}
				if (a[i][j] == -2) {
					a[i][j] = 0;
				}
				
			}
		}
		
	}
	/**
	 * check if there exist another good move
	 * @return
	 */
	public boolean moreGoodM() {
		if (move.isEmpty()) {
			return false;
		}
		return true;
	}
	/**
	 * check if there exist another bad move
	 * @return
	 */
	public boolean moreBadM() {
		if (moveBad.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * nextMoveAi1 return 'good' move in order to prune
	 * @return 'good' Move
	 */
	public Move nextGoodM() {
		return move.poll();
	}
	/**
	 * nextMoveAi2 return 'bad' move and not always be invoked
	 * @return 'bad' Move
	 */	
	public Move nextBadM() {
		return moveBad.poll();
	}
}