package gui;

import gui.screencomp.ButtonPanel;
import gui.screencomp.PrintPanel;
import gui.screencomp.LogSelectionPanel;
import loghandle.ChatLogEntry;

import javax.swing.*;
import java.awt.GridBagLayout;
import java.awt.event.*;

/**
 * @author Benjamin Xu
 */
public class OdysseyGUI implements Runnable {
    private final ConversationManager cMan = new ConversationManager();


    private JFrame baseWindow;
    private JPanel basePane;

    private JPanel mainScreen;
    private ButtonPanel mainScreenButton;
    private PrintPanel mainScreenPrintArea;

    private final Object autoLock = new Object();
    private Thread autoThread;

    //Constructor, builds screens
    OdysseyGUI() {
        frameSetup();
        setUpScreens();
        addKeyBindings();
    }

    private void addKeyBindings() {
        InputMap inFrameInput = basePane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actions = basePane.getActionMap();

        inFrameInput.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "advance");
        actions.put("advance", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Advancing script!");
                SwingUtilities.invokeLater(() -> {
                    System.out.println("Advancing the conversation one step at a time.");
                    mainScreenButton.setAuto(false);
                    cMan.advance();
                    if (cMan.hasNextEntry()) {
                        mainScreenPrintArea.append(cMan.getNextEntry());
                    }
                });
            }
        });
        inFrameInput.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.CTRL_DOWN_MASK, false), "launch");
        actions.put("launch", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Launching!");
                SwingUtilities.invokeLater(() -> System.out.println("Launched the conversation."));
            }
        });
        inFrameInput.put(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK, false), "reset");
        actions.put("reset", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Resetting!");
                SwingUtilities.invokeLater(() -> {
                    System.out.println("Reset the conversation.");
                    mainScreenButton.setAuto(false);
                });
            }
        });
        inFrameInput.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK, false), "setAuto");
        actions.put("setAuto", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Toggling auto!");
                SwingUtilities.invokeLater(() -> {
                    System.out.println("Toggled auto.");
                    mainScreenButton.toggleAuto();
                });
            }
        });
    }

    //JFrame setup
    private void frameSetup() {
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
    }
    //Window displays
    private void displayMainScreen() {
        clearBase();
        basePane.add(mainScreen, GUIHelper.getGBC(0, 0, 1, 1, 1,1,15,15));
        baseWindow.setBounds(300, 50, 530, 530);
        baseWindow.setVisible(true);
    }
    //Sub-window Setups
    private void setUpScreens() {
        setUpMainScreen();
    }
    private void setUpMainScreen() {
        //Add base panel.
        mainScreen = new JPanel(new GridBagLayout());
        //Add the three sections of the chat.
        LogSelectionPanel mainScreenLog = new LogSelectionPanel(this, cMan);
        mainScreen.add(mainScreenLog, GUIHelper.getGBC(1,1,1,1));
        mainScreenPrintArea = new PrintPanel();
        mainScreen.add(mainScreenPrintArea, GUIHelper.getGBC(1,2,5,1));
        mainScreenButton = new ButtonPanel(this);
        mainScreen.add(mainScreenButton, GUIHelper.getGBC(1,7,1,1));
    }
    //Remove all components apart from base
    private void clearBase() {
        basePane.removeAll();
    }

    //Make runnable from launcher
    @Override
    public void run() {
        displayMainScreen();
    }

    //Auto printing
    /**
     * Create and launch the auto printer.
     */
    public void createAndStartAutoThread() {
        synchronized (autoLock) {
            if (autoThread == null) {
                autoThread = new Thread(new AutoPrinter());
                autoThread.start();
            }
        }
    }
    /**
     * Interrupt the auto printer.
     */
    public void interruptAutoThread() {
        synchronized (autoLock) {
            if (autoThread != null) {
                autoThread.interrupt();
                autoThread = null;
            }
        }
    }
    /**
     * Request stop of auto print
     */
    public void requestHalt() {
        interruptAutoThread();
        mainScreenButton.setAuto(false);
    }

    public class AutoPrinter implements Runnable {
        AutoPrinter() {}

        @Override
        public void run() {
            if (!cMan.hasLogs()) {
                System.out.println("No loaded logs!");
                requestHalt();
                return;
            }
            while (cMan.hasNextEntry()) {
                ChatLogEntry cle = cMan.getNextEntry();
                //Wait for however long
                try {
                    Thread.sleep(cle.getDelay());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("Interrupted current thread.");
                    Thread.currentThread().interrupt();
                    break;
                }
                //Print, proceed
                SwingUtilities.invokeLater(() -> mainScreenPrintArea.append(cle));
                cMan.advance();
            }
            requestHalt();
        }
    }


}