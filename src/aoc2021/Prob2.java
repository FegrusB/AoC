package aoc2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


class instruction {
	
	private String dir;
	private int degree;
	
	public instruction(String dir, int degree) {
		this.dir = dir;
		this.degree = degree;
	}
	
	public String getDir() {return dir;}
	public int getDegree() {return degree;}
	
}

public class Prob2 {

	public static void main(String[] args) {
		//try to find file, copy file to ArrayList data. Catch if file not found
				
		int xPos = 0;
		int depth = 0;
		int aim = 0;
		
		ArrayList <instruction> data = new ArrayList<instruction>();
		try {
			File myFile = new File("C:\\Users\\fergu\\eclipse-workspace\\aoc2021\\data\\2021-2.txt");
			Scanner myScanner = new Scanner(myFile);
			while(myScanner.hasNextLine()) {
				String[] nl = myScanner.nextLine().split(" ");
				instruction nI = new instruction(nl[0],Integer.parseInt(nl[1]));
				data.add(nI);
			}
			myScanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
			e.printStackTrace();
		}
	
		
		for(instruction i:data) {	
			switch (i.getDir()) {
				case "forward": xPos += i.getDegree(); depth = depth + aim * i.getDegree();break;
				case "down": aim += i.getDegree();break;
				case "up": aim -= i.getDegree();break;
			}
		}
	
		System.out.println((xPos*depth));
	
	
	
	}

}
