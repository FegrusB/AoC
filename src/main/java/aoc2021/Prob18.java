package aoc2021;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Prob18 {

    public static void main(String[] args){
        Scanner myScanner = GetScanner.get("2021-18a");
        ArrayList<String> stringArray = new ArrayList<>();
ArrayList<ArrayList<Integer>>

        while (myScanner.hasNextLine()){
            stringArray.add(myScanner.nextLine());
        }




        calculate(numbersArray);
    }

    public static void calculate(ArrayList<String > numberArray){

        int i = 1;
        while( numberArray.size() > 1){

            String lastString = numberArray.get(i-1);
            String currString = numberArray.get(i);

            String addedString = "[" + lastString + "," + currString + "]";

            int stringPos = 0;
            boolean done = false;
            int squareCount = 0;

            while(!done){

                char pos = addedString.charAt(stringPos);

                if(squareCount > 3){
                    addedString = explode(currString,i);
                }



                if (stringPos >= addedString.length()){done = true;}
                else {stringPos++;}
            }



        }

    }
    public static String explode(String currString, int pos){

        int x = Character.getNumericValue(currString.charAt(pos));
        int y = Character.getNumericValue(currString.charAt(pos+2));

        int[] xChanges = explodeSearch(-1, currString,pos,x);
        int[] yChanges = explodeSearch(1, currString,pos,y);

        currString = explodeChange(currString, pos, xChanges);
        if(xChanges[1] != 0){ currString = explodeChange(currString,xChanges[1],-1);}

        currString = explodeChange(currString, pos+2,yChanges[0]);
        if(yChanges[1] != 0) {currString = explodeChange(currString, yChanges[1],-1);}

        return currString;
    }


    public static int[] explodeSearch(int leftRight, String currString, int pos,int num) {

        //leftRight == -1 search to left. leftRight == 1 search to right
        int i = pos + leftRight;
        boolean foundNumber = false;
        int foundNumberPos = 0;
        
        
        while(i > 0 && i < currString.length() && !foundNumber){
            if (currString.charAt(i) != '[' || currString.charAt(i) != ']'){
                num += Character.getNumericValue(currString.charAt(i));
                foundNumberPos = i;
                foundNumber = true;
            }
            i += leftRight;
        }

        if(!foundNumber){num = 0;}

        return new int[] {num,foundNumberPos};
    }
    public static String explodeChange(String currString, int posChange, int[] changeInfo){

        int valChange = changeInfo[0];
        int foundNumberPos = changeInfo[1];

        ArrayList<Character> charArr = new ArrayList<>(currString.chars().mapToObj(c -> (char) c).collect(Collectors.toList()));

        charArr.set(posChange,Character.)


        if(valChange == -1){
            charArr.remove(posChange);
            charArr.remove(explodeFindBracket(currString,posChange,leftRight));
        }else {



        }

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


