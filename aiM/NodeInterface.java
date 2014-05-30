package aiM;

public interface NodeInterface {

	public boolean gameOver();

	public double score();

	public Player getPlayer(boolean maximize);

	public MoveEnumeration getMoves(Player player, int[][] a);//let to be decided

	public void retractMove(Move m);

	void applyMove(Move m, Player player);

}
