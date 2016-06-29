package UI;

import model.SIGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
/**
 * Created by l3ee on 2016-03-29.
 */
public class FlyingSquare extends JFrame {
    public static final int INTERVAL = 20;
    private SIGame game;
    private GamePanel gp;
    private ScorePanel sp;
    private Timer t;

    // constructs main window
    // effects set up window in which Flying Square game will be played
    public FlyingSquare() {
        super ("Flying Square");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        game = new SIGame();
        gp = new GamePanel(game);
        sp = new ScorePanel(game);
        add(gp);
        add(sp, BorderLayout.NORTH);
        addKeyListener(new KeyHandler());
        pack();
        centreOnScreen();
        setVisible(true);
        addTimer();
        t.start();

    }

    // Set up timer
    // modifies: none:
    // effects: initalizes timer that updates games each INTERVAL milliseconds
    private void addTimer() {
        t = new Timer (INTERVAL, new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent ae) {
                game.update();
                gp.repaint();
                sp.update();
            }
        });
    }

    // Centres frame on desktop
    // modfies: this
    // effects: location of frame is set so frame is centered on desktop
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }


    // A key handler to respond to key events

    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {game.keyPressed(e);}
    }

    // Play the Game
    public static void main (String[] args) { new FlyingSquare();}
}
