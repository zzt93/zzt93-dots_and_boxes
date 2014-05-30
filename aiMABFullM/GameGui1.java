package aiMABFullM;

import gui.Button1;
import gui.Button2;
import gui.Button3;
import gui.Button4;
import gui.PlainButton;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;





public class GameGui1 extends JPanel{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GridBagConstraints c = new GridBagConstraints();
    PlainButton[][] b = new PlainButton[11][11];
    Listener1 l1=new Listener1();
    
    Move move;
    boolean played = false;
    
	public GameGui1(){
		
		this.setLayout(new GridBagLayout());
		this.setOpaque(true);
		
		for(int i=0;i<=10;i+=2){
			for(int j=0;j<=10;j+=2){
				b[i][j]=new Button1();
				b[i][j].addMouseListener(l1);

				c.gridx=i;
				c.gridy=j;
				this.add(b[i][j],c);
			}
		}
		
		for(int i=1;i<=10;i+=2){
			for(int j=0;j<=10;j+=2){
				b[i][j]=new Button2();
				b[i][j].addMouseListener(l1);
				c.gridx=i;
				c.gridy=j;
				this.add(b[i][j],c);
			}
		
		}
		
		for(int i=0;i<=10;i+=2){
			for(int j=1;j<=10;j+=2){
				b[i][j]=new Button3();
				b[i][j].addMouseListener(l1);
				c.gridx=i;
				c.gridy=j;
				this.add(b[i][j],c);
			}
		
		}
		
		for(int i=1;i<=10;i+=2){
			for(int j=1;j<=10;j+=2){
				b[i][j]=new Button4();
				b[i][j].addMouseListener(l1);
				c.gridx=i;
				c.gridy=j;
				this.add(b[i][j],c);
			}
		
		}
		
	}
	public void setColor (Move m) {
		b[m.locX][m.locY].setBackground(Color.red);
		b[m.locX][m.locY].setChangeColor();
	}
	class Listener1 implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			 Rectangle rt=((JButton)e.getSource()).getBounds();
				for(int i=0;i<=10;i++){
					for(int j=0;j<=10;j++){
						if(rt.equals(b[i][j].getBounds())&&b[i][j].getChangeColor()){
							b[i][j].setBackground(Color.red);
							b[i][j].setChangeColor();
							
							System.out.println(i + " " + j);
							move = new Move(i, j);
							played = true;// it must be the last one//debug
							}
					}
			}
			
		}
		

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
            Rectangle rt=((JButton)e.getSource()).getBounds();
			for(int i=0;i<=10;i++){
				for(int j=0;j<=10;j++){
					if(rt.equals(b[i][j].getBounds())&&b[i][j].getChangeColor()){
						b[i][j].setBackground(Color.red);}
				}
		}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			 Rectangle rt=((JButton)e.getSource()).getBounds();
				for(int i=0;i<=10;i++){
					for(int j=0;j<=10;j++){
						if(rt.equals(b[i][j].getBounds())&&b[i][j].getChangeColor()){
							b[i][j].setBackground(Color.white);
							}
					}
			}
		}
    	
    }
	public static void main(String[] args) {
		JFrame f = new JFrame("GridBagLayout 2");
		JPanel p = new GameGui1();
		f.getContentPane().add(p);
		f.pack();
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.setVisible(true);

	}

}
