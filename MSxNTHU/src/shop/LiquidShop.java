package shop;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import bag.Bag;
import display.DisplayPanel;

public class LiquidShop extends JPanel
{
	private Image image;
	private int x, y;
	private JButton apple, red, blue, orange, sup, exit;
	private Bag bag;
	private LiquidShop keep;
	
	public LiquidShop()
	{
		x=200;
		y=100;
		bag = null;
		this.setLayout(null);
		this.setOpaque(false);
		this.setSize(224, 334);
		this.setLocation(x, y);
		this.setVisible(false);
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
		g.drawImage(image, 0, 0, 224, 334, null);
	}
	
	public void callLiquidShop()
	{
		this.setVisible(true);
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
                if(e.getClickCount()==2)
                {
                	temp = bag.payMoney(50);
                	if(temp)
                	{
                		bag.putItem(null);
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
                if(e.getClickCount()==2)
                {
                	temp = bag.payMoney(100);
                	if(temp)
                	{
                		bag.putItem(null);
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
                if(e.getClickCount()==2)
                {
                	temp = bag.payMoney(150);
                	if(temp)
                	{
                		bag.putItem(null);
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
                if(e.getClickCount()==2)
                {
                	temp = bag.payMoney(300);
                	if(temp)
                	{
                		bag.putItem(null);
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
                if(e.getClickCount()==2)
                {
                	temp = bag.payMoney(1000);
                	if(temp)
                	{
                		bag.putItem(null);
                	}
                }
        }
		});
		this.add(sup);
		
		exit = new JButton();
		exit.setContentAreaFilled(false);
		exit.setFocusable(false);
		exit.setBounds(0, 0, 20, 20);
		exit.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				keep.setVisible(false);
        }
		});
	}
}
