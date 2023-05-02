package lesson5;
public abstract class Employee implements Comparable<Employee>{

	private String name;	
	public static double hourlyRate = 150.0;
	protected int baseHours;
	
	
	public Employee(String arg) {
		name = arg;
	}		
	
	public abstract void setBaseHours(int arg);
		
	public int compareTo(Employee arg) {
		return name.compareTo(arg.name);
	}
	
	public String toString() {
		String bonus = null;
		if (this instanceof ContractWork) {
			ContractWork temp = (ContractWork) this;
			return String.format("Name: %17s, Base salary: %6.2f, Bonus: %6.2f", name, hourlyRate*baseHours, temp.computeBonus());
		}		
		return String.format("Name: %17s, Base salary: %6.2f", name, hourlyRate*baseHours);
	}
}
