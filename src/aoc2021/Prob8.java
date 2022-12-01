package aoc2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Prob8 {
    public static void main(String[] args) {

        //Read in starting displays into two arraylists.
        Scanner myScanner = GetScanner.get("2021-8.txt");
        ArrayList<String[]> nums = new ArrayList<>();
        ArrayList<String[]> displays = new ArrayList<>();
        while (myScanner.hasNext()) {
            String[] inLine = myScanner.nextLine().split("\\|");
            nums.add(inLine[0].split(" "));
            displays.add(inLine[1].split(" "));
        }

        int count = 0;
        HashSet<Integer> checkMap = new HashSet<>(Arrays.asList(2,3, 4, 7));


        for(String[] line : displays){

            for (String digit : line){

                if(checkMap.contains(digit.length())){count++;}

            }

        }

        System.out.println(count);

    }
}

