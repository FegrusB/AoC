package aoc2021;

import java.util.*;

public class Prob10 {


    public static void main(String[] args){

        Scanner myScanner = GetScanner.get("2021-10a.txt");
        ArrayList<String> lines = new ArrayList<>();
        while(myScanner.hasNext()){lines.add(myScanner.nextLine());}
        HashSet<Character> leftBraces = new HashSet<>(Arrays.asList('{','[','<','('));
        HashMap<Character,Integer> corruptScores = new HashMap<>(Map.of('}',1197,']',57,'>',25137,')',3));

        int sum = 0;
        for(String line:lines){
            Stack<Character> stack = new Stack<>();

            for(char c : line.toCharArray()) {
                if(leftBraces.contains(c)){ stack.add(c);}
                else{
                    char pop = stack.pop();
                    if ( !( ((c - pop) == 1 )||( (c - pop) == 2))){
                        sum += corruptScores.get(c);
                        break;}
                }
            }
        }
        System.out.println(sum);
    }



}
