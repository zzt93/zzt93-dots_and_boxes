package gui;

import java.awt.Color;
import java.awt.Dimension;



public class Button4 extends PlainButton{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

