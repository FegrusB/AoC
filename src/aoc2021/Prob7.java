package aoc2021;

import java.util.Arrays;
import java.util.Scanner;

public class Prob7 {

    public static void main(String[] args) {

        //Read in starting crab positions to int array. Then quick sort.
        Scanner myScanner = GetScanner.get("2021-7.txt");
        int[] crabs = Arrays.stream(myScanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        quicksort(crabs,0,crabs.length-1);

        System.out.println(play1(crabs));
        System.out.println(play2(crabs));

    }

    public  static int play1(int[] inArr){
        //goal position is mode of values
        int endPos = inArr[(inArr.length/2)];
        int totFuel = 0;

        //sum difference between each pos and endPos. Return as total fuel used.
        for(int pos : inArr){
            if(pos < endPos){totFuel += endPos-pos;}
            else { totFuel += pos - endPos;}
        }
        return totFuel;
    }

    public static long play2(int[] inArr){

        //calculate, then round mean position. This is the goal (± 0.5) for part 2
        double mean;
        int tot = 0;
        for ( int pos: inArr) {tot += pos;}
        mean = tot / (float) inArr.length;
        int goalPos = (int) Math.round(mean);

        //calculate fuel used to reach each pos and output/return
        System.out.println(fuelUsedItt(inArr,goalPos - 1));
        return fuelUsedTri(inArr,goalPos - 1);

    }

    public static long fuelUsedItt(int[] inArr, int goalPos){
        //initial solve. calculate fuel used to reach goal poss iteratively for each initial crab.
        long fuelUsed = 0;

        for( int pos : inArr){
            int d;
            int stepCost= 1;

            //work out if step is + or - for loop.
            if (pos < goalPos){d = 1;}
            else              {d = -1;}

            //until goal pos reached, increment pos with d. add stepCost to total used then ++
            while (pos != goalPos){
                pos += d;
                fuelUsed += stepCost;
                stepCost++;
            }
        }
        return fuelUsed;
    }

    public static long fuelUsedTri(int[] inArr, int goalPos){
        //for each initial pos the fuel needed is the triangle number of the difference between the positions.

        long fuelUsed = 0;

        // for each pos, calculate difference(n), then triangle number of n, then sum. return final value
        for( int pos : inArr){
            int n;

            if (pos < goalPos){ n = goalPos - pos;}
            else              {n = pos  - goalPos;}

            fuelUsed += (long) n * (n + 1 ) / 2;
        }
        return fuelUsed;
    }
    public static void quicksort(int[] arrIn, int first, int last){
        //puzzle needs a sorted array. Implemented a quick sort for fun/practice

        //if there are more items to sort
        if (first<last){

            // sort list and returns the partition to be used for next recursion.
            int partitionIndex = partition(arrIn,first,last);

            //recursively call using partition
            quicksort(arrIn, first, partitionIndex-1);
            quicksort(arrIn, partitionIndex + 1, last);
        }

    }
    public static int partition(int[] arrIn, int first, int last){
        //take the last element as pivot.
        int pivot = arrIn[last];
        int i = first - 1;

        // for each element in array, if bigger than pivot, move one index up.
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

        //return position of last swapped item as pivot for next recursion.
        return i + 1;

    }

}
