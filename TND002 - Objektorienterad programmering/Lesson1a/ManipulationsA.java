package lesson1a;

public class ManipulationsA {

	public static boolean returnAnInt = true;
	private static double value = 4.0;
	
	public static void convert() {
		if (returnAnInt) {
			System.out.println(convert(value));
		}
		else
			System.out.println(value);
	}
	
	public static int convert(double arg) {
		return (int) arg;
	}
	
	public static double convert(int arg) {
		double temp = (double) arg;
		return temp;
	}
}
