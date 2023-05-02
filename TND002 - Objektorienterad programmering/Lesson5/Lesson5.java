package lesson5;

import java.util.Collections;

public class Lesson5 {

	public static void main(String[] args) {
		
		Company theCompany = new Company();
		
		Programmer pr; SalesPerson sp; ServiceWorker sw;
		
		pr = new Programmer("Adam Programmer");
		pr.setBaseHours(30); pr.setHours(10);
		theCompany.addEmployee(pr);
		
		sw = new ServiceWorker("Tom Serviceworker");
		sw.setBaseHours(20); sw.setHours(40);
		theCompany.addEmployee(sw);
		
		sp = new SalesPerson("Bruno Salesperson");
		sp.setBaseHours(40); 
		theCompany.addEmployee(sp);
		
		System.out.println(theCompany);

		theCompany.sortEmployee();
		
		System.out.println(theCompany);		
	}

}
