package aiMABFullM.order2NewE;

import java.util.LinkedList;

/**
 * this class search part of seemingly good edges(the principle used in greedy) at the 
 * initial depth
 * 
 * and the conditions to check the 'bad' edges still not for sure
 * 
 * a test for new evaluation function
 * @author zzt
 *
 */
public class MinimaxABO implements MinimaxInterface {
	static Move bestMove ;
	static int[][] chain = new int[25][2];
	public void score(NodeInterface node, boolean maximize, int depth) {
		staticScore((Node)node, depth);
	}

	public static void staticScore (Node node, int depth) 
	{
	    double alpha = -Double.MAX_VALUE;
	    double beta = Double.MAX_VALUE;
	    double bestScore = -Double.MAX_VALUE;
	    PartMove moves = new PartMove(node.state);
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
	            bestMove = m;
	            bestScore = alpha;
	        }
	        node.retractMove(m);
		}
	    /**
	     * the standard seems have something to do with boxes left
	     */
	    if (bestScore < 2*(node.ai.score - node.man.score)) {
	    	while (moves.moreBadM()) {
		    	Move m = moves.nextBadM();
		    	node.applyMove(m, null);
		    	
		        alpha = Math.max(alpha, miniMax(node, depth - 1, alpha, beta));
		        if(alpha > bestScore)
		        {
		            bestMove = m;
		            bestScore = alpha;
		        }
		        node.retractMove(m);
			}
		}
	    
	}

	private static double miniMax(Node currentNode, int depth, double alpha, double beta) 
	{
	    if(depth == 0 ) 
	    {
	        return evaluate(currentNode);
	    }
	    if (currentNode.gameOver()) {
			return currentNode.score;
		}
	    MoveE moves = new MoveE(currentNode.state);
	    if(currentNode.ai.isTurn)
	    {
	        while(moves.moreMoves())
	        {
	        	Move m = moves.nextGoodMove();
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
	    		Move m = moves.nextGoodMove();
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
		int chainLen = 0;
		double value = 0;
		
		for (int i = 1; i < 11; i+=2) {
			for (int j = 1; j < 11; j+=2) {
				if (node.state[i][j] == 3 ){
					chain[chainLen][0] = i;
					chain[chainLen][1] = j;
					chainLen ++;
				}
			}
		}
		value = 2*(node.ai.score - node.man.score) ; 
		
		return value;
	}
	
}
