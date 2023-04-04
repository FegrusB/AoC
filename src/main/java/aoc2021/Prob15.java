package aoc2021;

import java.util.*;

public class Prob15 {


    public static void main(String[] args) {

        Scanner myScanner = GetScanner.get("2021-15.txt");

        boolean[][] visitedBoard = new boolean[100][100];
        int[][] valueBoard = new int[100][100];

        int x = 0;
        while (myScanner.hasNext()) {
            String line = myScanner.nextLine();
            for (int i = 0; i < 100; i++) {
                valueBoard[x][i] = Integer.parseInt(String.valueOf(line.charAt(i)));
                visitedBoard[x][i] = false;
            }
            x++;
        }

        int startCords[] = {0, 0};
        ArrayList<Integer> shortest = new ArrayList<>();
        shortest.add(1800);
        visitedBoard[0][0] = true;
        findSafestRoute(visitedBoard.clone(), valueBoard, startCords, 0, shortest);
        System.out.println(shortest.get(0) - 1);

    }

    public static int findSafestRoute(boolean[][] visitedBoardIn, int[][] valueBoard, int[] currCords, int total, ArrayList<Integer> shortest) {

        boolean[][] visitedBoard = new boolean[100][100];

        for(int x = 0;x < 100; x++){

            visitedBoard[x] = visitedBoardIn[x].clone();

        }

        visitedBoard[currCords[0]][currCords[1]] = true;
        total += valueBoard[currCords[0]][currCords[1]];

        if (total > shortest.get(0)) {
            return 0;
        }
        if (!(currCords[0] == 99 && currCords[1] == 99)) {

            int[][] cordDiffs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

            for (int[] diffs : cordDiffs) {

                int nextX = currCords[0] + diffs[0];
                int nextY = currCords[1] + diffs[1];

                if ((nextX < 0 || nextX > 99) || (nextY < 0 || nextY > 99)) {
                    continue;
                }
                if (!visitedBoard[nextX][nextY]) {
                    findSafestRoute(visitedBoard, valueBoard, new int[]{nextX, nextY}, total, shortest);
                }
            }
        } else {
            shortest.add(0, total);
            return total;
        }
        return 0;
    }
}
