package aiM;

import java.util.Random;



public class GameStateM2 {
	
	public  GameStateM2(Player a, Player m) {
		ai = a;
		man = m;
	}
	
	private static final int stage0 = 0;
	private static final int stage1 = 1;
	private static final int stage2 = 3;
	Player ai ;
	Player man;
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
			{-1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1},};//-1 represent dots, 0 represent edge, 1 represent boxes
	
	public void setline(Move m) {
		if (valid(m)) {
			matrix[m.locX][m.locY] = -1;
			box(m);
		}else {
			System.out.println("Invalid move");
			System.exit(1);
		}
	}
	
	public boolean valid(Move m) {
		if (matrix[m.locX][m.locY] == 0) {//debug
			return true;
		}
		return false;
	}
	
	public void box(Move m) {
		int x = m.locX;	//(exchange the coordinates of x and y in order to suit the swing) 
		int y = m.locY;
		int count = 0;
		if ((x % 2) == 0 ) {
			switch (x) {
			case 0:
				count = ++ matrix[x + 1][y] ;// @warning
				enclose(count);
				break;
			case 10:
				count = ++ matrix[x - 1][y] ;
				enclose(count);
				break;
			case 2:
			case 4:
			case 6:
			case 8:
				int counti = ++ matrix[x + 1][y] ;
				int countj = ++ matrix[x - 1][y] ;
				enclose(counti, countj);
				break;
			default:
				System.out.println("invalid x in box");
				break;
			}
			
		}else {
			switch (y) {
			case 0:
				count = ++ matrix[x][y + 1] ;
				enclose(count);
				break;
			case 10:
				count = ++ matrix[x][y - 1] ;
				enclose(count);
				break;
			case 2:
			case 4:
			case 6:
			case 8:
				int counti = ++ matrix[x][y + 1] ;
				int countj = ++ matrix[x][y - 1] ;
				enclose(counti, countj);
				break;
			default:
				System.out.println("invalid x in box");
				break;
			}
			
		}
		
	
	}
	
	public void enclose(int i) {// bug here
		if ((i == 5)&&(ai.isTurn) ) {
			ai.score ++;
			System.out.println("ai got one point " + ai.score);
		}else if ((i == 5)&& (man.isTurn)){
			man.score ++;
			System.out.println("man got one point " + man.score);
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
	public void enclose(int i, int j) {
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
	
	public boolean end(Player p1, Player p2) {
		if ((p1.score + p2.score) == 25) {
			return true;
		}else {
			return false;
		}
	}
	
	public int calDoubleCross() {
		int chainNum = 0;
		int cycleNum = 0;
		for (int i = 1; i < 11; i += 2) {
			for (int j = 1; j < 11; j += 2) {
				if (matrix[i][j] == 3) {
					
				}
			}
		}
		return (chainNum - 1 + 2 * cycleNum);
	}
	
	
	
	public Move move() {
		switch (judgeStage()) {
		case stage0:
			return randomMove();
		case stage1:
			return miniMaxMove();
		case stage2:
			return sMove();
		default:
			if (judgeStage() == -1) {				
				System.out.println("error in move in GamestateM and/or judgeStage");
				return null;
			}else {
				System.out.println("is end");
				return null;
			}
		}
	}
	
	
	private Move randomMove() {
		Random random = new Random();
		
		for (int i = 1; i < 11; i += 2) {
			for (int j = 1; j < 11; j += 2) {
				if (matrix[i][j] == 4) {
					if (matrix[i - 1][j] == 0) {
						return new Move( i - 1, j);//(convert x and y)
					}else if (matrix[i + 1][j] == 0) {
						return new Move( i + 1, j);//(convert x and y)
					}else if (matrix[i][j - 1] == 0) {
						return new Move( i, j - 1);//(convert x and y)
					}else {
						return new Move (i, j + 1);//(convert x and y)
					}
					
				}
				
			}
		}
		//debug
		int temx = random.nextInt(10);
		int temy = random.nextInt(10);
		boolean again = true;
		while (true) {
			while ((temx%2) == 0 || (temy%2)== 0 || matrix[temx][temy] >= 3 || !again) {//debug
				temx = random.nextInt(10);
				temy = random.nextInt(10);
				again = true;
			}
			
			if (matrix[temx][temy - 1] == 0 && temy > 2 && matrix[temx][temy - 2] <= 2) {
				return new Move(temx,temy - 1);//may need improvement
			}else if (matrix[temx][temy - 1] == 0 && temy == 1 ) {
				return new Move(temx,temy - 1);
			}else if (matrix[temx][temy + 1] == 0 && temy < 8 && matrix[temx][temy + 2] <= 2) {
				return new Move(temx,temy + 1);
			}else if (matrix[temx][temy + 1] == 0 && temy == 9 ) {
				return new Move(temx,temy + 1);
			}else if (matrix[temx - 1][temy] == 0 && temx > 2 && matrix[temx - 2][temy] <= 2) {
				return new Move(temx - 1,temy);//may need improvement
			}else if (matrix[temx - 1][temy] == 0 && temx == 1 ) {
				return new Move(temx - 1,temy);//may need improvement
			}else if(matrix[temx + 1][temy] == 0 && temx < 8 && matrix[temx + 2][temy] <= 2){
				return new Move(temx + 1, temy);
			}else if(matrix[temx + 1][temy] == 0 && temx == 9){
				return new Move(temx + 1, temy);
			}else {
				System.out.println("no move valid in random");
				
			}
			again = false;
		}	
		
		//make fouth side is prior
	
	}
	
	private Move miniMaxMove() {
		Minimax minimax = new Minimax();
		Node node = new Node(ai, man, matrix);
		minimax.score(node, true, 5);//must perform it first
		return Minimax.bestMove;
	}
	
	private Move sMove() {
		// TODO Auto-generated method stub
		Minimax minimax = new Minimax();
		Node node = new Node(ai, man, matrix);
		minimax.score(node, true, 5);//must perform it first//it is wrong
		return Minimax.bestMove;
		
	}
	
	public int judgeStage() {
		
		int GETwo = 0;
		for (int i = 1; i < 11; i += 2) {
			for (int j = 1; j < 11; j += 2) {
				
				if (matrix[i][j] >= 3) {
					GETwo ++;
				}
			}
		}
		if (GETwo <= 15) {//the chain almost come into being at 21, 22, so should set it 18,19
			return stage0;
		}else if ((GETwo < 23) && (GETwo > 15)){
			return stage1;
		}
		else if (GETwo >= 23) {
			return stage1;
		}else {
			return -1;
		}
	}
}
