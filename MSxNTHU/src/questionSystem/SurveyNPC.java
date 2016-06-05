package questionSystem;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import MapleStory.Control;
import MapleStory.MapWithObsticle;
import display.DisplayPanel;
import role.NPC;

public class SurveyNPC extends NPC{
	private boolean answering;
	private QuestionClient client;
	private QuestionPanel panel;
	private Control parent;
	
	public SurveyNPC(String name, DisplayPanel display, MapWithObsticle map, Control parent){
		super(name, display, map, parent);
		this.parent = parent;
		answering = false;
		button.removeMouseListener(mouseListen);
		mouseListen = new MouseAdapter(){
	        public void mouseClicked(MouseEvent e){
	        	if(e.getClickCount() == 2){
	        		createPanel();
	        	}
	        }
        };
        button.addMouseListener(mouseListen);
	}
	
	private void createClient(){
		//client = new QuestionClient("127.0.0.1", 6000, this);
		client.connect();
	}
	
	private void createPanel(){
		panel = new QuestionPanel(display, this);
    	display.add(panel);
	}
	
	public void closeConversation(){
		answering = false;
		client = null;
		parent.resetMap("map1");
	}
	
	public void removePanel(){
		display.remove(panel);
	}
}
