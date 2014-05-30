package aiMABFullM.order3;


public class GameStateM22 {
	static int countOfMove = 0;
	public  GameStateM22(Player a, Player m) {
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
		countOfMove ++;
		CopyOfMinimaxABO3 minimax = new CopyOfMinimaxABO3();
		MinimaxABO minimax2 = new MinimaxABO();
		Node node = new Node(ai, man, matrix);
		if (countOfMove < 4) {
			minimax.score(node, true, 7);
			return CopyOfMinimaxABO3.bestMove;
		}else if(countOfMove <= 7){
			minimax.score(node, true, 9);
			return CopyOfMinimaxABO3.bestMove;
		}else if(countOfMove <= 10){
			minimax2.score(node, true, 9);
		}else if(countOfMove <= 14){
			minimax2.score(node, true, 12 + countOfMove-11);
		}else {
			minimax2.score(node, true, 19 + 2*(countOfMove-15));
		}
		return MinimaxABO.bestMove;
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
			return stage2;
		}else {
			return -1;
		}
	}
}
