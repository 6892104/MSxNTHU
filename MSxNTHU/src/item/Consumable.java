package item;


public class Consumable extends Item {
	private int hp, mp;
	private int maxNum;
	
	public Consumable(String name, int lvRequired, int price, int probability, int hp, int mp,int maxNum) {
		super(name, lvRequired, price, probability);
		this.hp = hp;
		this.mp = mp;
		this.maxNum = maxNum;
		this.itemType = ItemType.consumable;
	}

	public Consumable(Consumable c) {
		super(c.name, c.lvRequired, c.price, c.probability);
		this.hp = c.hp;
		this.mp = c.mp;
		this.maxNum = c.maxNum;
		this.itemType = ItemType.consumable;
	}
}
