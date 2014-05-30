package aiMABFullMOrder4;

import java.util.Comparator;
import java.util.TreeSet;


/**
 * this class has PartMove has initial depth, and the standard to search 'bad'
 * move is a trial
 * 
 * add the optimal way somewhat like literate: use the last alpha to help order
 * 
 * Result: It doesn't faster than order
 * @author zzt
 *
 */
public class MinimaxABO implements MinimaxInterface {
	static Move bestMove ;
	static double score;
	static TreeSet<Move> bestMoveSet = new TreeSet<Move>(new Comparator<Move>(){

		@Override
		public int compare(Move o1, Move o2) {
			if (o1.getPriority() > o2.getPriority()) {
				return -1;
			}else if (o1.getPriority() < o2.getPriority()) {
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
	    PartMove moves ;
	    if (!bestMoveSet.isEmpty()) {
	    	moves = new PartMove(node.state, bestMoveSet);
		}else {
			moves = new PartMove(node.state);
		}
    	bestMoveSet.clear();
	    bestMove = null;
	    while (moves.moreGoodM()) {
	    	Move m = moves.nextGoodM();
	    	node.applyMove(m, null);
	    	if(bestMove == null)
	        {
	            bestMove = m;
	        }
	        alpha = Math.max(alpha, miniMax(node, depth - 1, alpha, beta));
	        if(alpha > bestScore)
	        {
	        	m.setPriority(alpha);
	        	bestMoveSet.add(m);
	            bestMove = m;
	            bestScore = alpha;
	        }
	        node.retractMove(m);
		}
	    
	    /**
	     * the standard seems have something to do with boxes left
	     */
	    if (bestScore < score ) {
	    	System.out.println("in it");
	    	while (moves.moreBadM()) {
		    	Move m = moves.nextBadM();
		    	node.applyMove(m, null);
		    	
		        alpha = Math.max(alpha, miniMax(node, depth - 1, alpha, beta));
		        if(alpha > bestScore)
		        {
		        	m.setPriority(alpha);
		        	bestMoveSet.add(m);
		            bestMove = m;
		            bestScore = alpha;
		        }
		        node.retractMove(m);
			}
		}
	    score = bestScore;
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
	    MoveE moves;
	    if (!bestMoveSet.isEmpty()) {
	    	moves = new MoveE(currentNode.state, bestMoveSet);
		}else {
			moves = new MoveE(currentNode.state);
		}
    	bestMoveSet.clear();
	    if(currentNode.ai.isTurn)
	    {
	        while(moves.moreMoves())
	        {
	        	Move m = moves.nextMoveAi();
		    	currentNode.applyMove(m, null);
	            alpha = Math.max(alpha, miniMax(currentNode, depth - 1, alpha, beta));
	            currentNode.retractMove(m);
	            if(alpha >= beta)
	            {
	                return alpha;
	            }
	            
	        }
	        return alpha;
	    }
	    else
	    {
	    	while(moves.moreMoves())
	        {
	    		Move m = moves.nextMoveAi();
		    	currentNode.applyMove(m, null);
	            beta = Math.min(beta, miniMax(currentNode, depth - 1, alpha, beta));
	            currentNode.retractMove(m);
	            if(alpha >= beta)
	            {
	                return beta;
	            }
	            
	        }
	        return beta;
	    }
	}
	
	private static double evaluate(Node node) {
		int s3 = 0;
		int s2 = 0;
//		int s1 = 0;
		//int chain = 0;
		double value = 0;
		for (int i = 1; i < 11; i += 2) {
			for (int j = 1; j < 11; j += 2) {
				if (node.state[i][j] == 3) {
					s2 ++;
				}else if(node.state[i][j] == 4){
					s3 ++;
				/*	if (i < 9 && (node.state[i + 2][j] == 4) && node.state[i + 1][j] == 0) {
						chain ++;
					}else if (i > 1 && (node.state[i - 2][j] == 4) && node.state[i - 1][j] == 0) {
						chain ++;
					}else if (j > 1 && (node.state[i][j - 2] == 4) && node.state[i][j - 1] == 0) {
						chain ++;
					}else if (j < 9 && (node.state[i][j + 2] == 4) && node.state[i][j + 1] == 0) {
						chain ++;
					}  */
				}/*else if(node.state[i][j] == 2){
					s1 ++;
				}*/
			}
		}
	//System.out.println("in evaluate" );
		value = 2*(node.ai.score - node.man.score) + 0.5 * s2 - 0.75 * s3 ; 
		
//System.out.println(value);
		return value;
	}

}
