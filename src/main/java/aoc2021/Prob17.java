package aoc2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Prob17 {


    public static void main(String[] args){

        Scanner myScanner = GetScanner.get(2021,"2021-17.txt");
        int[][] boundsOut  = setUpBounds(myScanner.nextLine());
        int[] xBounds = boundsOut[0];
        int[] yBounds = boundsOut[1];

        mainLoop(xBounds,yBounds);

    }

    public static int[][] setUpBounds(String inString){

        String[] firstSplit = inString.split(":");
        String[] secondSplit = firstSplit[1].split(",");
        secondSplit[0] = secondSplit[0].substring(3);
        secondSplit[1] = secondSplit[1].substring(3);
        String[] xString = secondSplit[0].split("\\.\\.");
        String[] yString = secondSplit[1].split("\\.\\.");

        int[] xBounds = Arrays.stream(xString).mapToInt(Integer::parseInt).toArray();
        int[] yBounds = Arrays.stream(yString).mapToInt(Integer::parseInt).toArray();

        return new int[][] {xBounds,yBounds};
    }

    public static int probeLoop(int[] initialVelocity, int[] xBounds, int[] yBounds){

        probe probe = new probe(initialVelocity);
        int currX = 0;
        int currY = 0;
        int maxY = 0;
        boolean targetZone = false;

        while (currX <= xBounds[1] && currY >= yBounds[0]){
            boolean targetX = false;
            boolean targetY = false;
            
            int[] newCords = probe.step(1);
            currX = newCords[0];
            currY = newCords[1];

            if(currY > maxY){ maxY = currY;}
            
            if (xBounds[0] <= currX && currX <= xBounds[1] ){targetX = true;}
            if (yBounds[0] <= currY && currY <= yBounds[1] ){targetY = true;}
            if(targetX && targetY){targetZone = true;}


        }

        return (targetZone)? maxY : -1;
    }

    public static void mainLoop(int[] xBounds, int[] yBounds){

        ArrayList<Integer[]> winningVelocities = new ArrayList<>();
        int maxY = 0;

        for(int initialXVelocity = 1; initialXVelocity < xBounds[1] + 1; initialXVelocity++ ){

            for(int initialYVelocity = yBounds[0]; initialYVelocity < xBounds[1] + 1; initialYVelocity++ ){

                int newY = probeLoop(new int[] {initialXVelocity,initialYVelocity},xBounds,yBounds);
                if(newY > maxY){maxY = newY;}
                if(newY != -1){winningVelocities.add(new Integer[] {initialXVelocity,initialYVelocity});}

            }
        }
        System.out.println("Max y reached: " + maxY);
        /*for(Integer[] pair: winningVelocities ){
            if(pair[0] == 30){
                System.out.println();
            }
        }*/

        System.out.println("Number of winning initial velocities: " + winningVelocities.size());

    }



}
class probe {
    private int x;
    private int y;

    private int xVelocity;
    private int yVelocity;

    public probe(int initialXVelocity,int initialYVelocity){
        this.x = 0;
        this.y = 0;
        this.xVelocity = initialXVelocity;
        this.yVelocity = initialYVelocity;
    }
    public probe(int[] initialVelocity){
        this.x = 0;
        this.y = 0;
        this.xVelocity = initialVelocity[0];
        this.yVelocity = initialVelocity[1];
    }
    public void incrementXVelocity(){
        if(xVelocity < 0){xVelocity++;}
        else if(xVelocity > 0){xVelocity--;}
    }
    public void incrementYVelocity(){yVelocity--;}

    public int getX() {return x;}
    public int getY() {return y;}

    public int[] step(int stepNumber){

        for(int i = 0; i < stepNumber; i++){

            x += xVelocity;
            y += yVelocity;
            incrementXVelocity();
            incrementYVelocity();

        }

        return new int[] {x, y};

    }

}