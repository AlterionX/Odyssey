package gui.screencomp;

import gui.ConvSetter;

import java.awt.GridBagConstraints;
import java.awt.Point;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class ConvCBox extends JComboBox<String> {
	private ConvSetter convSetter = new ConvSetter();
	private GridBagConstraints c = new GridBagConstraints();
	
	public ConvCBox(){
		super(new DefaultComboBoxModel<String>());
		String[] list = convSetter.getConvNames("Chronological Order");
		for(String h: list){
			this.addItem(h);
		}
		setGBagC(0, 0, 0, 0);
	}
	public ConvCBox(ComboBoxModel<String> CBox, int name, int x, int y, int height, int length){
		super(CBox);
		String[] list = convSetter.getConvNames(name);
		for(String h: list){
			this.addItem(h);
		}
		setGBagC(x, y, height, length);
	}
	public ConvCBox(ComboBoxModel<String> CBox, int name, Point p, int height, int length){
		super(CBox);
		String[] list = convSetter.getConvNames(name);
		this.removeAll();
		for(String h: list){
			this.addItem(h);
		}
		setGBagC(p, height, length);
	}
	public ConvCBox(ComboBoxModel<String> CBox, String name, int x, int y, int height, int length){
		super(CBox);
		String[] list = convSetter.getConvNames(name);
		this.removeAll();
		for(String h: list){
			this.addItem(h);
		}
		setGBagC(x, y, height, length);
	}
	public void setGBagC(int x, int y, int height, int length){
		//TODO
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
	public GridBagConstraints getGBagC(){
		return c;
	}
	public void setItems(String name) {
		String[] list = convSetter.getConvNames(name);
		this.removeAllItems();
		for(String h: list){
			this.addItem(h);
		}
	}
}
