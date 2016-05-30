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
import item.ItemDatabase;

public class Bag{
	
	private DisplayPanel display;
	private ItemDatabase dataBase;
	private Vector<Item> items;
	
	private int x, y, menuNumber;
	private int width, height;
	private boolean visiable;
	
	private JButton bagButton, dragButton, closeButton;
	private ArrayList<JButton> bagMenuButtons;
	private ArrayList<JButton> itemButtons;
	
	public Bag(DisplayPanel display, ItemDatabase dataBase){
		this.dataBase = dataBase;
		this.display = display;
		items = new Vector<Item>();
		items.setSize(24);
		bagMenuButtons = new ArrayList<JButton>();
		itemButtons = new ArrayList<JButton>();
		
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
		for(i=0; i<24; i++) itemButtons.get(i).setVisible(visiable);
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
		int i;
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
		for(i=0; i<24; i++)
		{
			temp = new JButton();
		    temp.setContentAreaFilled(false);
		    temp.setBounds(x+10+i%4*42, y+51+i/4*36, 40, 35);
		    temp.setFocusable(false);
		    temp.addMouseListener(new itemMouseAdapter(i));
	        temp.setVisible(false);
            itemButtons.add(temp);
            display.add(temp);
		}
		
		bagButton = new JButton();
	    bagButton.setContentAreaFilled(false);
	    bagButton.setBounds(860, 655, 60, 60);
	    bagButton.setFocusable(false);
	    bagButton.addMouseListener(new MouseAdapter(){
	        public void mouseClicked(MouseEvent e){
	            if(e.getClickCount()==2){
	                open();
	            }
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
		int i;
		for(i=0; i<5; i++) bagMenuButtons.get(i).setBounds(x+8+i*37, y+25, 37, 21);
		for(i=0; i<24; i++) itemButtons.get(i).setBounds(x+10+i%4*42, y+51+i/4*36, 40, 35);
		dragButton.setBounds(x, y, 172, 20);
		closeButton.setBounds(x+172, y+2, 20, 20);
		display.repaint();
	}
	
	class menuMouseAdapter extends MouseAdapter
	{
		int num;
		public menuMouseAdapter(int i)
		{
			super();
			num = i;
		}
		public void mouseClicked(MouseEvent e)
		{
        	menuNumber = num;
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
}
