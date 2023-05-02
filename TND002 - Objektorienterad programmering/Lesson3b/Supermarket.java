package lesson3b;
import java.util.ArrayList;
public class Supermarket {

	private ArrayList<Apple> soldApples = new ArrayList<Apple>();
	private ArrayList<Strawberry> soldStrawberries = new ArrayList<Strawberry>();
	private ArrayList<Fruit> availableFruit = new ArrayList<Fruit>();
		
	
	public void addFruit(String arg1, String arg2, double arg3) {
		availableFruit.add(new Fruit(arg1,arg2,arg3));
	}
	
	public void stockUp(String arg1, String arg2, int arg3) {
		for (int i=0; i < availableFruit.size(); i++) {
			if (availableFruit.get(i).isThisFruit(arg1, arg2)) {
				availableFruit.get(i).stockUp(arg3);
			}
		}
	}
	
	public void takeInventory() {
		for (int i = 0; i < availableFruit.size(); i++) {
			String[] fruit = availableFruit.get(i).getInfo();
			int inStore = availableFruit.get(i).getAmount();
			int bought = 0;
			if (fruit[0].equals("Apple")) {
				for (int j=0; j < soldApples.size(); j++) {
					if (soldApples.get(j).isThisFruit(fruit[0], fruit[1])) {
						bought = bought + soldApples.get(j).getAmount();
					}
				}
				System.out.println(String.format("Available apples (%6s): %3d, Sold apples: %3d", fruit[1], inStore, bought));
			}
			else if (fruit[0].equals("Strawberry")) {
				for (int j=0; j < soldStrawberries.size(); j++) {
					if (soldStrawberries.get(j).isThisFruit(fruit[0], fruit[1])) {
						bought = bought + soldStrawberries.get(j).getAmount();
					}
				}
				System.out.println(String.format("Available strawberries (%6s): %3d, Sold: %3d", fruit[1], inStore, bought));				
			}
		}		
	}
	
	public void buyFruit(String arg1, String arg2, String arg3, int arg4) {
		for (int i=0; i < availableFruit.size(); i++) {
			if (availableFruit.get(i).isThisFruit(arg1, arg2)) {
				if (arg1.equals("Apple")) {soldApples.add(new Apple(arg3,availableFruit.get(i),arg4));} 
				else if (arg1.equals("Strawberry")) {soldStrawberries.add(new Strawberry(arg3,availableFruit.get(i),arg4));} 
			}
		}
	}
	
	public String customerInfo(String arg) {
		String result="";
		for (int i = 0; i < soldApples.size(); i++) {
			if (arg.equals(soldApples.get(i).getCustomer())) result = result + soldApples.get(i) + "\n";
		}
		for (int i = 0; i < soldStrawberries.size(); i++) {
			if (arg.equals(soldStrawberries.get(i).getCustomer())) result = result + soldStrawberries.get(i) + "\n";
		}
		return result;
	}
	
}
