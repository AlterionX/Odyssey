package gui;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AlterionX
 */
public class AreaPrinter implements Runnable{
    public String[] h;
    public AreaPrinter(String[] h){
        this.h = h;
    }
    @Override
    public void run(){
        for (String x : h){
            //accessing pause data
            boolean pause = Main.log.pausing;
            //Pausing mechanism
            while (pause){
                pause = Main.log.pausing;
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(AreaPrinter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //Actual Task
            //adding text
            Main.log.chatArea.append(x);
            //showing text
            Main.log.chatArea.repaint();
            //pausing before next
            if (Main.log.autoPause){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(AreaPrinter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            Main.log.autoPause = false;
            //Stopping mechanism
            if (Main.log.stop){
            	Main.log.stop = false;
                break;
            }
        }
    }
}
