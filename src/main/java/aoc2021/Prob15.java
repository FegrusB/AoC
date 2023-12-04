package aoc2021;

import java.util.*;

public class Prob15 {


    public static void main(String[] args) {

        Scanner myScanner = GetScanner.get(2021,"2021-15.txt");

        final int sizeXY = 100;
        final int sizeXYBig = 500;
        final int endCord = 499;

        int[][] valueBoardIn = new int[sizeXY][sizeXY];

        PriorityQueue<weightedCord> unvisitedQueue = new PriorityQueue<>();
        int x = 0;
        while (myScanner.hasNext()) {
            String line = myScanner.nextLine();
            for (int y = 0; y < sizeXY; y++) {
                valueBoardIn[x][y] = Integer.parseInt(String.valueOf(line.charAt(y)));
            }
            x++;
        }

        int[][] valueBoard = makeBoard(valueBoardIn,sizeXY,sizeXYBig);

        boolean[][] visitedBoard = new boolean[sizeXYBig][sizeXYBig];

        unvisitedQueue.add(new weightedCord(0,0,0 ));

        System.out.println(findPath(unvisitedQueue,valueBoard,visitedBoard,endCord));

    }

    public static int[][] makeBoard(int[][] boardIn,int sizeXY, int sizeXYBig){


        int[][] boardOut = new int[sizeXYBig][sizeXYBig];

        for(int x = 0; x < sizeXY; x++){
            for(int y = 0; y < sizeXY; y++){
                boardOut[x][y] = boardIn[x][y];
            }
        }

        for(int x = sizeXY; x < sizeXYBig; x++) {
            for (int y = 0; y < sizeXY; y++) {
                int change = boardOut[x - sizeXY][y] + 1;
                if (change > 9) {change -= 9;}
                boardOut[x][y] = change;
            }
        }

        for(int x = 0; x < sizeXYBig;x++){
            for(int y = sizeXY; y < sizeXYBig; y++){
                int change = boardOut[x][y - sizeXY] + 1;
                if (change > 9) {change -= 9;}
                boardOut[x][y] = change;
            }
        }
        return boardOut;
    }

    public static int findPath(PriorityQueue<weightedCord> unvisitedQueue, int[][] valueBoard, boolean[][] visitedBoard,int endCord){

        boolean finished = false;

        while(! finished) {

            weightedCord check = unvisitedQueue.poll();
            if (check.x == endCord && check.y == endCord) {return check.distance;}

            int[][] cordDiffs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

            for (int[] diffs : cordDiffs) {
                int nextX = check.x + diffs[0];
                int nextY = check.y + diffs[1];

                if ((nextX < 0 || nextX > endCord) || (nextY < 0 || nextY > endCord)) {continue;}
                if (visitedBoard[nextX][nextY]){continue;}

                unvisitedQueue.add(new weightedCord(nextX,nextY, check.distance + valueBoard[nextX][nextY]));
                visitedBoard[nextX][nextY] = true;

            }

        }

        return 0;

    }
}
class weightedCord implements Comparable<weightedCord>{
    int distance;
    final int x;
    final int y;

    public weightedCord(int x, int y){
        this.x = x;
        this.y = y;
        distance = 10000;
    }
    public weightedCord(int x, int y, int distance){
        this.x = x;
        this.y = y;
        this.distance = distance;
    }
    public void setDistance(int distanceIn){this.distance = distanceIn;}

    @Override
    public int compareTo(weightedCord o) {
        return this.distance - o.distance;
    }
}