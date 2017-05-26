import edu.princeton.cs.algs4.Stack;

/**
 * Created by rsraloha on 5/23/17.
 */

//Hours
    //2 setting up solution understanding problem
    //4 coding intial methods, researching A*


public class Board {
    private int[][] _tiles;
    private int _n;

    public Board(int[][] blocks) {

        _n = blocks.length;
        _tiles = new int[_n][];
        for (int i = 0; i < _n; i++) {
            _tiles[i] = blocks[i].clone();
        }
    }

    public int dimension() {
        return _n;
    }

    public int hamming() {
        int count = 0;
        for (int y = 0; y < _tiles.length; y++) {
            for (int x = 0; x < _tiles.length; x++) {
                int testValue;
                if (y <= 0) {
                    testValue = x + y + 1;
                } else {
                    testValue = (y * _tiles.length) + x + 1;
                }

                if(x == _n - 1 && y == _n -1)
                    testValue = 0;

                int tempValue = _tiles[y][x];
                if (tempValue != testValue) {
                    count++;
                }

            }
        }
        return count;
    }

    public int manhattan() {

        int manhattan = 0;
        for (int y = 0; y < _n; y++) {
            for (int x = 0; x < _n; x++) {
                int testValue = 0;
                if (y <= 0) {
                    testValue = x + y + 1;
                } else {
                    testValue = (y * _n) + x + 1;
                }
                if(x == _n - 1 && y == _n -1)
                    testValue = 0;

                int tempValue = _tiles[y][x];

                if (tempValue < testValue)
                    manhattan += testValue - tempValue;
                else
                    manhattan += tempValue - testValue;
            }
        }

        return manhattan;
    }

    public boolean isGoal() {
        return this.hamming() == 0;
    }

    private int[][] copy()
    {
        int[][] temp = new int[_n][];
        for (int i = 0; i < _n; i++) {
            temp[i] = _tiles[i].clone();
        }
        return temp;
    }

    public Board twin() {

        int[][] temp = copy();
        swap2(temp);
        return new Board(temp);

    }

    private void swap2(int[][] temp){

        Integer prev = null;
        for(int y = 0; y < temp.length;y++)
        {
            for(int x = 0; x < temp.length;x++){

                if(prev == null)
                {
                    prev = temp[y][x];
                    continue;
                }

                int prevX = x - 1;
                int prevY = y;

                if(prevX < 0 && prevY > 0)
                {
                    prevX = temp.length - 1;
                    prevY = y - 1;
                    prev = temp[prevY][prevX];
                }

                int cur = temp[y][x];
                if(prev != 0 && cur != 0)
                {
                    int t = cur;
                    temp[y][x] = prev;
                    temp[prevY][prevX] = cur;
                    return;
                }
            }

        }

    }

    public boolean equals(Object other){
        if(this == other)
            return true;
        if(other == null)
            return false;
        if (other.getClass() != this.getClass()) return false;
        Board that = (Board) other;

        if(this.dimension() == that.dimension() &&
                this.hamming() == that.hamming() &&
                this.manhattan() == that.manhattan()
                )
            return true;

        return false;

    }

    private class Point {
        public int x;
        public int y;
        public Point(int tempX, int tempY){
            x = tempX;
            y = tempY;
        }
    }

    public Iterable<Board> neighbors() {

        Stack<Board> boards = new Stack<Board>();
        Point p = getBlankIndex();
        //move number to left
        if(p.x > 0) {
            int[][] temp = copy();
            int val = temp[p.y][p.x - 1];
            temp[p.y][p.x - 1] = 0;
            temp[p.y][p.x] = val;
            boards.push(new Board(temp));
        }
        //move number to right
        if(p.x < _n - 1)
        {
            int[][] temp = copy();
            int val = temp[p.y][p.x + 1];
            temp[p.y][p.x + 1] = 0;
            temp[p.y][p.x] = val;
            boards.push(new Board(temp));
        }
        //move up
        if(p.y > 0)
        {
            int[][] temp = copy();
            int val = temp[p.y - 1][p.x];
            temp[p.y - 1][p.x] = 0;
            temp[p.y][p.x] = val;
            boards.push(new Board(temp));
        }
        //move up
        if(p.y < _n - 1)
        {
            int[][] temp = copy();
            int val = temp[p.y + 1][p.x];
            temp[p.y + 1][p.x] = 0;
            temp[p.y][p.x] = val;
            boards.push(new Board(temp));
        }

        return boards;

    }

    private Point getBlankIndex(){
        Point p = null;
        for(int y = 0; y < _n; y++){
            for(int x = 0; x < _n; x++){
                if(_tiles[y][x] == 0)
                {
                    p = new Point(x,y);
                    return p;
                }
            }
        }
        return p;

    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        int n = this.dimension();
        s.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", _tiles[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static void main(String[] args) {

    }
}
