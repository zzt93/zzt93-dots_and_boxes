package aiMmctsFinal;

import java.util.ArrayList;

public class MctsNode {
	double nVisits, totValue;
	ArrayList<MctsNode> children;

	int[][] state = new int[11][11];//out of memory
	
	Player ai ;
	Player man ;
	
	public MctsNode(int[][] matrix, Player aip, Player manp) {		

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				state[i][j] = matrix[i][j];
			}
		}
		ai = aip;
		man = manp;
	}
	public MctsNode(Player aip, Player manp,int[][] matrix) {		

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				state[i][j] = matrix[i][j];
			}
		}
		ai = new Player(aip.isTurn, aip.score);
		man = new Player(manp.isTurn, manp.score);
	}
   
    public boolean isLeaf() {
        return children == null;
    }
    
    public void applyMove(Move m, Player player) {
		if (valid1(m)) {
			state[m.locX][m.locY] = -1;
			//judge the score
			box(m);
		}else {
			System.out.println("invalid move in applymove");
			System.exit(0);
		}
	}
    public boolean valid1(Move m) {
		if (state[m.locX][m.locY] == 0 ) {//debug
			return true;
		}else {
			return false;
		}
	}
    public void box(Move m) {
		int x = m.locX;	//(exchange the coordinates of x and y in order to suit the swing) 
		int y = m.locY;
		int count = 0;
		if ((x % 2) == 0 ) {
			switch (x) {
			case 0:
				count = ++ state[x + 1][y] ;// @warning
				enclose(count);
				break;
			case 10:
				count = ++ state[x - 1][y] ;
				enclose(count);
				break;
			case 2:
			case 4:
			case 6:
			case 8:
				int counti = ++ state[x + 1][y] ;
				int countj = ++ state[x - 1][y] ;
				enclose(counti, countj);
				break;
			default:
				System.out.println("invalid x in box");
				break;
			}
			
		}else {
			switch (y) {//debug
			case 0:
				count = ++ state[x][y + 1] ;
				enclose(count);
				break;
			case 10:
				count = ++ state[x][y - 1] ;
				enclose(count);
				break;
			case 2:
			case 4:
			case 6:
			case 8:
				int counti = ++ state[x][y + 1] ;//debug
				int countj = ++ state[x][y - 1] ;//debug
				enclose(counti, countj);
				break;
			default:
				System.out.println("invalid y in box");
				break;
			}
			
		}
		
	
	}
    public void enclose(int i) {
		if ((i == 5)&&(ai.isTurn) ) {
			ai.score ++;
		}else if ((i == 5)&& (man.isTurn)){
			man.score ++;	
		}else {
			if (ai.isTurn) {
				ai.setTurn(false);
				man.setTurn(true);
			}else {
				man.setTurn(false);
				ai.setTurn(true);
			}
		}
	}	
	public void enclose(int i, int j) {//debug
		if ((i == 5) || (j == 5)) {
			if ((i == 5) && (ai.isTurn)) {
				ai.score ++;
			}if ((j == 5) && (ai.isTurn)) {
				ai.score ++;
			}if ((i == 5) && (man.isTurn)) {
				man.score ++;
			}if ((j == 5) && (man.isTurn)) {
				man.score ++;
			}
		}else {
			if (ai.isTurn) {
				ai.setTurn(false);
				man.setTurn(true);
			}else {
				man.setTurn(false);
				ai.setTurn(true);
			}
		}
	}
    
    public boolean gameOver() {
		if ((ai.score + man.score) == 25) {
			return true;
		}
		return false;
	}
    
    public boolean valid2(Move m) {
		if (state[m.locX][m.locY] == -1 && ((m.locX + m.locY)%2) == 1 ) {//debug
			return true;
		}else {
			return false;
		}
	}
    public void retractMove(Move m) {
		if (valid2(m)) {
			state[m.locX][m.locY] = 0;
			//subtract some scores
			unbox(m);
		}else {
			System.out.println("invalid move in retract");
			System.exit(0);
		}
    }
	public void unbox(Move m) {
		int x = m.locX;	//(exchange the coordinates of x and y in order to suit the swing) 
		int y = m.locY;
		int count = 0;
		if ((x % 2) == 0 ) {
			switch (x) {
			case 0:
				count = -- state[x + 1][y] ;// @warning
				unfold(count);
				break;
			case 10:
				count = -- state[x - 1][y] ;
				unfold(count);
				break;
			case 2:
			case 4:
			case 6:
			case 8:
				int counti = -- state[x + 1][y] ;
				int countj = -- state[x - 1][y] ;
				unfold(counti, countj);
				break;
			default:
				System.out.println("invalid x in box");
				break;
			}
			
		}else {
			switch (y) {
			case 0:
				count = -- state[x][y + 1] ;
				unfold(count);
				break;
			case 10:
				count = -- state[x][y - 1] ;
				unfold(count);
				break;
			case 2:
			case 4:
			case 6:
			case 8:
				int counti = -- state[x ][y + 1] ;
				int countj = -- state[x ][y - 1] ;
				unfold(counti, countj);
				break;
			default:
				System.out.println("invalid x in box");
				break;
			}
			
		}
	}	
	public void unfold(int i) {
		if ((i == 4)&&(ai.isTurn) ) {
			ai.score --;
		}else if ((i == 4)&& (man.isTurn)){
			man.score --;	
		}else {
			if (ai.isTurn) {
				ai.setTurn(false);
				man.setTurn(true);
			}else {
				man.setTurn(false);
				ai.setTurn(true);
			}
		}
	}	
	public void unfold(int i, int j) {
		if ((i == 4) || (j == 4)) {
			if ((i == 4) && (ai.isTurn)) {
				ai.score --;
			}if ((j == 4) && (ai.isTurn)) {
				ai.score --;
			}if ((i == 4) && (man.isTurn)) {
				man.score --;
			}if ((j == 4) && (man.isTurn)) {
				man.score --;
			}
		}else {
			if (ai.isTurn) {
				ai.setTurn(false);
				man.setTurn(true);
			}else {
				man.setTurn(false);
				ai.setTurn(true);
			}
		}
	}
}
