// Hejin Jeong

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Reads a file where the user's hands were one key to far to the right. Outputs
 * to standard output what the user intended to type.
 */
public class Typo {
	// The map from mistyped characters to the intended characters.
	private static Map<Character, Character> corrections = new HashMap<>();

	/**
	 * A method that creates a HashMap that has neighboring characters on the
	 * keyboard as its key-value pairs.
	 */
	private static void initCorrections() {

		// Create a String with all the keyboard characters in the order..
		String data = "`1234567890-=~!@#$%^&*()_+qwertyuiop[]\\QWERTYUIOP{}|asdfghjkl;\'ASDFGHJKL:\"zxcvbnm,./ZXCVBNM<>?";

		// Store each character as a key and the one on the right of the keyboard as its value in the HashMap.
		for (int i = 0; i < data.length() - 1; i++) {
			corrections.put(data.charAt(i), data.charAt(i + 1));
		}
	}

	/**
	 * Main method to implement the typo correction.
	 */
	public static void main(String[] args) {

		// Initialize the HashMap.
		System.out.println("Welcome to the Typo Correction System.");
		initCorrections();

		// Get the file name. Exit if none was provided.
		if (args.length == 0) {
			System.out.println("Please enter a file name when you run the program");
			return;
		}

		// Read and correct the file.
		try (Scanner in = new Scanner(new File(args[0]))) {
			while (in.hasNext()) {
				String notCorrected = in.nextLine();
				System.out.println("Before:  " + notCorrected);
				String corrected = correct(notCorrected);
				System.out.println("After:   " + corrected);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File " + args[0] + " could not be found.");
		}
	}

	/**
	 * Correct one line by shifting the characters one to the right on the keyboard.
	 * 
	 * @param line the line that was mis-typed
	 * @return the corrected line
	 */
	private static String correct(String line) {

		// Change the data type of the String parameter to the StringBuilder to make it mutable.
		StringBuilder newLine = new StringBuilder(line);

		// Loop through each character of the line.
		for (int i = 0; i < newLine.length(); i++) {
			if (newLine.charAt(i) == ' ') {
				// Leave the space character as it is.
				continue;
			} else {
				// Change the character to the one that is on the right of the keyboard.
				newLine.setCharAt(i, corrections.get(newLine.charAt(i)));
			}
		}

		// Change the data type of the corrected line to a String.
		return newLine.toString();
	}
}
