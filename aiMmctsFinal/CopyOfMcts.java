package aiMmctsFinal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;




public class CopyOfMcts {
	static Random r = new Random();
    static double epsilon = 1e-6;
    
    int stateduInRO [][] = new int[11][11];
    MctsNode root;
    //ArrayList<Move> moveList = new ArrayList<Move>();
    
	long start = System.currentTimeMillis();

	
	public CopyOfMcts(MctsNode mNode) {
		// TODO Auto-generated constructor stub
		root = mNode;		
		
	}
	
	public Move bestMove () {
		boolean good = false;
		Move best = null;
		while (!good) {
			long start = System.currentTimeMillis();
			while ((System.currentTimeMillis()- start) < 5000) {
				selectAction();
	//System.out.println("select move");
			}
			best = changeToMove(selectM());
			good = goodMove(best);
			if (!good) {
				for (Iterator<MctsNode> iterator = root.children.iterator(); iterator
						.hasNext();) {
					MctsNode type = (MctsNode) iterator.next();
					
					if (changeToMove(type).equals(best) ) {
						iterator.remove();
						break;
					}
				}
			}
		}
		return best;
	}
	
	private boolean goodMove(Move move) {
		//the fourth 
		if ( move.locX == 0 && root.state[move.locX+1][move.locY] == 4 && root.state[move.locX][move.locY] == 0 ) {
			return true;
		}else if (move.locX == 10 && root.state[move.locX-1][move.locY] == 4 && root.state[move.locX][move.locY] == 0) {
			return true;
		}else if ((move.locX%2) == 0 && move.locX >= 2 && move.locX <= 8 && (root.state[move.locX-1][move.locY] == 4 || root.state[move.locX+1][move.locY] == 4) && root.state[move.locX][move.locY] == 0) {
			return true;
		}else if ( move.locY == 0 && root.state[move.locX][move.locY+1] == 4 && root.state[move.locX][move.locY] == 0 ) {
			return true;
		}else if (move.locY == 10 && root.state[move.locX][move.locY-1] == 4 && root.state[move.locX][move.locY] == 0) {
			return true;
		}else if ((move.locY%2) == 0 && move.locY >= 2 && move.locY <= 8  && (root.state[move.locX][move.locY-1] == 4 || root.state[move.locX][move.locY+1] == 4) && root.state[move.locX][move.locY] == 0) {
			return true;
		}
		
		for (int i = 0; i < 11 ; i++) {
			for (int j = ((i%2)==0?1:0) ; j < 11 ; j+=2) {
				//add the fourth side
				if ( i == 0 && root.state[i+1][j] == 4 && root.state[i][j] == 0 ) {
					return false;
				}else if (i == 10 && root.state[i-1][j] == 4 && root.state[i][j] == 0) {
					return false;
				}else if ((i%2) == 0 && i >= 2 && i <= 8 && (root.state[i-1][j] == 4 || root.state[i+1][j] == 4) && root.state[i][j] == 0) {
					return false;
				}else if ( j == 0 && root.state[i][j+1] == 4 && root.state[i][j] == 0 ) {
					return false;
				}else if (j == 10 && root.state[i][j-1] == 4 && root.state[i][j] == 0) {
					return false;
				}else if ((j%2) == 0 && j >= 2 && j <= 8  && (root.state[i][j-1] == 4 || root.state[i][j+1] == 4) && root.state[i][j] == 0) {
					return false;
				}
				
			}
		}
		//second and first
		if ( move.locX == 0 && root.state[+1][move.locY] <= 2 && root.state[move.locX][move.locY] == 0 ) {
			return true;
		}else if (move.locX == 10 && root.state[move.locX-1][move.locY] <= 2 && root.state[move.locX][move.locY] == 0) {
			return true;
		}else if ((move.locX%2) == 0 && move.locX >= 2 && move.locX <= 8 && root.state[move.locX-1][move.locY] <= 2 && root.state[move.locX+1][move.locY] <= 2 && root.state[move.locX][move.locY] == 0) {
			return true;
		}else if ( move.locY == 0 && root.state[move.locX][move.locY+1] <= 2 && root.state[move.locX][move.locY] == 0 ) {
			return true;
		}else if (move.locY == 10 && root.state[move.locX][move.locY-1] <= 2 && root.state[move.locX][move.locY] == 0) {
			return true;
		}else if ((move.locY%2) == 0 && move.locY >= 2 && move.locY <= 8 && root.state[move.locX][move.locY-1] <= 2 && root.state[move.locX][move.locY+1] <= 2 && root.state[move.locX][move.locY] == 0) {
			return true;
		}
		
		
		for (int i = 0; i < 11 ; i++) {
			for (int j = ((i%2)==0?1:0); j < 11 ; j+=2) {
				//add the second and 
				if ( i == 0 && root.state[i+1][j] <= 2 && root.state[i][j] == 0 ) {
					return false;
				}else if (i == 10 && root.state[i-1][j] <= 2 && root.state[i][j] == 0) {
					return false;
				}else if ((i%2) == 0 && i >= 2 && i <= 8 && root.state[i-1][j] <= 2 && root.state[i+1][j] <= 2 && root.state[i][j] == 0) {
					return false;
				}else if ( j == 0 && root.state[i][j+1] <= 2 && root.state[i][j] == 0 ) {
					return false;
				}else if (j == 10 && root.state[i][j-1] <= 2 && root.state[i][j] == 0) {
					return false;
				}else if ((j%2) == 0 && j >= 2 && j <= 8 && root.state[i][j-1] <= 2 && root.state[i][j+1] <= 2 && root.state[i][j] == 0) {
					return false;
				}
			}
		}
		
		return true;
	}
	private MctsNode selectM() {
		double best = -1;
		MctsNode bestNode = null;
		for (MctsNode node : root.children) {
			if (node.nVisits != 0) {
				double score = node.nVisits;
				if (score > best ) {
					best = score;
					bestNode = node;
				}
			}
		}
		return bestNode;
	}

