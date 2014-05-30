package aiMABTFinal;

public class TwoMove {
	int[] one = new int[2];
	int[] another = new int[2];
	
	public TwoMove(Move m1, Move m2) {
		one[0] = m1.locX;
		one[1] = m1.locY;
		another[0] = m2.locX;
		another[1] = m2.locY;
	}
	
	public boolean equals(Object o) {
		TwoMove twoMove = (TwoMove)o;
		if (twoMove.one[0] == one[0] && twoMove.one[1] == one[1] && twoMove.another[0] == another[0] && twoMove.another[1] == another[1]) {
			return true;
		}
		if (twoMove.another[0] == one[0] && twoMove.another[1] == one[1] && twoMove.one[0] == another[0] && twoMove.one[1] == another[1]) {
			return true;
		}
		return false;
	}
	@Override
	public int hashCode() {
		return one[0]*11 + one[1] + another[0]*11 + another[1] + one[1]*11 + one[0] + another[1]*11 + another[0];
	}
}
