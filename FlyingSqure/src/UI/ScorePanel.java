package UI;

import model.SIGame;

import javax.swing.*;
import java.awt.*;
import java.util.LongSummaryStatistics;

/**
 * Created by l3ee on 2016-03-29.
 */
public class ScorePanel extends JPanel{
    private static final String TIMER = "Timer : ";
    private static final String EXIT = "PRESS ESCAPE or Q to Exit";
    private static final int LBL_WIDTH = SIGame.WIDTH/2;
    private static final int LBL_HEIGHT = 30;
    private SIGame game;
    private JLabel timer;
    private JLabel escape;

    // construct a score panel
    // effects: set background colour and draws intial labels
    //          updates this with the game whose score is displayed
    public ScorePanel (SIGame g) {
        game = g;
        setBackground (Color.white);
        timer = new JLabel(TIMER + game.getTime()/60 + " : " + game.getTime()%60);
        escape = new JLabel(EXIT);
        timer.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        escape.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        add(timer);
        add(Box.createHorizontalStrut(10));
        add(escape);
    }

    // updates the score panel when it is not game over
    // modifies: this
    // effects: updates the timer when the game is not over
    public void update() {
        if (!game.isOver()) {
            long n = game.getTime();
            String str1 = Long.toString(n % (long) 60);
            if ( (n % (long) 60) / (long) 10 == 0 ) {
                str1 = "0"+str1;
            }
            String str2 = Long.toString(n / (long) 60);
            if (n / (long) 60 > 23) {
                str2 = "00";
            } else if ( (n / (long) 60) / (long) 10 == 0 ) {
                str2 = "0" + str2;
            }
            String str3 = Long.toString(n/ (long) 1440);
            if ( n / (long) 1440 == 0) {
                str3 = "00";
            } else if ((n / (long) 1440) / 10 == 0 ) {
                str3 = "0" +  str3;
            }
            timer.setText(TIMER + str3 + " : " + str2 + " : " + str1);
        }
        repaint();
    }


}
