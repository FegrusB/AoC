package aoc2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//Solve for day three, part two of AoC2021
//Given a series of binary return two new binaries.
//take first the most, then least common bit in each position, and removing all binaries that dont share this bit
//return decimal value of final remaining binary
public class Prob3P2 {
	
	public static void main(String[] args) {
		
		int len = 0;
		
		//try to find file, copy file to ArrayList data of type boolean arrays, representing binary.
		ArrayList <boolean[]> data = new ArrayList<boolean[]>();
		try {
			File myFile = new File("C:\\Users\\fergu\\eclipse-workspace\\aoc2021\\data\\2021-3.txt");
			Scanner myScanner = new Scanner(myFile);
			while(myScanner.hasNextLine()) {
				String nl = myScanner.nextLine();
				len = nl.length();
				boolean[] nB = new boolean[len];
				for(int i = 0; i<nl.length();i++){ char c = nl.charAt(i); if(c == '1') {nB[i] = true;}}
				data.add(nB);
			}
			myScanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
			e.printStackTrace();
		} 

		//pass data ArrayList, length, and bool for oxy or co2. returns a decimal int from getDec
		int oxy = getRating(data,len,false);
		int co2 = getRating(data,len,true);

		//print final value
		System.out.println(oxy*co2);

	}
	
	//function to calculate oxygen or co2 rating from data. 
	//for each position, take the most common bit for oxy, and least for co2. Remove every binary that doesnt match this bit, then move on to next position.
	//return decimal from getDec from last remaining binary
	public static int getRating (ArrayList<boolean[]> dataIn, int len, boolean rating) {
		
		//copy dataIn to local data
		@SuppressWarnings("unchecked")
		ArrayList<boolean[]> data = (ArrayList<boolean[]>) dataIn.clone();
		float[] count = new float[len];
		int c = 0;
		
		//while there is more than 1 remaining data
		while (!(data.size()==1)){ 
			
			//s recalculated each loop to account for removed entries. rems redeclared to account for changed size
			float s = (float) data.size()/2;
			boolean rem;
			boolean[] rems = new boolean[data.size()]; 

			//get most common bit in position c
			for (boolean[] binary:data) {if(binary[c]) {count[c] += 1;}}
			
			//if current bit is most common rem = rating. rating = T for oxy F for co2
			//else rem = !rating
			if(count[c]>s||count[c]==s){ rem = rating;}
			else{rem  = !rating;}
			
			//get each entry left in data, if bit at current position = rem, add rems at same index = T
			for(int x = 0; x<data.size();x++) {boolean[] testing = data.get(x);if(testing[c] == rem){rems[x] = true;}}
				
			//for each entry where rems = t remove entry from data. Start from top to not effect indexing.
			for(int i = rems.length - 1; i>-1;i--) {if(rems[i]) {data.remove(i);}}
			
			//Increment bit position
			c++;
		}
		
		//return int value of final binary passed to getDec 
		return getDec(data.get(0));
		
	}
	
	//function to calculate decimal value of binary number
	public static int getDec(boolean[] binNum) {
		
		int out = 0;
		int e = 0;
		
		//starting from end (equivalent to right bit), if bit = T, out = out + 2 ^ position
		for(int i = (binNum.length - 1); i > -1 ;i--) {
			if (binNum[i]) {out = out + (int) Math.pow(2, e);}
			e+=1;			
		}
		
		//return final decimal
		return out;
		
	}
}
