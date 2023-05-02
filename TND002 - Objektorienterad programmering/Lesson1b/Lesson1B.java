package lesson1b;

public class Lesson1B {
	
	public static void main(String[] args) {
		
		// Instance variables vs class variables
		
		ManipulationsB.setSpecies("Human");
		System.out.println(ManipulationsB.getSpecies());
		
		// ManipulationsB. 
		
		ManipulationsB human1 = new ManipulationsB();
		// Access if name and species are public: person1.name and ManipulationsB.species
		ManipulationsB human2 = new ManipulationsB();
		
		// human1.name = "Paul"; human2.name = "Astrid";
		
		// System.out.println(human1.name); System.out.println(human2.name);
		
		// Instance methods
		
		human1.setName("Paul"); human2.setName("Astrid");
		
		System.out.println(human1.getName() + " and " +human2.getName());
		
		String temp = human2.getName(); 				
		human2.setName(human1.getName());
		human1.setName(temp);
		
		System.out.println(human1.getName() + " and " +human2.getName());
		
		ManipulationsB.staticSwap(human1, human2);
		
		System.out.println(human1.getName() + " and " +human2.getName());
		
		human1.instanceSwap(human2);
		
		System.out.println(human1.getName() + " and " +human2.getName());
		
		// The class String
		
		int days = 365;
		String text = String.format("One year has %1d days", days);
		System.out.println(text);
		
		double number = 654.321;
		text = String.format("The formatted number %7.3f is %1.1f", number,number);
		System.out.println(text);
	
		days = 365;
		String myText = "year";		
		text = String.format("One %4s has %3d days", myText, days);
		System.out.println(text);
		
		String severalNames = "Paul Astrid,Thomas  Sybill";
		
		String[] strArray = severalNames.split(",");
		System.out.println(strArray.length);
		
		human1 = new ManipulationsB("Paul");
		human2 = new ManipulationsB("Astrid");
		
		System.out.println(human1);
		System.out.println(human2);
		
		ManipulationsB dog = new ManipulationsB("Barky","Dog");
		System.out.println(human1);
		
		String input = "Paul Anki Cesar   Miranda";
		
		String[] array = input.split(" +");
		ManipulationsB[] people = new ManipulationsB[array.length];
		
		for (int i=0; i < array.length; i++) {
			people[i] = new ManipulationsB(array[i]);
		}
		
		int i=0;
		while(i < array.length) {
			people[i] = new ManipulationsB(array[i]); i++;
		}
		
		i=0;
		do {
			people[i] = new ManipulationsB(array[i]); i++;
		} while(i < array.length);
	}

}
