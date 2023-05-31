package aoc2021;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Prob18 {

    public static void main(String[] args) {
        Scanner myScanner = GetScanner.get("2021-18a");
        ArrayList<ArrayList<Character>> numbersArray = new ArrayList<>();

        while (myScanner.hasNextLine()) {
            String currString = myScanner.nextLine();
            ArrayList<Character> number = currString.chars().mapToObj(c -> (char) c).collect(Collectors.toCollection(ArrayList::new));
            numbersArray.add(number);
        }

        calculate(numbersArray);
    }

    public static void calculate(ArrayList<ArrayList<Character>> numberArray) {

        int i = 1;
        while (numberArray.size() > 1) {

            ArrayList<Character> lastNumber = numberArray.get(i - 1);
            ArrayList<Character> currNumber = numberArray.get(i);

            ArrayList<Character> addedNumber = new ArrayList<>();
            addedNumber.add('[');
            addedNumber.addAll(lastNumber);
            addedNumber.add(',');
            addedNumber.addAll(currNumber);
            addedNumber.add(']');

            reduce(addedNumber);

            numberArray.remove(0);
            numberArray.set(0,addedNumber);

        }
    }
    public static void reduce(ArrayList<Character> number){

        int squareCount = 0;
        int pos = 0;

        while (pos < number.size()) {

            char currChar = number.get(pos);
            pos++;

            if (squareCount > 4) {
                explode(number,pos);
                pos = 0;
            }else if (Character.isDigit(currChar) && Character.isDigit(number.get(pos))){
                //split
                pos = 0;
            } else if (currChar == '['){
                squareCount++;
            } else if(currChar == ']'){
                squareCount--;
            }
        }
    }
    public static void explode(ArrayList<Character> number, int pos){

        char leftChar = number.get(pos);
        char rightChar = number.get(pos + 2);

        boolean changed = false;
        while (!changed){

            int leftPos = pos;

            while(leftPos < 0){
                if(Character.isDigit(number.get(leftPos))){
                    int leftNum = Character.getNumericValue(number.get(leftPos));
                    leftNum += Character.getNumericValue(rightChar);
                    if
                }
                leftPos--;

            }



        }


    }
}


