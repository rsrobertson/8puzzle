import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SET;
import java.util.Comparator;
import java.util.HashSet;

/**
 * Created by rsraloha on 5/23/17.
 */
public class Solver {

    private boolean _isSolvable;
    private Queue _gameTree;
    private int _numMoves = -1;

    public Solver(Board initial) {

        Board twin = initial.twin();
        _gameTree = new Queue<Board>();
        MinPQ frontier = new MinPQ(new ManhattanComparator());
        Queue gameTreeTwin = new Queue();
        MinPQ frontierTwin = new MinPQ(new ManhattanComparator());

        GameNode start = new GameNode(initial,0);
        frontier.insert(start);
        GameNode startTwin = new GameNode(twin,0);
        frontierTwin.insert(startTwin);

        while (true) {
            GameNode node = (GameNode)frontier.delMin();
            GameNode nodeTwin = (GameNode)frontierTwin.delMin();
            _numMoves++;

            _gameTree.enqueue(node.board);
            if (node.board.isGoal()) {
                _isSolvable = true;
                break;
            }
            if (nodeTwin.board.isGoal()) {
                _isSolvable = false;
                break;
            }


            for (Board n : node.board.neighbors()) {
                GameNode next = new GameNode(n,node.numMoves + 1);
                frontier.insert(next);
            }
            for (Board n : nodeTwin.board.neighbors()) {
                GameNode next = new GameNode(n,nodeTwin.numMoves + 1);
                frontierTwin.insert(next);
            }

        }

    }

    public boolean isSolvable() {
        return _isSolvable;
    }

    public int moves() {
        return _numMoves;
    }

    public Iterable<Board> solution() {
        return _gameTree;
    }

    public static void main(String[] args) {


    }

    private class GameNode  {
        public GameNode(Board b, int moves){
            board = b;
            manhattan = b.manhattan();
            numMoves = moves;
            priority = manhattan + numMoves;
        }
        int manhattan;
        int numMoves;
        int priority;
        Board board;

        public boolean equals(Object other){
            if(this == other)
                return true;
            if(other == null)
                return false;
            if (other.getClass() != this.getClass()) return false;

            GameNode that = (GameNode) other;
            return this.board.equals(that.board);
        }
    }

    private class ManhattanComparator implements Comparator<GameNode> {
        @Override
        public int compare(GameNode a, GameNode b) {
            return Integer.compare(a.priority, b.priority);

        }
    }

}
