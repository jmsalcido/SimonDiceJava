package org.otfusion.java;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author jms
 */
public class GamePanel extends JPanel {
    
    public GamePanel() {
        setBackground(Constants.bgColor);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(Constants.YELLOW);
        g2.fill(Constants.YELLOW_RECT);
        g2.setPaint(Constants.BLUE);
        g2.fill(Constants.BLUERECT);
        g2.setPaint(Constants.RED);
        g2.fill(Constants.REDRECT);
        g2.setPaint(Constants.GREEN);
        g2.fill(Constants.GREENRECT);
        g2.setPaint(Constants.WHITE);
        g2.drawString("Press the button or Q,W,A,S", 10, 15);
        g2.drawString(String.format("SCORE: %d", Game.getInstance().getScore()),
                Constants.WINDOW_WIDTH - 100, 15);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
    }
    
}
