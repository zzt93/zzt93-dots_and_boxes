package aiMNim;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;



public class Nim {
	HashMap<Board, Integer> value = new HashMap<Board, Integer>();
	Board nBoard ;
	
	public Nim(int[][] state) {
		nBoard = new Board(state);
	}
	
	public void cut() {
		
	}
	
	public int mex(ArrayList<Integer> follower) {//the follower should be ordered in ascendent
		Collections.sort(follower);
		int i;
		for (i = 0; i < follower.size(); i++) {
			if (i != follower.get(i)) {
				return i;
			}
		}
		return follower.get(i) + 1;
	}
	/**
	 * this method run with cutting the board  
	 */
	public void calNim(int x, int y) {
		int moves = new MoveEnumeration(nBoard.board).move.size();
		int count = 1;
		while (moves != count) {
			
		}
	}
	
	
}
