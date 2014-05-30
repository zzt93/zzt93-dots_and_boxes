package aiMABFullMOrder4;

public class Move {
	int locX;
	int locY;
	private double priority;
	
	public Move(int x, int y) {
		// TODO Auto-generated constructor stub
		locX = x;
		locY = y;
	}

	public int getLocX() {
		return locX;
	}

	public void setLocX(int locX) {
		this.locX = locX;
	}

	public int getLocY() {
		return locY;
	}

	public void setLocY(int locY) {
		this.locY = locY;
	}

	public double getPriority() {
		return priority;
	}

	public void setPriority(double priority) {
		this.priority = priority;
	}
}
