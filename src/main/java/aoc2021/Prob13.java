package aoc2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Prob13 {

    public static void main(String[] args){

        Scanner myScanner = GetScanner.get("2021-13");
        boolean[][] paper = new boolean[894][1311];
        ArrayList<String> folds = new ArrayList<>();

        int s1Max = 0;
        int s0Max = 0;

        while (myScanner.hasNext(Pattern.compile(".*,.*"))){

            int[] s = Arrays.stream(myScanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();

            if (s[1] > s1Max){s1Max = s[1];}
            if (s[0] > s0Max){s0Max = s[0];}
            paper[s[1]][s[0]] = true;

        }

        myScanner.nextLine();

        while (myScanner.hasNext()){
            String[] s = myScanner.nextLine().split(" ");
            folds.addAll(List.of(s[2].split("=")));
        }

        for(int i = 0; i < folds.size();i += 2){

            if(i == 2){
                int firstFoldSum = 0;
                for(int x = 0; x < paper.length; x++){
                    for(int y = 0; y < paper[0].length; y++){;
                        if(paper[x][y]) { firstFoldSum++;}
                    }

                }
                System.out.println("There are " + firstFoldSum + " dots visible after the first fold");
            }

            if (folds.get(i).equals("x")){ paper = xFold(paper,folds.get(i+1),paper[0].length);}
            else {paper = yFold(paper, folds.get(i+1),paper[0].length);}

        }
        System.out.println();

        for(int x = 0; x < paper.length; x++){
            for(int y = 0; y < paper[0].length;y++){
                System.out.print(paper[x][y] ? '#':'.' );
            }
            System.out.println();
        }
    }

    public static boolean[][] yFold(boolean[][] paperIn, String cord,int rowLen){

        int cordInt = Integer.parseInt(cord);

        boolean[][] topHalf = new boolean[cordInt][rowLen];
        boolean[][] botHalf = new boolean[paperIn.length - cordInt][rowLen];

        for(int x= 0; x < paperIn.length;x++){

            if(x == cordInt){continue;}

            if (x < cordInt){topHalf[x] = paperIn[x];}
            else {botHalf[x - (cordInt + 1)] = paperIn[x];}
        }

        for(int x = 0; x < cordInt; x++){
            for(int y = 0; y < rowLen; y++){

                topHalf[x][y] = topHalf[x][y] || botHalf[(cordInt - 1) - x][y];

            }
        }
        return topHalf;
    }

    public static boolean[][] xFold(boolean[][] paperIn, String cord,int rowLen){

        int cordInt = Integer.parseInt(cord);

        boolean[][] leftHalf = new boolean[paperIn.length][cordInt];
        boolean[][] rightHalf = new boolean[paperIn.length][rowLen - cordInt];

        for(int x = 0; x < paperIn.length; x++) {
            for (int y = 0; y < rowLen; y++) {

                if (y == cordInt) {continue;}

                if (y < cordInt) {leftHalf[x][y] = paperIn[x][y];}
                else {rightHalf[x][y - (cordInt + 1)] = paperIn[x][y];}
            }
        }
        for(int x = 0; x < paperIn.length; x++){
            for(int y = 0; y < cordInt; y++){

                leftHalf[x][y] = leftHalf[x][y] || rightHalf[x][(cordInt - 1) - y];

            }
        }
        return leftHalf;
    }

}
