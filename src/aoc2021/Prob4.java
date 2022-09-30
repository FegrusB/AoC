package aoc2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

//Solve for day four, part one of AoC2021



public class Prob4 {

	public static void main(String[] args) {
		
		Scanner myScanner;
		myScanner = GetScanner.get("2021-4a.txt");
		
		String[] randInputS = myScanner.nextLine().split(",");
		Square[][] squares = new Square[5][5];
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
				boards.add(new Board(squares));
				Arrays.fill(squares, null);
				c = 0;
			}
			
		}
		boards.add(new Board(squares));
		myScanner.close();
		
		play1(randInputS,boards);
		play2(randInputS,boards);

	}
	
	private static void play1(String[] randInputS,ArrayList<Board> boardsIn) {

		ArrayList<Board> boards = new ArrayList<Board>();
		for (Board b:boardsIn) {
			Board nB = new Board(b);
			boards.add(nB);
			
		}
		
		int score = 0;
		int count = 0;
		int checkNum = 0;
		
		while(score == 0) {
			checkNum = Integer.parseInt(randInputS[count]);
			int countB = 0;
			
			while (score == 0 && countB<boards.size()) {
				score = boards.get(countB).check(checkNum);
				countB ++;
			}
			count++;
		}
		
		System.out.println(score);
		System.out.println(checkNum);
			
	}
	private static void play2(String[] randInputS,ArrayList<Board> boardsIn) {


		ArrayList<Board> boards = new ArrayList<Board>();
		for (Board b:boardsIn) {
			Board nB = new Board(b);
			boards.add(nB);
		}
	
		int score = 0;
		int count = 0;
		int checkNum = 0;
		int won = 0;
		
		while(won<boards.size()) {
			checkNum = Integer.parseInt(randInputS[count]);
			int countB = 0;
			
			while (countB<boards.size()) {
				score = boards.get(countB).check(checkNum);
				countB ++;
			}
			
			if(!(score == 0)) {
				won++;

			}
			count++;
		}
		
		System.out.println(score);
		System.out.println(checkNum);
			
	}
	
}



//class to store board information
class Board {

	
	private Square[][] board;
	private HashSet<Integer> numsHash;
	
	public Board(Square[][] boardNums){
		this.board = boardNums.clone();
		this.numsHash = new HashSet<Integer>();
	
		for (Square[] row:board) {
			for(Square sq:row) {
				this.numsHash.add(sq.getNum());
			}
		}
	}
	
	public Board(Board ob) {
		this.numsHash = ob.numsHash;
		this.board = new Square[5][5];
		
		for(int x = 0; x<5;x++){
			for(int y = 0;y<5;y++){
				this.board[x][y] = new Square(ob.board[x][y]);
			}
		}
	}
	
	private boolean checkHash(int i) {
		
		boolean present = numsHash.contains(i);
		
		return present;
	}

	private void setSquare(int in) {
		
		for (Square[] row:board) {
			for(Square sq:row) {
				if( in == sq.getNum()) { sq.setChecked();break;}
			}
		}	
	}
	
	private boolean checkFin() {	
		
		int count = 0;
		
		for(int x = 0; x<5;x++){
			for(int y = 0;y<5;y++){
				if(board[x][y].getChecked()) {count++;}
				if (count==5) {return true;}
			}
			count = 0;
		}
		for(int y = 0;y<5;y++) {
			for(int x = 0;x<5;x++) {
				if(board[x][y].getChecked()) {count++;}
				if (count==5) {return true;}
			}
			count = 0;
		}
		return false;
	}
	private int getScore() {
		
		int score = 0;
		
		for(int x = 0; x<5;x++) {
			for(int y = 0;y<5;y++) {
				if(!(board[x][y].getChecked())) {score = score + board[x][y].getNum();}
			}
		}
		
		return score;
	}
	
	public int check(int in) {
		
		int score = 0;

		if(this.checkHash(in)){
			setSquare(in);
			if(checkFin()) {
				score = getScore();
			}
		}
		return score * in;
	
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
	public Square(Square s) {
		this.num = s.num;
		this.checked = false;
	}
	
	//getter and setter for checked. getter for num.
	public void setChecked() {this.checked = true;}
	public boolean getChecked() {return checked;}
	public int getNum() {return this.num;}
}

