package aoc2021;

import java.util.Arrays;
import java.util.Scanner;

public class Prob11 {

    static final int xBounds = 10;
    static final int yBounds = 10;

    public static void main(String[] args){

        //Read in starting nums into int array.
        Scanner myScanner = GetScanner.get("2021-11a.txt");
        int[][] boardIn = new int[xBounds][yBounds];
        int count = 0;
        while (myScanner.hasNext()) {
            int[] row = Arrays.stream(myScanner.nextLine().split("")).mapToInt(Integer::parseInt).toArray();
            boardIn[count] = row;
            count ++;
        }

        System.out.println();


    }




}
