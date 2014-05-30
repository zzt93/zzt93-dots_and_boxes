package aiMNim;



//import java.util.*;
//import javax.swing.text.*;


public class Minimax implements MinimaxInterface {
	static Move bestMove ;
	public double score(NodeInterface node, boolean maximize, int depth) {
		return staticScore(node, maximize, depth);
	}

	public static double staticScore(NodeInterface node, boolean maximize, int depth) {
    // if this node has children - then make it the max of them
    // otherwise it must be a leaf node so - return its own score

    // the real problem here is defining the set of possible moves for this one

		if ( node.gameOver()) {
 //System.out.println("----------\n" + node + " scores " + node.score() + "\n---------\n");  
			return node.score();
		}else if (depth == 0) {
			return evaluate((Node)node);
		}
		else
			return bestScore((Node)node, maximize, depth);
	}
	
	private static double evaluate(Node node) {
		int s3 = 0;
		int s2 = 0;
		int s1 = 0;
		int chain = 0;
		double value = 0;
		for (int i = 1; i < 11; i += 2) {
			for (int j = 1; j < 11; j += 2) {
				if (node.state[i][j] == 3) {
					s2 ++;
				}else if(node.state[i][j] == 4){
					s3 ++;
					if (i < 9 && (node.state[i + 2][j] == 4) && node.state[i + 1][j] == 0) {
						chain ++;
					}else if (i > 1 && (node.state[i - 2][j] == 4) && node.state[i - 1][j] == 0) {
						chain ++;
					}else if (j > 1 && (node.state[i][j - 2] == 4) && node.state[i][j - 1] == 0) {
						chain ++;
					}else if (j < 9 && (node.state[i][j + 2] == 4) && node.state[i][j + 1] == 0) {
						chain ++;
					}  
				}else if(node.state[i][j] == 2){
					s1 ++;
				}
			}
		}
	//System.out.println("in evaluate" );
		value = 2*(node.ai.score - node.man.score) + 0.5 * s2 - 0.75 * s3 - 0.25 * s1 ; 
		
//System.out.println(value);
		return value;
	}

	private static double bestScore(Node node, boolean maximize, int depth) {

    // initialise it to the worst
		double best = worst(maximize);

		MoveEnumeration moves = node.getMoves(node.getPlayer(maximize),node.state);

		while ( moves.moreMoves() ) {
			Move m = moves.nextMove();
			node.applyMove(m, node.getPlayer(maximize) );
//System.out.println(node + " scores " + node.score());
			double childScore;
			if (node.ai.isTurn) {
				childScore = staticScore(node, maximize, depth-1);
			}else {
				childScore = staticScore(node, !maximize, depth-1);
			}
			
      
			if ( betterThan(childScore, best, maximize) ){
				best = childScore;
			//	bestMove = m; it is wrong here
				if (depth == 5) {// must correspond to the number when initializing in gamestate
					bestMove = m;				//bug here
				}
	//System.out.println(bestMove);
			}
			node.retractMove(m);//debug
		}
		return best;
	}

  // this should perhaps be a  method of NodeInterface
	public static double worst(boolean maximize) {
		if (maximize)
			return Integer.MIN_VALUE;
		else
			return Integer.MAX_VALUE;
	}

	public static boolean betterThan(double x, double y, boolean maximize) {
		return ((x > y) && maximize) || ((x < y) && !maximize);
	}
}
