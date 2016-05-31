package item;

public class Item {
	protected ItemType itemType;
	protected String name;
	protected int lvRequired;
	public boolean usable;
	public int x, y;
	private int width, height;
	protected int maxNum;
	public int amount;
	protected int price;
	protected int probability;
	
	public Item(String name, int lvRequired, int price, int probability) {
		this.x = -100;
		this.y = -100;
		this.usable = false;
		this.width = 40;
		this.height = 40;
		
		this.maxNum = 1;
		this.amount = 1;

		this.name = name;
		this.lvRequired = lvRequired;
		this.price = price;
		this.probability = probability;
	}

	public String name() {
		return this.name;
	}
	public ItemType type(){
		return this.itemType;
	}
	public int maxNum(){
		return maxNum;
	}
	public int width() {
		return this.width;
	}
	public int height() {
		return this.height;
	}
	public enum ItemType {
		consumable, equipment, otherItem
	};
}
