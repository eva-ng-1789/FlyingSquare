package UI;

import model.SIGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by l3ee on 2016-03-29.
 */
public class GamePanel extends JPanel {
    private static final String OVER = "GAME OVER";
    private static final String REPLAY = "PRESS R to Replay";
    private static final String EXIT = "PRESS ESCAPE or Q to Exit";
    private SIGame game;
    private BufferedImage image;

    // constructs game panel
    // effects: set size and background colour of panel
    //          and updates game to be displayed
    public GamePanel(SIGame g) {
        setPreferredSize(new Dimension(SIGame.WIDTH, SIGame.HEIGHT));
        try {
            image = ImageIO.read(new File("./res/Images/Sky.PNG"));
        } catch (IOException ex) {
            image = null;
            setBackground(Color.GRAY);
        }
        game = g;
    }

    @Override
    protected void paintComponent (Graphics g) {
        super.paintComponent(g);

        try {
            if (game.isOver()) {
                image = ImageIO.read(new File("./res/Images/storm.PNG"));
            } else {
                image = ImageIO.read(new File("./res/Images/Sky.PNG"));
            }
        } catch (IOException ex) {
            image = null;
            setBackground(Color.GRAY);
        }

        if (image != null) {
            g.drawImage(image, 0, 0, null);
        }
        drawGame(g);

        if (game.isOver()) {
            gameOver(g);
        }
    }

    // Draws the "game over" messages, replay instructions, and exit instructions
    // modies: g
    // effects: Draw "game over", replay instructions and escape instructions onto g
    private void gameOver(Graphics g) {
        Color saved = null;
        try {
            image = ImageIO.read(new File("./res/Images/storm.PNG"));
        } catch (IOException ex) {
            image = null;
            setBackground(Color.GRAY);
            saved = g.getColor();
        }
        g.setFont(new Font("Arial", 20, 20));
        FontMetrics fm = g.getFontMetrics();
        centreSpring(OVER, g, fm, SIGame.HEIGHT / 2 - 30);
        centreSpring(REPLAY, g, fm, SIGame.HEIGHT /2);
        centreSpring(EXIT, g, fm, SIGame.HEIGHT / 2 + 30);
        if (image == null) {g.setColor(saved);}

    }

    private void centreSpring(String str, Graphics g, FontMetrics fm, int i) {
        int width = fm.stringWidth(str);
        g.drawString (str, (SIGame.WIDTH - width) / 2 , i);
    }

    // Draw the game
    // modifies: g
    // effects: the game is drawn on the graphics object g
    private void drawGame(Graphics g) {
        game.draw(g);
    }

}
