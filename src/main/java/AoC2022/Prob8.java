package AoC2022;

import Common.GetScanner;

import java.util.Scanner;

public class Prob8 {

    public static void main(String[] args) {

        Scanner myScanner = GetScanner.get(2022,"Day-8");
        int[][] trees = new int[99][];
        int count = 0;

        while (myScanner.hasNext()){
            String line =  myScanner.nextLine();
            String[] splitLine = line.split("");
            int[] row = new int[line.length()];
            for (int i = 0; i < line.length(); i++){
                row[i] = Integer.parseInt(splitLine[i]);
            }
            trees[count] = row;
            count++;
        }

        System.out.println(partOne(trees));
        System.out.println(partTwo(trees));
    }
    private static int partTwo(int[][] trees){

        int[][] visible = new int[trees.length][trees.length];

        for(int x = 1; x < trees.length -1;x++){
            for(int y = 1; y < trees.length -1;y++){
                visible[y][x] = partTwoCheck(trees,x,y,0,1) * partTwoCheck(trees,x,y,0,-1)
                        * partTwoCheck(trees,x,y,1,0) * partTwoCheck(trees,x,y,-1,0);
            }
        }

        int best = 0;
        for(int x = 1; x < trees.length -1;x++){
            for(int y = 1; y < trees.length -1;y++){
                if(visible[y][x] > best){best = visible[y][x];}
               }
        }

        return best;
    }
    public static int partTwoCheck(int[][] trees, int x, int y, int xDiff, int yDiff){
        int check = trees[y][x];
        x += xDiff;
        y += yDiff;

        boolean finished  = false;
        int count = 0;
        while (!finished && (x > - 1 && x < trees.length) && ( y > -1 && y < trees.length) ){
            if(trees[y][x] >= check){count ++;finished = true;}
            else {count++;}
            x += xDiff;
            y += yDiff;
        }
        return count;
    }

    private static int partOne(int[][] trees) {

        boolean[][] visable = new boolean[trees.length][trees.length];

        for (int i = 0; i<trees.length;i++){
            visable[0][i] = true;
            visable[trees.length-1][i] = true;
            visable[i][0] = true;
            visable[i][trees.length-1] = true;
        }

        for(int x = 1; x < trees.length -1;x++){
            for(int y = 1; y < trees.length -1;y++){
                visable[y][x] = partOneCheck(trees,x,y,0,1) || partOneCheck(trees,x,y,0,-1)
                        || partOneCheck(trees,x,y,1,0) || partOneCheck(trees,x,y,-1,0);
            }
        }
        return count(visable);
    }
    public static boolean partOneCheck(int[][] trees, int x, int y, int xDiff, int yDiff){
        int check = trees[y][x];
        x += xDiff;
        y += yDiff;
        boolean tallest  = true;
        while (tallest && (x > - 1 && x < trees.length) && ( y > -1 && y < trees.length) ){
            if(trees[y][x] >= check){tallest = false;}
            x += xDiff;
            y += yDiff;
        }
        return tallest;
    }
    public static int count(boolean[][] visable){
        int count = 0;
        for(int x = 0; x < visable.length;x++){
            for(int y = 0; y < visable.length;y++){
                if(visable[x][y]){count++;}
            }
        }
        return count;
    }
}
