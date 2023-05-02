package lesson4;

public class Lesson4 {

	public static void main(String[] args) {
		
		Fruit.Strawberry theStrawberry = new Fruit.Strawberry("Strawberry", "red", 20.0);
		System.out.println(theStrawberry);
		System.out.println(theStrawberry.computeCost(1));
		System.out.println();
		
		Fruit theFruit = new Fruit("juicy", 10.0);
		System.out.println(theFruit);
		System.out.println(theFruit.computeCost(1));
		System.out.println();
		
		Fruit.Apple theApple = theFruit.new Apple("Apple");
		System.out.println(theApple);
		//System.out.println(theApple.computeCost(1));
		System.out.println();
		
		Fruit.Banana theBanana = theFruit.new Banana("Banana", "yellow", 5.0);		
		System.out.println(theBanana.diagnostics());
		System.out.println(theBanana);
		System.out.println();
		
	}

}
