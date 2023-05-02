package lesson5;
import java.util.ArrayList;
import java.util.Collections;
public class Company {

	private ArrayList<Employee> theStaff = new ArrayList<Employee>();
	
	public void addEmployee(Employee arg) {
		theStaff.add(arg);
	}
	
	public void sortEmployee() {
		Collections.sort(theStaff);
	}
	
	public String toString() {
		String result = "List:\n";
		for (int i=0; i < theStaff.size(); i++) {
			result = result + theStaff.get(i) + "\n";
		}		
		return result;
	}
}
