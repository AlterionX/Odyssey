package gui;

import gui.screencomp.ButtonPanel;
import gui.screencomp.PrintPanel;
import gui.screencomp.LogSelectionPanel;

import javax.swing.*;
import java.awt.GridBagLayout;

/**
 * @author Benjamin Xu
 */
public class OdysseyChatLogGUI implements Runnable {

    private boolean pause = true;
    private boolean auto = false;

    private JFrame baseWindow;
    private JPanel basePane;
    private JPanel mainBasePanel;

    //Constructor, builds screens
    public OdysseyChatLogGUI() {
        baseWindow = new JFrame();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Issues with the system look and feel.");
            e.printStackTrace();
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception e2) {
                System.out.println("Look and feel error with the defaults.");
                e2.printStackTrace();
                System.exit(-1);
            }
        }
        //Basic window information.
        baseWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        baseWindow.setResizable(false);
        baseWindow.setTitle("An Odyssey into Time: Reflections on Homer");

        basePane = new JPanel();
        baseWindow.setContentPane(basePane);
        setUpScreens();
    }

    //Window Setups
    private void setUpScreens() {
        setUpMainScreen();
    }
    private void setUpMainScreen() {
        //Add base panel.
        mainBasePanel = new JPanel(new GridBagLayout());
        //Add the three sections of the chat.
        mainBasePanel.add(new LogSelectionPanel(this), GUIHelper.getGBC(1,1,1,1));
        mainBasePanel.add(new PrintPanel(this),
                GUIHelper.getGBC(1,2,5,1));
        mainBasePanel.add(new ButtonPanel(this), GUIHelper.getGBC(1,7,1,1));
    }

    private void resetBase() {
        basePane.removeAll();
    }

    //Window displays
    private void displayMainScreen() {
        resetBase();
        basePane.add(mainBasePanel);
        baseWindow.setBounds(300, 50, 500, 500);
        baseWindow.setVisible(true);
    }

    //Message display settings
    public void setAuto(boolean auto) {
        this.auto = auto;
    }
    public boolean isAuto() {
        return auto;
    }
    public void setPause(boolean pause) {
        if (ConversationManager.getCurrentConversation() != null) {
            this.pause = pause;
        } else {
            halt();
        }
    }
    public boolean isPause() {
        return pause;
    }
    public void halt() {
        setPause(true);
        ConversationManager.resetCurrent();
    }

    //Make runnable from launcher
    @Override
    public void run() {
        displayMainScreen();
    }

}