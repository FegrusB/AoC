package AoC2022;

import Common.GetScanner;

import java.util.LinkedList;
import java.util.Scanner;

public class Prob10 {

    public static void main(String[] args) {
        Scanner myScanner = GetScanner.get(2022,"Day-10");
        LinkedList<Integer[]> instructions =  new LinkedList<>();

        while (myScanner.hasNext()){
            String line = myScanner.nextLine();
            String[] lineSplit = line.split(" ");

            if(lineSplit[0].charAt(0) == 'n'){instructions.offer(new Integer[] {0,0});}
            else {instructions.offer(new Integer[] {1, Integer.parseInt(lineSplit[1])});}
        }

        System.out.println();

        int x = 1;
        int counter = 0;
        long sum = 0;
        int nextCheck = 39;
        StringBuilder line  = new StringBuilder();

        while (!instructions.isEmpty()){
            Integer[] instruction = instructions.poll();
            if(counter == x - 1 || counter == x || counter == x+1){line.append('#');}
            else{line.append('.');}
            if (counter == nextCheck){
                sum += (long) counter * x;
                counter -= 40;
                System.out.println(line.toString());
                line = new StringBuilder();
            } else {

            }

            switch (instruction[0]){
                case 0 -> counter++;
                case 1 -> {
                    counter += 1;
                    instructions.addFirst(new Integer[] {2,instruction[1]});
                }
                case 2 -> {
                    counter += 1;
                    x += instruction[1];
                }
            }
        }
        System.out.println(sum);
    }
}
