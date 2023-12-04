package Common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

//class with single method, to start scanners for reading in puzzle inputs. Finds path to current dir, returns scanner of txt file from data folder 

public class GetScanner {
	public static Scanner get(int year,String fileName) {

		InputStream stream = GetScanner.class.getClassLoader().getResourceAsStream("PuzzleText\\" + year + "\\" + fileName + ".txt");
		assert stream != null;
		return new Scanner(stream);

	}
	public static InputStream getStream(int year,String fileName) {

		InputStream stream = GetScanner.class.getClassLoader().getResourceAsStream("PuzzleText\\" + year + "\\" + fileName + ".txt");
		assert stream != null;
		return stream;

	}
}
