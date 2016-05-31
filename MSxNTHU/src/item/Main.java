package item;

public class Main {
    public static void main(String [] args) {
        ItemDatabase itemDatabase = new ItemDatabase();
		/*itemDatabase.init();
		itemDatabase.start();*/
//		itemDatabase.setFocusable(true);

        
        Item water = itemDatabase.createItem("Ä«ªG");
        if(water != null) System.out.println(water.name);
        else System.out.println("No Apple");;
    }
}
