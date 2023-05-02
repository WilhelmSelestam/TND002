package lesson3b;
public class Strawberry extends Fruit {

	private String customer;
	private int amount;
	private double cost;
	
	public Strawberry(String arg1, Fruit arg2, int arg3) {
		super(arg2);
		customer = arg1;
		amount = arg2.getFruit(arg3);
		cost = amount * PRICE;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public String getCustomer() {
		return customer;
	}
	
	public String toString() {
		return String.format("Customer %6s bought %3d strawberries for %5.2f SEK", customer, amount, cost);
	}	
}
