package role;

import java.util.ArrayList;
import java.util.Random;

import MapleStory.MapWithObsticle;
import display.DisplayPanel;
import role.RoleMode.Mode;

public abstract class Monster extends Role{
	
	protected ArrayList<String> treasure;
	protected int damage;
	protected Random ran;
	
	public Monster(String name, DisplayPanel display,MapWithObsticle map){
		super(name, display, map);
		human = false;
	    //this->setFixedSize(100,70);
	    width = 100;
	    height = 70;
	    dir=0;
	    jump_mod=0;
	    move_mod=0;
	    able=true;
	    move_pace=10;
	    
	    damage = 10;
	    
	    ran = new Random();
	    
	    treasure = new ArrayList<String>();
	}
	
	public abstract void RandomMove();
	
	public RoleMode getMode(){
	    if(be_hit > 0)
	    	return new RoleMode(Mode.be_hit, be_hit);
	    else if(!map.standable(x+width()/2 , y+height()) || jump_mod > 0)
	    	return new RoleMode(Mode.jump, jump_mod);
	    else if(move_mod > 0)
	    	return new RoleMode(Mode.move, move_mod);
	    else
	    	return new RoleMode(Mode.stand, 0);
	}
	
	public ArrayList<String> getTreasureList(){
		return treasure;
	}
	
	public void setRange(int left, int right){
		move_range_left = left;
		move_range_right = right;
	}
	
	public void setStartPosition(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int damage(){
		return damage;
	}
}
