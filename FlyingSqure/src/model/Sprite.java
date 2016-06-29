package model;
import java.awt.Graphics;
/**
 * Created by l3ee on 2016-03-28.
 */
public abstract class Sprite {

    protected int x;
    protected int y;
    protected int width;
    protected int height;

    // constructs a spite
    // Sprite is at the specified location with give height and width
    public Sprite (int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {return x;}
    public int getY() {return y;}
    public int getWidth() {return width;}
    public int getHeight() {return height;}
    public void move() {handleBoundary();}
    // Draws the sprite
    // modifies: g
    // effects: draws the sprite on te graphic object g
    public abstract void draw(Graphics g);
    // constrains sprite so that it doesn't travel off sides of screen
    // modifies: this
    // effects: sprite is constrained to remain horizontal boundaries of game
    protected void handleBoundary() {
        if (x > SIGame.WIDTH) {
            x = SIGame.WIDTH;
        }
        if (y < 0) {
            y = 0;
        } else if (y > SIGame.HEIGHT) {
            y = SIGame.HEIGHT;
        }
    }

}
