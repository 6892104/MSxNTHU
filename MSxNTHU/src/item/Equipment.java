package item;

import java.util.Random;

public class Equipment extends Item {
	private int atk, matk;
	private int def, mdef;
	
	public Equipment(String name, int lvRequired, int atk, int matk, int def, int mdef, int price, int probability) {
		super(name, lvRequired, price, probability);
		
		this.atk = atk;
		this.matk = matk;
		this.def = def;
		this.mdef = mdef;
		this.itemType = ItemType.equipment;
		
	}

	public Equipment(Equipment e) {
		super(e.name, e.lvRequired, e.price, e.probability);
		this.atk = setRandom(e.atk);
		this.matk = setRandom(e.matk);
		this.def = setRandom(e.def);
		this.mdef = setRandom(e.mdef);
		this.itemType = ItemType.equipment;
	}
	private int setRandom(int value) {
		Random r = new Random();
		int i = r.nextInt(21) -10;
		return value + value*i/100;
	}
}
