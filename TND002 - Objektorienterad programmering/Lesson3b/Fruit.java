package lesson3b;

public class Fruit {
	
	protected final String NAME, SPECIFICS;
	protected final double PRICE;
	private int amount;

	public Fruit(Fruit arg) {
		NAME = arg.NAME; SPECIFICS = arg.SPECIFICS; PRICE = arg.PRICE; 
	}
	
	public Fruit(String arg1, String arg2, double arg3) {
		NAME = arg1; SPECIFICS = arg2; PRICE = arg3; amount = 0;
	}
	
	public int getAmount() {
		return amount;
	}
		
	public String[] getInfo() {
		String[] temp = new String[2];
		temp[0] = NAME;
		temp[1] = SPECIFICS;
		return temp;
	}
	
	public void stockUp(int arg) {
		amount = amount + arg;
	}

	public boolean isThisFruit(String arg1, String arg2) {
		if (NAME.equals(arg1) && SPECIFICS.equals(arg2)) return true;
		else return false;
	}
	
	public int getFruit(int arg) {
		if (arg < amount) amount = amount - arg;
		else {arg = amount; amount = 0;}
		return arg;
	}
	
	public String theName() {
		if (this instanceof Apple) {
			return "Apple";
		}
		else if (this instanceof Strawberry) {
			return "Strawberry";			
		}
		else return "Fruit";
	}
		
	public String toString() {
		return String.format("Name: %8s, Specifics: %8s, Price: %5.2f, Amount: %3d",NAME, SPECIFICS, PRICE, amount);
	}
}
