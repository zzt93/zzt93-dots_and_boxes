package aiMABTN;
/**
 * a new board to save memory
 * @author zzt
 *
 */
public class Board2 {
	static final byte maxDepth = 12;
	byte[][] moveSet = new byte[maxDepth][2];//correspond to the max depth in minimax
	byte depth;
	
	public Board2(byte x, byte y, byte currentDepth) {
		depth = currentDepth;
		moveSet[maxDepth-depth-1][0] = x;
		moveSet[maxDepth-depth-1][1] = y;
		
	}
	public Board2(byte x, byte y, Board2 board){
		depth = (byte) (board.depth - 1);
		for (byte i = 0; i < maxDepth - board.depth; i++) {
			for (byte j = 0; j < 2; j++) {
				moveSet[i][j] = board.moveSet[i][j];
			}
		}
		moveSet[maxDepth-depth-1][0] = x;
		moveSet[maxDepth-depth-1][1] = y;
	}
	
	public Board2 NewBoard(byte x, byte y, Board2 board) {
		return new Board2(x, y, board);
	}
	
	public boolean equals(Object o) {
		for (byte i = 0; i < maxDepth - depth; i++) {
			for (byte j = 0; j < 2; j++) {
				if (((Board2)o).moveSet[i][j] != moveSet[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	public int hashCode() {
		int code = 0;
		for (byte i = 0; i < maxDepth - depth; i++) {
			for (byte j = 0; j < 2; j++) {
				code += (i*11 + j + j*11 + i);
			}
		}
		return code;
	}
	
	
}
