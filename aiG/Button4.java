package aiG;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;


public class Button4 extends MyButton{
	
	private int coloredLines=0;
	public Button4(){
		setPreferredSize(new Dimension(80, 80));
		setBackground(Color.white);
		changeColor=false;
	}
	
	public boolean getChangeColor(){
		return changeColor;
	}
	
    public void setChangeColoe(){
    	changeColor=false;
    }
    
	public void setColoredLines(){
		coloredLines++;
	}
	public int getColoredLines(){
		return coloredLines;
	}
	
	public void SetColor(Color i){
		setBackground(i);
	}

}

