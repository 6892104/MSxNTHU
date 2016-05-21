package MapleStory;

import java.util.Random;

import MapleStory.RoleMode.Mode;

public class Pig extends Role{
	Random ran;
	public Pig(MainWindow parent, DisplayPanel display,MapWithObsticle map){
		super(parent, display, map);
		human = false;
	    //this->setFixedSize(100,70);
	    width = 100;
	    height = 70;
	    dir=0;
	    jump_mod=0;
	    move_mod=0;
	    able=true;
	    move_pace=10;
	    
	    ran = new Random();
	}
	
	public void RandomMove(){
	    if(able)
	    {
	        if(move_mod <= 0)
	        {
	            if(ran.nextInt(20) == 1){
	                dir = ran.nextInt(2);
	                move_mod = display.getPigPictureNumber()*(ran.nextInt(3)+1);
	            }
	        }
	        if(jump_mod <= 0 && (map.standable(x+width()/2 , y+height()) || climb_mod > 0) && fall <= 0)
	        {
	            if(ran.nextInt(30) == 1){
	                jump_mod = 5;
	            }
	        }
	    }
	}
	
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
}
