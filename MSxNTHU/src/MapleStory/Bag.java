package MapleStory;

import java.util.Vector;

import item.Item;
import item.ItemDatabase;

public class Bag{
	
	private ItemDatabase dataBase;
	private Vector<Item> items;
	
	private int x, y;
	private int width, height;
	private boolean visiable;
	
	public Bag(ItemDatabase dataBase){
		this.dataBase = dataBase;
		items = new Vector<Item>();
		items.setSize(20);
		x = 100;
		y = 100;
		width = 200;
		height = 300;
		visiable = false;
	}
	
	public void open(){
		visiable = !visiable;
	}
	
	public boolean visiable(){
		return visiable;
	}
	
	public int x(){
		return x;
	}
	
	public int y(){
		return y;
	}
	
	public int width(){
		return width;
	}
	
	public int height(){
		return height;
	}
	/*@Override
	protected void paintComponent(java.awt.Graphics g) { //paint pictures (using TA's code)
		super.paintComponent(g);
		g.setColor(Color.BLUE);
		g.drawLine(0, 0, 100, 100);
	}*/
}
