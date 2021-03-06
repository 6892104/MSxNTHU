package role;



import MapleStory.MapWithObsticle;
import display.DisplayPanel;

public class Pig extends Monster{
	public Pig(String name, DisplayPanel display,MapWithObsticle map){
		super(name, display, map);
		human = false;
	    //this->setFixedSize(100,70);
	    width = 100;
	    height = 70;
	    
	    max_hp=100;
	    hp=max_hp;

	    move_pace=10;
	    exp = 50;
	    damage = 10;
	    
	    treasure.add("ī�G");
	    treasure.add("ۣۣ��");
	    treasure.add("���Ĥ�");
	    
	    money = 30;
	}
	
	@Override
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
}
