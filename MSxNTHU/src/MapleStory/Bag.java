package MapleStory;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
	
	private int x, y, setNumber, menuNumber;
	private int width, height;
	private boolean visiable;
	
	private JButton bagButton;
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
		setNumber=0;
		menuNumber=0;
		setButtons();
		
		bagButton = new JButton();
	    bagButton.setContentAreaFilled(false);
	    bagButton.setBounds(860, 655, 60, 60);
	    bagButton.setFocusable(false);
	    bagButton.addMouseListener(new MouseAdapter(){
	        @Override
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
	    
	}
	
	public void open(){
		visiable = !visiable;
		for(int i=0; i<5; i++) bagMenuButtons.get(i).setVisible(visiable);
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
		for(int i=0; i<5; i++)
		{
			temp = new JButton();
		    temp.setContentAreaFilled(false);
		    temp.setBounds(x+8+i*37, y+25, 37, 21);
		    //System.out.println(x+" "+ y);
		    temp.setFocusable(false);
		    temp.addMouseListener(new MouseAdapter(){
		        @Override
		        public void mouseClicked(MouseEvent e){
		        	menuNumber = bagMenuButtons.indexOf();
		        }
		    });
	        try {
		        Image img = ImageIO.read(getClass().getResource("/bag/empty.png"));
		        img = img.getScaledInstance( 20, 30,  java.awt.Image.SCALE_SMOOTH ) ;
		        temp.setIcon(new ImageIcon(img));
		    } catch (IOException ex) {
		    	javax.swing.JOptionPane.showMessageDialog(null, "¸ü¤Jbag button¹ÏÀÉ¿ù»~");
		    }
	        temp.setVisible(false);
            setNumber++;
            bagMenuButtons.add(temp);
            display.add(temp);
		}
	}
	
	public int getMenu()
	{
		return menuNumber;
	}
	
	class mjfielwjf extends MouseAdapter{
		int i;
		mjfielwjf(int i){
			super();
		}
	}
	
	/*@Override
	protected void paintComponent(java.awt.Graphics g) { //paint pictures (using TA's code)
		super.paintComponent(g);
		g.setColor(Color.BLUE);
		g.drawLine(0, 0, 100, 100);
	}*/
}
