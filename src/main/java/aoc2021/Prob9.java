package aoc2021;

import java.util.ArrayList;
import java.util.Collections;

public class Prob9 {

    static final int xBounds = 100;
    static final int yBounds = 100;

    public static void main(String[] args){

        //build board of ints with boundary of 10s
        int[][] board = boardWithBoundaries.build("2021-9.txt",xBounds,yBounds,10);


        //loop through all points finding low points, if a bottom point, send to searchStart to find size of basin. Add returned int to basins.
        ArrayList<Integer> basins = new ArrayList<>();
        for (int x  =1; x<xBounds + 1;x++){
            for(int y=1; y<yBounds + 1;y++){

                if((    board[x][y-1] > board[x][y])
                        &&(board[x][y+1] > board[x][y])
                        &&(board[x-1][y] > board[x][y])
                        &&(board[x+1][y] > board[x][y])){basins.add(searchStart(new int[] {x,y},board));
                }
            }
        }

        //multiply three biggest basins and print result
        basins.sort(Collections.reverseOrder());
        System.out.println(basins.get(0) * basins.get(1) * basins.get(2));

    }


    public static int searchStart(int[] pos, int[][] board){

        //new searcher, set initial position and count
        searcher searcher = new searcher(xBounds + 2,yBounds + 2);
        searcher.setSearched(pos);
        int count = 1;

        //start a searchR search going in each direction.
        count += searchR(searcher,pos,board,true,true);
        count += searchR(searcher,pos,board,false,true);
        count += searchR(searcher,pos,board,true,false);
        count += searchR(searcher,pos,board,false,false);

        return count;
    }

    public static int searchR(searcher searcher, int[] posIn, int[][]board, boolean xYBool, boolean posNegBool){

        //initial setup. Get direction from xYBool and posNegBool, use to set starting point.
        int[] pos = posIn.clone();
        int xY = xYBool ? 1:0;
        int posNeg = posNegBool ? 1:-1;
        int count = 0;
        pos[xY] += posNeg;

        //if pos < 9, in bounds and not already searched, loop
        while(board[pos[0]][pos[1]] < 9 && !(searcher.getSearched(pos)) && (pos[0]<xBounds+1 && pos[1]<yBounds+1)){

            searcher.setSearched(pos);
            count ++;

            //send searchRs in both opposite xY directions.
            count += searchR(searcher,pos,board, !xYBool, true);
            count += searchR(searcher,pos,board, !xYBool, false);
            pos[xY] += posNeg;

        }

        return count;
    }



}

class searcher{
    //simple class containing searched bool array. Used to persist array over searchRs
    boolean[][] searched;
    public searcher(int xSize, int ySize){this.searched = new boolean[xSize][ySize];}
    public void setSearched(int[] pos){this.searched[pos[0]][pos[1]] = true;}
    public boolean getSearched(int[]pos){return this.searched[pos[0]][pos[1]];}

}
