package aoc2021;


public class Prob11 {

    static final int xBounds = 10;
    static final int yBounds = 10;

    public static void main(String[] args){

        //Build board of ints with boundary of 0s
        board board =  new board(xBounds,yBounds);

        System.out.println();


    }
}
class board{

    private int[][] board;

    public board(int xBounds, int yBounds){

        this.board = boardWithBoundaries.build("2021-11a.txt",xBounds,yBounds,0);

    }

    public int getPos(int[] pos) {
        return board[pos[0]][pos[1]];
    }

    public void setPos(int valIn, int[] pos) {
        this.board[pos[0]][pos[1]] = valIn;
    }
}
