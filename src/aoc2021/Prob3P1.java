package aoc2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Prob3P1 {
	
	public static void main(String[] args) {
		
		int len = 12;
		int[] count = new int[len];
		boolean[] out = new boolean[len];
		int gamma;
		int epsilon;
		
		
		ArrayList <boolean[]> data = new ArrayList<boolean[]>();
		try {
			File myFile = new File("C:\\Users\\fergu\\eclipse-workspace\\aoc2021\\data\\2021-3.txt");
			Scanner myScanner = new Scanner(myFile);
			while(myScanner.hasNextLine()) {
				String nl = myScanner.nextLine();
				boolean[] nB = new boolean[len];
				for(int i = 0; i<nl.length();i++){ char c = nl.charAt(i); if(c == '1') {nB[i] = true;}}
				data.add(nB);
			}
			myScanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
			e.printStackTrace();
		} 
		
		
		for (boolean[] binary:data) {
			for (int c = 0;c<len;c++){
				if(binary[c]) {count[c] += 1;}
			}
		}
		
		int s = data.size()/2;
		for(int c = 0;c<len;c++) {if(count[c]>s||count[c]==s){out[c] = true;}}

		gamma = getInt(out);
		for(int c = 0;c<len;c++) {out[c] = !out[c];};
		epsilon =  getInt(out);
		
		System.out.println(gamma*epsilon);

	}
	
	public static int getInt(boolean[] binNum) {
		
		int out = 0;
		int e = 0;
		
		for(int i = (binNum.length - 1); i > -1 ;i--) {
			if (binNum[i]) {out = out + (int) Math.pow(2, e);}
			e+=1;			
		}
		
		return out;
		
		
	}
	

}
