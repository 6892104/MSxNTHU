package item;

public class Item {
	protected String name;
	protected int lvRequired;
	public boolean usable;
	protected int atk, matk;
	protected int def, mdef;
	public int x, y;

	public Item(String name, int lvRequired, int atk, int matk, int def, int mdef) {
		this.x = -100;
		this.y = -100;
		this.usable = false;

		this.name = name;
		this.lvRequired = lvRequired;
		this.atk = atk;
		this.matk = matk;
		this.def = def;
		this.mdef = mdef;
	}
}
