package org.example;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class dayFive {

    public static void main(String[] args) {


        Scanner myScanner = GetScanner.get("Day-5");
        ArrayList<ArrayList<Integer>> instructions = new ArrayList<>();

        while (myScanner.hasNext()){
            ArrayList<Integer> instruction = new ArrayList<>();

            String line = myScanner.nextLine();

            line = line.substring(5);
            if(line.charAt(1) != ' '){
                int x = Character.getNumericValue(line.charAt(0)) * 10;
                line = line.substring(1);
                x += Character.getNumericValue(line.charAt(0));
                instruction.add(x);
            }else {instruction.add(Character.getNumericValue(line.charAt(0)));}
            line = line.substring(7);
            instruction.add(Character.getNumericValue(line.charAt(0)) - 1);
            line = line.substring(5);
            instruction.add(Character.getNumericValue(line.charAt(0)) - 1);
            instructions.add(instruction);
        }

        System.out.println("The top boxes for part one are: " + movePartOne(instructions));
        System.out.println("The top boxes for part two are: " + movePartTwo(instructions));
    }
    public static String movePartTwo(ArrayList<ArrayList<Integer>> instructions){

        ArrayList<Stack<Character>> stacks = buildStacks();

        for (ArrayList<Integer> instruction : instructions){
            Stack<Character> tempStack = new Stack<>();
            for(int i = 0; i < instruction.get(0);i++){
                Character toMove = stacks.get(instruction.get(1)).pop();
                tempStack.push(toMove);
            }
            while (!tempStack.empty()){stacks.get(instruction.get(2)).push(tempStack.pop());}
        }

        StringBuilder topBoxes = new StringBuilder();
        for (Stack<Character> stack: stacks){topBoxes.append(stack.pop());}
        return topBoxes.toString();
    }
    public static String movePartOne(ArrayList<ArrayList<Integer>> instructions){

        ArrayList<Stack<Character>> stacks = buildStacks();

        for (ArrayList<Integer> instruction : instructions){
            for(int i = 0; i < instruction.get(0);i++){
                Character toMove = stacks.get(instruction.get(1)).pop();
                stacks.get(instruction.get(2)).push(toMove);
            }
        }

        StringBuilder topBoxes = new StringBuilder();
        for (Stack<Character> stack: stacks){topBoxes.append(stack.pop());}
        return topBoxes.toString();
    }
    public static ArrayList<Stack<Character>> buildStacks(){

        ArrayList<Stack<Character>> stacks = new ArrayList<>();

        stacks.add(new Stack<>());
        stacks.add(new Stack<>());
        stacks.add(new Stack<>());
        stacks.add(new Stack<>());
        stacks.add(new Stack<>());
        stacks.add(new Stack<>());
        stacks.add(new Stack<>());
        stacks.add(new Stack<>());
        stacks.add(new Stack<>());

        //String stack1 = "ZN";
        //String stack2 = "MCD";
        //String stack3 = "P";

        String stack1 = "MJCBFRLH";
        String stack2 = "ZCD";
        String stack3 = "HJFCNGW";
        String stack4 = "PJDMTSB";
        String stack5 = "NCDRJ";
        String stack6 = "WLDQPJGZ";
        String stack7 = "PZTFRH";
        String stack8 = "LVMG";
        String stack9 = "CBGPFQRJ";

        for(int i = 0; i < stack1.length();i++){stacks.get(0).push(stack1.charAt(i));}
        for(int i = 0; i < stack2.length();i++){stacks.get(1).push(stack2.charAt(i));}
        for(int i = 0; i < stack3.length();i++){stacks.get(2).push(stack3.charAt(i));}
        for(int i = 0; i < stack4.length();i++){stacks.get(3).push(stack4.charAt(i));}
        for(int i = 0; i < stack5.length();i++){stacks.get(4).push(stack5.charAt(i));}
        for(int i = 0; i < stack6.length();i++){stacks.get(5).push(stack6.charAt(i));}
        for(int i = 0; i < stack7.length();i++){stacks.get(6).push(stack7.charAt(i));}
        for(int i = 0; i < stack8.length();i++){stacks.get(7).push(stack8.charAt(i));}
        for(int i = 0; i < stack9.length();i++){stacks.get(8).push(stack9.charAt(i));}

        return stacks;
    }
}
