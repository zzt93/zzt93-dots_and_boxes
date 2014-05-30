package man;

import gui.GameGui1;

import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;


public class MainM {
	Player ai = new Player(true);
	Player man = new Player(false);
	GameGui1 gui1 = new GameGui1();
	GameStateM2 stateM2 = new GameStateM2(ai, man);
	
	public MainM() {
		try {
			SwingUtilities.invokeAndWait(new Runnable() 
			{
			    public void run() 
			    {
			    	JFrame f = new JFrame("GridBagLayout 2");
					JPanel p = gui1;
					f.setLocation(600, 0);
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
		MainM main = new MainM();
		main.start();
		main.win();
	}
	
	public void start() {
		while (!stateM2.end(man, ai)) {
			if (gui1.played) {
				stateM2.setline(gui1.move);
				gui1.played = false;
			}
		}
	}
	public void win() {
		if (ai.score > man.score) {
			System.out.println("ai win" + ai.score + " " + man.score);
		}else {
			System.out.println("man win" + man.score);
		}
	}
}
