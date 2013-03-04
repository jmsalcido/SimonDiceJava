/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.otfusion.java;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.otfusion.java.SimonDice.GAME_NAME;

/**
 * Game Logic
 * @author jms
 */
public class Game {
    
    private static Game mGame;
    private GameFrame mFrame;
    private GamePanel mGamePanel;
    private Color mColorButton;
    private Queue<Rectangle> mPattern;
    private int mScore;
    
    private Game() {
        mScore = 0;
    }
    
    public static Game getInstance() {
        if(mGame == null) {
            mGame = new Game();
        } else {
            // Nothing
        }
        return mGame;
    }
    
    public void prepareGame() {
        if(mFrame != null && mGamePanel != null) {
            mFrame.setTitle(GAME_NAME);
            mFrame.setResizable(false);
            mFrame.toFront();
            mFrame.add(mGamePanel);
        } else {
            // Nothing
        }
    }
    
    public void waitSeconds(int seconds) {
        TimerWorker timer = new TimerWorker(seconds);
        timer.execute();
    }
    
    public void init() {
        mGamePanel.repaint();
        mGamePanel.addMouseListener(mGame.createMouseAdapter());
        mGamePanel.addKeyListener(mGame.createKeyAdapter());
        mPattern = new LinkedList<>();
        mScore = 0;
    }
    
    public MouseAdapter createMouseAdapter() {
        return new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mColorButton = getButtonClicked(e.getX(), e.getY());
                isCorrect(mColorButton);
            }
        };
    }
    
    public KeyAdapter createKeyAdapter() {
        return new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_Q ) {
                    System.out.println("YELLOW");
                    mColorButton = Constants.YELLOW;
                } else if(e.getKeyCode() == KeyEvent.VK_W ) {
                    System.out.println("BLUE");
                    mColorButton = Constants.BLUE;
                } else if(e.getKeyCode() == KeyEvent.VK_A ) {
                    System.out.println("RED");
                    mColorButton = Constants.RED;
                } else if(e.getKeyCode() == KeyEvent.VK_S ) {
                    System.out.println("GREEN");
                    mColorButton = Constants.GREEN;
                } else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }
                isCorrect(mColorButton);
            }
        };
    }
    
    private void isCorrect(Color color) {
        if(color == Constants.YELLOW) {
            mScore++;
            mGamePanel.repaint();
        }
    }
    
    public Color getButtonClicked(int x, int y) {
        Point p = new Point(x, y);
        if(Constants.YELLOW_RECT.contains(p)) {
            System.out.println("YELLOW");
            return Constants.YELLOW;
        } else if(Constants.BLUERECT.contains(p)) {
            System.out.println("BLUE");
            return Constants.BLUE;
        } else if(Constants.REDRECT.contains(p)) {
            System.out.println("RED");
            return Constants.RED;
        } else if(Constants.GREENRECT.contains(p)) {
            System.out.println("GREEN");
            return Constants.GREEN;
        } else {
            return null;
        }
    }
    
    public void setFrame(GameFrame frame) {
        this.mFrame = frame;
    }
    
    public GameFrame getFrame() {
        return this.mFrame;
    }
    
    public void setGamePanel(GamePanel panel) {
        this.mGamePanel = panel;
    }
    
    public GamePanel getGamePanel() {
        return this.mGamePanel;
    }
    
    public int getScore() {
        return this.mScore;
    }
    
    public void setScore(int score) {
        this.mScore = score;
    }
    
}
