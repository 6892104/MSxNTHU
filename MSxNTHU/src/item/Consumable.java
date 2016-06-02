package item;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;
import role.Beginner;

public class Consumable extends Item {
	private int hp, mp;
	Minim minim;
	AudioPlayer drinkItem;
	public boolean soundOn;
	
	
	public Consumable(String name, int lvRequired, int price, int probability, int hp, int mp,int maxNum) {
		super(name, lvRequired, price, probability);
		this.hp = hp;
		this.mp = mp;
		this.maxNum = maxNum;
		this.itemType = ItemType.consumable;
		minim = new Minim(new PApplet());
		drinkItem = minim.loadFile(this.getClass().getResource("/drink_item.mp3").getPath());
		soundOn = true;
	}

	public Consumable(Consumable c) {
		super(c.name, c.lvRequired, c.price, c.probability);
		this.hp = c.hp;
		this.mp = c.mp;
		this.maxNum = c.maxNum;
		this.itemType = ItemType.consumable;
		minim = new Minim(new PApplet());
		drinkItem = minim.loadFile(this.getClass().getResource("/drink_item.mp3").getPath());
		soundOn = true;
	}
	@Override
	public void use(Beginner ch) {
		ch.gain_hp(this.hp);
		ch.gain_mp(this.mp);
		if(soundOn) {
			drinkItem.rewind();
			drinkItem.play();
		}
	}
	
	
}
