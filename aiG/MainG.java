package aiG;

import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import aiG.GameStateL.MyPosition;




public class MainG {
	GameStateL state = new GameStateL();
	Player ai = new Player(true);
	Player man = new Player(false);
	GameGui2 gui = new GameGui2(man); 
	
	public MainG() {
		try {
			SwingUtilities.invokeAndWait(new Runnable() 
			{
			    public void run() 
			    {
			    	JFrame f = new JFrame("GridBagLayout 2");
					JPanel p = gui;
					f.getContentPane().add(p);
					f.pack();
					f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
					f.setVisible(true);
			    }
			});
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//start the game
		MainG game = new MainG();
		game.strat();
		game.win();
	}
	public void strat() {
		while (!state.end()) {
			
			if (ai.isTurn) {
				MyPosition mp = move();				
//System.out.println(mp.x + " in start " + mp.y);
				gui.Swap(mp.v, mp.x, mp.y);				
				state.ChangeLeft();
//System.out.println(state.linePosition.size());
			}else if (gui.played){
				update(gui.mpG);
				state.setPosition(gui.mpG);
				
				state.ChangeLeft();
				gui.played = false;
//		System.out.println(state.linePosition.size());
			}
			
		}
	}
	
	public MyPosition move() {
		MyPosition mp = state.makePriority();
		System.out.println(" " + mp.v +" "+ mp.x+" " + mp.y +" "+mp.getPriority());
		if ( mp.getPriority() == 4) {
			ai.score ++;
			System.out.println("ai get one point" + ai.score);
			//state.makePriority()  change the color of box
		}else if (mp.getPriority() == 5) {
			ai.score += 2;
		}
		else {
			ai.setTurn(false);
			man.setTurn(true);
		}
		return mp;
	}
	public void update(MyPosition mp) {
		for (MyPosition m : state.leftPosition) {
			if ((mp.x == m.x) && (mp.y == m.y) && (mp.v == m.v)) {//must exit one meet the need
				state.new Surrounder(m).look();
				if (m.getPriority() == 4) {
					man.score ++;
					System.out.println("man get one point" + man.score);
					// change the color of box
				}else if (m.getPriority() == 5) {
					man.score += 2;
				}
				else {
					man.setTurn(false);
					ai.setTurn(true);				
				}
				break;
			}
		}
		
	
		
		
		//state.setPosition(mp);
	}
	public void win() {
		if (ai.score > man.score) {
			System.out.println("ai win " + ai.score +" "+ man.score);
		}else {
			System.out.println("man win " + man.score);
		}
	}
	
}
