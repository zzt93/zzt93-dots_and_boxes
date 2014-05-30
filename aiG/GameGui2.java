package aiG;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
//import javax.swing.JFrame;
import javax.swing.JPanel;
//import javax.swing.WindowConstants;

import aiG.GameStateL.MyPosition;


public class GameGui2 extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MyPosition mpG ;
	boolean played = false;
	Player manPlayer ;
	
    private GridBagConstraints c = new GridBagConstraints();
    MyButton[][] b = new MyButton[11][11];
    Listener1 l1=new Listener1();
    
	public GameGui2(Player man){
		
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
			manPlayer = man;
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
	class Listener1 implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			 Rectangle rt=((JButton)e.getSource()).getBounds();
				for(int i=0;i<=10;i++){
					for(int j=0;j<=10;j++){
						if(rt.equals(b[i][j].getBounds())&&b[i][j].getChangeColor()&& manPlayer.isTurn){
							b[i][j].setBackground(Color.red);
							b[i][j].setChangeColor();
							position(i, j);
							played = true;
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
						b[i][j].setBackground(Color.blue);
						}
					}
			}
		}
		public void position(int i,int j) {
			boolean bl = false;
			int x=0;
			int y=0;
			if(j%2==0){
				bl=false;
				x=(i-1)/2;
				y=j/2;
			}
			else{
				bl=true;
				x=i/2;
				y=(j-1)/2;
			}
			mpG = new GameStateL().new MyPosition (bl,x,y);
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
	
	public void Swap(boolean bl,int x,int y){
		int i,j;
		if(bl){
			i=2*x;
			j=2*y+1;					
		}
		else{
			i=2*x+1;
			j=2*y;
		}
//System.out.println(i + "   in swap   " + j);
		b[i][j].setBackground(Color.red);
		b[i][j].setChangeColor();
	}
	
/*	public static void main(String[] args) {
		JFrame f = new JFrame("GridBagLayout 2");
		JPanel p = new GameGui1();
		f.getContentPane().add(p);
		f.pack();
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.setVisible(true);

	}*/

}
