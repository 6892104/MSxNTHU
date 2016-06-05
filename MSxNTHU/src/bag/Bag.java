package bag;

import java.awt.Image;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import display.DisplayPanel;
import item.Item;
import item.Item.ItemType;
import role.Beginner;

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
	private Vector<Item> fasts;
	private JLabel moneyLabel;
	private ArrayList<JButton> fastButtons;
	private Vector<Integer> fastTable;
	
	public Bag(DisplayPanel display, Beginner character){
		this.character = character;
		this.display = display;
		items = new ArrayList<Vector<Item>>();
		fasts = new Vector<Item>();
		bagMenuButtons = new ArrayList<JButton>();
		itemButtons = new ArrayList<ArrayList<JButton>>();
		fastButtons = new ArrayList<JButton>();
		fasts.setSize(8);
		fastTable = new Vector<Integer>();
		fastTable.setSize(8);
		for(int i=0; i<8; i++) fastTable.set(i, -1);
		
		x = 100;
		y = 100;
		width = 200;
		height = 300;
		visiable = false;
		menuNumber=0;
		setButtons();
		setLabel();
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
		    temp.addMouseListener(new MenuMouseAdapter(i));
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
			for(j=0; j<24; j++)
			{
				temp = new JButton();
			    temp.setContentAreaFilled(false);
			    temp.setBounds(x+10+j%4*42, y+51+j/4*36, 40, 35);
			    temp.setFocusable(false);
			    temp.addMouseListener(new itemMouseAdapter(i,j));
			    temp.addMouseMotionListener(new itemMoveAdapter(temp));	
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
	        Image img = ImageIO.read(getClass().getResource("/bag_image/bag_button.png"));
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
	    
	    for(i=0; i<fasts.size(); i++)
	    {
	    	temp = new JButton();
		    temp.setContentAreaFilled(false);
		    temp.setBounds(1110+i%4*41, 640+i/4*39, 40, 35);
		    temp.setFocusable(false);
		    temp.addMouseListener(new fastMouseAdapter(i));
	        temp.setVisible(true);
            fastButtons.add(temp);
            display.add(temp);
	    }
	}
	
	public void setLabel()
	{
		int i;
		JLabel temp;
		for(i=0; i<fasts.size(); i++)
		{
			temp = new JLabel(Integer.toString(i+1));
			temp.setBounds(1112+i%4*41, 639+i/4*39, 20, 20);
			temp.setVisible(true);
			display.add(temp);
		}
		moneyLabel = new JLabel("0", 4);
		moneyLabel.setBounds(x+60, y+272, 82, 20);
		moneyLabel.setVisible(false);
		display.add(moneyLabel);
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
		moneyLabel.setBounds(x+60, y+272, 82, 20);
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
		int i, j;
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
							for(j=0; j<8; j++)
							{
								if(fastTable.get(j)==i)
								{
									fastButtons.get(j).setIcon(new ImageIcon(display.getItemImage(item.name(), item.amount)));
									break;
								}
							}
							found = true;
							break;
						}
					}
				}
			}
			if(!found)
			{
				int victim = 0;
				for(j = 0 ; j < items.get(1).size() ; j++){
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
				for(j = 0 ; j < items.get(2).size() ; j++){
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
	
	public void useFast(int num)
	{
		num--;
		int type=1, find;
		Item item = fasts.get(num);
		if(item != null){
			item.use(character);
			find = fastTable.get(num);
			if(item.amount > 1){
				item.amount--;
				itemButtons.get(type).get(find).setIcon(new ImageIcon(display.getItemImage(item.name(), item.amount)));
				fastButtons.get(num).setIcon(new ImageIcon(display.getItemImage(item.name(), item.amount)));
			}else{
				items.get(type).set(find, null);
				fasts.set(num, null);
				itemButtons.get(type).get(find).setIcon(null);
				fastButtons.get(num).setIcon(null);
				fastTable.set(num, -1);
			}
		}
	}
	
	private class MenuMouseAdapter extends MouseAdapter
	{
		private int num;
		public MenuMouseAdapter(int i)
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
		int type, num, i, find=-1;
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
					for(i=0; i<8; i++)
					{
						if(fastTable.get(i).equals(num))
						{
							find = i;
							break;
						}
					}
					if(item.amount > 1){
						item.amount--;
						itemButtons.get(type).get(num).setIcon(new ImageIcon(display.getItemImage(item.name(), item.amount)));
						if(find!=-1) fastButtons.get(find).setIcon(new ImageIcon(display.getItemImage(item.name(), item.amount)));
					}else{
						items.get(type).set(num, null);
						itemButtons.get(type).get(num).setIcon(null);
						if(find!=-1)
						{
							fasts.set(find, null);
							fastButtons.get(find).setIcon(null);
							fastTable.set(find, -1);
						}
					}
				}
			}
		}
		public void mouseReleased(MouseEvent e)
		{
			int i, find=-1;
			JButton button = itemButtons.get(type).get(num);
			JButton temp, temp2;
			Item temp3, temp4;
			Icon temp5;
			for(i=0; i<8; i++)
			{
				if(fastTable.get(i).equals(num))
				{
					find = i;
					break;
				}
			}
			for(i=0; i<24; i++)
			{
				if(button.getX()>x-10+i%4*42 && button.getX()<x+30+i%4*42 && button.getY()>y+34+i/4*36 && button.getY()<y+69+i/4*36)
				{
					temp = itemButtons.get(type).get(i);
					temp2 = itemButtons.get(type).get(num);	
					temp3 = items.get(type).get(i);
					temp4 = items.get(type).get(num);
					items.get(type).set(i, temp4);
					items.get(type).set(num, temp3);
					temp5 = temp.getIcon();
					temp.setIcon(temp2.getIcon());
					temp2.setIcon(temp5);
					if(find!=-1) fastTable.set(find, i);
					break;
				}
			}
			
			if(type==1)
			{
				for(i=0; i<8; i++)
				{
					if(button.getX()>=1090+i%4*41 && button.getX()<=1130+i%4*41 && button.getY()>=623+i/4*39 && button.getY()<=658+i/4*39)
					{
						if(find!=-1)
						{
							fasts.set(find, null);
							fastButtons.get(find).setIcon(null);
							fastTable.set(find, -1);
						}
						temp3 = items.get(type).get(num);
						temp2 = fastButtons.get(i);
						fasts.set(i, temp3);
						temp2.setIcon(button.getIcon());
						fastTable.set(i, num);
						break;
					}

				}
			}
			button.setBounds(x+10+num%4*42, y+51+num/4*36, 40, 35);
		}

	}
	
	private class itemMoveAdapter extends MouseMotionAdapter
	{
		private JButton button;
		public itemMoveAdapter(JButton in)
		{
			super();
			button = in;
		}
		public void mouseDragged(MouseEvent e)
		{
			button.setBounds(button.getX() + e.getX()-10, button.getY()-10 + e.getY(), 40, 35);
		}
	}
	
	private class fastMouseAdapter extends MouseAdapter
	{
		int type, num, find;
		public fastMouseAdapter(int i)
		{
			super();
			this.type = 1;
			num = i;
		}
		public void mouseClicked(MouseEvent e)
		{
			if(e.getClickCount()==2)
			{
				Item item = fasts.get(num);
				if(item != null){
					item.use(character);
					find = fastTable.get(num);
					if(item.amount > 1){
						item.amount--;
						itemButtons.get(type).get(find).setIcon(new ImageIcon(display.getItemImage(item.name(), item.amount)));
						fastButtons.get(num).setIcon(new ImageIcon(display.getItemImage(item.name(), item.amount)));
					}else{
						items.get(type).set(find, null);
						fasts.set(num, null);
						itemButtons.get(type).get(find).setIcon(null);
						fastButtons.get(num).setIcon(null);
						fastTable.set(num, -1);
					}
				}
			}
		}
	}
	
	public boolean search(String name, int number)
	{
		int i, j, k, find=-1;
		Item item;
		boolean found = false;
		for(i=0; i<5 && !found; i++)
		{
			for(j=0; j<24 && !found; j++)
			{
				if(items.get(i).get(j).name().equals(name))
				{
					if(items.get(i).get(j).amount>=number)
					{
						found = true;
						item = items.get(i).get(j);
						item.amount-=number;
						if(i==1) //consumable
						{
							for(k=0; k<8; k++)
							{
								if(fastTable.get(k).equals(j))
								{
									find = k;
									break;
								}
							}
						}
						if(item.amount>0)
						{
							itemButtons.get(i).get(j).setIcon(new ImageIcon(display.getItemImage(item.name(), item.amount)));
							if(find!=-1) fastButtons.get(find).setIcon(new ImageIcon(display.getItemImage(item.name(), item.amount)));
						}
						else
						{
							items.get(i).set(j, null);
							itemButtons.get(i).get(j).setIcon(null);
							if(find!=-1)
							{
								fasts.set(find, null);
								fastButtons.get(find).setIcon(null);
								fastTable.set(find, -1);
							}
						}
					}
				}
			}
		}
		return found;
	}
}
