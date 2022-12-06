package aoc2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Prob9 {

    static final int xBounds = 100;
    static final int yBounds = 100;

    public static void main(String[] args){

        //Read in starting nums into int array.
        Scanner myScanner = GetScanner.get("2021-9.txt");
        int[][] board = new int[xBounds+2][yBounds+2];
        int[][] boardIn = new int[xBounds][yBounds];
        int count = 0;
        while (myScanner.hasNext()) {
            int[] row = Arrays.stream(myScanner.nextLine().split("")).mapToInt(Integer::parseInt).toArray();
            boardIn[count] = row;
            count ++;
        }

        //add boundary of 10s for edges
        for(int[] row : board){Arrays.fill(row,10);}
        for (int i = 1; i < xBounds+1;i++) {
            System.arraycopy(boardIn[i - 1], 0, board[i], 1, yBounds);
        }



        ArrayList<Integer> basins = new ArrayList<>();
        for (int x  =1; x<xBounds + 1;x++){
            for(int y=1; y<yBounds + 1;y++){

                if((board[x][y-1] > board[x][y])&&(board[x][y+1] > board[x][y])&&(board[x-1][y] > board[x][y])&&(board[x+1][y] > board[x][y])){

                    basins.add(searchStart(new int[] {x,y},board));

                }

            }
        }

        basins.sort(Collections.reverseOrder());
        int sum = basins.get(0) * basins.get(1) * basins.get(2);
        System.out.println(sum);

    }


    public static int searchStart(int[] pos, int[][] board){

        searcher searcher = new searcher(xBounds + 2,yBounds + 2);

        searcher.setSearched(pos);

        int count = 1;

        count += searchR(searcher,pos,board,true,true);
        count += searchR(searcher,pos,board,false,true);
        count += searchR(searcher,pos,board,true,false);
        count += searchR(searcher,pos,board,false,false);

        return count;
    }

    public static int searchR(searcher searcher, int[] posIn, int[][]board, boolean xYBool, boolean posNegBool){

        int[] pos = posIn.clone();
        int xY = xYBool ? 1:0;
        int posNeg = posNegBool ? 1:-1;
        int count = 0;

        pos[xY] += posNeg;

        while(board[pos[0]][pos[1]] < 9 && !(searcher.getSearched(pos)) && (pos[0]<xBounds+1 && pos[1]<yBounds+1)){

            searcher.setSearched(pos);
            count ++;

            count += searchR(searcher,pos,board, !xYBool, true);
            count += searchR(searcher,pos,board, !xYBool, false);

            pos[xY] += posNeg;

        }

        return count;
    }



}

class searcher{

    boolean[][] searched;

    public searcher(int xSize, int ySize){

        this.searched = new boolean[xSize][ySize];

    }
    public void setSearched(int[] pos){this.searched[pos[0]][pos[1]] = true;}
    public boolean getSearched(int[]pos){return this.searched[pos[0]][pos[1]];}

}
