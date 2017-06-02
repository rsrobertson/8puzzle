/**
 * Created by rsraloha on 5/26/17.
 */
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import org.junit.Assert;
import org.junit.Test;

import java.io.Console;
import java.io.File;

public class BoardTest {

    private Board LoadBoard(String fileName) {
        String home = System.getProperty("user.home");
        File f = new File(home + "/Documents/AlgorithmsPart1/8puzzle/8puzzle/" + fileName);
        In in = new In(f);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                blocks[i][j] = in.readInt();
            }
        }

        return new Board(blocks);

    }

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
    public void hammingPuzzle4Test(){
        Board board = LoadBoard("puzzle04.txt");
        Assert.assertEquals(board.hamming(),4);

    }
    @Test
    public void manhattanPuzzle4Test(){
        Board board = LoadBoard("puzzle04.txt");
        Assert.assertEquals(board.manhattan(),4);

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
