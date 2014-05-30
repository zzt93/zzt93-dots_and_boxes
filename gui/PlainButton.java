package gui;

import javax.swing.JButton;


public class PlainButton extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected  boolean changeColor;
	public boolean getChangeColor(){
		return changeColor;
	}
	
    public void setChangeColor(){
    	changeColor=false;
    }

}
