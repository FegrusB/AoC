package aoc2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Prob3P2 {
	
	public static void main(String[] args) {
		
		int len = 0;


		int oxy;
		int co2;
		
		
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

		oxy = getRating(data,len,false);
		co2 = getRating(data,len,true);
		
		System.out.print(oxy);
		System.out.println();
		System.out.print(co2);
		System.out.println();
		
		System.out.println(oxy*co2);

	}
	
	public static int getRating (ArrayList<boolean[]> dataIn, int len, boolean rating) {
		
		@SuppressWarnings("unchecked")
		ArrayList<boolean[]> data = (ArrayList<boolean[]>) dataIn.clone();
		float[] count = new float[len];
		int c = 0;
		
		while (!(data.size()==1)){ 
			
			float s = (float) data.size()/2;
			boolean rem;
			boolean[] rems = new boolean[data.size()]; 

			for (boolean[] binary:data) {if(binary[c]) {count[c] += 1;}}
			
			if(count[c]>s||count[c]==s){ rem = rating;}
			else{rem  = !rating;}//else {if(count[c]<s) {rem = rating;}}
			
		
			for(int x = 0; x<data.size();x++) {
				boolean[] testing = data.get(x);
				if(testing[c] == rem){rems[x] = true;}
			}
				
			
			for(int i = rems.length - 1; i>-1;i--) {
				if(rems[i]) {data.remove(i);}
			}
			
			c++;
		}
		
		return getInt(data.get(0));
		
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
