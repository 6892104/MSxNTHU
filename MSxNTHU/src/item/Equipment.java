package item;

import java.util.Random;

public class Equipment extends Item {
	public Equipment(String name, int lvRequired, int atk, int matk, int def, int mdef) {
		super(name, lvRequired, atk, matk, def, mdef);
	}

	public Equipment(Equipment e) {
		super(e.name, e.lvRequired, e.atk, e.matk, e.def, e.mdef);
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
