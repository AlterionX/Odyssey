package gui.screencomp;

import java.awt.GridBagConstraints;
import java.awt.Point;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class SPButton extends JButton {
	public GridBagConstraints c;
	private boolean pausing;
	
	public SPButton(){
		super("Start");
	}
	
	public GridBagConstraints getGBagC(){
		return c;
	}
	public void setGBagC(int x, int y, int height, int length){
		c.gridx = x;
		c.gridy = y;
		c.gridheight = height;
		c.gridwidth = length;
	}
	public void setGBagC(Point p, int height, int length){
		int x = p.x;
		int y = p.y;
		setGBagC(x, y, height, length);
	}
	public void setGBagC(int x, int y){
		c.gridx = x;
		c.gridy = y;
	}
	public void setGBagC(Point p){
		setGBagC(p.x, p.y);
	}
	public void setGBagC(GridBagConstraints c){
		this.c = c;
	}
	
	public void setPausing(boolean pausing){
		this.pausing = pausing; 
	}
	public boolean isPausing(){
		return pausing;
	}
}
