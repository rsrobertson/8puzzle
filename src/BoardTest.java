/**
 * Created by rsraloha on 5/26/17.
 */
import edu.princeton.cs.algs4.StdOut;
import org.junit.Assert;
import org.junit.Test;

import java.io.Console;

public class BoardTest {

    @Test
    public void cTorTest(){
        int[][] blocks = {
                {1,2,3},
                {4,5,6},
                {7,8,0}
        };

        Board b = new Board(blocks);
        Assert.assertEquals(b.dimension(),3);
    }

    @Test
    public void hammingTest(){
        int[][] blocks = {
                {1,2,3},
                {4,5,6},
                {7,8,0}
        };

        Board b = new Board(blocks);
        Assert.assertEquals(b.hamming(),0);
    }

    @Test
    public void hammingTest2(){
        int[][] blocks = {
                {2,1,3},
                {4,5,6},
                {7,8,0}
        };

        Board b = new Board(blocks);
        Assert.assertEquals(b.hamming(),2);
    }
    @Test
    public void isGoalTestTrue(){
        int[][] blocks = {
                {1,2,3},
                {4,5,6},
                {7,8,0}
        };
        Board b = new Board(blocks);
        Assert.assertTrue(b.isGoal());
    }

    @Test
    public void isGoalTestFalse(){
        int[][] blocks = {
                {2,1,3},
                {4,5,6},
                {7,8,0}
        };
        Board b = new Board(blocks);
        Assert.assertFalse(b.isGoal());
    }
    @Test
    public void twinTest(){
        int[][] blocks = {
                {1,2,3},
                {4,5,6},
                {7,8,0}
        };
        Board b = new Board(blocks);
        Board twin = b.twin();
        Assert.assertEquals(twin.hamming(), 2);
    }

    @Test
    public void neighborsTest(){
        int[][] blocks = {
                {0,1,3},
                {4,5,6},
                {7,8,2}
        };
        Board b = new Board(blocks);
        Iterable<Board> boards = b.neighbors();
        for(Board x : boards){
            StdOut.println(x.toString());
        }

    }
}
