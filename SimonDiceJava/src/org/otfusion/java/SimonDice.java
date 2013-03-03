package org.otfusion.java;

import javax.swing.SwingUtilities;

/**
 *
 * @author jms
 */
public class SimonDice {

    public static final String GAME_NAME = "Simon Dice";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                runGame();
            }
        });
    }
    
    private static void runGame() {
        // DEBUG INFO
        String value = SwingUtilities.isEventDispatchThread() ? "ON" : "OFF";
        
        // Set the game.
        Game game = Game.getInstance();
        game.setFrame(new GameFrame());
        game.setGamePanel(new GamePanel());
        game.prepareGame();
        game.getGamePanel().setFocusable(true);
        game.getGamePanel().requestFocusInWindow();
        game.getFrame().pack();
        game.getFrame().setVisible(true);
        System.out.println(String.format("Event Dispatch Thread: [%s]", value));
    }
}
