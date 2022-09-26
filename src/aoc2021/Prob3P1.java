package aoc2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//Solve for day three, part one of AoC2021
//Given a series of binary inputs calculate two new binaries, from first the most, then least common bit in each position 
public class Prob3P1 {
	
 	
	public static void main(String[] args) {
		
		int len = 0;
		
		//try to find file, copy file to ArrayList data of type boolean arrays, representing binary. 
		ArrayList <boolean[]> data = new ArrayList<boolean[]>();
		try {
			String mainAdd = new File("").getAbsolutePath();
			File myFile = new File(mainAdd + "\\data\\2021-3.txt");
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
		
		//create arrs for analysing data
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

		//pass binaries to getDec, returns equivalent decimal value. store in gamma
		int gamma = getDec(out);
		
		//binary NOT the final binary, to get least common bit of each pos. store getDec in epsilon
		for(int c = 0;c<len;c++) {out[c] = !out[c];};
		int epsilon =  getDec(out);
		
		//print final answer
		System.out.println(gamma*epsilon);

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
