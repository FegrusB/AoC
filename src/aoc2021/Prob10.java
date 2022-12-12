package aoc2021;

import java.util.*;

public class Prob10 {


    public static void main(String[] args){

        //build Arraylist of strings for input.
        Scanner myScanner = GetScanner.get("2021-10.txt");
        ArrayList<String> lines = new ArrayList<>();
        while(myScanner.hasNext()){lines.add(myScanner.nextLine());}

        //hashmaps/sets for building scores
        final HashSet<Character> leftBraces = new HashSet<>(Arrays.asList('{','[','<','('));
        final HashMap<Character,Integer> corruptScores = new HashMap<>(Map.of('}',1197,']',57,'>',25137,')',3));
        final HashMap<Character,Integer> oppositeScores = new HashMap<>(Map.of('{',3,'[',2,'<',4,'(',1));

        //loop through each character of each puzzle line.
        int sum = 0;
        ArrayList<Long> scores = new ArrayList<>();
        for(String line:lines){
            Stack<Character> stack = new Stack<>();
            boolean corrupt = false;
            for(char c : line.toCharArray()) {

                //add left braces to the stack
                if(leftBraces.contains(c)){ stack.add(c);}
                else{

                    //if right brace pop last from stack, if they don't match then line is corrupted. Get score and break
                    char pop = stack.pop();
                    if ( !( ((c - pop) == 1 )||( (c - pop) == 2))){
                        corrupt = true;
                        sum += corruptScores.get(c);
                        break;}
                }
            }
            //for incomplete lines, take each char remaining on the stack, calculate score, then add score to scores
            if(!corrupt) {
                long score = 0;
                while (!stack.isEmpty()) {
                    char c = stack.pop();
                    score = (score * 5) + oppositeScores.get(c);
                }
                scores.add(score);
            }
        }

        scores.sort(null);
        System.out.println(sum);
        System.out.println(scores.get((scores.size()/2)));

    }



}
