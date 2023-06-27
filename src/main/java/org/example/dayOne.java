package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class dayOne {
    public static void main(String[] args){

        Scanner myScanner = GetScanner.get("Day-1");
        ArrayList<Integer> calories = new ArrayList<>();

        int current = 0;

        while (myScanner.hasNext()){
            String string = myScanner.nextLine();
            if(string.equals("")){
                calories.add(current);
                current = 0;
            } else {
                current += Integer.parseInt(string);
            }
        }

        calories.sort(Collections.reverseOrder());
        int topThree = calories.get(0) + calories.get(1) + calories.get(2);

        System.out.println("The greatest number of calories carried by a single elf: " + calories.get(0));
        System.out.println("The number of calories carried by the top three elves: " + topThree);

    }
}
