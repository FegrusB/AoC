package aoc2021;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Prob18 {

    public static void main(String[] args){
        Scanner myScanner = GetScanner.get("2021-18a");
        ArrayList<ArrayList<Character>> characterArrayArray = new ArrayList<>();

        while (myScanner.hasNextLine()){
            String currString = myScanner.nextLine();
            ArrayList<Character> charArr = currString.chars().mapToObj(c -> (char) c).collect(Collectors.toCollection(ArrayList::new));
            characterArrayArray.add(charArr);
        }

        calculate(characterArrayArray);
    }

    public static void calculate(ArrayList<ArrayList<Character>> numberArray){

        int i = 1;
        while( numberArray.size() > 1){

            ArrayList<Character> lastString = numberArray.get(i-1);
            ArrayList<Character> currString = numberArray.get(i);

            ArrayList<Character> addedString = new ArrayList<>();
            addedString.add('[');
            addedString.addAll(lastString);
            addedString.add(',');
            addedString.addAll(currString);
            addedString.add(']');

            int stringPos = 0;
            boolean done = false;
            int squareCount = 0;

            while(!done){

                char pos = addedString.get(stringPos);

                if(squareCount > 3){
                    explode(addedString,i);
                }



                if (stringPos >= addedString.size()){done = true;}
                else {stringPos++;}
            }



        }

    }
    public static void explode(ArrayList<Character> currString, int pos){

        int x = Character.getNumericValue(currString.get(pos));
        int y = Character.getNumericValue(currString.get(pos+2));

        int[] xChanges = explodeSearch(-1, currString,pos,x);
        int[] yChanges = explodeSearch(1, currString,pos,y);

        explodeChange(currString, pos, xChanges);
        //if(xChanges[1] != 0){ currString = explodeChange(currString,xChanges[1],-1);}

        //currString = explodeChange(currString, pos+2,yChanges[0]);
        //if(yChanges[1] != 0) {currString = explodeChange(currString, yChanges[1],-1);}

    }


    public static int[] explodeSearch(int leftRight, ArrayList<Character> currString, int pos,int num) {

        //leftRight == -1 search to left. leftRight == 1 search to right
        int i = pos + leftRight;
        boolean foundNumber = false;
        int foundNumberPos = 0;
        
        
        while(i > 0 && i < currString.size() && !foundNumber){
            if (!(currString.get(i) == '[' || currString.get(i) == ']' || currString.get(i) == ',')){
                num += Character.getNumericValue(currString.get(i));
                foundNumberPos = i;
                foundNumber = true;
            }
            i += leftRight;
        }

        if(!foundNumber){num = 0;}

        return new int[] {num,foundNumberPos};
    }
    public static String explodeChange(ArrayList<Character> currString, int posChange, int[] changeInfo){

        int valChange = changeInfo[0];
        int foundNumberPos = changeInfo[1];

        currString.set(posChange,(char) valChange);


        if(valChange == -1){
            currString.remove(posChange);
            //charArr.remove(explodeFindBracket(currString,posChange,leftRight));
        }else {



        }
        return null;
    }

    public static int explodeFindBracket(String currString, int pos, int leftRight){

        char searchChar = (leftRight == -1)? '[':']';

        int i = 0;
        while(currString.charAt(pos + i) != searchChar){
            i += leftRight;
        }

        return pos + i;

    }

}


