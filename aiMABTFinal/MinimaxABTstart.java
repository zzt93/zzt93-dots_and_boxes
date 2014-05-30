package aiMABTFinal;

/**
 * use the TwoMove as the key and not make use of the hashcode
 * for I iterate the hashmap myself.
 * not so efficient
 * 
 * depth = 7
 */

import java.util.HashMap;



public class MinimaxABTstart implements MinimaxInterface {
//	int visit = 0;
	static Move bestMove;
	static int best;
	static int maxDepth = 8;
	Node rootNode;
	HashMap<TwoMove, Double> trans = new HashMap<TwoMove, Double>();
	public void score(NodeInterface node, boolean maximize, int depth) {
		staticScore((Node)node, depth);
	}

	public void staticScore (Node node, int depth) 
	{
	    double alpha = -Double.MAX_VALUE;
	    double beta = Double.MAX_VALUE;
	    double bestScore = -Double.MAX_VALUE;
	    rootNode = node;
	    PartMove moves = new PartMove(node.state);
	    bestMove = null;
	    while (moves.moreGoodM()) {
	    	Move m = moves.nextGoodM();
	    	node.applyMove(m, null);
	    	if(bestMove == null)
	        {
	            bestMove = m;
	        }
	        alpha = Math.max(alpha, miniMax(node, depth - 1, alpha, beta, m));        
	        if(alpha > bestScore)
	        {
	            bestMove = m;
	            bestScore = alpha;
	        }
	        node.retractMove(m);
		}
	    //the standard is left to chech
	    if (bestScore < 2*(node.ai.score - node.man.score)) {
	    	while (moves.moreBadM()) {
		    	Move m = moves.nextBadM();
		    	node.applyMove(m, null);
		    	
		        alpha = Math.max(alpha, miniMax(node, depth - 1, alpha, beta, m));
		        if(alpha > bestScore)
		        {
		            bestMove = m;
		            bestScore = alpha;
		        }
		        node.retractMove(m);
			}
		}
	    
	}

	private double miniMax(Node currentNode, int depth, double alpha, double beta, Move lastm) 
	{
		boolean posess = false;
		double temp;
	    if(depth == 0 ) 
	    {
	 //   	visit ++;
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
	        	posess = false;
	        	Move m = moves.nextGoodMove();
	        	if (depth == maxDepth -1) {        		
	        		TwoMove tem = new TwoMove(lastm, m);
	        		if (trans.containsKey(tem)) {							
						temp = trans.get(tem) + 2*(rootNode.ai.score - rootNode.man.score);
						alpha = temp;
						posess = true;
					}	
					
					if (!posess) {
						currentNode.applyMove(m, null);
			            alpha = Math.max(alpha, miniMax(currentNode, depth - 1, alpha, beta, null));
			            trans.put(tem, alpha - 2*(rootNode.ai.score - rootNode.man.score));
			            currentNode.retractMove(m);
					}
				}		    	
	    		else {
	            	currentNode.applyMove(m, null);
		            alpha = Math.max(alpha, miniMax(currentNode, depth - 1, alpha, beta, null));
		            currentNode.retractMove(m);
				}
	            
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
	    		posess = false;
	    		Move m = moves.nextGoodMove();
	    		if (depth == maxDepth - 1) {
	    			TwoMove tem = new TwoMove(lastm, m);
	    			if (trans.containsKey(tem)) {							
						temp = trans.get(tem) + 2*(rootNode.ai.score - rootNode.man.score);
						beta = temp;
						posess = true;
					}
					if (!posess) {
						currentNode.applyMove(m, null);
			            beta = Math.min(beta, miniMax(currentNode, depth - 1, alpha, beta, null));
			            trans.put(tem, beta - 2*(rootNode.ai.score - rootNode.man.score));
			            currentNode.retractMove(m);
					}
				}else {
	            	currentNode.applyMove(m, null);
		            beta = Math.min(beta, miniMax(currentNode, depth - 1, alpha, beta, null));
		            currentNode.retractMove(m);
				}
	            
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
		if (node.man.score >= 13) {
			return -10*node.man.score;
		}else {
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
					}
				}
			}
		//System.out.println("in evaluate" );
			value = 2*(node.ai.score - node.man.score);// + 0.5 * s2 - 0.75 * s3; 
			
	//System.out.println(value);
			return value;
		}
	}

	

  // this should perhaps be a  method of NodeInterface
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
