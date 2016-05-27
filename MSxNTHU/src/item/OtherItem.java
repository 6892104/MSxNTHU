package item;

import java.util.Random;

public class OtherItem extends Item {
	private int maxNum;
	public int hp, mp;
	public OtherItem(String name, int lvRequired, int atk, int matk, int def, int mdef, int maxNum, int hp, int mp) {
		super(name, lvRequired, atk, matk, def, mdef);
		this.maxNum = maxNum;
		this.hp = hp;
		this.mp = mp;
	}

	public OtherItem(OtherItem o) {
		super(o.name, o.lvRequired, o.atk, o.matk, o.def, o.mdef);
		this.atk = setRandom(this.atk);
		this.matk = setRandom(this.matk);
		this.def = setRandom(this.def);
		this.mdef = setRandom(this.mdef);
		this.maxNum = o.maxNum;
		this.hp = o.hp;
		this.mp = o.mp;
	}

	private int setRandom(int value) {
		Random r = new Random();
		int i = r.nextInt(21) -10;
		return value + value*i/100;
	}
}
