package AoC2022;

import Common.GetScanner;

import java.util.*;

public class Prob9 {
    public static void main(String[] args) {

        Scanner myScanner = GetScanner.get(2022,"Day-9");
        LinkedList<Character> instructionQueuePart1 = new LinkedList<>();
        LinkedList<Character> instructionQueuePart2 = new LinkedList<>();

        while (myScanner.hasNext()){
            String line = myScanner.nextLine();
            String[] lineSplit = line.split(" ");
            for(int i = 0; i < Integer.parseInt(lineSplit[1]);i++){
                instructionQueuePart1.offer(lineSplit[0].charAt(0));
                instructionQueuePart2.offer(lineSplit[0].charAt(0));
            }
        }

        System.out.println(sequence(instructionQueuePart1, 500, 1000,2));
        System.out.println(sequence(instructionQueuePart2, 500, 1000,10));

    }
    public static int sequence(LinkedList<Character> instructionQueue, int start, int boardSize, int ropeLength){

        int[][] rope = new int[ropeLength][2];

        for (int i = 0; i < ropeLength; i++){
            rope[i] = new int[] {start,start};
        }

        boolean[][] visited = new boolean[boardSize][boardSize];

        while (!instructionQueue.isEmpty()) {

            switch (instructionQueue.poll()){
                case 'R' -> rope[0][0]++;
                case 'L' -> rope[0][0]--;
                case 'U' -> rope[0][1]++;
                case 'D' -> rope[0][1]--;
            }

            for(int i = 1; i < ropeLength; i++){
                rope[i] = move(rope[i-1],rope[i]);
            }

            visited[rope[ropeLength-1][0]][rope[ropeLength-1][1]] = true;
        }
        return count(visited);
    }
    public static int count(boolean[][] visited){
        int count = 0;
        for (int x = 0; x < visited.length;x++){
            for (int y = 0; y < visited.length;y++){
                if (visited[x][y]){count++;}
            }
        }
        return count;
    }
    public static int[] move(int[] headPos, int[] tailPos){
        int xDiff = headPos[0] - tailPos[0];
        int yDiff = headPos[1] - tailPos[1];
        int absDiff = Math.abs(xDiff) + Math.abs(yDiff);

        if ( absDiff == 2){
            if(Math.abs(xDiff) == 2){
                if ((headPos[0] - tailPos[0] == 2)) {tailPos[0]++;
                } else {tailPos[0]--;}
            } else if (Math.abs(yDiff) == 2) {
                if ((headPos[1] - tailPos[1] == 2)) {tailPos[1]++;
                } else {tailPos[1]--;}
            }
        } else if (absDiff >= 3){
            if (xDiff > 0){
                tailPos[0]++;
                if(yDiff > 0){
                    tailPos[1]++;
                } else {
                    tailPos[1]--;
                }
            } else {
                tailPos[0]--;
                if(yDiff > 0){
                    tailPos[1]++;
                } else {
                    tailPos[1]--;
                }
            }
        }
        return tailPos;
    }
}
