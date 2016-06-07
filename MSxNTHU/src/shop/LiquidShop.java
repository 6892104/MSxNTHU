package shop;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import MapleStory.Control;
import bag.Bag;
import display.DisplayPanel;
import item.Item;

public class LiquidShop extends JPanel
{
	private Image image;
	private int x, y;
	private JButton apple, red, blue, orange, sup, exit;
	private LiquidShop keep;
	private Control control;
	private DisplayPanel display;
	
	public LiquidShop(Control control, DisplayPanel display)
	{
		this.control = control;
		this.display = display;
		x=200;
		y=100;
		this.setLayout(null);
		this.setOpaque(false);
		this.setSize(224, 334);
		this.setLocation(x, y);
		this.setVisible(false);
		this.setFocusable(true);
		try {
			image = ImageIO.read(this.getClass().getResourceAsStream("/LiquidShop.jpg"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		keep = this;
		setButton();
	}
	
	protected void paintComponent(Graphics g)
	{
		g.drawImage(image, 0, 0, null);
	}
	
	public void callLiquidShop()
	{
		this.setVisible(true);
		this.requestFocus();
	}
	
	public void setButton()
	{
		apple = new JButton();
		apple.setContentAreaFilled(false);
		apple.setFocusable(false);
		apple.setBounds(5, 125, 34, 37);
		apple.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				boolean temp = false;
                if(e.getClickCount()>=2)
                {
                	Item item = control.creatItem("Ä«ªG");
                	temp = control.checkBag(item.price());
                	if(temp)
                	{
                		control.setBagItem(item);
                	}
                }
        }
		});
		this.add(apple);
		
		red = new JButton();
		red.setContentAreaFilled(false);
		red.setFocusable(false);
		red.setBounds(5, 165, 34, 37);
		red.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				boolean temp = false;
                if(e.getClickCount()>=2)
                {
                	Item item = control.creatItem("¬õÃÄ¤ô");
                	temp = control.checkBag(item.price());
                	if(temp)
                	{
                		control.setBagItem(item);
                	}
                }
        } 
		});
		this.add(red);
		
		blue = new JButton();
		blue.setContentAreaFilled(false);
		blue.setFocusable(false);
		blue.setBounds(5, 207, 34, 37);
		blue.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				boolean temp = false;
                if(e.getClickCount()>=2)
                {
                	Item item = control.creatItem("ÂÅÃÄ¤ô");
                	temp = control.checkBag(item.price());
                	if(temp)
                	{
                		control.setBagItem(item);
                	}
                }
        }
		});
		this.add(blue);
		
		orange = new JButton();
		orange.setContentAreaFilled(false);
		orange.setFocusable(false);
		orange.setBounds(5, 247, 34, 37);
		orange.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				boolean temp = false;
                if(e.getClickCount()>=2)
                {
                	Item item = control.creatItem("¾ïÃÄ¤ô");
                	temp = control.checkBag(item.price());
                	if(temp)
                	{
                		control.setBagItem(item);
                	}
                }
        }
		});
		this.add(orange);
		
		sup = new JButton();
		sup.setContentAreaFilled(false);
		sup.setFocusable(false);
		sup.setBounds(5, 286, 34, 37);
		sup.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				boolean temp = false;
                if(e.getClickCount()>=2)
                {
                	Item item = control.creatItem("¶W¯ÅÃÄ¤ô");
                	temp = control.checkBag(item.price());
                	if(temp)
                	{
                		control.setBagItem(item);
                	}
                }
        }
		});
		this.add(sup);
		
		exit = new JButton();
		exit.setContentAreaFilled(false);
		exit.setFocusable(false);
		exit.setBounds(141, 14, 67, 16);
		exit.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				keep.setVisible(false);
				display.requestFocus();
			}
		});
		this.add(exit);
	}
}
