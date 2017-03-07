package gui;

import java.awt.GridBagLayout;

import gui.screencomp.ButPanel;
import gui.screencomp.PrintPanel;
import gui.screencomp.SelecPanel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * 
 * @author Benjamin Xu
 *
 */
public class OdysseyChatLogGUI implements Runnable{
	
	public boolean pausing;
	public boolean stop;
	public boolean autoPause = false;
	
	@Override
	/**
	 * Runs the class.
	 */
	public void run() {
		mainWindow();
	}
	
	/**
	 * Sets the main window: the only one.
	 */
	private void mainWindow() {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel(new GridBagLayout());
		// TODO Auto-generated method stub
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("An Odyssey into Time: Reflections on Homer");
		frame.add(panel);
		
		panel.add(new SelecPanel());
		panel.add(new PrintPanel());
		panel.add(new ButPanel());
		
		frame.pack();
		frame.setBounds(300, 50, 500, 500);
		frame.setVisible(true);
	}
}