package lesson1b;

public class ManipulationsB {
	
	private static String species;
	
	private String name;
	
	public ManipulationsB() {
		name = ""; species = "Human"; 
		System.out.println(name); System.out.println(species);
	}
	
	public ManipulationsB(String arg) {
		name = arg; species = "Human";
		System.out.println(name); System.out.println(species);
	}
	
	public ManipulationsB(String arg1, String arg2) {
		setName(arg1); setSpecies(arg2);
		System.out.println(name); System.out.println(species);
	}
	
	public static void setSpecies(String arg) {
		species = arg;
	}

	public static String getSpecies() {
		return species;
	}
	
	public void setName(String arg) {
		name = arg;
	}
	
	public String getName() {
		return name;
	}
	
	public static void staticSwap(ManipulationsB h1, ManipulationsB h2) {
		String temp; temp = h1.getName(); h1.setName(h2.getName()); h2.setName(temp);
	}
	
	public void instanceSwap(ManipulationsB h) {
		String temp = name;
		name = h.getName(); 
		h.setName(temp);
	}
	
	public String toString() {
		return String.format("Species:%-10s, Name:%10s", species,name);
	}
}
