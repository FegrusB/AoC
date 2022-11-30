package aoc2021;

import java.util.Arrays;
import java.util.Scanner;

public class Prob7 {

    public static void main(String[] args) {

        //Read in starting fish to arraylist
        Scanner myScanner = GetScanner.get("2021-7.txt");
        int[] crabs = Arrays.stream(myScanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();

        int[] sortedCrabs = quicksort(crabs,0,crabs.length-1);


        System.out.println(play1(sortedCrabs));
        System.out.println(play2(sortedCrabs));

    }

    public  static int play1(int[] inArr){

        int endPos = inArr[(inArr.length/2)];
        int totFuel = 0;

        for(int pos : inArr){
            if(pos < endPos){totFuel += endPos-pos;}
            else { totFuel += pos - endPos;}
        }
        return totFuel;
    }

    public static long play2(int[] inArr){

        double avgF;
        int tot = 0;


        for ( int pos: inArr) {tot += pos;}
        avgF = tot / (float) inArr.length;

        int goalPos = (int) Math.round(avgF);

        return fuelUsedItt(inArr,goalPos);

    }

    public static long fuelUsedItt(int[] inArr, int goalPos){

        long fuelUsed = 0;

        for( int pos : inArr){


            int d;
            int stepCost= 1;

            if (pos < goalPos){d = 1;}
            else              {d = -1;}

            while (pos != goalPos - 1){

                pos += d;

                fuelUsed += stepCost;
                stepCost++;

            }

        }

        return fuelUsed;

    }
    public static int[] quicksort(int[] arrIn, int first, int last){

        if (first<last){

            int pivot = getPivot(arrIn,first,last);

            quicksort(arrIn, first, pivot-1);
            quicksort(arrIn, pivot + 1, last);
        }
        return arrIn;
    }

    public static int getPivot(int[] arrIn, int first, int last){

        int pivot = arrIn[last];
        int i = first - 1;

        for(int x = first; x < last; x++){
            if (arrIn[x] < pivot){
                i++;

                int swap = arrIn[i];
                arrIn[i] = arrIn[x];
                arrIn[x] = swap;
            }

        }

        int swap = arrIn[i+1];
        arrIn[i+1] = arrIn[last];
        arrIn[last] = swap;

        return i + 1;

    }

}
