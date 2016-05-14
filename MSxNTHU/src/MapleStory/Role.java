package MapleStory;

public abstract class Role {
	/*public:
	    void paintEvent(QPaintEvent *);*/
    protected int x,y;
    protected int width, height;
    protected int shift;
    protected int hp;
    protected int max_hp;
    protected int exp;
    protected int level;
    protected int move_range_left;
    protected int move_range_right;
    protected int move_pace;

    protected Direction dir;            // 0  Left  1  Right
	    /*enum{left=0,right=1};
	    enum{up=0,down=1};*/

	    //mode
    protected double move_mod;
    protected int jump_mod;
    protected int climb_mod;
    protected int be_hit;
    protected int fall;
    protected boolean able;
    protected int dead_time;




    protected MapWithObsticle map;
    protected boolean human;
    
	public int pic_num;
	    /*QString std_pic[2];
	    QString jump_pic[2];
	    QString move_pic[2][10];
	    QString climb_pic[2];*/
	public int climb_pic_num;
	    //QString be_hit_pic[2];
	public int [] g;
	  
	public Role(MapWithObsticle _map){
		map = _map;
	    x = 1000;
	    y = map.getMax_y()/2;
	    shift = 0;
	    //move( x-map.getShift_x() , y-map.getShift_y() );
	    //this->setFixedSize(100,150);
	    move_range_left=0;
	    move_range_right=map.getMax_x();


	    dir=Direction.left;
	    jump_mod=0;
	    move_mod=0;
	    climb_mod=0;
	    be_hit=0;
	    fall=0;
	    able=true;


	    move_pace=1;
	    max_hp=100;
	    hp=max_hp;
	    exp=100;
	    level=1;
	    climb_pic_num=0;

	    g = new int[21];
	    for(int i=2 ; i<23 ; i++){
	        g[i-2]=(int)(i*i/4);
	    }
	}
	/*private slots:
	    void RoleAction();
	    void Be_atk(int,int,int,int,int,int);
	signals:
	    void atk(int,int,int,int,int,int);
	    void dead(int);
	    void create_treasure(int,int,std::string);*/
	protected void RoleMove(Direction direction){
		dir = direction;
	    if(move_mod<=0 && climb_mod <= 0 && able)
	        move_mod = pic_num;
	}
    /*protected void climb(int tmp){
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
                dir=left;
                jump_mod=0;
                move_mod=0;
                climb_mod=0;
                be_hit=0;
                fall=0;
                hp=max_hp;
                able=true;
                show();
            }
        }
        //if(able) emit atk(x,y,x+width(),y+height(),1000/max_hp,dir);
    }*/
	    //virtual void setMap(MapWithObsticle *);
    public int width(){
    	return width;
    }
    
    public int height(){
    	return width;
    }
}
