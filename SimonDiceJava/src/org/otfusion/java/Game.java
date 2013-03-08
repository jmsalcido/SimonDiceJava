/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.otfusion.java;

import java.awt.Color;
import java.awt.Point;
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
    private OggClip mOggPlayer;
    
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
    
    public void initInSeconds(int seconds) {
        TimerWorker timer = new TimerWorker(seconds);
        timer.execute();
    }
    
    public void init() {
        mGamePanel.repaint();
        mGamePanel.addMouseListener(mGame.createMouseAdapter());
        mGamePanel.addKeyListener(mGame.createKeyAdapter());
        mPattern = new LinkedList<>();
        mPattern.add(Constants.YELLOW);
        mScore = 0;
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
                    resource = getClass().getResourceAsStream("/org/otfusion/java/resources/beep1.ogg");
                    button = Constants.YELLOW;
                } else if(e.getKeyCode() == KeyEvent.VK_W ) {
                    resource = getClass().getResourceAsStream("/org/otfusion/java/resources/beep2.ogg");
                    System.out.println("BLUE");
                    button = Constants.BLUE;
                } else if(e.getKeyCode() == KeyEvent.VK_A ) {
                    resource = getClass().getResourceAsStream("/org/otfusion/java/resources/beep3.ogg");
                    System.out.println("RED");
                    button = Constants.RED;
                } else if(e.getKeyCode() == KeyEvent.VK_S ) {
                    resource = getClass().getResourceAsStream("/org/otfusion/java/resources/beep4.ogg");
                    System.out.println("GREEN");
                    button = Constants.GREEN;
                } else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }
                playSound(resource);
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
            resource = getClass().getResourceAsStream("/org/otfusion/java/resources/beep1.ogg");
        } else if(Constants.BLUERECT.contains(p)) {
            System.out.println("BLUE");
            color = Constants.BLUE;
            resource = getClass().getResourceAsStream("/org/otfusion/java/resources/beep2.ogg");
        } else if(Constants.REDRECT.contains(p)) {
            System.out.println("RED");
            color = Constants.RED;
            resource = getClass().getResourceAsStream("/org/otfusion/java/resources/beep3.ogg");
        } else if(Constants.GREENRECT.contains(p)) {
            System.out.println("GREEN");
            color = Constants.GREEN;
            resource = getClass().getResourceAsStream("/org/otfusion/java/resources/beep4.ogg");
        } else {
            color = null;
        }
        playSound(resource);
        return color;
    }
    
    private void playSound(InputStream sound_input_stream) {
        try {
            mOggPlayer = new OggClip(sound_input_stream);
            mOggPlayer.play();
            mOggPlayer = null;
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void verifyAndScore(Color color) {
        if(color == null) {
            return;
        } else {
            // Continue
        }
        Color value = mPattern.poll();
        if(value == color) {
            mScore++;
            if(mScore == Constants.WIN_POINTS) {
                doWinAnimation();
            }
            mGamePanel.repaint();
        } else if(value == null) {
            System.out.println("Todos los objetos correctos");
        }else {
            // LOOSER
            System.exit(0);
        }
    }
    
    private void addRandomColor() {
        int randomNumber = (int)(Math.random() * 5);
        Color array[] = new Color[] {
            Constants.YELLOW,
            Constants.BLUE,
            Constants.RED,
            Constants.GREEN
        };
        mPattern.add(array[randomNumber]);
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
