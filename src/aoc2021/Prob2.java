package aoc2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


//Solve for day two of AoC2021
//Given a series of inputs with a direction and a magnitude, calculate final position
public class Prob2 {

	public static void main(String[] args) {
	
		int xPos = 0;
		int depth = 0;
		int aim = 0;
		
		//try to find file, copy file to ArrayList data of type instruction. Each line = 1 instruction, dir and magnitude split with " ". Catch if file not found
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
	
		//for each instruction, select based on dir, then modify appropriate pos value.
		//for part 2 down + up modifies aim rather than y
		for(instruction i:data) {	
			switch (i.getDir()) {
				case "forward": xPos += i.getMagnitude(); depth = depth + aim * i.getMagnitude();break;
				case "down": aim += i.getMagnitude();break;
				case "up": aim -= i.getMagnitude();break;
			}
		}
	
		//print final output
		System.out.println((xPos*depth));
	
	
	
	}
}
	
//simple class for storing instruction data.
//2 vars, set on construction + getters for each
class instruction {
	
	private String dir;
	private int magnitude;
	
	public instruction(String dir, int magnitude) {
		this.dir = dir;
		this.magnitude = magnitude;
	}
	
	public String getDir() {return dir;}
	public int getMagnitude() {return magnitude;}
	
}

