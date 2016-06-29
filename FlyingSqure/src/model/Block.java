package model;

import java.awt.*;

import static java.awt.Color.*;

/**
 * Created by l3ee on 2016-03-28.
 */
public class Block extends Sprite{
    protected static final int SIZE_X = 75;
    protected static final int SIZE_Y = 75;
    protected static final int BLOCK_X = SIGame.WIDTH + SIZE_X/2;
    private static final Color COLOR = Color.green;
    protected int DX = 2;

    public Block(int y) {
        super(BLOCK_X,y, SIZE_X, SIZE_Y);
    }

    @Override
    public void draw (Graphics g) {
        Color savedCol = g.getColor();
        g.setColor(COLOR);
        g.fillRect(getX() - SIZE_X / 2, getY() - SIZE_Y / 2, SIZE_X, SIZE_Y);
        g.setColor(savedCol);
    }

    @Override
    public void move(){
        x = x - DX;
    }

    public boolean collideWith (Sprite other) {
        Rectangle thisBoundingRect = new Rectangle( getX() - getWidth()/2,
                getY() - getHeight()/2, getWidth(), getHeight() );
        Rectangle otherBoundingRect = new Rectangle( other.getX() - other.getWidth()/2,
                other.getY() - other.getHeight()/2, other.getWidth(), other.getHeight() );
        return thisBoundingRect.intersects(otherBoundingRect);
    }
}
