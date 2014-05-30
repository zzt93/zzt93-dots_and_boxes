package aiMABFullM.order3;

/**
 * this class search part of seemingly good edges(the principle used in greedy) at the 
 * every depth
 * 
 * and the conditions for check other 'bad' edges still not for sure
 * @author zzt
 *
 */
public class MinimaxABO3 implements MinimaxInterface {
	static Move bestMove ;
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
			return 2*(currentNode.ai.score-currentNode.man.score);
		}
	    PartMove moves = new PartMove(currentNode.state);
	    if(currentNode.ai.isTurn)
	    {
	    	double best = -100;
	        while(moves.moreGoodM())
	        {
	        	Move m = moves.nextGoodM();
		    	currentNode.applyMove(m, null);
	            alpha = Math.max(alpha, miniMax(currentNode, depth - 1, alpha, beta));
	            if(alpha > best)//find the highest
		        {
		            best = alpha;
		        }
	            currentNode.retractMove(m);
	            if(alpha >= beta)
	            {
	                return alpha;
	            }
	            
	        }
	        if (best < 2*(currentNode.ai.score - currentNode.man.score)) {//if too low
	        	System.out.println("in it");
		    	while (moves.moreBadM()) {
			    	Move m = moves.nextBadM();
			    	currentNode.applyMove(m, null);
			    	
			        alpha = Math.max(alpha, miniMax(currentNode, depth - 1, alpha, beta));
			        if(alpha > best)
			        {
			            best = alpha;
			        }
			        currentNode.retractMove(m);
				}
			}
	        return alpha;
	    }
	    else
	    {
	    	double best = 100;
	    	while(moves.moreGoodM())
	        {
	    		Move m = moves.nextGoodM();
		    	currentNode.applyMove(m, null);
	            beta = Math.min(beta, miniMax(currentNode, depth - 1, alpha, beta));
	            if (best > beta) {//find the lowest
					best = beta;
				}
	            currentNode.retractMove(m);
	            if(alpha >= beta)
	            {
	                return beta;
	            }
	            
	        }
	        if (best > 2*(currentNode.ai.score - currentNode.man.score)) {//if too high
	        	System.out.println("in it");
		    	while (moves.moreBadM()) {
			    	Move m = moves.nextBadM();
			    	currentNode.applyMove(m, null);
			    	
			        beta = Math.min(beta, miniMax(currentNode, depth - 1, alpha, beta));
			        if(beta < best)
			        {
			            best = beta;
			        }
			        currentNode.retractMove(m);
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
