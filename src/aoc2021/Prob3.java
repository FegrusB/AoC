package aoc2021;

import java.util.ArrayList;
import java.util.Scanner;

//Solve for day three, part two of AoC2021
//Given a series of binary return two new binaries.
//take first the most, then least common bit in each position, and removing all binaries that don't share this bit
//return decimal value of final remaining binary
public class Prob3 {
	
	public static void main(String[] args) {
		
		int len = 0;
		ArrayList <boolean[]> data = new ArrayList<>();
		
		//Use GetScanner to create scanner, of un-separated 0/1 chars, representing binary, copy ArrayList data of boolean arrays.
		Scanner myScanner;
		myScanner = GetScanner.get("2021-3.txt");
		while(myScanner.hasNextLine()) {
			String nl = myScanner.nextLine();
			len = nl.length();
			boolean[] nB = new boolean[len];
			for(int i = 0; i<nl.length();i++){ char c = nl.charAt(i); if(c == '1') {nB[i] = true;}}
			data.add(nB);
		}
		myScanner.close();
	
		////pass data ArrayList, length to gammaEpsilon, returns int[], returns[0] = gamma, [1] = epsilon
		//problem 1 solution is gamma * epsilon. Print.
		int[] gammaEpsilon = gammaEpsilon(data, len);		
		int out = gammaEpsilon[0]*gammaEpsilon[1];
		System.out.println("Problem 1 solution: " + out);
		
		
		//pass data ArrayList, length, and bool for oxy or co2. returns a decimal int from getDec
		//problem 2 solution is oxy * co2. Print.
		int oxy = getRating(data,len,false);
		int co2 = getRating(data,len,true);
		out = oxy*co2;
		System.out.println("Problem 2 solution: " + out);

	}
	
	public static int[] gammaEpsilon(ArrayList<boolean[]> dataIn,int len) {
		
		int[] returns = new int[2];
		@SuppressWarnings("unchecked")
		ArrayList<boolean[]> data = (ArrayList<boolean[]>) dataIn.clone();
		
		//create arrays for analysing data
		int[] count = new int[len];
		boolean[] out = new boolean[len];
		
		//count number of Ts in each position, store in count
		for (boolean[] binary:data) {
			for (int c = 0;c<len;c++){
				if(binary[c]) {count[c] += 1;}
			}
		}
		
		//for each position, if position value of count > half the size of data, 1s are more common, store T in out
		int s = data.size()/2;
		for(int c = 0;c<len;c++) {if(count[c]>s||count[c]==s){out[c] = true;}}

		//pass binaries to getDec, returns equivalent decimal value. this is gamma, store in returns[0]
		returns[0] = getDec(out);
		
		//binary NOT out, to get the least common bit of each pos. pass to getDec store result in returns[1] this is epsilon
		for(int c = 0;c<len;c++) {out[c] = !out[c];}
		returns[1] =  getDec(out);
		
		//
		return returns;
		
	}
	
	//function to calculate oxygen or co2 rating from data. 
	//for each position, take the most common bit for oxy, and least for co2. Remove every binary that doesn't match this bit, then move on to next position.
	//return decimal from getDec from last remaining binary
	public static int getRating (ArrayList<boolean[]> dataIn, int len, boolean rating) {
		
		//copy dataIn to local data
		@SuppressWarnings("unchecked")
		ArrayList<boolean[]> data = (ArrayList<boolean[]>) dataIn.clone();
		float[] count = new float[len];
		int c = 0;
		
		//while there is more than 1 remaining data
		while (!(data.size()==1)){ 
			
			//s recalculated each loop to account for removed entries. rems is to re-declared to account for changed size
			float s = (float) data.size()/2;
			boolean rem;
			boolean[] rems = new boolean[data.size()]; 

			//get most common bit in position c
			for (boolean[] binary:data) {if(binary[c]) {count[c] += 1;}}
			
			//if current bit is the most common rem = rating. rating = T for oxy F for co2
			//else rem = !rating
			if(count[c]>s||count[c]==s){ rem = rating;}
			else{rem  = !rating;}
			
			//get each entry left in data, if a bit at current position = rem, add rems at same index = T
			for(int x = 0; x<data.size();x++) {boolean[] testing = data.get(x);if(testing[c] == rem){rems[x] = true;}}
				
			//for each entry where rems = t remove entry from data. Start from top to not affect indexing.
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
