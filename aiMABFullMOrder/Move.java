package aiMABFullMOrder;

public class Move {
	int locX;
	int locY;
	double priority;
	
	public Move(int x, int y) {
		// TODO Auto-generated constructor stub
		locX = x;
		locY = y;
	}
	public void setPriority(double p) {
		priority = p;
	}
}
