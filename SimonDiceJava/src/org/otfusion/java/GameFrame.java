/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.otfusion.java;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author jms
 */
public class GameFrame extends JFrame {
    
    public GameFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
    }
}
