package aiMABFullMOrder2ttFinal;

public interface NodeInterface {

	public boolean gameOver();

	public double score();

	public Player getPlayer(boolean maximize);


	public void retractMove(Move m);

	void applyMove(Move m, Player player);

}
