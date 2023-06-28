package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class dayTwo {

    public static void main(String[] args) {

        Scanner myScanner = GetScanner.get("Day-2");
        ArrayList<ArrayList<String>> instructions = new ArrayList<>();
        while (myScanner.hasNext()){
            instructions.add(new ArrayList<>(List.of(myScanner.nextLine().split(" "))));
        }

        int scoreOne = 0;
        int scoreTwo = 0;
        for(ArrayList<String> game: instructions){
            scoreOne += playGameOne(new ArrayList<>(game));
            scoreTwo += playGameTwo(new ArrayList<>(game));
        }

        System.out.println("Part one score: " + scoreOne);
        System.out.println("Part two score: " + scoreTwo);
    }

    public static int playGameTwo(ArrayList<String> game){
        int score = 0;

        switch (game.get(0)){
            case "A"->{
                switch (game.get(1)){
                    case "X"->score = 3;
                    case "Y"->score = 4;
                    case "Z"->score = 8;
                }
            }
            case "B"->{
                switch (game.get(1)){
                    case "X"->score = 1;
                    case "Y"->score = 5;
                    case "Z"->score = 9;
                }
            }
            case "C"->{
                switch (game.get(1)){
                    case "X"->score = 2;
                    case "Y"->score = 6;
                    case "Z"->score = 7;
                }
            }
        }

        return score;
    }

    public static int playGameOne(ArrayList<String> game){

        int score = 0;

        switch (game.get(1)){
            case "X" -> {
                game.set(1,"A");
                score += 1;
            }
            case "Y" -> {
                game.set(1,"B");
                score += 2;
            }
            case "Z" -> {
                game.set(1,"C");
                score += 3;
            }
        }

        if(game.get(0).equals(game.get(1))){score += 3;
        } else if(game.get(0).equals("A") && game.get(1).equals("B")){score += 6;
        } else if(game.get(0).equals("B") && game.get(1).equals("C")){score += 6;
        } else if(game.get(0).equals("C") && game.get(1).equals("A")){score += 6;}

        return score;

    }

}
