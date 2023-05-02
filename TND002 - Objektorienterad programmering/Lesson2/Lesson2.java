package lesson2;

import java.io.*;

public class Lesson2 {

	public static BufferedReader console  = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) {		
		Database db = new Database();
		String answer = null;		
		do {
			try {
			System.out.print("Your command: ");
			answer = console.readLine();
			if (answer.equals("load")) {
				System.out.println(db.load("inputFile.txt"));
			}
			else if (answer.equals("save")) {
				System.out.println(db.save("outputFile.txt"));
			}
			else if (answer.equals("end")) console.close(); break;
			}
			catch(IOException ierr) {}
		}
		while(!answer.equals("end"));
	}
}
