package model;
import UI.FlyingSquare;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;

/**
 * Created by l3ee on 2016-03-28.
 */
public class SIGame {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;
    public static final Random RND = new Random();
    public static final int MAX_BLOCKS = 5;
    public static final int DISTANCE_BETWEEN_BLOCKS = 50;

    private List<Block> blocks;
    private Square square;
    private boolean isGameOver;
    private long time;
    private long counter;

    public SIGame() {
        blocks = new ArrayList<Block>();
        setUp();
    }

    private void setUp() {
        blocks.clear();
        square = new Square();
        isGameOver = false;
        time = 0;
        counter = System.currentTimeMillis();
    }

    // Updates the game on clock tick
    // modifies: this
    // effects: update the square and blocks
    //          and check if game over conditions are true
    public void update() {
        moveSprites();
        updateTimer();
        checkBlocks();
        generateBlock();
        checkGameOver();
    }

    // Responds to key press codes
    // modfies: this
    // effects: moves square up, restarts game
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            square.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_R && isGameOver) {
            setUp();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE || e.getKeyCode() == KeyEvent.VK_Q) {
            System.exit(0);
        }
    }

    public void draw (Graphics g) {
        for (Block b : blocks) {
            b.draw(g);
        }
        square.draw(g);
    }

    public boolean isOver() { return isGameOver;}
    public long getTime() { return time;}

    // check if any blocks have gone beyond its bounds
    // NOTE: ONLY ONE BLOCK SHOULD PASS THE BOUND AT A TIME
    //       SO WE ONLY NEED TO CHECK THE FIRST ONE IN BLOCKS
    // modifies: this
    // effects: if the first block has gone off bounds, delete it from blocks
    private void checkBlocks() {
        if (blocks == null || blocks.size() == 0) {return;}
        Block b = blocks.get(0);
        if (b.getX() <= -1 * b.getWidth()/2) {
            blocks.remove(0);
        }
    }

    private void updateTimer() {
        time = (System.currentTimeMillis() - counter)/1000 ;
    }
    private void moveSprites() {
        if (isGameOver) {return;}
        for (Block b : blocks) {
            b.move();
        }
        square.move();
    }


    private void generateBlock() {
        if (blocks.size() <= MAX_BLOCKS && farEnough(0, blocks) && !isGameOver) {
            if (RND.nextInt(100) < 1) {
                Block b = new Block(RND.nextInt(HEIGHT));
                blocks.add(b);
            }
        }
    }

    // Check if the blocks are far enough from each other
    // Expect: every following block in the list has a greater x than the previous
    // Modifies: Nothing
    // Effects: returns true if blocks are more than a 50 distance
    //          from each other, otherwise false

    public boolean farEnough(int f, List<Block> list) {
        if (list == null || list.size() == 0) {return true;}
        int currentX = list.get(f).getX();
        int nextX;
        int diff;
        for (int i = f+1 ; i < f + list.size() ; i++ ) {
            nextX = list.get(i).getX();
            diff = nextX - currentX;
            if (diff < DISTANCE_BETWEEN_BLOCKS && diff > -DISTANCE_BETWEEN_BLOCKS) {
                return false;
            }
            currentX = nextX;
        }
        return true;
    }
    // Did the square move out of bounds?
    // effects: return true if square is out of bounds, otherwise false
    private boolean checkSquareBounds() {
        if (square.getY() >= SIGame.HEIGHT || square.getY() <= 0) {
            return true;
        }
        return false;
    }

    // Did the first block collide with the square? (Has a square run into a block?)
    // effects: if square runs into a block return true, otherwise false
    private boolean checkBlockSquareCollision() {
        for (Block b : blocks) {
            if (b.collideWith(square)) {
                return true;
            }
        }
        return false;
    }

    // Check if gameOver conditions are true
    // modifies: this
    // effects: if gameover condition is true change
    //          isGameOver to true, otherwise nothing
    private void checkGameOver() {
        if (checkBlockSquareCollision() || checkSquareBounds()) {
            isGameOver = true;
        }
    }

}
