package role;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import MapleStory.DisplayPanel;
import MapleStory.MapWithObsticle;


public class NPC extends Role{
	public NPC(String name, DisplayPanel display,MapWithObsticle map){
		super(name, display, map);
		width = 200;
	    height = 100;
	    x = 1000;
	    y = 1000;
	    /*shift = 0;
	    move_range_left=0;
	    move_range_right=map.getMax_x();*/


	    move_pace=1;
	    level=1;

	    money = 0;
	    
	    JButton temp = new JButton();
	    temp.setContentAreaFilled(false);
	    temp.setBounds(x, y, width, height);
	    temp.setFocusable(false);
	    temp.addMouseListener(new MouseAdapter(){
	        public void mouseClicked(MouseEvent e){
                //open();
	        }
        });
        temp.setVisible(false);
        display.add(temp);
	}
}
