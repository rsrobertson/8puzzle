import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import java.util.Comparator;

/**
 * Created by rsraloha on 5/23/17.
 */
public class Solver {

    public Solver(Board initial) {

        Board twin = initial.twin();
        Stack<Board> test = new Stack();
        Stack<Board> gameTree = new Stack();
        MinPQ<Board> pq = new MinPQ(new ManhattanComparator());
        MinPQ<Board> pqTwin = new MinPQ(new ManhattanComparator());
        pq.insert(initial);

        pq.insert(initial);
        pqTwin.insert(twin);
        while (!pq.isEmpty()) {
            Board node = pq.delMin();

            gameTree.push(node);
            if (node.isGoal()) {
                return;
            }

            for (Board b : node.neighbors()) {
                pq.insert(b);
            }

        }

    }

    public boolean isSolvable() {
        return true;
    }

    public int moves() {
        return 0;
    }

    public Iterable<Board> solution() {
        return new Stack<Board>();
    }

    public static void main(String[] args) {


    }

    private class ManhattanComparator implements Comparator<Board> {
        @Override
        public int compare(Board a, Board b) {
            return Integer.compare(a.manhattan(), b.manhattan());

        }
    }
    private class HammingComparator implements Comparator<Board> {
        @Override
        public int compare(Board a, Board b) {
            return Integer.compare(a.hamming(), b.hamming());

        }
    }

}
