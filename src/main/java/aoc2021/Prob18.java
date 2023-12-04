package aoc2021;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Prob18 {

    public static void main(String[] args) {
        Scanner myScanner = GetScanner.get("2021-18");
        ArrayList<ArrayList<String>> numbersArray = new ArrayList<>();

        while (myScanner.hasNextLine()) {
            String currString = myScanner.nextLine();
            numbersArray.add( new ArrayList<>(List.of(currString.split(""))));
        }

        System.out.println(partTwo(numbersArray));

        calculate(numbersArray);
        System.out.println(rebuild(numbersArray.get(0)));

        ArrayList<String> number = numbersArray.get(0);
        magnitude(number);
        System.out.println(number.get(0));


    }

    public static Double partTwo(ArrayList<ArrayList<String>> numbers){
        ArrayList<Double> mags = new ArrayList<>();

        for(ArrayList<String> number: numbers){
            for (ArrayList<String> pair: numbers){
                if (number.equals(pair)){continue;}
                ArrayList<ArrayList<String>> calcedNum = new ArrayList<>();
                calcedNum.add(number);
                calcedNum.add(pair);
                calculate(calcedNum);
                magnitude(calcedNum.get(0));
                mags.add(Double.valueOf(calcedNum.get(0).get(0)));
            }
        }

        mags.sort(null);
        return mags.get(mags.size()-1);
    }

    public static String rebuild(ArrayList<String> number){
        StringBuilder outNum = new StringBuilder();
        for (String s: number){
            outNum.append(s);
        }
        return  outNum.toString();
    }

    public static void magnitude(ArrayList<String> number){

        int pos = 0;

        while (number.size() != 1){

            if (number.get(pos).equals(",")){
                if(NumberUtils.isCreatable(number.get(pos-1)) && NumberUtils.isCreatable(number.get(pos+1))){
                    magnitudeCalculate(number,pos);
                }
            }
            pos++;
            if( pos >= number.size() ){pos = 0;}
        }
    }

    public static void magnitudeCalculate(ArrayList<String> number, int pos){
        double leftNum = Double.parseDouble(number.get(pos - 1));
        double rightNum = Double.parseDouble(number.get(pos + 1));

        double newNum = leftNum * 3 + rightNum * 2;

        pos -= 2;
        number.remove(pos);
        number.remove(pos);
        number.remove(pos);
        number.remove(pos);
        number.set(pos, String.valueOf(newNum));

    }

    public static void calculate(ArrayList<ArrayList<String>> numberArray) {

        int i = 1;
        while (numberArray.size() > 1) {
            add(numberArray,i);
            reduce(numberArray.get(0));
        }
    }
    public static void reduce(ArrayList<String> number){

        int squareCount = 0;
        int pos = 0;
        boolean finished = false;
        boolean exploded = true;
        boolean split = false;

        while (!finished){

            exploded = explode(number);

            if ( ! exploded){
                split = splitSearch(number);
            }
            if(!split && !exploded){
                finished = true;
            }
        }

    }

    private static boolean splitSearch(ArrayList<String> number) {

        boolean split = false;
        int pos = 0;

        while (pos < number.size() - 1 && !split) {

            pos++;

            if (NumberUtils.isCreatable(number.get(pos)) && Integer.parseInt(number.get(pos)) >= 10){
                split = true;
                split(number, pos);
            }
        }
        return split;
    }

    private static void split(ArrayList<String> number, int pos) {
        int originalNumber = Integer.parseInt(number.get(pos));
        ArrayList<String> addNumber = new ArrayList<>();

        float div = (float) originalNumber /2;
        int left = (int) Math.floor(div);
        int right = Math.round(div);

        addNumber.add("[");
        addNumber.add(String.valueOf(left));
        addNumber.add(",");
        addNumber.add(String.valueOf(right));
        addNumber.add("]");

        number.remove(pos);
        number.addAll(pos,addNumber);
    }

    public static boolean explode(ArrayList<String> number){

        boolean exploded = false;

        int squareCount = 0;
        int pos = -1;

        while (pos < number.size() -1 && !exploded) {

            pos++;
            String currChar = number.get(pos);

            if (squareCount > 4) {
                exploded = true;
                squareCount--;
            } else if (currChar.equals("[")){
                squareCount++;
            } else if(currChar.equals("]")){
                squareCount--;
            }
        }

        if(exploded){
            explodeSearch(number, pos, 0, -1);
            explodeSearch(number, pos+2, number.size() -1, 1);
            number.set(pos-1,"0");
            number.remove(pos);
            number.remove(pos);
            number.remove(pos);
            number.remove(pos);

        }

        return exploded;

    }

    private static void explodeSearch(ArrayList<String> number, int originPos, int bounds, int increment) {

        int searchPos = originPos + increment;
        boolean set = false;
        while(searchPos != bounds && !set){

            if(NumberUtils.isCreatable(number.get(searchPos))){
                int searchNum = Integer.parseInt(number.get(searchPos));
                searchNum += Integer.parseInt(number.get(originPos));
                number.set(searchPos, String.valueOf(searchNum));
                set = true;
            }
            searchPos += increment;
        }

    }


    public static void add(ArrayList<ArrayList<String>> numberArray, int i){

        ArrayList<String> lastNumber = numberArray.get(i - 1);
        ArrayList<String> currNumber = numberArray.get(i);

        ArrayList<String> addedNumber = new ArrayList<>();
        addedNumber.add("[");
        addedNumber.addAll(lastNumber);
        addedNumber.add(",");
        addedNumber.addAll(currNumber);
        addedNumber.add("]");

        reduce(addedNumber);

        numberArray.remove(0);
        numberArray.set(0,addedNumber);

    }
}


