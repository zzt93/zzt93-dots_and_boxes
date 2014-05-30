package aiG;

import java.util.Comparator;


public class Position implements  Comparator<Position>{
	public Position (){
		v = false;
		x = 0;
		y = 0;
	}
	public Position(boolean vv,int xx, int yy) {
		v = vv;
		x = xx;
		y = yy;
		
	}
	int x = 0;
	int y = 0;
	boolean v = false;
	
	public int getX() {
		return x ;
	}
	public void setX(int xx) {
		x = xx;
	}
	public int getY() {
		return y;
	}
	public void setY(int yy) {
		y = yy;
	}
	public boolean getV() {
		return v ;
	}
	public void setV(boolean vv) {
		v = vv;
	}
	public Position getMyPosition() {
		return new Position(false,0, 0  );
	}
	
	
	public int compare(Position o1, Position o2) {
		//boolean prior = ((new Surrounder(o1).look()) > (new Surrounder(o2).look()));
		/*if (prior){
			return 
		}*/
		return 0;
	}

}
