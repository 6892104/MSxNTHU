package role;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import MapleStory.MapWithObsticle;
import display.DisplayPanel;


public class NPC extends Role{
	protected JButton button;
	protected MouseAdapter mouseListen;
	protected int ScreenX, ScreenY;
	
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
	    
	    button = new JButton();
	    button.setContentAreaFilled(false);
	    button.setFocusable(false);
	    button.setBorderPainted(false);
	    mouseListen = new MouseAdapter(){
	        public void mouseClicked(MouseEvent e){
	        	/*JOptionPane.showMessageDialog(null, "有事嗎？", "ToolMan :", JOptionPane.INFORMATION_MESSAGE );
	        	int option = JOptionPane.showConfirmDialog(null, "想被肛？", "ToolMan :", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	        	if(option == JOptionPane.YES_OPTION)
	        		System.exit(0);*/
	        	new Task(name);
	        }
        };
	    button.addMouseListener(mouseListen);
        display.add(button);
	}
	
	public void set(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		button.setIcon(new ImageIcon(display.getNPCImage(name).getScaledInstance( width - 2, height - 2,  java.awt.Image.SCALE_SMOOTH )));
	}
	
	public void move(int x, int y){
		//System.out.println(x + " " + y);
		this.ScreenX = x;
		this.ScreenY = y;
		button.setBounds(ScreenX, ScreenY, width, height);
	}
	
	public void removeButton(){
		display.remove(button);
	}
}
