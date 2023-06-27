package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;


class GetScanner {

	public static Scanner get(String fileName) {

		InputStream stream = GetScanner.class.getClassLoader().getResourceAsStream("PuzzleText\\" + fileName + ".txt");
		assert stream != null;
		return new Scanner(stream);

	}
}
