package aiG;
import javax.swing.JButton;


public class MyButton extends JButton {
	protected  boolean changeColor;
	public boolean getChangeColor(){
		return changeColor;
	}
	
    public void setChangeColor(){
    	changeColor=false;
    }

}
