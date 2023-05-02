package lesson1a;

public class Lesson1A {

	public static void main(String[] args) {
		
		System.out.println("Lesson 1A\n---------");
		
		// Primitive variables, casting operators and constants.
		
		double d1;
		//System.out.println("The double value is " + d1);		
		d1 = 4.0;
		System.out.println("The double value is " + d1);
		
		int i1 = 10;
		
		System.out.println("The sum of the double value and the integer value is " + (d1 + i1));

		System.out.println(d1 + i1 + " is the  sum of the double value and the integer value.");
		
		int i2 = (int) d1;
		
		System.out.println(i2);
		
		boolean b = true; System.out.println(b);
		
		final double d2 = 2.0;
		
		final double d3;
		d3 = 2.0; //d3 = 1.0;	
		
		// Static methods and overloading a method
		
		System.out.println(convert(1));
		
		System.out.println(convert(4.0));
		
		System.out.println(ManipulationsA.convert(1));
		
		System.out.println(ManipulationsA.convert(4.0));
		
		ManipulationsA manip = null; System.out.println(manip);
		
		manip = new ManipulationsA(); System.out.println(manip);
		
		// manip.
		
		ManipulationsA.convert();
		
		ManipulationsA.returnAnInt = false;
		
		ManipulationsA.convert();
	}
	
	public static double convert(int arg) {
		double temp = (double) arg;
		return temp;
	}
	
	public static int convert(double arg) {
		return (int) arg;
	}
		
}
