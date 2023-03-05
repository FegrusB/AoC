package aoc2021;

import java.util.ArrayList;
import java.util.Scanner;


//Solve for day two of AoC2021
//Given a series of inputs with a direction and a magnitude, calculate final position
public class Prob2 {

	public static void main(String[] args) {
	
		int xPos = 0;
		int depth = 0;
		int aim = 0;
		ArrayList <instruction> data = new ArrayList<>();
		
		//Use GetScanner to create scanner, of lines with string dir and int magnitude, copy to data arraylist w/ type instruction.
		//Each line = 1 instruction, dir and magnitude split with " 
		Scanner myScanner;
		myScanner = GetScanner.get("2021-2.txt");
		while(myScanner.hasNextLine()) {
			String[] nl = myScanner.nextLine().split(" ");
			instruction nI = new instruction(nl[0],Integer.parseInt(nl[1]));
			data.add(nI);
		}
		myScanner.close();

		//for each instruction, select based on dir, then modify appropriate pos value.
		//for part 2 down + up modifies aim rather than y
		for(instruction i:data) {
			switch (i.dir()) {
				case "forward" -> {
					xPos += i.magnitude();
					depth = depth + aim * i.magnitude();
				}
				case "down" -> aim += i.magnitude();
				case "up" -> aim -= i.magnitude();
			}
		}
	
		//print final output
		System.out.println((xPos*depth));
	
	
	
	}
}

//simple class for storing instruction data.
//2 vars, set on construction + getters for each
record instruction(String dir, int magnitude) {


}

