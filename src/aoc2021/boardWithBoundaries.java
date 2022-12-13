package aoc2021;

import java.util.Arrays;
import java.util.Scanner;

public class boardWithBoundaries {

    public static int[][] build(final String file, final int xBounds, final int yBounds,final int boundary){
        //code to build 2d array of ints with a boundary. Moved to class for reuse.

        //start scanner and read in initial array of ints
        Scanner myScanner = GetScanner.get(file);
        int[][] boardIn = new int[xBounds][yBounds];
        int count = 0;
        while (myScanner.hasNext()) {
            int[] row = Arrays.stream(myScanner.nextLine().split("")).mapToInt(Integer::parseInt).toArray();
            boardIn[count] = row;
            count ++;
        }

        //add boundary of boundary around initial board.
        int[][] board = new int[xBounds+2][yBounds+2];
        for(int[] row : board){Arrays.fill(row,boundary);}
        for (int i = 1; i < xBounds+1;i++) {System.arraycopy(boardIn[i - 1], 0, board[i], 1, yBounds);}

        return board;

    }
}
