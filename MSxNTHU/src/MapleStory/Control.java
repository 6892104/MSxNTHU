package MapleStory;

import java.util.ArrayList;

import Role.Beginner;
import Role.Monster;

public class Control extends Thread{

	private MapWithObsticle map;
	private Beginner character;
	private ArrayList<Monster> monsters;
	
	private DisplayPanel display;
	private KeyControl keyControl;

	public Control(DisplayPanel display){
		this.display = display;
		map = new MapWithObsticle(display);
	    display.setMap(map);
	    character = new Beginner("ชüจ}จ}ค์", display, map);
	    display.setCharacter(character);
	    monsters = map.createMonster();
	    display.setMonsters(monsters);
	}
	
	@Override
	public void run() {
		super.run();
		long lastTime = System.currentTimeMillis();
		while (true){
			try{
				/* Game Loop */
				/*repaint();
				if( DELAY < 60) {
					DELAY++;
					if(curScore <= winScore-10){ // Near the end of game
						bgCurrentX--;
						if(curScore > this.winScore-20){ // Can show the ball now
							ballCurrentX--;
						}
					} else {
						duckCurrentX++;
					}
				}*/
				/* Game Loop End */
				//lastTime = lastTime + DELAY;
				Thread.sleep(40);
				keyDetect();
				character.RoleAction();
				for(int i = 0 ; i < monsters.size() ; i++){
					monsters.get(i).RandomMove();
					monsters.get(i).RoleAction();;
				}
				display.repaint();
				//System.out.println(keyControl.get("right"));
			} catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	
	private void keyDetect(){
		if(keyControl.get("right"))      character.RoleMove(1);
		if(keyControl.get("left"))       character.RoleMove(0);
		if(keyControl.get("space"))      character.jump();
	    if(keyControl.get("up"))         character.climb(0);
	    if(keyControl.get("down"))     	 character.climb(1);
	    /*if(key["esc"]){
	        menuBar()->show();
	        showNormal();
	    }
	    if(key["F11"]){
	        menuBar()->hide();
	        showFullScreen();
	    }
	    if(key["space"])
	    {

	        role->normal_attack();
	    }
	    if(key["i"]){
	        if(bag->isHidden()) bag->show();
	        else                bag->hide();
	    }





	    if(key["z"])    pick();
	    if(key["1"])    bag->use_item_key(0,role);
	    if(key["2"])    bag->use_item_key(1,role);
	    if(key["3"])    bag->use_item_key(2,role);
	    if(key["4"])    bag->use_item_key(3,role);
	    if(key["5"])    bag->use_item_key(4,role);
	    if(key["6"])    bag->use_item_key(5,role);
	    if(key["7"])    bag->use_item_key(6,role);
	    if(key["8"])    bag->use_item_key(7,role);*/
	}

	public void setCharacter(Beginner character) {
		this.character = character;
	}

	public void setDisplay(DisplayPanel display) {
		this.display = display;
	}
	
	public void setKeyControl(KeyControl keyControl) {
		this.keyControl = keyControl;
	}
}
