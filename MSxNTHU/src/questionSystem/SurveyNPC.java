package questionSystem;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import MapleStory.Control;
import MapleStory.MapWithObsticle;
import display.DisplayPanel;

public class SurveyNPC extends QuestionNPC{
	private QuestionPanel panel;
	
	public SurveyNPC(String name, DisplayPanel display, MapWithObsticle map, Control parent){
		super(name, display, map, parent);
		this.parent = parent;
		button.removeMouseListener(mouseListen);
		mouseListen = new MouseAdapter(){
	        public void mouseClicked(MouseEvent e){
	        	if(e.getClickCount() >= 2){
	        		if(panel == null)
	        			createPanel();
	        		panel.callSurvey();
	        	}
	        }
        };
        button.addMouseListener(mouseListen);
	}
	
	private void createPanel(){
		panel = new QuestionPanel(display, this);
    	display.add(panel);
    	display.questionPanel = panel;
	}
	
	public void sendQuestion(String question){
		System.out.println(question);
		createClient();
		client.sendMessage("write");
		client.sendMessage(question);
		client.closeConnection();
		client = null;
	}
	
	@Override
	public void closeConversation(){
		client = null;
	}
	
	public void removePanel(){
		display.remove(panel);
	}
}
