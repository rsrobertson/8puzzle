/**
 * Created by rsraloha on 5/23/17.
 */
import edu.princeton.cs.algs4.*;

import java.io.File;

public class TestClient {

    public static void main(String[] args) {
        String home = System.getProperty("user.home");
        File f = new File(home + "/Documents/AlgorithmsPart1/8puzzle/8puzzle/input10.txt");
        if (!f.exists()) {

            return;
        }
        In in = new In(f);
        // create initial board from file
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
