package lab1;

import java.lang.Math;

public class Vector {
	
	private double x, y, z;
	public static Vector vdef = new Vector(0.0, 0.0, 0.0);
	
	public Vector() {
		setToDefault();
	}
	
	public Vector(double arg1, double arg2, double arg3) {
		x = arg1;
		y = arg2;
		z = arg3;
	}
	
	public static void setDefault(Vector arg) {
		vdef.x = arg.x;
		vdef.y = arg.y;
		vdef.z = arg.z;
	}

	public void setToDefault() {
		x = vdef.x;
		y = vdef.y;
		z = vdef.z;
	}
	
	public static Vector plus(Vector arg1, Vector arg2) {
		Vector sum = new Vector();
		sum.x = arg1.x + arg2.x;
		sum.y = arg1.y + arg2.y;
		sum.z = arg1.z + arg2.z;
		
		return sum;
	}
	
	public Vector minus(Vector arg) {
		Vector difference = new Vector();

		difference.x = x - arg.x;
		difference.y = y - arg.y;
		difference.z = z - arg.z;
		
		return difference;
	}
	
	public Vector mult(double arg) {
		Vector product = new Vector();

		product.x = x * arg;
		product.y = y * arg;
		product.z = z * arg;
		
		return product;
	}
	
	public double mult(Vector arg) {
		double product = arg.x * x + arg.y * y + arg.z * z;
		return product;
	}
	
	public double length() {
		double length = Math.sqrt(x*x + y*y + z*z);
		return length;
	}
	
	public void norm() {
		x = 1/length() * x;
		y = 1/length() * y;
		z = 1/length() * z;
	}
	
	public Vector matrixMult(double[][] arg) {
		Vector vector = new Vector();
		
		if(arg.length == 3) {
			
			if(arg[0].length == 3 && arg[1].length == 3 && arg[2].length == 3) {
				vector.x = arg[0][0] * x + arg[0][1] * y + arg[0][2] * z;
				vector.y = arg[1][0] * x + arg[1][1] * y + arg[1][2] * z;
				vector.z = arg[2][0] * x + arg[2][1] * y + arg[2][2] * z;
			} else {
				vector.x = x;
				vector.y = y;
				vector.z = z;
			}
		} else {
			vector.x = x;
			vector.y = y;
			vector.z = z;
		}
		return vector;
	}
	
	public int compareTo(Vector arg) {
		
		Vector vector = new Vector(arg.x, arg.y, arg.z);
		
		if(length() == vector.length()) {
			return 0;
		} else if(length() > vector.length()) {
			return 1;
		} else {
			return -1;
		}
	}
	
	public String toString() {
		String output = String.format("%3.1f %3.1f %3.1f", x, y, z);
		return output;
	}

}