	public Move changeToMove(MctsNode mNode) {
		int[] count = new int[3];
		count[2] = 0;
		for (int i = 0; i < root.state.length; i++) {
			for (int j = 0; j < root.state.length; j++) {
				if (mNode.state[i][j] != root.state[i][j] && (i + j)%2 == 1) {
					count[0] = i;
					count[1] = j;
					count[2] ++;
				}
			}
		}
		if (count[2] > 1) {
			System.out.println("select wrong move");
			System.exit(0);
		}
		return new Move(count[0], count[1]);
		
	}
	
	public void selectAction() {
		boolean expand;
		List<MctsNode> visited = new LinkedList<MctsNode>();
		visited.add(root);
        MctsNode cur = root;     
        while (!cur.isLeaf()) {
            cur = select(cur);
            // System.out.println("Adding: " + cur);
            visited.add(cur);
        }
        double value;
        expand = expand(cur);
        if (expand) {
        	MctsNode newNode = select(cur);       
            visited.add(newNode);
            value = rollOut(newNode);
		}else {
			value = cur.ai.score;
		}
        for (MctsNode node : visited) {     
            updateStats(node, value);//need improvement 
        }
        visited = null;
    }
	
	public boolean expand(MctsNode mctsNode) {
		boolean expand = false;        
        MoveEnumeration moves = new MoveEnumeration(mctsNode.state);
      /*  if (!moves.moreMoves()) {
			System.out.println("no move" );
		}*/
        if (moves.moreMoves()) {
        	
        	if (mctsNode == root) {
        		mctsNode.children = new ArrayList<MctsNode>(60);
        		for (int i = 0; moves.moreMoves(); i++) {           	
                	Move move = moves.nextMove();
                	mctsNode.applyMove(move, null);
                    mctsNode.children.add(new MctsNode(mctsNode.ai, mctsNode.man, mctsNode.state));//out of memory
                    mctsNode.retractMove(move);
                    expand = true;
        		}
			}else {
				mctsNode.children = new ArrayList<MctsNode>(5);
				for (int i = 0; moves.moreMoves() && i < 5; i++) {           	
	            	Move move = moves.nextMove();
	            	mctsNode.applyMove(move, null);
	                mctsNode.children.add(new MctsNode(mctsNode.ai, mctsNode.man, mctsNode.state));//out of memory
	                mctsNode.retractMove(move);
	                expand = true;
	    		}
			}
        	mctsNode.children.trimToSize();
		}else {
			mctsNode.children = null;
		}
        moves = null;
      //  System.out.println(expand);
        return expand;
    }
	private MctsNode select(MctsNode cur) {
        MctsNode selected = null;
        double bestValue = -1;
       /* if (cur.children.isEmpty()) {
			System.out.println("no children in select");
		}*/
        for (MctsNode c : cur.children) {
        	if (c == null ) {
				System.out.println("children is null");
				System.exit(0);
			}
            double uctValue =	//need to delve into it
                    c.totValue / (c.nVisits + epsilon) +
                          0.7 * Math.sqrt(Math.log(cur.nVisits+1) / (c.nVisits + epsilon))+ 
                            r.nextDouble() * epsilon;
            
            //System.out.println("UCT value = " + uctValue);
            if (uctValue > bestValue) {
                selected = c;
                bestValue = uctValue;
                //System.out.println("UCT value = " + uctValue);
            }
        }
        if (selected == null) {
        	System.out.println("mistakes in selcet");
			System.exit(0);
		}
   //System.out.println("in select");
        return selected;
    }
	

