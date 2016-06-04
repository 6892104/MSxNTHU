package role;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import MapleStory.MapWithObsticle;
import display.DisplayPanel;

public class QuestionNPC extends NPC{
	public QuestionNPC(String name, DisplayPanel display,MapWithObsticle map){
		super(name, display, map);
		button.removeMouseListener(mouseListen);
		mouseListen = new MouseAdapter(){
	        public void mouseClicked(MouseEvent e){
	        	//new Task(name);
	        	JOptionPane.showMessageDialog(null, "what the ass");
	        }
        };
        button.addMouseListener(mouseListen);
	}
}
