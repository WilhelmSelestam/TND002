package lesson3a;
import java.util.ArrayList;
public class Product {

	public final String NAME;
	public final static String[] ALLSTORES = {"Norrköping", "Linköping", "Stockholm", "Malmö"};
	private Double userScore=null;
	private static ArrayList<String> availableStores;
	public String description;
	private static ArrayList<String> stores = new ArrayList<String>();
	
	public Product(String arg) {
		NAME = arg;
		convertArray();
	}

	public Product(String arg1, Double arg2) {
		NAME = arg1; userScore=arg2;
		convertArray();
	}
	
	private void convertArray() {
		availableStores = new ArrayList<String>();
		for (int i=0; i < ALLSTORES.length; i++) {
			availableStores.add(ALLSTORES[i]);
		}
	}
	
	public static String listAvailableStores() {
		String result = "Number, city\n";
		for (int i=0; i < availableStores.size(); i++) {
			result = result + i + " " + availableStores.get(i) + "\n";
		}
		return result;
	}
	
	public static String selectStore(int arg) {
		if (arg < availableStores.size()) {
			String temp = availableStores.get(arg);
			stores.add(temp);
			availableStores.remove(arg);
			return temp + " added";
		}
		return "Wrong number";
	}
	
	public String setDescription(String arg) {
		description = arg;
		return "Product description done";
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getRating() {
		if (userScore == null) return "Not rated yet";
		else return "Rating: " + String.valueOf(userScore);
	}
	
	public String toString() {
		String result = "This product ";
		if (userScore != null) {result = result + " has the user score " + userScore;} 
		result = result + " and is available in the shops in:";
		for (int i=0; i < stores.size()-1; i++) {
			result = result + " " + stores.get(i) + ", ";
		}		
		result = result + " and " + stores.get(stores.size()-1) + ".";
		return result;
	}
}
