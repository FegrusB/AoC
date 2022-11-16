package aoc2021;

import java.util.ArrayList;
import java.util.Scanner;

public class Prob5 {

	public static void main(String[] args) {

		//sets up input scanner, and needed objects/vars 
		Scanner myScanner = GetScanner.get("2021-5a.txt");
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
		
	}

}

class Line{
	
	public int[][] points;
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
		//get increment for building line line
		int out;
		
		if(in > 0) {out = -1;}
		else if (in == 0) {out = 0;}
		else{out = 1;}
		
		return out;
	}
	
}
