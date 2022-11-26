package aoc2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Prob6 {

	public static void main(String[] args) {
		
		//Read in starting fish to arraylist
		Scanner myScanner = GetScanner.get("2021-6.txt");
		String[] line = myScanner.nextLine().split(",");
		ArrayList<Integer> fishSetup = new ArrayList<>(Arrays.stream(line).mapToInt(Integer::parseInt).boxed().toList());
		long[] fish = new long[9];
		
		//count number of fish at each stage, store in fish
		for(int i = 0; i<9;i++) {for(Integer x:fishSetup) {if(x==i) {fish[i]++;}}}

		//System.out.println(play1(fish,80));
		System.out.println(play2(fish,256));
	}
	@SuppressWarnings("unused")
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
	
	public static long play2(long[] fish, int days) {
		
		
		long last;
		long next = 0;
		
		//loop through each possible age, each day move fish at each age down one.
		//if at age 0 move fish to age 6, add in more at age 8
		for(int i = 0;i<days;i++) {	
			for(int age = 8;age>-1;age--) {
				last = next;
				if(age == 0) {
					fish[8] = fish[age];
					fish[6] += fish[age];
				} else {
					next = fish[age];
				}
				fish[age]=last;
			}
		
		}
		
		//sum num of fish at each age.
		long sum = 0;
		for(long x:fish) {sum+=x;}
		return sum;
		
	}

}
