package Role;



import MapleStory.DisplayPanel;
import MapleStory.MapWithObsticle;
import Role.RoleMode.Mode;
import Skill.NormalAttack;
import Skill.Skill.Direction;

public class Beginner extends Role {
	
	private int[] max_exp;
	private int max_mp;
	private int mp;
	private int original_mp;
	public int atk_mod;
	
	public Beginner(String name, DisplayPanel display, MapWithObsticle map){
		super(name, display, map);
		
		human = true;
		width = 200;
	    height = 100;
	    x=1420;
	    //x = parent.getWidth()/2 - width/2;
	    y=874;
	    //y = parent.getHeight()/2 - height/2;
	    shift=95;
	    move_range_left=0-shift;
	    move_range_right=map.getMax_x()+shift;



	    atk_mod = 0;
	    //this->setFixedSize(200,100);
	    move_pace=10;
	    max_mp=100;
	    mp=max_mp;
	    //level = 1;
	    exp=0;
	    max_exp = new int[100];
	    max_exp[1]=100;
	    //level_effect=0;
	    for(int i=2;i<100;i++)
	        max_exp[i]=max_exp[i-1]+50;


	    






	    /*char slevel_up[30]="Sound/LevelUp.mp3";
	    char snor_attack[30]="Sound/normal attack.mp3";
	    level_up = new easyMusic(slevel_up,80,0);
	    nor_attack = new easyMusic(snor_attack,50,0);

	    char sjump_effect[30]="Sound/Jump.mp3";
	    jump_effect = new easyMusic(sjump_effect,80,0);*/
	}

	    //easyMusic *nor_attack,*level_up, *jump_effect;
	public void gain_hp(int gnhp){
		hp+=gnhp;
	    hp=Math.min(hp,max_hp);
	}
	public void gain_mp(int gnmp){
    	mp+=gnmp;
        mp=Math.min(mp,max_mp);
	}
	
	public RoleMode getMode(){
		if(atk_mod > 0)
	        return new RoleMode(Mode.attack, atk_mod);
	    else if(be_hit > 0)
	    	return new RoleMode(Mode.be_hit, be_hit);
	    else if(climb_mod > 0)
	    	return new RoleMode(Mode.climb, climb_mod);
	    else if(!map.standable(x+width()/2 , y+height()) || jump_mod > 0)
	    	return new RoleMode(Mode.jump, jump_mod);
	    else if(move_mod > 0)
	    	return new RoleMode(Mode.move, move_mod);
	    else
	    	return new RoleMode(Mode.stand, 0);
	}
	
	public void jump(){
		if(jump_mod <= 0 && (map.standable(x+width()/2 , y+height()) || climb_mod > 0) && fall <= 0)
	    {
	        /*if(play_soundEffect)
	            jump_effect.play();*/
	        jump_mod = 10;
	        climb_mod = 0;
	    }
	}
	
	public NormalAttack normal_attack(){
		if(atk_mod <= 0 && climb_mod <= 0 && able){
	      atk_mod = display.getAtkParameter();
	      move_mod = 0;
	      able = false;
	    // 0  left   1  right
	      if(dir == 0){
	    	  return new NormalAttack(x+width()/2 - 100 , y , 100 , height() , 20 , Direction.left , true);
	      }else{
	    	  return new NormalAttack(x+width()/2 , y , 100 , height() , 20 , Direction.right , true);
	      }
	      //if(play_soundEffect) nor_attack->play();
		}
		return null;
	}
	
	@Override
	protected void AtkAction()
	{
	    if(atk_mod > 0){
	        atk_mod--;
	        if(atk_mod == 0)
	            able = true;
	    }
	    //emit state_change(hp*100/max_hp,mp*100/max_mp,exp*100/max_exp[level],level);
	}

	/*public slots:
	    virtual void gain_exp(int);
	signals:
	    void state_change(int,int,int,int);*/
	
	public int mp(){
		return mp;
	}
}
