package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class dayFour {

    public static void main(String[] args){

        Scanner myScanner = GetScanner.get("Day-4");
        ArrayList<ArrayList<Integer>> pairs = new ArrayList<>();

        while (myScanner.hasNext()){

            String line = myScanner.nextLine();
            String[] lineSplit = line.split(",");

            ArrayList<Integer> pair = new ArrayList<>();
            String[] pairSplit1 = lineSplit[0].split("-");
            String[] pairSplit2 = lineSplit[1].split("-");

            pair.add(Integer.valueOf(pairSplit1[0]));
            pair.add(Integer.valueOf(pairSplit1[1]));

            pair.add(Integer.valueOf(pairSplit2[0]));
            pair.add(Integer.valueOf(pairSplit2[1]));

            pairs.add(pair);

        }

        int i = 0;
        for (ArrayList<Integer> pair:pairs){
            if(pair.get(0) <= pair.get(2) && pair.get(1) >= pair.get(3) ){i++;}
            else if(pair.get(2) <= pair.get(0) && pair.get(3) >= pair.get(1) ){i++;}
        }
        System.out.println("There are: " + i + " pairs that overlap totally.");

        int x = 0;
        for (ArrayList<Integer> pair:pairs){
            boolean overlap1 = checkSet(pair.get(0),pair.get(1),pair.get(2),pair.get(3));
            boolean overlap2 = checkSet(pair.get(2),pair.get(3),pair.get(0),pair.get(1));
            if (overlap1 || overlap2){x++;}
        }
        System.out.println("There are: " + x + " pairs that overlap at all.");
    }
    public static boolean checkSet(int bound1, int bound2,int check1, int check2){
        HashSet<Integer> set = new HashSet<>();
        boolean contained = false;
        for(int i = bound1 -1; i < bound2; i++){set.add(i);}
        for (int i = check1 - 1; i < check2; i++){if(set.contains(i)){contained = true;}}
        return contained;
    }
}
