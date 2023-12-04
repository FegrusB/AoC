package aoc2021;

import Common.GetScanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Prob14 {

    public static void main(String args[]){

        Scanner myScanner = GetScanner.get(2021,"2021-14a");
        int numLoops = 40;
        String initString = myScanner.nextLine();

        myScanner.nextLine();

        HashMap<String, String[]> transformations = new HashMap<>();
        HashMap<String, Long> emptyCount = new HashMap<>();
        HashMap<Character, Double> charCount = new HashMap<>();

        while(myScanner.hasNext()){

            String[] s = myScanner.nextLine().split(" -> ");
            transformations.put(s[0], new String[]{s[0].charAt(0) + s[1],s[1]  + s[0].charAt(1)});
            emptyCount.put(s[0], 0L);

            charCount.put(s[0].charAt(0), 0d);
            charCount.put(s[0].charAt(1), 0d);
            charCount.put(s[1].charAt(0), 0d);
        }

        HashMap<String, Long> count =(HashMap<String, Long>) emptyCount.clone();

        charCount.put(initString.charAt(0),0.5);
        charCount.put(initString.charAt(initString.length() - 1),0.5);
        for( int i = 0; i < initString.length() - 1; i += 1){

            String pair = initString.substring(i,i+2);
            long x = count.get(pair);
            count.put(pair,++x);

        }



        for(int i = 0; i < numLoops;i++){

            HashMap<String, Long> additions =(HashMap<String, Long>) emptyCount.clone();

            HashMap<String, Long> finalCount = count;
            count.forEach((k, v) -> {
                if(v != 0){

                    String[] additionKeys = transformations.get(k);
                    additions.put(additionKeys[0],v + additions.get(additionKeys[0]));
                    additions.put(additionKeys[1],v + additions.get(additionKeys[1]));
                }
            });
            count = additions;

        }

        count.forEach((k,v) -> {
            charCount.put(k.charAt(0),(charCount.get(k.charAt(0)) + (v * 0.5)));
            charCount.put(k.charAt(1),(charCount.get(k.charAt(1)) + (v * 0.5)));
        } );

        ArrayList<Double> charCountArr = new ArrayList<>(charCount.values());
        charCountArr.sort(null);

        System.out.println(charCountArr.get(charCountArr.size() - 1)  - charCountArr.get(0));


    }

}
