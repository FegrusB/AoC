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
                    if(board.getPos(pos) == 0){if(board.getChanged(pos)){continue;}}
                    board.incPos(pos);
                    if( board.getPos(pos) > 9){ count = count +  flash(board,pos);}

                }

            }


        }
        System.out.println(count);
    }
    public static int flash(Board11 board, int[] pos){

        int countF = 1;
        board.zeroPos(pos);
        board.setChanged(pos);

        for(int xD = -1; xD < 2; xD ++){
            for(int yD = -1; yD < 2; yD++){
                int[] newPos = {pos[0] + xD,pos[1]+yD};
                int val = board.getPos(newPos);
                if(val == -1 || (val == 0 && board.getChanged(newPos) )){continue;}
                board.incPos(newPos);
                if (board.getPos(newPos)>9){board.zeroPos(newPos); countF += flash(board,newPos);}
            }

        }
        return countF;
    }
    public static boolean checkAll(Board11 board){
        boolean flashed = true;
        for(int x = 1; x < xBounds+1; x++){
            for(int y = 1; y<yBounds+1;y++){
                if(!board.getChanged(new int[] {x,y})){ flashed = false;}
            }
        }
        return flashed;
    }
}
class Board11 {

    private final int[][] board;
    private final boolean[][] changed;

    public Board11(int xBounds, int yBounds){

        this.board = boardWithBoundaries.build("2021-11.txt",xBounds,yBounds, -1);
        this.changed = new boolean[xBounds][yBounds];
    }

    public int getPos(int[] pos) {return board[pos[0]][pos[1]];}
    //public void setPos(int valIn, int[] pos) {this.board[pos[0]][pos[1]] = valIn;}
    public void incPos(int[] pos){this.board[pos[0]][pos[1]]++;}
    public void zeroPos(int[] pos){this.board[pos[0]][pos[1]] = 0;}
    public void resetChanged(){
        for(int i = 0; i < Prob11.xBounds; i ++)Arrays.fill(this.changed[i],false);
    }
    public void setChanged(int[] pos){this.changed[pos[0] - 1][pos[1] - 1] = true;}
    public boolean getChanged(int[] pos){return this.changed[pos[0] - 1][pos[1] -1];}
}
