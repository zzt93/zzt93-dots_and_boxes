package aiMABTN;

import org.junit.Test;


public class TimeOutTest {
	Player ai = new Player(true);
	Player man = new Player(false);
	int[][] matrix = {//it is not coordinate with the axis of swing(rotate)
			{-1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1},
			{ 0, 1,  0, 1, 0 , 1, 0 , 1,  0 , 1, 0},
			{-1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1},
			{ 0, 1,  0, 1, 0 , 1, 0 , 1,  0 , 1, 0},
			{-1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1},
			{ 0, 1,  0, 1, 0 , 1, 0 , 1,  0 , 1, 0},
			{-1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1},
			{ 0, 1,  0, 1, 0 , 1, 0 , 1,  0 , 1, 0},
			{-1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1},
			{ 0, 1,  0, 1, 0 , 1, 0 , 1,  0 , 1, 0},
			{-1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1},};
	
	MinimaxABTD7 minimax = new MinimaxABTD7();
	Node node = new Node(ai, man, matrix);
	
	
	@Test(timeout = 5000)
	public void timeOut(){
		minimax.score(node, true, 7);
		
	}
	
	@Test(timeout = 100)
	public void noTimeOut(){
		//minimax.noTimeOut();
	}	
}
