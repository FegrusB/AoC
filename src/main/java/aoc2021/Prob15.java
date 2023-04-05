package aoc2021;

import java.util.*;

public class Prob15 {


    public static void main(String[] args) {

        Scanner myScanner = GetScanner.get("2021-15.txt");

        int[][] valueBoard = new int[100][100];
        boolean[][] visitedBoard = new boolean[100][100];
        PriorityQueue<weightedCord> unvisitedQueue = new PriorityQueue<>();
        int x = 0;
        while (myScanner.hasNext()) {
            String line = myScanner.nextLine();
            for (int y = 0; y < 100; y++) {
                valueBoard[x][y] = Integer.parseInt(String.valueOf(line.charAt(y)));
                visitedBoard[x][y] = false;
                unvisitedQueue.add(new weightedCord(x,y));
            }
            x++;
        }

        weightedCord init = unvisitedQueue.poll();
        init.distance = 0;
        unvisitedQueue.add(init);

        System.out.println(findPath(unvisitedQueue,valueBoard,visitedBoard));

    }

    public static int findPath(PriorityQueue<weightedCord> unvisitedQueue, int[][] valueBoard, boolean[][] visitedBoard){

        boolean finished = false;

        while(! finished) {

            weightedCord check = unvisitedQueue.poll();
            if (check.x == 99 && check.y == 99) {return check.distance;}

            int[][] cordDiffs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

            for (int[] diffs : cordDiffs) {
                int nextX = check.x + diffs[0];
                int nextY = check.y + diffs[1];

                if ((nextX < 0 || nextX > 99) || (nextY < 0 || nextY > 99)) {continue;}
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