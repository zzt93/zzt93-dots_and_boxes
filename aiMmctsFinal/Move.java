package aiMmctsFinal;

public class Move {
	int locX;
	int locY;
	int priority;
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public Move(int x, int y) {
		// TODO Auto-generated constructor stub
		locX = x;
		locY = y;
	}
	public boolean equals(Move m) {
		if (m.locX == this.locX && m.locY == this.locY) {
			return true;
		}
		return false;
	}
}
