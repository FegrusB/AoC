package aoc2021;

import java.util.ArrayList;
import java.util.Scanner;

public class Prob5 {

	public static void main(String[] args) {

		//sets up input scanner, and needed objects/vars 
		Scanner myScanner = GetScanner.get("2021-5a.txt");
		ArrayList<Line> lines = new ArrayList<>();
			
		while (myScanner.hasNextLine()) {
			String nl = myScanner.nextLine();
			String[] spL = nl.split(" ");
			String[][] points = new String[2][2];
			points[0] = spL[0].split(",");
			points[1] = spL[2].split(",");
			Line line = new Line(points);
			lines.add(line);
		}
		
		System.out.println();
		
	}

}

class Line{
	
	private int[][] points;
	public boolean diag;
	
	public Line(String[][] pointsS) {

		int numPoints = 0;
		int xInc = 0;
		int yInc = 0;
		
		int x1 = Integer.parseInt(pointsS[0][0]);
		int y1 = Integer.parseInt(pointsS[0][1]);
		
		int x2 = Integer.parseInt(pointsS[1][0]);
		int y2 = Integer.parseInt(pointsS[1][1]);
		
		int dx = x1 - x2;
		int dy = y1 - y2;
		
		if (dx != 0 && dy != 0) {
			this.diag = true;
		}
		
		if (Math.abs(dx) > Math.abs(dy)) {numPoints = Math.abs(dx);}
		else { numPoints = Math.abs(dy);}
		
		this.points = new int[numPoints+1][2];
		
		if (dx > 0) {xInc = -1;}
		else if (dx == 0) {xInc = 0;}
		else {xInc = 1;}
		
		if(dy > 0) {yInc = -1;}
		else if (dy == 0) {yInc = 0;}
		else{yInc = 1;}
		
		int curX = x1;
		int curY = y1;
		
		for (int i = 0; i < numPoints + 1; i++) {
			
			points[i][0] = curX;
			points[i][1] = curY;
			
			curX += xInc;
			curY += yInc;
			
		}
		
		System.out.println();
		
	}
	
}
