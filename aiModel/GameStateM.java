package aiModel;

import java.util.Random;




public class GameStateM {
	
	public  GameStateM(Player p1, Player p2) {
		man = p1;
		ai = p2;
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
	
	public void setline(Phase phase) {
		if (valid(phase)) {
			matrix[phase.locX][phase.locY] = -1;
			box(phase);
		}
	}
	
	public boolean valid(Phase phase) {
		if (matrix[phase.locX][phase.locY] == 0) {
			return true;
		}else {
			System.out.println("invalid phase");
			return false;
		}
	}
	
	public void box(Phase phase) {
		int x = phase.locX;	//(exchange the coordinates of x and y in order to suit the swing) 
		int y = phase.locY;
		int count = 0;
		if ((x % 2) == 0 ) {
			switch (x) {
			case 0:
				count = ++ matrix[x + 1][y] ;// @warning
				enclose(phase.p,count);
				break;
			case 10:
				count = ++ matrix[x - 1][y] ;
				enclose(phase.p,count);
				break;
			case 2:
			case 4:
			case 6:
			case 8:
				count = ++ matrix[x + 1][y] ;
				enclose(phase.p,count);
				count = ++ matrix[x - 1][y] ;
				enclose(phase.p,count);
				break;
			default:
				System.out.println("invalid x in box");
				break;
			}
			
		}else {
			switch (y) {
			case 0:
				count = ++ matrix[x][y + 1] ;
				enclose(phase.p,count);
				break;
			case 10:
				count = ++ matrix[x][y - 1] ;
				enclose(phase.p,count);
				break;
			case 2:
			case 4:
			case 6:
			case 8:
				count = ++ matrix[x][y + 1] ;
				enclose(phase.p, count);
				count = ++ matrix[x][y - 1] ;
				enclose(phase.p, count);
				break;
			default:
				System.out.println("invalid y in box");
				break;
			}
			
		}
		
	
	}
	
	public void enclose(Player p,int i) {
		if (i == 5) {
			p.score ++;
			if (p.name == "ai") {
				p.setTurn(true);
				man.setTurn(false);
			}else {
				p.setTurn(true);
				ai.setTurn(false);
			}
		}else {
			if (p.name == "ai") {
				p.setTurn(false);
				man.setTurn(true);
			}else {
				p.setTurn(false);
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
	
	
	
	public Phase move() {
		switch (judgeStage()) {
		case stage0:
			return randomPhase();
		case stage1:
			return makeChainPhase();
		case stage2:
			return sPhase();
		default:
			if (judgeStage() == -1) {				
				System.out.println("error in move in Gamestate and/or judgeStage");
				return null;
			}else {
				System.out.println("is end");
				return null;
			}
		}
	}
	
	
	private Phase randomPhase() {
		Random random = new Random();
		
		for (int i = 1; i < 11; i += 2) {
			for (int j = 1; j < 11; j += 2) {
				if (matrix[i][j] == 4) {
					if (matrix[i - 1][j] == 0) {
						return new Phase(ai, j, i -1);//convert x and y
					}else if (matrix[i + 1][j] == 0) {
						return new Phase(ai, j, i + 1);//convert x and y
					}else if (matrix[i][j - 1] == 0) {
						return new Phase(ai, j - 1, i);//convert x and y
					}else {
						return new Phase(ai, j + 1, i);//convert x and y
					}
					
				}else {
					int temx = random.nextInt(10);
					int temy = random.nextInt(10);
					while (((temx%2) == 0) || ((temy%2)== 0) || matrix[temx][temy] >= 3 ||matrix[temx][temy - 1]>= 3 ||matrix[temx][temy + 1]>= 3 ||matrix[temx - 1][temy]>= 3 ||matrix[temx + 1][temy]>= 3 ) {
						temx = random.nextInt(10);
						temy = random.nextInt(10);	
					}
					return new Phase(ai, temy - 1, temx);// may need improved
				}
				
			}
		}
		
		//make fouth side is prior to others
		return null;
	}
	
	private Phase makeChainPhase() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Phase sPhase() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int judgeStage() {
		int LETwo = 0;
		int GETwo = 0;
		for (int i = 1; i < 11; i += 2) {
			for (int j = 1; j < 11; j += 2) {
				if (matrix[i][j] <= 3) {
					LETwo ++;
				}
				if (matrix[i][j] >= 3) {
					GETwo ++;
				}
			}
		}
		if (LETwo <= 12) {
			return stage0;
		}else if (GETwo < 23){
			return stage1;
		}
		else if (GETwo >= 23) {// logic problem here
			return stage2;
		}else {
			return -1;
		}
	}
}
