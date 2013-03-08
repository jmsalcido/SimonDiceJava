/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.otfusion.java;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Queue;
import org.newdawn.easyogg.OggClip;

import static org.otfusion.java.SimonDice.GAME_NAME;

/**
 * Game Logic
 * @author jms
 */
public class Game {
    
    private static Game mGame;
    private GameFrame mFrame;
    private GamePanel mGamePanel;
    private Queue<Color> mPattern;
    private int mScore;
    private Music mMusic;
    
    private Game() {
        mScore = 0;
        mMusic = new Music();
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
    
    public void initInSeconds(int seconds) {
        TimerWorker timer = new TimerWorker(seconds);
        timer.execute();
    }
    
    public void init() {
        mGamePanel.repaint();
        mGamePanel.addMouseListener(mGame.createMouseAdapter());
        mGamePanel.addKeyListener(mGame.createKeyAdapter());
        mPattern = new LinkedList<>();
        //mPattern.add(Constants.YELLOW);
        mScore = 0;
        addRandomColor();
        playPattern();
    }
    
    public MouseAdapter createMouseAdapter() {
        return new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Color button = getButtonClicked(e.getX(), e.getY());
                verifyAndScore(button);
            }
        };
    }
    
    public KeyAdapter createKeyAdapter() {
        return new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                Color button = null;
                InputStream resource = null;
                if (e.getKeyCode() == KeyEvent.VK_Q ) {
                    System.out.println("YELLOW");
                    resource = getClass().getResourceAsStream(Constants.BEEP1_FILE);
                    button = Constants.YELLOW;
                } else if(e.getKeyCode() == KeyEvent.VK_W ) {
                    resource = getClass().getResourceAsStream(Constants.BEEP2_FILE);
                    System.out.println("BLUE");
                    button = Constants.BLUE;
                } else if(e.getKeyCode() == KeyEvent.VK_A ) {
                    resource = getClass().getResourceAsStream(Constants.BEEP3_FILE);
                    System.out.println("RED");
                    button = Constants.RED;
                } else if(e.getKeyCode() == KeyEvent.VK_S ) {
                    resource = getClass().getResourceAsStream(Constants.BEEP4_FILE);
                    System.out.println("GREEN");
                    button = Constants.GREEN;
                } else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }
                mMusic.playSound(resource);
                verifyAndScore(button);
            }
        };
    }
    
    public Color getButtonClicked(int x, int y) {
        Point p = new Point(x, y);
        Color color = null;
        InputStream resource = null;
        if(Constants.YELLOW_RECT.contains(p)) {
            System.out.println("YELLOW");
            color = Constants.YELLOW;
            resource = getClass().getResourceAsStream(Constants.BEEP1_FILE);
        } else if(Constants.BLUERECT.contains(p)) {
            System.out.println("BLUE");
            color = Constants.BLUE;
            resource = getClass().getResourceAsStream(Constants.BEEP2_FILE);
        } else if(Constants.REDRECT.contains(p)) {
            System.out.println("RED");
            color = Constants.RED;
            resource = getClass().getResourceAsStream(Constants.BEEP3_FILE);
        } else if(Constants.GREENRECT.contains(p)) {
            System.out.println("GREEN");
            color = Constants.GREEN;
            resource = getClass().getResourceAsStream(Constants.BEEP4_FILE);
        } else {
            color = null;
        }
        mMusic.playSound(resource);
        return color;
    }
    
    private void verifyAndScore(Color color) {
        Queue<Color> temp = new LinkedList<>();
        if(color == null) {
            return;
        } else {
            // Continue
        }
        
        Color value = mPattern.poll();
        temp.add(value);
        if(value == color) {
            // Nothing :^)
        } else {
            System.exit(0);
        }
        
        value = mPattern.peek();
        if(value == null) {
            mScore++;
            mPattern = temp;
            try { Thread.sleep(500); } catch (Exception ex) {}
            addRandomColor();
            playPattern();
            System.out.println("Todos los objetos correctos");
            if(mScore == Constants.WIN_POINTS) {
                doWinAnimation();
            }
            mGamePanel.repaint();
        }
    }
    
    private void addRandomColor() {
        int randomNumber = (int)(Math.random() * 5);
        if(randomNumber >= 4) {
            randomNumber = 3;
        }
        Color array[] = new Color[] {
            Constants.YELLOW,
            Constants.BLUE,
            Constants.RED,
            Constants.GREEN
        };
        mPattern.add(array[randomNumber]);
    }
    
    private void playPattern() {
        for(Object o : mPattern.toArray()) {
            Color color = (Color) o;
            Color color_tmp = color;
            Graphics2D g = (Graphics2D)mGamePanel.getGraphics();
            Rectangle rectangle = null;
            if(color == Constants.YELLOW) {
                color = Constants.BRIGHT_YELLOW;
                rectangle = Constants.YELLOW_RECT;
            } else if(color == Constants.BLUE) { 
                color = Constants.BRIGHT_BLUE;
                rectangle = Constants.BLUERECT;
            } else if(color == Constants.RED) {
                color = Constants.BRIGHT_RED;
                rectangle = Constants.REDRECT;
            } else if(color == Constants.GREEN) {
                color = Constants.BRIGHT_GREEN;
                rectangle = Constants.GREENRECT;
            }
            g.setPaint(color);
            g.fill(rectangle);
            mGamePanel.paintComponents(g);
            mMusic.playSound(color_tmp);
            try { Thread.sleep(1000 - (mScore * 20)); } catch (Exception ex) {}
            g.setPaint(color_tmp);
            g.fill(rectangle);
            mGamePanel.paintComponents(g);
        }
    }
    
    private void doWinAnimation() {
        
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
