package Common;

import java.io.InputStream;
import java.util.Scanner;


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
