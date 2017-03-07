package gui.screencomp;

import java.awt.GridBagConstraints;
import java.awt.Point;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class NameCBox extends JComboBox<String> {
	private final String[] NAMES = {"Chronological Order", "Terence", "Aaron", "Penny", "Aurora", "Malcolm", "Nowell", "Oscar"};
	private GridBagConstraints c = new GridBagConstraints();
	
	public NameCBox(){
		super(new DefaultComboBoxModel<String>());
		for(String h: NAMES){
			this.addItem(h);
		}
		setGBagC(0, 0, 0, 0);
	}
	public NameCBox(ComboBoxModel<String> CBox, int x, int y, int height, int length){
		super(CBox);
		for(String h: NAMES){
			this.addItem(h);
		}
		setGBagC(x, y, height, length);
	}
	public NameCBox(ComboBoxModel<String> CBox, Point p, int height, int length){
		super(CBox);
		setGBagC(p, height, length);
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
}