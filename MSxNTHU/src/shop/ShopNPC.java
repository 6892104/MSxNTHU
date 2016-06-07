package shop;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import MapleStory.Control;
import MapleStory.MapWithObsticle;
import display.DisplayPanel;
import role.NPC;

public class ShopNPC extends NPC
{
	private LiquidShop liquidShop;
	protected Control parent;
	
	public ShopNPC(String name, DisplayPanel display, MapWithObsticle map, Control control) {
		super(name, display, map, control);
		this.parent = control;
		button.removeMouseListener(mouseListen);
		mouseListen = new MouseAdapter(){
	        public void mouseClicked(MouseEvent e)
	        {
	        	if(e.getClickCount() >= 2)
	        	{
	        		if(liquidShop == null){
			        	liquidShop = new LiquidShop(control, display);
			        	display.add(liquidShop);
	        		}
		        	liquidShop.callLiquidShop();
	        	}
	        }
        };
        button.addMouseListener(mouseListen);
	}
	
}
