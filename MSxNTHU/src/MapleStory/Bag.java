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
import javax.swing.JLabel;

import item.Item;
import item.Item.ItemType;
import role.Beginner;
import item.ItemDatabase;

public class Bag{
	
	private DisplayPanel display;
	private Beginner character;
	
	private int x, y, menuNumber;
	private int width, height;
	private boolean visiable;
	
	private JButton bagButton, dragButton, closeButton;
	private ArrayList<JButton> bagMenuButtons;
	private ArrayList<ArrayList<JButton>> itemButtons;
	private ArrayList<Vector<Item>> items;
	private JLabel moneyLabel;
	
	public Bag(DisplayPanel display, Beginner character){
		this.character = character;
		this.display = display;
		items = new ArrayList<Vector<Item>>();
		bagMenuButtons = new ArrayList<JButton>();
		itemButtons = new ArrayList<ArrayList<JButton>>();
		moneyLabel = new JLabel();
		
		
		/*consumableItemNumber = new int[24];
		otherItemNumber = new int[24];
		for(int i=0; i<24; i++)
		{
			consumableItemNumber[i]=0;
			otherItemNumber[i]=0;
		}*/
		
		x = 100;
		y = 100;
		width = 200;
		height = 300;
		visiable = false;
		menuNumber=0;
		setButtons();
		moneyLabel.setBounds(x+60, y+272, 200, 20);
		moneyLabel.setText("0");
		moneyLabel.setVisible(false);
		display.add(moneyLabel);
	}
	
	public void open(){
		int i;
		visiable = !visiable;
		for(i=0; i<5; i++) bagMenuButtons.get(i).setVisible(visiable);
		for(i=0; i<24; i++) itemButtons.get(menuNumber).get(i).setVisible(visiable);
		dragButton.setVisible(visiable);
		closeButton.setVisible(visiable);
		moneyLabel.setVisible(visiable);
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
			Vector<Item> vec = new Vector<Item>();
			vec.setSize(24);
			items.add(vec);
			//System.out.println(items.get(0).size());
			for(j=0; j<24; j++)
			{
				temp = new JButton();
			    temp.setContentAreaFilled(false);
			    temp.setBounds(x+10+j%4*42, y+51+j/4*36, 40, 35);
			    temp.setFocusable(false);
			    temp.addMouseListener(new itemMouseAdapter(i,j));
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
		moneyLabel.setBounds(x+60, y+272, 200, 20);
		display.repaint();
	}
	
	public void putMoney(int add)
	{
		 Integer now = Integer.valueOf(moneyLabel.getText());
		 now = now + add;
		 moneyLabel.setText(now.toString());
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
			boolean found = false;
			for(i=0; i<items.get(1).size(); i++)
			{
				Item item = items.get(1).get(i);
				if(item != null){
					if(item.name().equals(in.name()))
					{
						if(item.amount < item.maxNum())
						{
							item.amount++;
							itemButtons.get(1).get(i).setIcon(new ImageIcon(display.getItemImage(item.name(), item.amount)));
							found = true;
							break;
						}
					}
				}
			}
			if(!found)
			{
				int victim = 0;
				for(int j = 0 ; j < items.get(1).size() ; j++){
					if(items.get(1).get(j) == null){
						victim = j;
						break;
					}
				}
				if(items.get(1).get(victim) == null){
					items.get(1).set(victim, in);
					itemButtons.get(1).get(victim).setIcon(new ImageIcon(display.getItemImage(in.name(), in.amount)));
				}
			}
		}
		else if(in.type()==ItemType.otherItem)
		{
			boolean found = false;
			for(i=0; i<items.get(2).size(); i++)
			{
				Item item = items.get(2).get(i);
				if(item != null){
					if(item.name().equals(in.name()))
					{
						if(item.amount < item.maxNum())
						{
							item.amount++;
							itemButtons.get(2).get(i).setIcon(new ImageIcon(display.getItemImage(item.name(), item.amount)));
							found = true;
							break;
						}
					}
				}
			}
			if(!found)
			{
				int victim = 0;
				for(int j = 0 ; j < items.get(2).size() ; j++){
					if(items.get(2).get(j) == null){
						victim = j;
						break;
					}
				}
				if(items.get(2).get(victim) == null){
					items.get(2).set(victim, in);
					itemButtons.get(2).get(victim).setIcon(new ImageIcon(display.getItemImage(in.name(), in.amount)));
				}
			}
		}
		
	}
	
	private class menuMouseAdapter extends MouseAdapter
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
	
	private class itemMouseAdapter extends MouseAdapter
	{
		int type;
		int num;
		public itemMouseAdapter(int type, int i)
		{
			super();
			this.type = type;
			num = i;
		}
		public void mouseClicked(MouseEvent e)
		{
			if(e.getClickCount()==2)
			{
				Item item = items.get(type).get(num);
				if(item != null){
					item.use(character);
					if(item.amount > 1){
						item.amount--;
						itemButtons.get(type).get(num).setIcon(new ImageIcon(display.getItemImage(item.name(), item.amount)));
					}else{
						items.get(type).set(num, null);
						itemButtons.get(type).get(num).setIcon(null);
					}
				}
			}
		}
	}
}
