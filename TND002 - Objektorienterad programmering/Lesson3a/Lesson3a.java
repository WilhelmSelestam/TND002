package lesson3a;

public class Lesson3a {

	public static void main(String[] args) {
		
		// Setting up the TVs
		System.out.println("Number of TVs: " + TVSet.allTVs.size());
		new TVSet("Brand1");		
		new TVSet("Brand2", 4.0);
		new TVSet("Brand3", 1.0);
		new TVSet("Brand4", 2.5);
		new TVSet("Brand5", 4.5);	
		new TVSet("Brand6", 1.5);
		new TVSet("Brand7");
		new TVSet("Brand8", 4.5);
		System.out.println("Number of TVs: " + TVSet.allTVs.size());		
		TVSet.listTVs();
		System.out.println();
		
		// Setting the descriptions of Products and TVs.
		TVSet.allTVs.get(0).setDescription("The product is a TV, Its brand is brand1");
		System.out.println(TVSet.allTVs.get(0).getDescription()+"\n");
		
		// Setting up the stores
		System.out.println(Product.listAvailableStores());
		
		System.out.println(Product.selectStore(0));
		
		System.out.println(Product.listAvailableStores());
		
		System.out.println(Product.selectStore(2));
		
		System.out.println();
		System.out.println(Product.listAvailableStores());
		
		System.out.println(TVSet.allTVs.get(0));
	}

}