    public double rollOut(MctsNode tn) {
        // ultimately a roll out will end in some value
        // assume for now that it ends in a win or a loss
        boolean isAiTurn ;
    	int score = 0;
    	if (tn == null) {
			System.out.println("tn is null");
		}
    	//out of memory
    	int scdu = tn.ai.score;
    	int manscdu = tn.man.score;
    	isAiTurn = tn.ai.isTurn;
    	Move m ;
    	for (int i = 0; i < stateduInRO.length; i++) {
			for (int j = 0; j < stateduInRO.length; j++) {
				stateduInRO[i][j] = tn.state[i][j];
				
			}
		}
    	MoveEnumeration moveEnumeration = new MoveEnumeration(tn.state);
    	while ( moveEnumeration.moreMoves()) {
    		CopyOfMoveEnum moveEnum = new CopyOfMoveEnum(tn.state);
    		m = moveEnum.nextMove();
    		moveEnumeration.nextMove();
			tn.applyMove(m, null);
			moveEnum = null;
//System.out.println(node + " scores " + node.score());			
		}
    	moveEnumeration = null;
    	for (int i = 0; i < stateduInRO.length; i++) {
			for (int j = 0; j < stateduInRO.length; j++) {
				tn.state[i][j] = stateduInRO[i][j];
			}
		}
    	score = tn.ai.score;
		tn.ai.score = scdu;
		tn.man.score = manscdu;
		tn.ai.isTurn = isAiTurn;
		tn.man.isTurn = !isAiTurn;
		
        return score;
    }
  /*  private boolean moreMoves(int[][] state) {
    	for (int i = 0; i < 11; i+=2) {
			for (int j = 1; j < 11; j+=2) {
				if (state[i][j] == 0) {
					moveList.add(new Move(i, j));
				}
			}
		}
		for (int i = 1; i < 11; i+=2) {
			for (int j = 0; j < 11; j+=2) {
				if (state[i][j] == 0) {
					moveList.add(new Move(i, j));// out of memory
				}
			}
		}
		moveList.trimToSize();
		if (moveList.isEmpty()) {
			return false;
		}
		return true;
	}*/

	public void updateStats(MctsNode node, double value) {
        node.nVisits++;
        node.totValue += value;
    }
    
    
    
}
