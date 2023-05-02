package lab1;

public class Lab1b {

	public static void main(String[] args) {
		
		Vector v1 = new Vector();
		
		System.out.println(v1);
		
		Vector vdef = new Vector(1.0, 2.0, 3.0);
		Vector.setDefault(vdef);
		
		v1.setToDefault();
		
		System.out.println(v1);
		
		Vector v2 = new Vector(1.0, 1.0, 2.0);
		System.out.println("Length: " + v1.length());
		
		
		double[][] m1 = {{1.0, 0.0, 0.0}, {0.0, 1.0, 0.0}, {0.0, 0.0, 1.0}};
		Vector mult = v1.matrixMult(m1);
		System.out.println(mult.toString());
		
		System.out.println("\n");
		
		
		double[][] m2 = {{1.0, 0.0}, {0.0, 1.0}};
		Vector mult2 = v1.matrixMult(m2);
		System.out.println(mult2.toString());
		
		System.out.println("\n");
		
		
		double[][] m3 = {{0.0, 1.0, 0.0}, {1.0, 0.0, 0.0}, {0.0, 0.0, 1.0}};
		
		Vector mult3 = v1.matrixMult(m3);
		System.out.println(mult3.toString());
		
		System.out.println("\n");
		
		
		Vector plus = Vector.plus(v1, v2);
		System.out.println(plus.toString());
		
		
		System.out.println(v1.minus(v2));
		
		System.out.println(v1.mult(2));
		
		System.out.println(v1.mult(v2));
		
		System.out.println(v1.compareTo(v2));
		
		v1.norm();
		v2.norm();
		
		System.out.println(v1.mult(v2));
		System.out.println(v2.mult(v2));
		

		
	}

}
