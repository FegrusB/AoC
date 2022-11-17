package aoc2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Prob6 {

	public static void main(String[] args) {
		
		//Read in starting fish to arraylist
		Scanner myScanner = GetScanner.get("2021-6a.txt");
		String[] line = myScanner.nextLine().split(",");
		ArrayList<Integer> fish = new ArrayList<>(Arrays.stream(line).mapToInt(Integer::parseInt).boxed().toList());
		
		System.out.println(play1(fish,80));
	}
	public static int play1(ArrayList<Integer> fish, int days) {
	
		//loop for days and each Integer in fish. Reset addFish each loop
		for(int i = 0; i<days;i++) {
			int addFish = 0;
			for(int x = 0; x < fish.size();x++) {
				
				//if fish is 0, reset to 6 and increment add fish, else decrement
				if( fish.get(x) == 0) {addFish ++; fish.set(x, 6);}
				else {fish.set(x,(fish.get(x)-1));}
			
			}
			
			//add one fish at age 8 for each addFish
			for(int a = 0;a<addFish;a++) {fish.add(8);}
		}
		
		//return final num of fish
		return fish.size();
	}

}
