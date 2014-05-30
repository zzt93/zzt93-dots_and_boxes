package aiMmctsFinal;

import java.util.Random;



public class CopyOfMoveEnum {
	Move move ;
	
	public CopyOfMoveEnum(int[][] a) {
		// TODO Auto-generated constructor stub
		boolean exit = false;

		Random random = new Random();
		int col = 0;
		int row = 0;
		int trial = 0;
		while (!exit && trial <= 121) {
			trial ++;
			col = random.nextInt(11);
			row = random.nextInt(11);
			while ((col + row)%2 == 0) {
				col = random.nextInt(11);
				row = random.nextInt(11);
			}
			for (int i = col; i < 11 && !exit; i++) {
				for (int j = row ; j < 11 && !exit; j+=2) {
					//add the fourth side
					if ( i == 0 && a[i+1][j] == 4 && a[i][j] == 0 ) {

						move = new Move(i, j);
						exit = true;
					}else if (i == 10 && a[i-1][j] == 4 && a[i][j] == 0) {

						move = new Move(i, j);
						exit = true;
					}else if ((i%2) == 0 && i >= 2 && i <= 8 && (a[i-1][j] == 4 || a[i+1][j] == 4) && a[i][j] == 0) {

						move = new Move(i, j);
						exit = true;
					}else if ( j == 0 && a[i][j+1] == 4 && a[i][j] == 0 ) {

						move = new Move(i, j);
						exit = true;
					}else if (j == 10 && a[i][j-1] == 4 && a[i][j] == 0) {

						move = new Move(i, j);
						exit = true;
					}else if ((j%2) == 0 && j >= 2 && j <= 8  && (a[i][j-1] == 4 || a[i][j+1] == 4) && a[i][j] == 0) {

						move = new Move(i, j);
						exit = true;
					}
					
				}
			}
			for (int i = col; i < 11 && !exit; i++) {
				for (int j = row; j < 11 && ! exit; j+=2) {
					//add the second and 
					if ( i == 0 && a[i+1][j] <= 2 && a[i][j] == 0 ) {

						move = new Move(i, j);
						exit = true;
					}else if (i == 10 && a[i-1][j] <= 2 && a[i][j] == 0) {

						move = new Move(i, j);
						exit = true;
					}else if ((i%2) == 0 && i >= 2 && i <= 8 && a[i-1][j] <= 2 && a[i+1][j] <= 2 && a[i][j] == 0) {

						move = new Move(i, j);
						exit = true;
					}else if ( j == 0 && a[i][j+1] <= 2 && a[i][j] == 0 ) {

						move = new Move(i, j);
						exit = true;
					}else if (j == 10 && a[i][j-1] <= 2 && a[i][j] == 0) {

						move = new Move(i, j);
						exit = true;
					}else if ((j%2) == 0 && j >= 2 && j <= 8 && a[i][j-1] <= 2 && a[i][j+1] <= 2 && a[i][j] == 0) {

						move = new Move(i, j);
						exit = true;
					}
				}
			}
		}
		
		for (int i = 0; i < 11 && !exit; i++) {
			for (int j = ((i%2==0)?1:0); j < 11 && !exit; j+=2) {
				if (a[i][j] == 0) {
					move = new Move(i, j);
					exit = true;
				}
			}
		}
		
	}
	

	public Move nextMove() {//debug
		//move.trimToSize();
		return move;
	}

}
