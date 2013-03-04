/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.otfusion.java;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

/**
 *
 * @author jms
 */
public class TimerWorker extends SwingWorker<Object, Object> {
    
    private int mSeconds;
    private JLabel mLabel;
    
    public TimerWorker(int seconds) {
        this.mSeconds = seconds;
    }
    
    @Override
    protected Object doInBackground() throws Exception {
        Game game = Game.getInstance();
        mLabel = new JLabel("" + mSeconds);
        mLabel.setForeground(Color.RED);
        game.getGamePanel().add(mLabel);
        int i = 0;
        do {
            Thread.sleep(1000);
            mLabel.setText("" + (mSeconds - i));
            i += 1;
        } while(i < mSeconds);
        return null;
    }

    @Override
    protected void done() {
        Game game = Game.getInstance();
        game.getGamePanel().remove(mLabel);
        mLabel = null;
        game.init();
    }
    
}
