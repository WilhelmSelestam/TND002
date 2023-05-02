package lab1;

public class Lab1 {

	public static void main(String[] args) {

		double[] da1 = {1.0, 2.0, 3.0};
		
		double[] da2 = {0.0, -0.5, -2.0};
		
		System.out.println(da1); // Vi får addressen där vektorn är lagrad i heap minnet, addresser lagras i stack, sakerna i heap.
		
		double[] da3 = addition(da1, da2);
		
		System.out.println(da3[1]);
		
		printVector(da1);
	}
	
	public static double[] addition(double[] arg1, double[] arg2) {
		double[] sum = new double[3];
		for(int i = 0; i < arg1.length; i++) {
			sum[i] = arg1[i] + arg2[i];
		}
		return sum;
	}
	
	public static double[] subtraction(double[] arg1, double[] arg2) {
		double[] sum = new double[3];
		for(int i = 0; i < arg1.length; i++) {
			sum[i] = arg1[i] - arg2[i];
		}
		return sum;
	}
	
	public static void printVector(double[] arg) {
		if(arg.length == 3) {
			String output = String.format("%3.1f %3.1f %3.1f", arg[0], arg[1], arg[2]);
			System.out.println(output);
		}
	}

}
