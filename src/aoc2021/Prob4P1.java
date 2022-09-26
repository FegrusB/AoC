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
			Square[][] squares = new Square[5][5];
			ArrayList<Square[]> buildBoard = new ArrayList<Square[]>();
			ArrayList<Board> boards = new ArrayList<Board>();
			int c = 0;
			while(myScanner.hasNextLine()) {
				String nl = myScanner.nextLine();
				if (!(nl == "")) {
					if( nl.charAt(0) == ' ') {nl = nl.substring(1);}
					nl = nl.replace("  ", " ");
					Square[] row = new Square[5];
					String[] split = nl.split(" ");
					for(int i = 0;i<split.length;i++) {if (!(split[i] == "")) {row[i] = new Square(Integer.parseInt(split[i]));}}
					squares[c] = row;
					c++;
				}else if(c != 0) {
					System.out.println();
					boards.add(new Board(squares));
					Arrays.fill(squares, null);
					c = 0;
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
class Board {

	
	private Square[][] board;
	private HashSet<Integer> numsHash;
	
	public Board(Square[][] boardNums){
		this.board = boardNums.clone();
	
		for (Square[] row:board) {
			for(Square sq:row) {
				numsHash.add(sq.getNum());
			}
		}
	}
	
	public boolean checkHash(int i) {
		
		boolean present = numsHash.contains(i);
		
		return present;
	}

	public void setSquare(int in) {
		
		for (Square[] row:board) {
			for(Square sq:row) {
				if( in == sq.getNum()) { sq.setChecked();break;}
			}
		}
		
	}
}

//simple class to store number and checked for a single square. Num is gettable, checked is gettable and settable 
class Square{
	
	private int num;
	private boolean checked;
	
	//constructor, just takes initial values.
	public Square(int numIn) {
		this.num = numIn;
		this.checked = false;
	}
	
	//getter and setter for checked. getter for num.
	public void setChecked() {this.checked = true;}
	public boolean getChecked() {return checked;}
	public int getNum() {return this.num;}
}

