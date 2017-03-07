package gui.screencomp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ButPanel extends JPanel {
	SPButton sPButton = new SPButton();
	StopButton stopButton = new StopButton();
	
	public ButPanel(){
		super();
		
		this.add(sPButton);
		this.add(stopButton);
		stopButton.addActionListener(new StopListener());
		
		
	}
	
	class SPListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			sPButton.setPausing(!sPButton.isPausing());
			if(sPButton.isPausing()){
				sPButton.setText("Start");
			} else{
				sPButton.setText("Pause");
			}
		}
	}
	
	class StopListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			stopButton.setStop(true);
		}
	}
}
