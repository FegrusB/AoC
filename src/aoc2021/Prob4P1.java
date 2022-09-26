package aoc2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

//Solve for day four, part one of AoC2021
public class Prob4P1 {

	public static void main(String[] args) {
		
		try {
			
			String mainAdd = new File("").getAbsolutePath();
			File myFile = new File(mainAdd + "\\data\\2021-4a.txt");
			Scanner myScanner = new Scanner(myFile);
			String[] randInputS = myScanner.nextLine().split(",");
			ArrayList<Integer[]> buildBoard = new ArrayList<Integer[]>();
			
			while(myScanner.hasNextLine()) {
				String nl = myScanner.nextLine();
				if (!(nl == "")) {
					if( nl.charAt(0) == ' ') {nl = nl.substring(1);}
				
					
					Integer[] IntArr = new Integer[intArr.length]; 
					for(int i = 0; i<intArr.length;i++){IntArr[i] = Integer.valueOf(intArr[i]);}
					buildBoard.add(IntArr);
				}
			}
			myScanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
			e.printStackTrace();
		} 
		
		System.out.println();

	}

}

//class to store board information
class board {

	
	private square[][] board;
	private HashSet<Integer> numsHash;
	
	public board(square[][] boardNums){
		this.board = boardNums.clone();
	
		for (square[] row:board) {
			for(square sq:row) {
				numsHash.add(sq.getNum());
			}
		}
	}
	
	public boolean checkHash(int i) {
		
		boolean present = numsHash.contains(i);
		
		return present;
	}

	public void setSquare(int in) {
		
		for (square[] row:board) {
			for(square sq:row) {
				if( in == sq.getNum()) { sq.setChecked();break;}
			}
		}
		
	}
}

//simple class to store number and checked for a single square. Num is gettable, checked is gettable and settable 
class square{
	
	private int num;
	private boolean checked;
	
	//constructor, just takes initial values.
	public square(int numIn, boolean checkedIn) {
		this.num = numIn;
		this.checked = checkedIn;
	}
	
	//getter and setter for checked. getter for num.
	public void setChecked() {this.checked = true;}
	public boolean getChecked() {return checked;}
	public int getNum() {return this.num;}
}

