package example2;

public class Question1 {

	public static void main(String[] args) {
		
		Database data = new Database();	
		
		System.out.println(data.loadStudents("Students.txt", 4));
		
		System.out.println(); System.out.println(data.addExam("Computer graphics", 1234));
		System.out.println(); System.out.println(data.addExam("Algorithms", 2345));
		System.out.println(); System.out.println(data.addExam("Programming", 3456));
		
		System.out.println();
		
		System.out.println(data.markStudent("Secondname1", 1234, 22));
		System.out.println(data.markStudent("Secondname1", 2345, 25));
		System.out.println(data.markStudent("Secondname1", 3456, 13));
		
		System.out.println(data.markStudent("Secondname2", 1234, 12));
		System.out.println(data.markStudent("Secondname2", 2345, 15));
		System.out.println(data.markStudent("Secondname2", 3456, 27));
		
		System.out.println(data.markStudent("Secondname3", 1234, 9));
		System.out.println(data.markStudent("Secondname3", 2345, 12));
		System.out.println(data.markStudent("Secondname3", 3456, 20));
		
		System.out.println(data.markStudent("Secondname4", 1234, 20));
		System.out.println(data.markStudent("Secondname4", 2345, 18));
		System.out.println(data.markStudent("Secondname4", 3456, 21));
		
		System.out.println(data.markStudent("NoSuchStudent", 3456, 21));
		
		System.out.println();
		System.out.println(data);
		
		
		System.out.println(data.checkStudent(2));
		
		
		System.out.println(data.checkExam(1));
	}

}
