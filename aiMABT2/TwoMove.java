package aiMABT2;

public class TwoMove extends Move{
	int[] one = new int[2];
	int[] another = new int[2];
	
	public TwoMove(Move m1, Move m2) {
		super(0, 0);
		one[0] = m1.locX;
		one[1] = m1.locY;
		another[0] = m2.locX;
		another[1] = m2.locY;
	}
	public boolean equal(Move t1, Move t2) {
		if (t1.locX == one[0] && t1.locY == one[1] && t2.locX == another[0] && t2.locY == another[1]) {
			return true;
		}
		if (t2.locX == one[0] && t2.locY == one[1] && t1.locX == another[0] && t1.locY == another[1]) {
			return true;
		}
		return false;
	}
}
