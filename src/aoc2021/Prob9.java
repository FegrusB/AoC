package aoc2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Prob9 {

    static final int xBounds = 5;
    static final int yBounds = 10;

    public static void main(String[] args){

        //Read in starting nums into int array.
        Scanner myScanner = GetScanner.get("2021-9a.txt");
        int[][] board = new int[xBounds][yBounds];
        int count = 0;
        while (myScanner.hasNext()) {
            int[] row = Arrays.stream(myScanner.nextLine().split("")).mapToInt(Integer::parseInt).toArray();
            board[count] = row;
            count ++;
        }



    }
}
