package MapleStory;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import item.Item;
import item.Item.ItemType;
import item.ItemDatabase;

public class Bag{
	
	private DisplayPanel display;
	private ItemDatabase dataBase;
	
	private int x, y, menuNumber;
	private int width, height;
	private boolean visiable;
	
	private JButton bagButton, dragButton, closeButton;
	private ArrayList<JButton> bagMenuButtons;
	private ArrayList<ArrayList<JButton>> itemButtons;
	private ArrayList<Vector<Item>> items;
	private int[] consumableItemNumber;
	private int[] otherItemNumber;
	
	public Bag(DisplayPanel display, ItemDatabase dataBase){
		this.dataBase = dataBase;
		this.display = display;
		items = new ArrayList<Vector<Item>>();
		bagMenuButtons = new ArrayList<JButton>();
		itemButtons = new ArrayList<ArrayList<JButton>>();
		consumableItemNumber = new int[24];
		otherItemNumber = new int[24];
		for(int i=0; i<24; i++)
		{
			consumableItemNumber[i]=0;
			otherItemNumber[i]=0;
		}
		
		x = 100;
		y = 100;
		width = 200;
		height = 300;
		visiable = false;
		menuNumber=0;
		setButtons();
		
	}
	
	public void open(){
		int i;
		visiable = !visiable;
		for(i=0; i<5; i++) bagMenuButtons.get(i).setVisible(visiable);
		for(i=0; i<24; i++) itemButtons.get(menuNumber).get(i).setVisible(visiable);
		dragButton.setVisible(visiable);
		closeButton.setVisible(visiable);
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
	
	public void setButtons()
	{
		JButton temp;
		ArrayList<JButton> temp2;
		int i, j;
		for(i=0; i<5; i++)
		{
			temp = new JButton();
		    temp.setContentAreaFilled(false);
		    temp.setBounds(x+8+i*37, y+25, 37, 21);
		    temp.setFocusable(false);
		    temp.addMouseListener(new menuMouseAdapter(i));
	        temp.setVisible(false);
            bagMenuButtons.add(temp);
            display.add(temp);
		}
		for(i=0; i<5; i++)
		{
			temp2 = new ArrayList<JButton>();
			itemButtons.add(temp2);
			items.add(new Vector<Item>(24));
			for(j=0; j<24; j++)
			{
				temp = new JButton();
			    temp.setContentAreaFilled(false);
			    temp.setBounds(x+10+j%4*42, y+51+j/4*36, 40, 35);
			    temp.setFocusable(false);
			    temp.addMouseListener(new itemMouseAdapter(j));
		        temp.setVisible(false);
	            itemButtons.get(i).add(temp);
	            display.add(temp);
			}
		}
		
		bagButton = new JButton();
	    bagButton.setContentAreaFilled(false);
	    bagButton.setBounds(860, 655, 60, 60);
	    bagButton.setFocusable(false);
	    bagButton.addMouseListener(new MouseAdapter(){
	        public void mouseClicked(MouseEvent e){
	                open();
	        }
	    });
	    try {
	        Image img = ImageIO.read(getClass().getResource("/bag/bag_button.png"));
	        img = img.getScaledInstance( 60, 60,  java.awt.Image.SCALE_SMOOTH ) ;
	        bagButton.setIcon(new ImageIcon(img));
	    } catch (IOException ex) {
	    	javax.swing.JOptionPane.showMessageDialog(null, "¸ü¤Jbag button¹ÏÀÉ¿ù»~");
	    }
	    display.add(bagButton);
	    
	    dragButton = new JButton();
	    dragButton.setContentAreaFilled(false);
	    dragButton.setBounds(x, y, 172, 20);
	    dragButton.setFocusable(false);
	    dragButton.addMouseMotionListener(new MouseMotionAdapter(){
	        public void mouseDragged(MouseEvent e){
	        	x += e.getX() - 100;
	            y += e.getY() - 5;
	            moveBag();
	        }
	    });
	    dragButton.setBorderPainted(false);
	    dragButton.setVisible(false);
	    display.add(dragButton);
	    
	    closeButton = new JButton();
	    closeButton.setContentAreaFilled(false);
	    closeButton.setBounds(x+172, y+2, 20, 20);
	    closeButton.setFocusable(false);
	    closeButton.addMouseListener(new MouseAdapter(){
	        public void mousePressed(MouseEvent e){
	            open();
	        }
	    });
	    closeButton.setBorderPainted(false);
	    closeButton.setVisible(false);
	    display.add(closeButton);
	    
	}
	
	public int getMenu()
	{
		return menuNumber;
	}
	
	public void moveBag()
	{
		int i, j;
		for(i=0; i<5; i++) bagMenuButtons.get(i).setBounds(x+8+i*37, y+25, 37, 21);
		for(i=0; i<5; i++) for(j=0; j<24; j++) itemButtons.get(i).get(j).setBounds(x+10+j%4*42, y+51+j/4*36, 40, 35);
		dragButton.setBounds(x, y, 172, 20);
		closeButton.setBounds(x+172, y+2, 20, 20);
		display.repaint();
	}
	
	class menuMouseAdapter extends MouseAdapter
	{
		private int num;
		public menuMouseAdapter(int i)
		{
			super();
			num = i;
		}
		public void mouseClicked(MouseEvent e)
		{
			int i;
			for(i=0; i<24; i++) itemButtons.get(menuNumber).get(i).setVisible(false);
        	menuNumber = num;
        	for(i=0; i<24; i++) itemButtons.get(menuNumber).get(i).setVisible(true);
        }
	}
	
	class itemMouseAdapter extends MouseAdapter
	{
		int num;
		public itemMouseAdapter(int i)
		{
			super();
			num = i;
		}
		public void mouseClicked(MouseEvent e)
		{
			if(e.getClickCount()==2)
			{
				//use item
			}
		}
	}
	
	public void putItem(Item in)
	{
		int i;
		if(in.type()==ItemType.equipment)
		{
			
			items.get(0).add(in);
			itemButtons.get(0).get(items.get(0).indexOf(in)).setIcon(new ImageIcon(display.getItemImage(in.name(), in.amount)));
			
		}
		else if(in.type() == ItemType.consumable)
		{
			for(i=0; i<items.get(1).size(); i++)
			{
				Item item = items.get(1).get(i);
				if(item.name().equals(in.name()))
				{
					if(item.amount < item.maxNum())
					{
						item.amount++;
						itemButtons.get(1).get(i).setIcon(new ImageIcon(display.getItemImage(item.name(), item.amount)));
						break;
					}
				}
			}
			if(i==items.get(1).size())
			{
				items.get(1).add(in);
				consumableItemNumber[items.get(1).indexOf(in)]++;
				itemButtons.get(1).get(items.get(1).indexOf(in)).setIcon(new ImageIcon(display.getItemImage(in.name(), in.amount)));
			}
		}
		else if(in.type()==ItemType.otherItem)
		{
			for(i=0; i<items.get(2).size(); i++)
			{
				if(items.get(2).get(i).name().equals(in.name()))
				{
					if(otherItemNumber[i]<items.get(2).get(i).maxNum())
					{
						consumableItemNumber[i]++;
						break;
					}
				}
			}
			if(i==items.get(2).size())
			{
				items.get(2).add(in);
				consumableItemNumber[items.get(2).indexOf(in)]++;
				itemButtons.get(2).get(items.get(2).indexOf(in)).setIcon(new ImageIcon(display.getItemImage(in.name(), in.amount)));
			}
		}
		
	}
}
