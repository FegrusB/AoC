package aoc2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Prob8 {

    static final String alpha = "abcdefg";
    public static void main(String[] args) {

        //Read in starting displays into two arraylists.
        Scanner myScanner = GetScanner.get("2021-8.txt");
        ArrayList<String[]> digits = new ArrayList<>();
        ArrayList<String[]> displays = new ArrayList<>();
        int solution = 0;

        while (myScanner.hasNext()) {
            String[] inLine = myScanner.nextLine().split("\\| ");
            digits.add(inLine[0].split(" "));
            displays.add(inLine[1].split(" "));
        }

        int count = 0;
        while(count < digits.size()){
            solution += (solve(digits.get(count),displays.get(count)));
            count++;
        }

        System.out.println(solution);

    }


    private static int solve(String[] digits, String[] display){

        ArrayList<Integer> mappedDisplay = new ArrayList<>();
        HashMap<Character,Character> wireMap = buildMap(digits);

        for(String digit:display){
            StringBuilder outDigit = new StringBuilder();
            for(int i = 0; i < digit.length();i++){outDigit.append(wireMap.get(digit.charAt(i)));}
            mappedDisplay.add(getValue(outDigit.toString()));
        }

        int outInt = 0;
        outInt += mappedDisplay.get(0) * 1000;
        outInt += mappedDisplay.get(1) * 100;
        outInt += mappedDisplay.get(2) * 10;
        outInt += mappedDisplay.get(3);

        return outInt;

    }

    private static int getValue(String digit){

        char[] chars = digit.toCharArray();
        Arrays.sort(chars);
        String sortedDigit = new String(chars);

        switch(sortedDigit){
            case "abcefg" -> {return 0;}
            case "cf" ->{ return 1;}
            case "acdeg" ->{ return 2;}
            case "acdfg" ->{ return 3;}
            case "bcdf" -> {return 4;}
            case "abdfg" ->{ return 5;}
            case "abdefg" ->{ return 6;}
            case "acf" ->{ return 7;}
            case "abcdefg" ->{return 8;}
            case "abcdfg" ->{ return 9;}
        }

        return 0;

    }
    private static HashMap<Character, Character> buildMap(String[] digits){

        int[] wireCount = new int[7];
        HashMap<Character, Character> wireSolves = new HashMap<>();

        //count occurrences of each wire in digits.
        for (String digit : digits) {for (int i = 0; i < 7; i++) {if (digit.indexOf(alpha.charAt(i)) != -1) {wireCount[i]++;}}}

        for (int x = 0; x < 7; x ++) {

            switch (wireCount[x]) {
                case 4 -> wireSolves.put(alpha.charAt(x), 'e');
                case 6 -> wireSolves.put(alpha.charAt(x), 'b');
                case 7 -> {
                    boolean set = false;
                    for (String digit : digits) {
                        if ((digit.length() == 4) && digit.contains("" + alpha.charAt(x))) {set = true;break;}
                    }
                    if (set) {
                        wireSolves.put(alpha.charAt(x), 'd');
                    } else {
                        wireSolves.put(alpha.charAt(x), 'g');
                    }
                }
                case 8 -> {
                    boolean set = false;
                    for (String digit : digits) {
                         if ((digit.length() == 2) && digit.contains("" + alpha.charAt(x))){ set = true;break;}
                    }
                    if (set) {
                        wireSolves.put(alpha.charAt(x), 'c');
                    } else {
                        wireSolves.put(alpha.charAt(x), 'a');
                    }
                }
                case 9 -> wireSolves.put(alpha.charAt(x), 'f');
            }
        }

        return wireSolves;
    }
}


