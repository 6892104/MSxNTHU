package item;

public class OtherItem extends Item {
	private int maxNum;
	public OtherItem(String name, int lvRequired, int price, int probability, int maxNum) {
		super(name, lvRequired, price, probability);
		this.maxNum = maxNum;
	}

	public OtherItem(OtherItem o) {
		super(o.name, o.lvRequired, o.price, o.probability);
		this.maxNum = o.maxNum;
		this.itemType = ItemType.otherItem;
	}
}
