package aoc2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Prob9 {

    static final int xBounds = 10;
    static final int yBounds = 5;

    public static void main(String[] args){

        //Read in starting nums into int array.
        Scanner myScanner = GetScanner.get("2021-9a.txt");
        int[][] board = new int[xBounds+2][yBounds+2];
        int[][] boardIn = new int[xBounds][yBounds];
        boolean[][] basinBool = new boolean[xBounds+2][yBounds+2];
        int count = 0;
        while (myScanner.hasNext()) {
            int[] row = Arrays.stream(myScanner.nextLine().split("")).mapToInt(Integer::parseInt).toArray();
            boardIn[count] = row;
            count ++;
        }
        for(int[] row : board){Arrays.fill(row,10);}
        for (int i = 1; i < xBounds+1;i++) {
            System.arraycopy(boardIn[i - 1], 0, board[i], 1, yBounds);
        }

        for (int x  =1; x<xBounds + 2;x++) {
            for (int y = 1; y < yBounds + 2; y++) {
                if(board[x][y]>9){basinBool[x][y] = true;}
            }
        }

        ArrayList<Integer> basins = new ArrayList<>();
        for (int x  =1; x<xBounds + 1;x++){
            for(int y=1; y<yBounds + 1;y++){

                if((board[x][y-1] > board[x][y])&&(board[x][y+1] > board[x][y])&&(board[x-1][y] > board[x][y])&&(board[x+1][y] > board[x][y])){



                }

            }
        }

        int sum = 0;

        System.out.println(sum);

    }

}
