package role;

import MapleStory.DisplayPanel;
import MapleStory.MapWithObsticle;
import skill.Skill.Direction;

public abstract class Role {
	
	protected String name;
    protected int x,y;
    //public int screen_x, screen_y;
    protected int width, height;
    protected int shift;
    protected int hp;
    protected int max_hp;
    protected int exp;
    protected int level;
    protected int move_range_left;
    protected int move_range_right;
    protected int move_pace;

    protected int dir;            // 0  Left  1  Right
	    /*enum{left=0,right=1};
	    enum{up=0,down=1};*/

	    //mode
    protected int move_mod;
    protected int jump_mod;
    protected int climb_mod;
    protected int be_hit;
    protected int fall;
    protected boolean able;
    protected int dead_time;
    protected boolean visiable;



    protected DisplayPanel display;
    protected MapWithObsticle map;
    protected boolean human;
    


	protected int [] g;
	  
	public Role(String name, DisplayPanel display,MapWithObsticle map){
		this.name = name;
		this.display = display;
		this.map = map;
	    x = 1000;
	    y = map.getMax_y()/2;
	    shift = 0;
	    //move( x-map.getShift_x() , y-map.getShift_y() );
	    //this->setFixedSize(100,150);
	    move_range_left=0;
	    move_range_right=map.getMax_x();


	    dir=0;
	    jump_mod=0;
	    move_mod=0;
	    climb_mod=0;
	    be_hit=0;
	    fall=0;
	    able=true;
	    visiable = true;


	    move_pace=1;
	    max_hp=100;
	    hp=max_hp;
	    exp=100;
	    level=1;
	    //climb_pic_num=0;

	    g = new int[21];
	    for(int i=2 ; i<23 ; i++){
	        g[i-2]=(int)(i*i/4);
	    }
	}

	public void RoleAction(){
		//move(x-map.getShift_x() , y-map.getShift_y());
	    AtkAction();
	    if(able)
	    {
	        //climb
	        if(climb_mod > 0)
	        {
	            if(climb_mod > 1){
	            	// 0  up   1  down
	                if(dir == 0){
	                    if(human) map.change_shift_y( (-1)*move_pace );
	                    y -= move_pace;
	                }else if(dir == 1){
	                    if(human) map.change_shift_y( move_pace );
	                    y += move_pace;
	                }
	                climb_mod--;
	            }

	            return;
	        }
	    }
	        //jump and fall
	        if(jump_mod > 0)
	        {
	            if(human) map.change_shift_y( (-1)*g[jump_mod] );
	            y -= g[jump_mod--];
	        }else if(!map.standable(x+width()/2 , y+height()))
	        {
	            int amount = g[fall];
	            for(int i=0 ; i<amount ; i++){
	                if(human) map.change_shift_y(1);
	                y++;
	                if(map.standable(x+width()/2 , y+height())) break;
	            }
	            if(fall < 12) fall++;
	        }else{
	            fall=0;
	        }

	        //move
	        if(move_mod > 0)
	        {
	            int move_dir=dir;
	            if(be_hit > 0){
	            	if(dir == 0)
	            		move_dir = 1;
	            	else
	            		move_dir = 0;
	            }
	            // 0  left   1  right
	            if(move_dir == 0 && x-move_pace > move_range_left){
	                if(human) map.change_shift_x( (-1)*move_pace );
	                x-=move_pace;
	            }else if(move_dir == 1 && x+move_pace+width() < move_range_right){
	                if(human) map.change_shift_x( move_pace );
	                x+=move_pace;
	            }
	            move_mod--;
	        }

	    if(be_hit > 0){
	        be_hit--;
	        if(be_hit == 0 && hp > 0)
	            able=true;
	    }
	}
	
	    /*void Be_atk(int,int,int,int,int,int);
	signals:
	    void atk(int,int,int,int,int,int);
	    void dead(int);
	    void create_treasure(int,int,std::string);*/
	public void RoleMove(int direction){
	    if(move_mod<=0 && climb_mod <= 0 && able){
	    	dir = direction;
	        move_mod = display.getCharacterPictureNumber();
	    }
	}
	
    public void climb(int tmp){
    	// 0  up   1  down
    	if(able)
        {
            if(map.climbable(x+width()/2 , y+height()/2)){
                dir=tmp;
                if(climb_mod <= 1)
                    climb_mod=3;
                jump_mod=0;
                fall=0;
                move_mod=0;
            }else if(climb_mod > 0){
                climb_mod = 0;
            }
        }
    }
    
    protected void AtkAction(){
    	if(dead_time > 0){
            dead_time--;
            if(dead_time == 0){
                dir=0;
                jump_mod=0;
                move_mod=0;
                climb_mod=0;
                be_hit=0;
                fall=0;
                hp=max_hp;
                able=true;
                visiable = true;
                //show();
            }
        }
        //if(able) emit atk(x,y,x+width(),y+height(),1000/max_hp,dir);
    }
    
    public boolean beAttacked(int damage, int dir){
    	if(able){
            move_mod = 3;
            if(!human){
	            if(dir == 0)
	            	this.dir = 1;
	            else
	            	this.dir = 0;
            }
            //System.out.println("dir = " + this.dir);
            be_hit = 20;
            hp -= damage;
            able = false;
            if(hp <=0 ){
                visiable = false;
                hp=0;
                dead_time = 200;
                return true;
                /*emit dead(exp);
                if(rand()%2==1){
                    int choose=rand()%8;
                    if(choose == 0) emit create_treasure(x+width()/2,y+height(),"apple");
                    if(choose == 1) emit create_treasure(x+width()/2,y+height(),"red_medicine");
                    if(choose == 2) emit create_treasure(x+width()/2,y+height(),"blue_snail_shell");
                    if(choose == 3) emit create_treasure(x+width()/2,y+height(),"full_medicine");
                    if(choose == 4) emit create_treasure(x+width()/2,y+height(),"orange_medicine");
                    if(choose == 5) emit create_treasure(x+width()/2,y+height(),"blue_medicine");
                    if(choose == 6) emit create_treasure(x+width()/2,y+height(),"mushroom_cap");
                    if(choose == 7) emit create_treasure(x+width()/2,y+height(),"green_wet_fairy");
                }*/
            }
        }
    	return false;
    }
	    //virtual void setMap(MapWithObsticle *);
    
    /*public void move(int x, int y){
    	this.x = x;
    	this.y = y;
    }*/
    
    public boolean visiable(){
    	return visiable;
    }
    
    public String name(){
    	return name;
    }
    
    public int level(){
    	return level;
    }
    
    public int hp(){
    	return hp;
    }
    
    public int maxHP(){
    	return max_hp;
    }
    
    public int exp(){
    	return exp;
    }
    
    public int dir(){
    	return dir;
    }
    
    public int width(){
    	return width;
    }
    
    public int height(){
    	return height;
    }
    
    public int x(){
    	return x;
    }
    
    public int y(){
    	return y;
    }
}
