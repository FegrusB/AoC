package aoc2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Prob6 {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		//Read in starting fish to arraylist
		Scanner myScanner = GetScanner.get("2021-6.txt");
		String[] line = myScanner.nextLine().split(",");
		ArrayList<Integer> fishMain = new ArrayList<>(Arrays.stream(line).mapToInt(Integer::parseInt).boxed().toList());
		int days = 256;
		ArrayList<ArrayList<Integer>> fishLists = new ArrayList<ArrayList<Integer>>();
		
		int c = 0;
		for (int i = 0; i<6;i++) {
			ArrayList<Integer> newList = new ArrayList<Integer>(fishMain.subList(c, c+50));
			fishLists.add(newList);
			c += 50;
		}
		
		ExecutorService service = Executors.newFixedThreadPool(6);
		
		Future<Integer> result1 = service.submit(new calcFishes(fishLists.get(0), days));
		Future<Integer> result2 = service.submit(new calcFishes(fishLists.get(1), days));
		Future<Integer> result3 = service.submit(new calcFishes(fishLists.get(2), days));
		Future<Integer> result4 = service.submit(new calcFishes(fishLists.get(3), days));
		Future<Integer> result5 = service.submit(new calcFishes(fishLists.get(4), days));
		Future<Integer> result6 = service.submit(new calcFishes(fishLists.get(5), days));
		
		int result1Out = result1.get();
		int result2Out = result2.get();
		int result3Out =result3.get();
		int result4Out =result4.get();
		int result5Out =result5.get();
		int result6Out =result6.get();
		
		Integer result = result1Out + result2Out + result3Out + result4Out +result5Out + result6Out;
		
		System.out.println(result);
	}

}

class calcFishes implements Callable<Integer>{
	
	//parameters and constructor to set them on tread creation
	ArrayList<Integer> fish;
	int days;
	public calcFishes(ArrayList<Integer> fishIn, int daysIn) {
		this.fish = fishIn;
		this.days = daysIn;
	}
	
	public Integer call() throws Exception {
	
		//loop for days and each Integer in fish. Reset addFish each loop
		for(int i = 0; i<days;i++) {
				int addFish = 0;
				for(int x = 0; x < fish.size();x++) {
					
					//if fish is 0, reset to 6 and increment add fish, else decrement
					if( fish.get(x) == 0) {addFish ++; fish.set(x, 6);}
					else {fish.set(x,(fish.get(x)-1));}
				
				}
				
				//add one fish at age 8 for each addFish
				for(int a = 0;a<addFish;a++) {fish.add(8);}
			
				System.out.println(i);
			}
			
			//return final num of fish
			return fish.size();
	}
}
