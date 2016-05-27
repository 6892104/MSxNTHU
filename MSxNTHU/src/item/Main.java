package item;

public class Main {
    public static void main(String [] args) {
        ItemDatabase itemDatabase = new ItemDatabase();
		/*itemDatabase.init();
		itemDatabase.start();*/
//		itemDatabase.setFocusable(true);

        
        OtherItem bread = (OtherItem) itemDatabase.createItem("bread");
        System.out.println(bread.hp);
    }
}
