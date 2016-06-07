package questionSystem;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import MapleStory.Control;
import MapleStory.MapWithObsticle;
import display.DisplayPanel;
import role.NPC;

public class QuestionNPC extends NPC{
	private boolean answering;
	protected QuestionClient client;
	protected Control parent;
	private int qnum;
	
	public QuestionNPC(String name, DisplayPanel display, MapWithObsticle map, Control parent){
		super(name, display, map, parent);
		this.parent = parent;
		answering = false;
		button.removeMouseListener(mouseListen);
		mouseListen = new MouseAdapter(){
	        public void mouseClicked(MouseEvent e){
	        	//new Task(name);
	        	//JOptionPane.showMessageDialog(null, "what the ass");
	        	if(e.getClickCount() >= 2){
		        	if(answering){
		        		int option = JOptionPane.showConfirmDialog(null, "選擇好了嗎？", name+" :", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if(option == JOptionPane.YES_OPTION){
							int x = display.getCharacter().x() + display.getCharacter().width()/2;
							int y = display.getCharacter().y() + display.getCharacter().height();
							client.sendMessage(new Integer(map.atWhichFloor(x, y)).toString());
							qnum++;
							//answering = false;
							//client.closeConnection();
						}
		        	}else{
		        		if(client == null){
		        			qnum = 0;
		        			createClient();
		        			client.sendMessage("read");
		        		}
		    			answering = true;
		        	}
	        	}
	        }
        };
        button.addMouseListener(mouseListen);
        qnum = 0;
	}
	
	protected void createClient(){
		client = new QuestionClient("127.0.0.1", 6000, this);
		client.connect();
	}
	
	public void closeConversation(){
		answering = false;
		client = null;
		System.out.println(100 * qnum);
		parent.rewardCharacter(100 * qnum, 100 * qnum);
		parent.resetMap("map1");
	}
}
