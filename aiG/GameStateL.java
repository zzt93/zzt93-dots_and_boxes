package aiG;


import java.util.Collections;

import java.util.HashSet;

import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;






public class GameStateL {
	
	public GameStateL() {
		left = 60;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5; j++) {
				leftPosition.add(new MyPosition(false, j, i));//horizontal
			}											// it is mismatched at the beginning
			
		}
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5; j++) {
				leftPosition.add(new MyPosition(true, i, j));//vertical
			}
			
		}
	}
	
	int left ;
	Set<MyPosition> linePosition = Collections.synchronizedSet(new HashSet<MyPosition>());
	PriorityBlockingQueue<MyPosition> leftPosition = new PriorityBlockingQueue<MyPosition>();
	
	public void ChangeLeft () {
		left --;
	}
	public boolean end() {
		if (left == 0) {
			return true;
		}else {
			return false;
		}
	}
	public void setPosition(MyPosition mp) {
//		linePosition.add(mp);
//	System.out.println(leftPosition.contains(mp));
//	leftPosition.remove(mp);   it is useless for it isn't in it
		for (MyPosition m : leftPosition) {
			if ((m.v == mp.v) && (m.x == mp.x) && (m.y == mp.y)) {
				linePosition.add(m);
				leftPosition.remove(m);// the change of priorityblockingqueue may wrong
			}
		}
		
		
		
	}
	
	public MyPosition makePriority () {//let to be made random
		for (MyPosition m : leftPosition) {
			new Surrounder(m).look();
		}
		PriorityBlockingQueue<MyPosition> leftPositionDu = new PriorityBlockingQueue<MyPosition>();
		leftPosition.drainTo(leftPositionDu);
		leftPositionDu.drainTo(leftPosition);
	/*	for (MyPosition m : leftPosition) {
			new Surrounder(m).look();	//make it here is useless
		}*/
		linePosition.add(leftPosition.peek());
		return leftPosition.remove();
	}
	
	public class Surrounder {
		static final int l1 = 3;
		static final int l2 = 2;
		static final int l3 = 1;
		MyPosition test ;
		public Surrounder (MyPosition mp){
			test = mp;
		}
		public void look () {
			
			if (test.v) {
				switch (test.x) {
				case 0:test.setPriority(checkV(true, test.x + 1, test.y));
					break;
				case 5:test.setPriority(checkV(false, test.x - 1, test.y));
					break;
				case 1:
				case 2:
				case 3:
				case 4:
					int i = checkV(true, test.x + 1, test.y );
					int j = checkV(false, test.x - 1, test.y );
					if (i == 4 && j == 4){
						test.setPriority(5);
						break;
					}else if ((i == 4) || (j == 4)) {
						test.setPriority(4);
					}
					else {
						test.setPriority(Math.min(i, j));	
					}
								
					break;
				default:
					System.out.println("location error in vertical");
					break;
				}
			}
			else {
				switch (test.y) {
				case 0:test.setPriority(checkH(false, test.x, test.y + 1));
					break;
				case 5:test.setPriority(checkH(true, test.x, test.y - 1));					
					break;
				case 1:
				case 2:
				case 3:
				case 4:
					int i = checkH(false, test.x, test.y + 1);
					int j = checkH(true, test.x, test.y - 1);
					if (i == 4 && j == 4){
						test.setPriority(5);
						break;
					}else if (i == 4 || j == 4) {
						test.setPriority(4);
					}
					else {
						test.setPriority(Math.min(i, j));		
					}
								
					break;
				default:
					System.out.println("location error in horizonal");
					break;
				}
				
			}
		}
		public int checkV(boolean right, int xx, int yy) {
			MyPosition op = new MyPosition(true, xx, yy);
			MyPosition up;
			MyPosition down; 
			if (right) {
				up = new MyPosition(false, xx - 1, yy);
				down = new MyPosition(false, xx - 1, yy + 1);
				
			}else {
				up = new MyPosition(false, xx, yy);
				down = new MyPosition(false, xx, yy + 1);
			}
			int num = 3;
			for (MyPosition m : linePosition) {
				if ((op.x == m.x) && (op.y == m.y) && (op.v == m.v)) {
					num --;
				}
			}
			
			for (MyPosition m : linePosition) {
				if ((up.x == m.x) && (up.y == m.y) && (up.v == m.v)) {
					num --;
				}
			}			
			for (MyPosition m : linePosition) {//no way to use contain
				if ((down.x == m.x) && (down.y == m.y) && (down.v == m.v)) {
					num --;
				}
			}
			if (num == 0) {
				num = 4;
			}
			return num;
		}
		public int checkH(boolean up, int xx, int yy) {
			MyPosition op = new MyPosition(false, xx, yy);
			MyPosition left;
			MyPosition right; 
			if (up) {
				left = new MyPosition(true, xx , yy);
				right = new MyPosition(true, xx + 1, yy );
				
			}else {
				left = new MyPosition(true, xx, yy - 1);
				right = new MyPosition(true, xx + 1, yy - 1);
			}
			int num = 3;
			for (MyPosition m : linePosition) {
				if ((op.x == m.x) && (op.y == m.y) && (op.v == m.v)) {
					num --;
				}
			}
			
			for (MyPosition m : linePosition) {
				if ((left.x == m.x) && (left.y == m.y) && (left.v == m.v)) {
					num --;
				}
			}			
			for (MyPosition m : linePosition) {//no way to use contain
				if ((right.x == m.x) && (right.y == m.y) && (right.v == m.v)) {
					num --;
				}
			}
			if (num == 0) {
				num = 4;
			}
			return num;
		}
	}
	

	public class MyPosition implements  Comparable<MyPosition>{
		public MyPosition(boolean vv,int xx, int yy) {
			x = xx;
			y = yy;
			v = vv;
		}
		int x ;
		int y ;
		boolean v ;
		int priority ;
		
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
		public int getPriority() { 
			 return priority; 
		} 
			 
		public void setPriority(int priority) { 
			 this.priority = priority; 
		}

		public MyPosition getMyPosition() {
			return new MyPosition(false, 0, 0 );
		}
		
		
		/*@Override
		public int compare(MyPosition o1, MyPosition o2) {
			boolean prior = ((o1.getPriority()) > (o2.getPriority()));
			if (prior){
				return 1;
			}
			return 0;
		}*/
		
		@Override
		public int compareTo(MyPosition o) {
			if (new Integer(o.getPriority())> new Integer(getPriority())) {
				return 1;
			}else if(new Integer(o.getPriority())< new Integer(getPriority())){
				return -1;
			}else {
				return 0;
			}
			
		}

	}

}
