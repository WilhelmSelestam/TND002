package lesson3b;

public class Lesson3b {

	public static void main(String[] args) {
		
		Supermarket supm = new Supermarket();
		
		supm.addFruit("Strawberry","red",10.0);
		supm.addFruit("Strawberry","sweet",10.0);
		supm.addFruit("Apple","green",12.0);
		supm.addFruit("Apple","yellow",12.0);
		
		supm.stockUp("Strawberry", "red", 20);
		supm.stockUp("Strawberry", "sweet", 30);
		supm.stockUp("Apple", "green", 15);
		supm.stockUp("Apple", "yellow", 25);
		
		supm.takeInventory();
		System.out.println();
		
		supm.buyFruit("Apple", "green", "Mark", 3);
		supm.buyFruit("Apple", "green", "Mark", 4);
		supm.buyFruit("Apple", "yellow", "Steven", 30);
		supm.buyFruit("Strawberry", "red", "Anne", 4);
		supm.buyFruit("Strawberry", "sweet", "Claire", 7);
		
		supm.takeInventory();
		System.out.println();
		
		System.out.println(supm.customerInfo("Mark"));
		System.out.println(supm.customerInfo("Steven"));
		System.out.println(supm.customerInfo("Anne"));
		System.out.println(supm.customerInfo("Claire"));
	}

}
