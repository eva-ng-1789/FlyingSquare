package test;

import model.Block;
import model.SIGame;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by l3ee on 2016-03-29.
 */
public class SIGameTest {
    private List<Block> nullexample;
    private List<Block> tester;
    private Block b1 = new Block(10); // 400 - 250 = 150
    private Block b2 = new Block(20); // 150
    private Block b3 = new Block(30); // 300
    private Block b4 = new Block(40); // 350
    private Block b5 = new Block(50); // 390
    private Block b6 = new Block(60); // 400

    private Block b7 = new Block(60); // 0

    private SIGame s = new SIGame();


    @Before
    public void setUp() {

        tester = new ArrayList<Block>();
        for (int i = 0 ; i <= 250 ; i++) {
            b1.move();
        }
        for (int i = 0 ; i <= 250 ; i++) {
            b2.move();
        }

        for (int i = 0 ; i <= 100 ; i++) {
            b3.move();
        }

        for (int i = 0 ; i <= 50 ; i++) {
            b4.move();
        }

        for (int i = 0 ; i <= 10 ; i++) {
            b5.move();
        }

        for (int i = 0 ; i <= 400 ; i++ ) {
            b7.move();
        }
    }

    @Test
    public void testNULL () {
        assertTrue(s.farEnough(0, nullexample));
    }

    @Test
    public void testEmpty() {
        tester.add(b1);
        assertTrue(s.farEnough(0, tester));
    }

    @Test
    public void testSameBlocks () {
        tester.add(b1);
        tester.add(b1);
        assertFalse(s.farEnough(0, tester));
    }

    @Test
    public void testTwoBlocksAtTheSameSpot () {
        tester.add(b1);
        tester.add(b2);
        assertFalse(s.farEnough(0, tester));
    }

    @Test
    public void testTwoFarEnoughBlocks () {
        tester.add(b1);
        tester.add(b3);
        assertTrue(s.farEnough(0, tester));
    }

    @Test
    public void testOrderMatters () {
        tester.add(b1);
        tester.add(b3);
        tester.add(b2);
        assertTrue(s.farEnough(0, tester));
        tester.remove(b3);
        assertFalse(s.farEnough(0,tester));
    }

    @Test
    public void testLimitTrueof50 () {
        tester.add(b3);
        tester.add(b4);
        assertTrue(s.farEnough(0,tester));
    }

    @Test
    public void addALLAlrightOrder() {
        tester.add(b1);
        tester.add(b6);
        tester.add(b4);
        tester.add(b2);
        tester.add(b3);
        tester.add(b5);
        assertTrue(s.farEnough(0,tester));
    }

    @Test
    public void testSize() {
        assertEquals(tester.size(), 0);
        tester.add(b7);
        assertEquals(tester.size(), 1);
        assertFalse(tester == null);
        Block b = tester.get(0);
        if (b.getX() <= 0) {
            tester.remove(0);
        }
        assertEquals(tester.size(), 0);

    }

}
