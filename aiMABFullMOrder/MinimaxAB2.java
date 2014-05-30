package aiMABFullMOrder;

/**
 * use the last alpha to order part of edges
 */
import java.util.Comparator;
import java.util.TreeSet;

/**
 *  the compare function need checking; the evaluation function needs tuning
 * @author zzt
 *
 */
 
public class MinimaxAB2 implements MinimaxInterface {
	static TreeSet<Move> bestMove = new TreeSet<Move>(new Comparator<Move>(){

		@Override
		public int compare(Move o1, Move o2) {
			if (o1.priority > o2.priority) {
				return -1;
			}else if (o1.priority < o2.priority) {
				return 1;
			}else {
				return 0;
			}
		}
		
	});
	public void score(NodeInterface node, boolean maximize, int depth) {
		staticScore((Node)node, depth);
	}

	public static void staticScore (Node node, int depth) 
	{
	    double alpha = -Double.MAX_VALUE;
	    double beta = Double.MAX_VALUE;
	    double bestScore = -Double.MAX_VALUE;
	    MoveEnumeration moves;
	    if (!bestMove.isEmpty()) {
	    	moves = new MoveEnumeration(node.state, bestMove);
		}else {
			moves = new MoveEnumeration(node.state);
		}
    	bestMove.clear();
	    while (moves.moreMoves()) {
	    	Move m = moves.nextMoveA();
	    	node.applyMove(m, null);

	        alpha = Math.max(alpha, miniMax(node, depth - 1, alpha, beta));
	        if(alpha >= bestScore)
	        {
	        	m.setPriority(alpha);
	            bestMove.add(m);	            
	            bestScore = alpha;
	        }
	        node.retractMove(m);
		}
	    
	}

	private static double miniMax(Node currentNode, int depth, double alpha, double beta) 
	{
	    if(depth == 0 ) 
	    {
	        return evaluate(currentNode);
	    }
	    if (currentNode.gameOver()) {
			return 2*(currentNode.ai.score-currentNode.man.score);
		}
	    MoveEnumeration moves = new MoveEnumeration(currentNode.state);
	    if(currentNode.ai.isTurn)
	    {
	        while(moves.moreMoves())
	        {
	        	Move m = moves.nextMoveA();
		    	currentNode.applyMove(m, null);
	            alpha = Math.max(alpha, miniMax(currentNode, depth - 1, alpha, beta));
	            currentNode.retractMove(m);
	            if(alpha >= beta)
	            {
	                return beta;
	            }
	            
	        }
	        return alpha;
	    }
	    else
	    {
	    	while(moves.moreMoves())
	        {
	    		Move m = moves.nextMoveA();
		    	currentNode.applyMove(m, null);
	            beta = Math.min(beta, miniMax(currentNode, depth - 1, alpha, beta));
	            currentNode.retractMove(m);
	            if(alpha >= beta)
	            {
	                return alpha;
	            }
	            
	        }
	        return beta;
	    }
	}
	
	private static double evaluate(Node node) {
		int s3 = 0;
		int s2 = 0;
		//int s1 = 0;
		//int chain = 0;
		double value = 0;
		for (int i = 1; i < 11; i += 2) {
			for (int j = 1; j < 11; j += 2) {
				if (node.state[i][j] == 3) {
					s2 ++;
				}else if(node.state[i][j] == 4){
					s3 ++;
					/*if (i < 9 && (node.state[i + 2][j] == 4) && node.state[i + 1][j] == 0) {
						chain ++;
					}else if (i > 1 && (node.state[i - 2][j] == 4) && node.state[i - 1][j] == 0) {
						chain ++;
					}else if (j > 1 && (node.state[i][j - 2] == 4) && node.state[i][j - 1] == 0) {
						chain ++;
					}else if (j < 9 && (node.state[i][j + 2] == 4) && node.state[i][j + 1] == 0) {
						chain ++;
					}  */
				}
			}
		}
	//System.out.println("in evaluate" );
	/*	if (node.ai.isTurn) {
			value = 4*node.ai.score - node.man.score * 2 + 0.5 * s2 - 0.75 * s3  ; 
		}else {
			value = 4*node.ai.score - node.man.score * 2 - 0.5 * s2 + 0.75 * s3  ; 
		}*/
		value = 2*node.ai.score - node.man.score * 2 + 0.5 * s2 - 0.75 * s3  ;
		
//System.out.println(value);
		return value;
	}

	

  
/*	public static double worst(boolean maximize) {
		if (maximize)
			return Integer.MIN_VALUE;
		else
			return Integer.MAX_VALUE;
	}

	public static boolean betterThan(double x, double y, boolean maximize) {
		return ((x > y) && maximize) || ((x < y) && !maximize);
	}*/
}
