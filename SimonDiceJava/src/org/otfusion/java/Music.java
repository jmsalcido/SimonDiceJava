/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.otfusion.java;

import java.awt.Color;
import java.io.InputStream;
import org.newdawn.easyogg.OggClip;

/**
 *
 * @author jms
 */
public class Music {
    
    private OggClip mOggPlayer;
    
    public void playSound(InputStream sound_input_stream) {
        try {
            mOggPlayer = new OggClip(sound_input_stream);
            mOggPlayer.play();
            mOggPlayer = null;
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void playSound(Color color) {
        InputStream sound_stream = null;
        if(color == Constants.YELLOW) {
            sound_stream = getClass().getResourceAsStream(Constants.BEEP1_FILE);
        } else if(color == Constants.BLUE) {
            sound_stream = getClass().getResourceAsStream(Constants.BEEP2_FILE);
        } else if(color == Constants.RED) {
            sound_stream = getClass().getResourceAsStream(Constants.BEEP3_FILE);
        } else if(color == Constants.GREEN) {
            sound_stream = getClass().getResourceAsStream(Constants.BEEP4_FILE);
        }
        playSound(sound_stream);
    }
    
}
