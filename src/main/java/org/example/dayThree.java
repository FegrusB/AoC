package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class dayThree {


    public static void main(String[] args) {

        Scanner myScanner = GetScanner.get("Day-3");

        ArrayList<ArrayList<String>> rucksacks = new ArrayList<>();
        ArrayList<ArrayList<String>> groups = new ArrayList<>();
        ArrayList<String> group = new ArrayList<>();

        int i = 0;
        while (myScanner.hasNext()) {
            String line = myScanner.nextLine();
            group.add(line);

            ArrayList<String> lineSplit = new ArrayList<>();
            lineSplit.add(line.substring(0, line.length() / 2));
            lineSplit.add(line.substring(line.length() / 2));
            rucksacks.add(lineSplit);
            if (i == 2){
                groups.add(group);
                group = new ArrayList<>();
                i = 0;
            } else {i++;}
        }

        System.out.println("The sum of the priorities for part one are: " + getPriorities(getDuplicates(rucksacks)));
        System.out.println("The sum of the priorities for part two are: " + getPriorities(getBadges(groups)));

    }

    public static ArrayList<Character> getBadges(ArrayList<ArrayList<String>> groups){

        String alph = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        ArrayList<Character> badges = new ArrayList<>();

        for (ArrayList<String> group: groups){
            ArrayList<HashSet<Character>> sets = new ArrayList<>();
            int[] count = new int[52];
            for (String rucksack: group){
                HashSet<Character> set = new HashSet<>();
                for(Character ch: rucksack.toCharArray()){set.add(ch);}
                sets.add(set);


            }
            for (HashSet<Character> set: sets){
                for (int i = 0; i < 52; i++){
                    if (set.contains(alph.charAt(i))){
                        count[i]++;
                    }
                }
            }
            for (int i = 0; i < 52; i++){
                if(count[i]>2){badges.add(alph.charAt(i));}
            }
        }

        return badges;
    }

    public static ArrayList<Character> getDuplicates(ArrayList<ArrayList<String>> rucksacks){
        ArrayList<Character> duplicates = new ArrayList<>();

        for (ArrayList<String> rucksack : rucksacks) {
            ArrayList<Character> added = new ArrayList<>();
            for (Character ch : rucksack.get(0).toCharArray()) {
                if (rucksack.get(1).contains("" + ch) && !added.contains(ch)) {
                    added.add(ch);
                    duplicates.add(ch);
                }
            }
        }
        return duplicates;
    }
    public static int getPriorities(ArrayList<Character> characters){

            HashMap<Character,Integer> map = new HashMap<>();

            for(int i = 97;i<123;i++){map.put((char) i, i - 96);}
            for(int i = 65;i<91;i++){map.put((char) i, i - 38);}

            int i = 0;
            for (Character ch: characters){
                i += map.get(ch);
            }

            return i;
    }

}
