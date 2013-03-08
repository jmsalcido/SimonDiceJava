/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.otfusion.java;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

/**
 *
 * @author jms
 */
public class TimerWorker extends SwingWorker<Object, Object> {
    
    private int mSeconds;
    private JLabel mLabel;
    
    public TimerWorker(int seconds) {
        // Add extra second to wait second more.
        this.mSeconds = seconds+1;
    }
    
    @Override
    protected Object doInBackground() throws Exception {
        Game game = Game.getInstance();
        // Remove one second
        int seconds = mSeconds - 1;
        
        // set the label
        mLabel = new JLabel("" + seconds);
        mLabel.setForeground(Color.RED);
        
        // add the label
        game.getGamePanel().add(mLabel);
        
        // iterate tru the i
        int i = 0;
        do {
            // sleep the timer thread
            Thread.sleep(1000);
            
            // set the text label
            mLabel.setText("" + (seconds - i));
            i += 1;
        } while(i < mSeconds);
        // mSeconds because we are using the real time here.
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
