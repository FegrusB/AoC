package aoc2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Prob6 {

	public static void main(String[] args) {
		
		Scanner myScanner = GetScanner.get("2021-6a.txt");
		String[] line = myScanner.nextLine().split(",");
		int[] startFish = Arrays.stream(line).mapToInt(Integer::parseInt).toArray();
		//ArrayList<Integer> fish = new ArrayList<>(Arrays.stream(startFish).boxed().toList());
		
		
		ArrayList<Integer> fish = new ArrayList<>(Arrays.stream(line).mapToInt(Integer::parseInt).boxed().toList());
		System.out.println();
		
	}

}
