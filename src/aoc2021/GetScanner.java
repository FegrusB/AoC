package aoc2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//class with single method, to start scanners for reading in puzzle inputs. Finds path to current dir, returns scanner of txt file from data folder 
class GetScanner {
	
	//
	public static Scanner get(String fileName) {
		
		//strings to build address with, data folder, and absolute path to aoc2021
		String data = "\\data\\";
		String mainAdd = new File("").getAbsolutePath();
		
		//create file object with correct address and initialise myScanner
		File myFile = new File(mainAdd + data + fileName);
		Scanner myScanner = null;
		
		//try to create scanner of file at myFile, throw exception if no file
		try {
			myScanner = new Scanner(myFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//return final scanner
		return myScanner;
	
	}
}
