package MapleStory;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
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
	
	private int x, y;
	private int width, height;
	private boolean visiable;
	
	private JButton bagButton;
	
	public Bag(DisplayPanel display, ItemDatabase dataBase){
		this.dataBase = dataBase;
		items = new Vector<Item>();
		items.setSize(20);
		x = 100;
		y = 100;
		width = 200;
		height = 300;
		visiable = false;
		
		bagButton = new JButton();
	    bagButton.setContentAreaFilled(false);
	    bagButton.setBounds(860, 655, 60, 60);
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
