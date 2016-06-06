package role;



import javax.swing.JOptionPane;

import MapleStory.MapWithObsticle;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import display.DisplayPanel;
import processing.core.PApplet;
import role.RoleMode.Mode;
import skill.NormalAttack;
import skill.Skill.Direction;
import skill.Skymad;

public class Beginner extends Role {
	
	private int[] max_exp;
	private int max_mp;
	private int mp;
	public int atk_mod;
	private int level_effect;
	
	public Tomb tomb;

	Minim minim;
	AudioPlayer lvUp;
	AudioPlayer norAtk;
	AudioPlayer jumpEffect;
	AudioPlayer tombFall;

	public boolean soundOn;
	
	
	public Beginner(String name, DisplayPanel display, MapWithObsticle map){
		super(name, display, map);
		
		human = true;
		width = 200;
	    height = 100;
	    //x=1420;
	    x = map.getMax_x()-200;
	    map.change_shift_x(x - map.getMax_x()/2);
	    //x = parent.getWidth()/2 - width/2;
	    //y=874;
	    y = 0;
	    map.change_shift_y(y - map.getMax_y()/2);
	    //y = parent.getHeight()/2 - height/2;
	    shift=95;
	    move_range_left=0-shift;
	    move_range_right=map.getMax_x()+shift-30;

	    atk = 20;
	    matk = 0;
	    def = 0;
	    mdef = 0;

	    atk_mod = 0;
	    //this->setFixedSize(200,100);
	    move_pace=10;
	    max_mp=100;
	    mp=max_mp;
	    level = 1;
	    exp=0;
	    max_exp = new int[100];
	    max_exp[1]=100;
	    level_effect=0;
	    for(int i=2;i<100;i++)
	        max_exp[i]=max_exp[i-1]+50;


	    tomb = new Tomb();






	    /*char slevel_up[30]="Sound/LevelUp.mp3";
	    char snor_attack[30]="normal_attack.mp3";
	    level_up = new easyMusic(slevel_up,80,0);
	    nor_attack = new easyMusic(snor_attack,50,0);

	    char sjump_effect[30]="Sound/Jump.mp3";
	    jump_effect = new easyMusic(sjump_effect,80,0);*/
	    
	    minim = new Minim(new PApplet());
	    lvUp = minim.loadFile(this.getClass().getResource("/LevelUp.mp3").getPath());
	    norAtk = minim.loadFile(this.getClass().getResource("/normal_attack.mp3").getPath());
	    jumpEffect = minim.loadFile(this.getClass().getResource("/Jump.mp3").getPath());
	    tombFall = minim.loadFile(this.getClass().getResource("/dead.mp3").getPath());
	    soundOn = true;
	}
	
	public Beginner(String name, DisplayPanel display, MapWithObsticle map, int lv, int exp, int hp, int max_hp, int mp, int max_mp){
		this(name, display, map);
		this.level = lv;
		this.exp = exp;
		this.hp = hp;
		this.max_hp = max_hp;
		this.mp = mp;
		this.max_mp = max_mp;
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
			if(soundOn) {
				jumpEffect.rewind();
				jumpEffect.play();
			}
	        jump_mod = 10;
	        climb_mod = 0;
	    }
	}
	
	public NormalAttack normal_attack(){
		if(atk_mod <= 0 && climb_mod <= 0 && able){
	      atk_mod = display.getAtkParameter();
	      move_mod = 0;
	      able = false;
	      if(soundOn) {
	    	  norAtk.rewind();
	    	  norAtk.play();
	      }

	    // 0  left   1  right
	      if(dir == 0){
	    	  return new NormalAttack(x+width()/2 - 100 , y , 100 , height() , atk , Direction.left , true);
	      }else{
	    	  return new NormalAttack(x+width()/2 , y , 100 , height() , atk , Direction.right , true);
	      }
	      //if(play_soundEffect) nor_attack->play();
		}
		return null;
	}
	
	public Skymad skymad(){
		if(atk_mod <= 0 && climb_mod <= 0 && able){
			if(mp >= 100){
				mp -= 100;
				atk_mod = display.getAtkParameter();
				move_mod = 0;
				able = false;
				if(soundOn) {
					norAtk.rewind();
					norAtk.play();
				}
				return new Skymad(x + width/2, y + height, 9999, Direction.left, true);
			}
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
	    if(level_effect > 0)
	    	level_effect--;
	}
	
	
	public void beBumped(int x, int y, int width, int height, int damage){
		if(this.x+this.width - shift > x && this.x + shift < x+width && this.y+this.height > y && this.y < y+height){
			beAttacked(damage, dir);
		}
	}

	/*public slots:
	    virtual void gain_exp(int);
	signals:
	    void state_change(int,int,int,int);*/
	
	public void gainEXP(int new_exp){
		exp+=new_exp;
	    if(exp >= max_exp[level])
	    {
	        /*if(play_soundEffect)
	            level_up->play();*/
	    	if(soundOn) {
	    		lvUp.rewind();
	    		lvUp.play();
	    	}
	        exp-=max_exp[level];
	        level++;
	        max_hp+=20;
	        hp=max_hp;
	        max_mp+=20;
	        mp=max_mp;
	        level_effect = 60;
	        
	        atk += 10;
	    }
	}
	
	public void setPosition(int x, int y){
		this.x = x;
	    map.change_shift_x(x - map.getMax_x()/2);
	    this.y = y;
	    map.change_shift_y(y - map.getMax_y()/2);
	    move_range_right = map.getMax_x() + shift;
	}
	
	public int levelEffect(){
		return level_effect;
	}
	
	public int mp(){
		return mp;
	}
	
	public int maxMP(){
		return max_mp;
	}
	
	public int maxEXP(){
		return max_exp[level];
	}
	
	public boolean isDead(){
		if(hp <= 0)
			return true;
		else
			return false;
	}
	
//----------------------------------------------------------------	
//-----------------inner classes----------------------------------
//----------------------------------------------------------------	
	public class Tomb{
		private int y;
		private int tombEffect;
		
		public Tomb(){
			y = y() - 190;
			tombEffect = 0;
		}
		
		private void reset(){
			JOptionPane.showMessageDialog(null, "UCCU\n你死去了。\n哈哈笑你～", "系統 :", JOptionPane.WARNING_MESSAGE );
			x = x();
			y = y() - 190;
			tombEffect = 21;
			if(soundOn) {
				tombFall.rewind();
				tombFall.play();
			}
		}
		
		public void move(){
			if(tombEffect > 1){
				y = y + 10;
				tombEffect--;
			}
			if(hp <= 0 && tombEffect == 0)
				reset();
		}
		
		public int getParameter(){
			return tombEffect;
		}
		
		public int getY(){
			return y;
		}
	}
}
