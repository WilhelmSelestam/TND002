package lesson3a;
import java.util.ArrayList;
import java.util.Random;
public class TVSet extends Product {

	private String brand;
	private int price;
	private String description;
	public static ArrayList<TVSet> allTVs = new ArrayList<TVSet>();
	
	public TVSet(String arg) {
		super("tv set");
		brand = arg;
		allTVs.add(this);
		Random rnd = new Random(); price = rnd.nextInt(5000)+10000;
	}
	
	public TVSet(String arg1, Double arg2) {
		super("tv set", arg2);
		brand = arg1;
		allTVs.add(this);
		Random rnd = new Random(); price = rnd.nextInt(5000)+10000;
	}
	
	public String setDescription(String arg) {
		String[] temp = arg.split(","); 
		description = temp[1].trim();
		return super.setDescription(temp[0].trim());
	}
	
	public String getDescription() {
		String result = "TV description: " + description + "\n";
		return result + "Product information: " + super.getDescription();
	}
	
	public static void listTVs() {
		for (int i = 0; i < allTVs.size(); i++) {
			System.out.println(allTVs.get(i).brand + " " + allTVs.get(i).price + " " + allTVs.get(i).getRating());
		}
	}
	
	public String toString() {
		String result = "The " + NAME + " is manufactured by " + brand + " and costs " + price + "SEK.\n";
		return result + super.toString();
	}
}
