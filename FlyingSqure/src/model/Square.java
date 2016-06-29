package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by l3ee on 2016-03-28.
 */
public class Square extends Sprite{

    public static final int DY = 2;
    public static final int upDY = DY*8;
    protected static final int SIZE_X = 30;
    protected static final int SIZE_Y = 30;
    protected static final int SQUARE_X = SIGame.WIDTH /4;
    protected static final int SQUARE_Y = SIGame.HEIGHT/2;
    private static final Color COLOR = Color.blue;
    private static int counter = 0;


    private BufferedImage image1 ;
    private BufferedImage image2 ;
    private BufferedImage image3 ;

    public Square () {
        super(SQUARE_X, SQUARE_Y, SIZE_X, SIZE_Y);
        try
        {
            image1 = ImageIO.read(new File("./res/Images/Square1.PNG"));
            image2 = ImageIO.read(new File("./res/Images/Square2.PNG"));
            image3 = ImageIO.read(new File("./res/Images/Square3.PNG"));
        } catch (IOException ex) {
            image1 = null;
            image2 = null;
            image3 = null;
        }
    }

    @Override
    public void move() {
        if (counter > DY) {
            y = y - DY * 2;
            counter = counter - 2;
        } else if (counter > 0) {
            y = y + DY;
            counter = counter - 2;
            if (counter < 0) {
                counter = 0;
            }
        } else if (counter == 0){
            y = y + DY*2;
        }
        super.move();
    }

    public void moveUp() {
        counter = upDY*2;
    }

    @Override
    public void draw(Graphics g) {
        if (counter > DY) {
            if (image2 != null) {
                g.drawImage(image2, x-SIZE_X/2, y-SIZE_Y/2, null);
                return;
            }
        } else if (counter > 0) {
            if (image3 != null) {
                g.drawImage(image3, x-SIZE_X/2, y-SIZE_Y/2, null);
                return;
            }
        } else {
            if (image1 != null) {
                g.drawImage(image1, x-SIZE_X/2, y-SIZE_Y/2, null);
                return;
            }
        }
        Color savedCol = g.getColor();
        g.setColor(COLOR);
        g.fillRect(getX() - SIZE_X / 2, getY() - SIZE_Y / 2, SIZE_X, SIZE_Y);
        g.setColor(savedCol);
    }


}
