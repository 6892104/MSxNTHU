package item;

import java.util.Random;

public class Consumable extends Item {
	public Consumable(String name, int lvRequired, int atk, int matk, int def, int mdef) {
		super(name, lvRequired, atk, matk, def, mdef);
	}

	public Consumable(Consumable c) {
		super(c.name, c.lvRequired, c.atk, c.matk, c.def, c.mdef);
		this.atk = setRandom(this.atk);
		this.matk = setRandom(this.matk);
		this.def = setRandom(this.def);
		this.mdef = setRandom(this.mdef);
	}
	private int setRandom(int value) {
		Random r = new Random();
		int i = r.nextInt(21) -10;
		return value + value*i/100;
	}
}
