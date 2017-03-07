package gui.screencomp;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SelecPanel extends JPanel {
	private NameCBox nameCBox;
	private ConvCBox convCBox;
	private StartButton startButton;
	private boolean start;
	public String seleconv;
	private static GridBagLayout l = new GridBagLayout();
	
	public SelecPanel(){
		super(l);
		nameCBox = new NameCBox();
		convCBox = new ConvCBox();
		startButton = new StartButton();
		nameCBox.setGBagC(2, 2, 1, 2);
		convCBox.setGBagC(2, 3, 1, 2);
		this.add(nameCBox, nameCBox.getGBagC());
		this.add(convCBox, convCBox.getGBagC());
		this.add(startButton);
		setListener(nameCBox, new NameCBoxListener());
		setListener(convCBox, new ConvCBoxListener());
		setListener(startButton, new StartButtonListener());
	}
	public String getNameChosen(){
		return (String) nameCBox.getSelectedItem();
	}
	
	public String getConvChosen(){
		return (String) convCBox.getSelectedItem();
	}
	
	/**
	 * ActionListener Handling
	 * @param CBox
	 * @param listen
	 */
	private void setListener(JComboBox<String> cBox, ActionListener listen) {
		cBox.addActionListener(listen);
	}
	private void setListener(JButton button, ActionListener listen) {
		button.addActionListener(listen);
	}
	
	public boolean isStart(){
		return start;
	}
	class NameCBoxListener implements ActionListener{
		public NameCBoxListener(){}
		@Override
		public void actionPerformed(ActionEvent e) {
			convCBox.setItems((String) nameCBox.getSelectedItem());
		}
	}
	class ConvCBoxListener implements ActionListener{
		public ConvCBoxListener(){}
		@Override
		public void actionPerformed(ActionEvent e) {
			seleconv = (String) convCBox.getSelectedItem();
		}
	}
	class StartButtonListener implements ActionListener{
		public StartButtonListener(){}
		@Override
		public void actionPerformed(ActionEvent e) {
			start = true;
		}
	}
}
