package role;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import MapleStory.MapWithObsticle;
import display.DisplayPanel;
import questionSystem.QuestionClient;

public class QuestionNPC extends NPC{
	boolean answering;
	QuestionClient client;
	
	public QuestionNPC(String name, DisplayPanel display,MapWithObsticle map){
		super(name, display, map);
		answering = false;
		button.removeMouseListener(mouseListen);
		mouseListen = new MouseAdapter(){
	        public void mouseClicked(MouseEvent e){
	        	//new Task(name);
	        	//JOptionPane.showMessageDialog(null, "what the ass");
	        	if(answering){
	        		int option = JOptionPane.showConfirmDialog(null, "選擇好了嗎？", name+" :", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(option == JOptionPane.YES_OPTION){
						client.sendMessage(new Integer(map.atWhichFloor(display.getCharacter().x(), display.getCharacter().y())).toString());
						answering = false;
						client.closeConnection();
					}
	        	}else{
	        		client = new QuestionClient("127.0.0.1", 6000);
	    			client.connect();
	    			answering = true;
	        	}
	        }
        };
        button.addMouseListener(mouseListen);
	}
}
