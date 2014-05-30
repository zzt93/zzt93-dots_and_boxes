package aiMABT2;

public class TriMove {
	int[] one = new int[2];
	int[] second = new int[2];
	int[] third = new int[2];
	
	
	public TriMove(Move m1, Move m2) {
		if (m1 instanceof TwoMove) {
			TwoMove two = (TwoMove) m1;
			third[0] = two.one[0];
			third[1] = two.one[1];
			one[0] = two.another[0];
			one[1] = two.another[1];	
		}else {
			System.err.println("wrong in initalize trimove");
		}
		second[0] = m2.locX;
		second[1] = m2.locY;
		
	}
	
	
	public boolean equal(TwoMove two, Move t3) {
		Move t1 = new Move(two.one[0], two.one[1]);
		Move t2 = new Move(two.another[0], two.another[1]);
		if (t1.locX == one[0] && t1.locY == one[1] && t2.locX == second[0] && t2.locY == second[1] && t3.locX == third[0] && t3.locY == third[1]) {
			return true;
		}
		if (t1.locX == one[0] && t1.locY == one[1] && t3.locX == second[0] && t3.locY == second[1] && t2.locX == third[0] && t2.locY == third[1]) {
			return true;
		}
		if (t2.locX == one[0] && t2.locY == one[1] && t1.locX == second[0] && t1.locY == second[1] && t3.locX == third[0] && t3.locY == third[1]) {
			return true;
		}
		if (t2.locX == one[0] && t2.locY == one[1] && t3.locX == second[0] && t3.locY == second[1] && t1.locX == third[0] && t1.locY == third[1]) {
			return true;
		}
		if (t3.locX == one[0] && t3.locY == one[1] && t1.locX == second[0] && t1.locY == second[1] && t2.locX == third[0] && t2.locY == third[1]) {
			return true;
		}
		if (t3.locX == one[0] && t3.locY == one[1] && t2.locX == second[0] && t2.locY == second[1] && t1.locX == third[0] && t1.locY == third[1]) {
			return true;
		}
		return false;
	}
}
