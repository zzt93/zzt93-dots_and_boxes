package aiMABTN;

public class Board {
	int[][] board = new int[11][11];
	public Board(int[][] a) {
		// TODO Auto-generated constructor stub
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length; j++) {
				board[i][j] = a[i][j];
			}
		}
	}
	public boolean equals(Object o) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (((Board)o).board[i][j] != board[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	public int hashCode() {
		int code = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if ((i+j)%2 == 1 && board[i][j] == -1) {
					code += (i*11 + j + j*11 + i);
				}
			}
		}
		return code;
	}
	
	
}
