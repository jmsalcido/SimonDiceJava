package org.otfusion.java;

import java.awt.Color;
import java.awt.Rectangle;

/**
 *
 * @author jms
 */
public class Constants {
    
    // ========================================
    // GAME WINDOW SETTINGS
    // ========================================
    public static final int FPS = 30;
    public static final int WINDOW_WIDTH = 640;
    public static final int WINDOW_HEIGHT = 480;
    public static final int FLASH_SPEED = 500;
    public static final int BUTTON_SIZE = 200;
    public static final int BUTTON_GAP_SIZE = 20;
    public static final int TIMEOUT_SECS = 4;
    
    // ========================================
    // GAME BUTTONS
    // ========================================
    public static final int XMARGIN = (WINDOW_WIDTH - (2 * BUTTON_SIZE) - BUTTON_GAP_SIZE)/2;
    public static final int YMARGIN = (WINDOW_HEIGHT - (2 * BUTTON_SIZE) - BUTTON_GAP_SIZE)/2;
    
    public static final Rectangle YELLOW_RECT = new Rectangle(XMARGIN, YMARGIN, BUTTON_SIZE, BUTTON_SIZE);
    public static final Rectangle BLUERECT = new Rectangle(XMARGIN + BUTTON_SIZE + BUTTON_GAP_SIZE, YMARGIN, BUTTON_SIZE, BUTTON_SIZE);
    public static final Rectangle REDRECT = new Rectangle(XMARGIN, YMARGIN + BUTTON_SIZE + BUTTON_GAP_SIZE, BUTTON_SIZE, BUTTON_SIZE);
    public static final Rectangle GREENRECT = new Rectangle(XMARGIN + BUTTON_SIZE + BUTTON_GAP_SIZE, YMARGIN + BUTTON_SIZE + BUTTON_GAP_SIZE, BUTTON_SIZE, BUTTON_SIZE);
    
    // ========================================
    // COLORS
    // ========================================
    //                                                    R    G    B
    public static final Color WHITE         = new Color(255, 255, 255);
    public static final Color BLACK         = new Color(  0,   0,   0);
    public static final Color RED           = new Color(155,   0,   0);
    public static final Color BRIGHT_RED    = new Color(255,   0,   0);
    public static final Color GREEN         = new Color(  0, 155,   0);
    public static final Color BRIGHT_GREEN  = new Color(  0, 255,   0);
    public static final Color BLUE          = new Color(  0,   0, 155);
    public static final Color BRIGHT_BLUE   = new Color(  0,   0, 255);
    public static final Color YELLOW        = new Color(155, 155,   0);
    public static final Color BRIGHT_YELLOW = new Color(255, 255,   0);
    public static final Color DARK_GRAY     = new Color( 40,  40,  40);
    public static final Color bgColor       = BLACK;    
    
    /**
     * Please never build a Constants object.
     */
    private Constants() {/* NO constructor */}
}
