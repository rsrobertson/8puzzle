import edu.princeton.cs.algs4.Stack;
import java.util.Arrays;

/**
 * Created by rsraloha on 5/23/17.
 */

//Hours
    //2 setting up solution understanding problem
    //4 coding intial methods, researching A*
    //4 coding board api
    //2 fixing manhattan and hamming


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
                    break;

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

                int tempValue = _tiles[y][x];

                if(tempValue == 0)
                    continue;

                if(tempValue != testValue){

                    Point p = getProperCoordinates(tempValue);
                    int distanceX = p.x - x;
                    int distanceY = p.y - y;
                    if(distanceX < 0)
                        distanceX = distanceX * -1;
                    if(distanceY < 0)
                        distanceY = distanceY * -1;
                    manhattan += distanceX;
                    manhattan += distanceY;
                }
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

        for(int y = 0; y < temp.length;y++)
        {
            for(int x = 0; x < temp.length;x++){

                int currentValue = temp[y][x];
                if(currentValue != 0)
                {
                    //try four neighbors till you find a non zero value then perform swap
                    //try right
                    if(x < temp.length - 1)
                    {
                        int swapValue = temp[y][x + 1];
                        if(swapValue != 0){
                            temp[y][x] = swapValue;
                            temp[y][x + 1] = currentValue;
                            return;
                        }
                    }
                    //try left
                    if(x > 0){
                        int swapValue = temp[y][x - 1];
                        if(swapValue != 0){
                            temp[y][x] = swapValue;
                            temp[y][x - 1] = currentValue;
                            return;
                        }
                    }
                    //try below
                    if(y < temp.length - 1){
                        int swapValue = temp[y + 1][x];
                        if(swapValue != 0){
                            temp[y][x] = swapValue;
                            temp[y + 1][x] = currentValue;
                            return;
                        }
                    }
                    //try above
                    if(y > 0){
                        int swapValue = temp[y - 1][x];
                        if(swapValue != 0){
                            temp[y][x] = swapValue;
                            temp[y - 1][x] = currentValue;
                            return;
                        }
                    }

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

        return Arrays.deepEquals(this._tiles,that._tiles);

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

    private Point getProperCoordinates(int a){
        int x,y;
        if(a < _n)
        {
            x = a - 1;
            y = 0;
            return new Point(x,y);
        }

        y = a / _n;
        if( a % _n != 0)
            y++;

        x = a % _n;
        if(x == 0)
            x = _n;

        y--;
        x--;

        return new Point(x,y);

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
