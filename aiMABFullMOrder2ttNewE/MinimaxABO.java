package aiMABFullMOrder2ttNewE;

/**
 * this class has PartMove has initial depth, and the standard to search 'bad'
 * move is a trial
 * 
 * plain evalution
 * @author zzt
 *
 */
public class MinimaxABO implements MinimaxInterface {
	static Move bestMove ;
	static double score;
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
	    if (bestScore < score ) {
	    	System.out.println("in it");
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
	    MoveE moves = new MoveE(currentNode.state);
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
		return 2*(node.ai.score - node.man.score);
	}

}
