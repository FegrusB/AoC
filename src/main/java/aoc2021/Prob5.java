package aoc2021;

import java.util.ArrayList;
import java.util.Scanner;

public class Prob5 {

	public static void main(String[] args) {

		//sets up input scanner, and needed objects/vars 
		Scanner myScanner = GetScanner.get("2021-5.txt");
		ArrayList<Line> lines = new ArrayList<>();
		
		//clean input, then populate lines with lines from scanner.
		while (myScanner.hasNextLine()) {
			String nl = myScanner.nextLine();
			String[] spL = nl.split(" ");
			String[][] points = new String[2][2];
			points[0] = spL[0].split(",");
			points[1] = spL[2].split(",");
			Line line = new Line(points);
			lines.add(line);
		}	
		
		//play game with board size 10, lines array, and false for check diagonal
		int out = play1(1000,lines,true);
		System.out.println(out);
		
	}
	
	public static int play1(int size,ArrayList<Line> lines, boolean checkD) {
		
		//build board with given size
		int[][] board = new int[size][size];
		
		//for each point in each line of lines.
		//if line is not diagonal or check diagonal = true. increment board[x][y]
		for(Line line:lines) {
			if (checkD||!(line.diag)) {
				for(int[] point:line.points) {board[point[0]][point[1]] += 1;}
			}
		}
		
		//get and return num of intersections from board
		int intersections = 0;
		for(int[] x:board) {
			for(int y:x) {if(y>1) {intersections ++;}}
		}
		return intersections;
	}

}

class Line{
	
	public final int[][] points;
	public boolean diag;
	
	public Line(String[][] pointsS) {

		int x1 = Integer.parseInt(pointsS[0][0]);
		int y1 = Integer.parseInt(pointsS[0][1]);
		
		int x2 = Integer.parseInt(pointsS[1][0]);
		int y2 = Integer.parseInt(pointsS[1][1]);

		int dx = x1 - x2;
		int dy = y1 - y2;
		
		//check if line is diagonal
		if (dx != 0 && dy != 0) {
			this.diag = true;
		}
		
		//use absolute value of bigger dx/dy to set the length of line, use to set size of points array.
		int length;
		if (Math.abs(dx) > Math.abs(dy)) {length = (Math.abs(dx)+1);}
		else { length = (Math.abs(dy)+1);}
		this.points = new int[length][2];
		
		int xInc = getInc(dx);
		int yInc = getInc(dy);

		int curX = x1;
		int curY = y1;
		
		//build line. For each point increment x/y by xInc/yInc
		for (int i = 0; i < length; i++) {
			points[i][0] = curX;
			points[i][1] = curY;
			curX += xInc;
			curY += yInc;
		}
		
	}
	private int getInc(int in) {
		//get increment for building line

		//noinspection UseCompareMethod
		if(in > 0) {return  -1;}
		else if (in == 0) {return  0;}
		else{return  1;}

	}
	
}
