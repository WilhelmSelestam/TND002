package lesson2;
import java.io.*;
import java.util.ArrayList;
public class Database {
	private ArrayList<String> words; 
		
	public Database() {
		words = new ArrayList<String>();
	}
	
	public String load(String arg) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(arg));
			String result; String[] sarray;
			while((result = reader.readLine())!=null) {
				sarray = result.split(" +");
				for (int i=0; i < sarray.length; i++) {
					words.add(sarray[i]);
				}				
			}
			reader.close();
			return String.format("Text loaded:%4d words", words.size());
		}
		catch(IOException ierr) {
			return "Something went wrong";
		}
	}
	
	public String save(String arg) throws IOException {
			BufferedWriter writer = new BufferedWriter(new FileWriter(arg));
			for (int i=0; i < words.size(); i++) {
				writer.write(words.get(i)); writer.newLine();
			}
			writer.close();
			return String.format("Wrote%4d words to the file %14s", words.size(),arg);
	}	
}
