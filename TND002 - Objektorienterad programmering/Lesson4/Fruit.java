package lesson4;
public class Fruit {

	private String specifics;
	private double price;
	private Fruit outerClass;
		
	
	public Fruit(String arg1, double arg2) {
		specifics = arg1; price = arg2; outerClass = this;
	}
		
	public String computeCost(int arg) {
		return "Fruit: " + price * arg;
	}
	
	public String toString() {
		return String.format("Specifics:%7s, Price: %5.2f", specifics, price);
	}
	
	public class Apple {
		private String name;
		public Apple(String arg1) {
			name = arg1;			
		}		
		public String toString() {
			return String.format("Name:%7s, Specifics:%7s, Price:%5.2f", name, specifics, price) + " (outer class call) " + outerClass.toString();
		}
	}
	
	public class Banana {
		private String name, specifics;
		private double price;
		public Banana(String arg1, String arg2, double arg3) {
			name = arg1; specifics = arg2; price = arg3;
		}		
		public String diagnostics() {
			return "Diagnostics: " + name + " " + specifics + " " + price;
		}
	}
	
	
	public static class Strawberry {
		private String name, specifics;
		private double price;
		public Strawberry(String arg1, String arg2, double arg3) {
			name = arg1; specifics = arg2; price = arg3;
		}	
		public String computeCost(int arg) {
			return "Strawberry: " + price * arg;
		}
		public String toString() {
			Fruit temp = new Fruit(specifics, price);
			return temp.toString();
		}
	}
}
