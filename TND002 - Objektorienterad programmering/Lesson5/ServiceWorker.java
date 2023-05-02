package lesson5;

public class ServiceWorker extends Employee implements ContractWork {

	private int travel;
	public static double hourlyRate = 1.0*Employee.hourlyRate;
	
	public ServiceWorker(String arg) {
		super(arg);
		travel = 0;
	}
	
	public void setBaseHours(int arg) {
		baseHours = arg;
	}
	
	public void setHours(int arg) {
		travel = arg;
	}
	
	public double computeBonus() {
		return travel * ServiceWorker.hourlyRate;
	}
}
