package aoc2021;


import java.util.Arrays;

public class Prob11 {

    static final int xBounds = 10;
    static final int yBounds = 10;

    public static void main(String[] args){

        //Build board of ints with boundary of 0s
        Board11 board =  new Board11(xBounds,yBounds);
        int steps = 500;

        int count = 0;
        for(int i = 0; i < steps ; i++){
            //if all have flashed print step
            if(checkAll(board)){System.out.println(i);}
            board.resetChanged();

            for(int x = 1; x < xBounds + 1; x ++){
                for( int y = 1; y < yBounds+1; y++){
                    int[] pos = {x, y};
                    //if flashed already continue.
                    if(board.getChanged(pos)){continue;}
                    board.incPos(pos);
                    if( board.getPos(pos) > 9){ count = count +  flash(board,pos);}
                }
            }
        }
        System.out.println(count);
    }
    public static int flash(Board11 board, int[] pos){

        //zero and count start flash
        int countF = 1;
        board.zeroPos(pos);
        board.setChanged(pos);

        //loop through neighbors and inc. Call flash for any that go over 9.
        for(int xD = -1; xD < 2; xD ++){
            for(int yD = -1; yD < 2; yD++){
                int[] newPos = {pos[0] + xD,pos[1]+yD};
                int val = board.getPos(newPos);
                //if in boundary (-1) or already flashed, skip
                if(val == -1 || board.getChanged(newPos)){continue;}
                board.incPos(newPos);
                if (board.getPos(newPos)>9){board.zeroPos(newPos); countF += flash(board,newPos);}
            }

        }
        return countF;
    }
    public static boolean checkAll(Board11 board){
        //loop through all, if any have not flashed return false, else return true
        boolean flashed = true;
        for(int x = 1; x < xBounds+1; x++){for(int y = 1; y<yBounds+1;y++){if(!board.getChanged(new int[] {x,y})){ flashed = false;}}}
        return flashed;
    }
}
class Board11 {
//simple class to store board state, with getters and setters.
    private final int[][] board;
    private final boolean[][] changed;

    public Board11(int xBounds, int yBounds){
        this.board = boardWithBoundaries.build("2021-11.txt",xBounds,yBounds, -1);
        this.changed = new boolean[xBounds][yBounds];
    }

    public int getPos(int[] pos) {return board[pos[0]][pos[1]];}
    public void incPos(int[] pos){this.board[pos[0]][pos[1]]++;}
    public void zeroPos(int[] pos){this.board[pos[0]][pos[1]] = 0;}
   //resets all vals in changed to false
    public void resetChanged(){for(int i = 0; i < Prob11.xBounds; i ++)Arrays.fill(this.changed[i],false);}
    public void setChanged(int[] pos){this.changed[pos[0] - 1][pos[1] - 1] = true;}
    public boolean getChanged(int[] pos){return this.changed[pos[0] - 1][pos[1] -1];}
}
