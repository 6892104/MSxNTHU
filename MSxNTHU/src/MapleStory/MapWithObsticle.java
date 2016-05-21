package MapleStory;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class MapWithObsticle {
	
	private MainWindow parent;
	private DisplayPanel display;
	private int size_x, size_y, screen_size_x, screen_size_y;
	private int shift_x, shift_y, over_shift_x, over_shift_y;

    private Vector<BlockOnMap> floors;
    private Vector<BlockOnMap> slopes;
    private Vector<BlockAndMonsterLimit> BAML;
	
	public MapWithObsticle(MainWindow parent, DisplayPanel display){
		this.parent = parent;
		this.display = display;
		
		floors = new Vector<BlockOnMap>();
		slopes = new Vector<BlockOnMap>();
		BAML = new Vector<BlockAndMonsterLimit>();
		
		size_x = 2840;
	    size_y = 1748;
	    screen_size_x = 1280;
	    screen_size_y = 720;

	    shift_x = size_x/2 - screen_size_x/2;
	    shift_y = size_y/2 - screen_size_y/2;
	    over_shift_x = 0;
	    over_shift_y = 0;
	    
	    floors.add(new BlockOnMap( 0, 1392 , 2840 , 1402) );      //1
	    BAML.add(new BlockAndMonsterLimit( 0, 1392 , 2840 , 1402 , 4) );       //1
	    floors.add(new BlockOnMap( 822, 1360 , 930 , 1368) );     //2
	    floors.add(new BlockOnMap( 930, 1282 , 1037 , 1290) );    //3
	    floors.add(new BlockOnMap( 1043, 1206 , 1898 , 1216) );   //4
	    BAML.add(new BlockAndMonsterLimit( 1043, 1206 , 1898 , 1216 , 2) );    //4
	    floors.add(new BlockOnMap( 1903, 1283 , 2009 , 1291) );   //5
	    floors.add(new BlockOnMap( 2012, 1359 , 2123 , 1367) );   //6

	    floors.add(new BlockOnMap( 481, 1055 , 756 , 1066) );     //7
	    BAML.add(new BlockAndMonsterLimit( 481, 1055 , 756 , 1066 , 1));       //7
	    floors.add(new BlockOnMap( 823, 1129 , 987 , 1139) );     //8
	    floors.add(new BlockOnMap( 1960, 1131 , 2124 , 1141) );   //9
	    floors.add(new BlockOnMap( 2188, 1056 , 2349 , 1065) );   //10

	    floors.add(new BlockOnMap( 355, 822 , 871 , 832) );       //11
	    BAML.add(new BlockAndMonsterLimit( 355, 822 , 871 , 832 , 1));         //11
	    floors.add(new BlockOnMap( 820, 748 , 986 , 758) );       //12
	    floors.add(new BlockOnMap( 1038, 674 , 1899 , 684) );     //13
	    BAML.add(new BlockAndMonsterLimit( 1038, 674 , 1899 , 684 , 2));       //13
	    floors.add(new BlockOnMap( 1954, 597 , 2122 , 607) );     //14
	    floors.add(new BlockOnMap( 2067, 674 , 2242 , 684) );     //15
	    floors.add(new BlockOnMap( 2180, 750 , 2809 , 760) );     //16
	    BAML.add(new BlockAndMonsterLimit( 2180, 750 , 2809 , 760 , 2));       //16

	    floors.add(new BlockOnMap( 22, 446 , 539 , 456 ) );       //17
	    floors.add(new BlockOnMap( 588, 522 , 764 , 532) );       //18
	    floors.add(new BlockOnMap( 814, 597 , 990 , 607) );       //19
	    floors.add(new BlockOnMap( 1043, 295 , 1900 , 305) );     //20
	    BAML.add(new BlockAndMonsterLimit( 1043, 295 , 1900 , 305 , 3));       //20
	    floors.add(new BlockOnMap( 2293, 443 , 2810 , 453) );     //21

	    slopes.add(new BlockOnMap(675 , 993 , 690 , 1311) );      //1
	    slopes.add(new BlockOnMap(777 , 759 , 815 , 1044) );      //2
	    slopes.add(new BlockOnMap(2220 , 689 , 2260 , 969) );     //3
	    slopes.add(new BlockOnMap(336 , 389 , 371 , 736) );       //4
	    slopes.add(new BlockOnMap(1465 , 226 , 1500 , 625) );     //5
	    slopes.add(new BlockOnMap(2536 , 382 , 2571 , 666) );     //6
	}
	
	public boolean standable(int x, int y){
		BlockOnMap it;
	    for(int i = 0 ; i < floors.size() ; i++)
	    {
	    	it = floors.get(i);
	        if( x > it.start_x  &&  x < it.end_x ){
	            if( y > it.start_y  &&  y < it.end_y){
	                return true;
	            }
	        }
	    }
	    return false;
	}
	
	public boolean climbable(int x ,int y){
		BlockOnMap it;
	    for(int i = 0 ; i < slopes.size() ; i++)
	    {
	    	it = slopes.get(i);
	        if( x > it.start_x  &&  x < it.end_x ){
	            if( y > it.start_y  &&  y < it.end_y){
	                return true;
	            }
	        }
	    }
	    return false;
    }

	public int getMax_x(){
		return size_x;
	}
	
	public int getMax_y(){
		return size_y;
	}
	
	public int getShift_x(){
		return shift_x;
	}
	
	public int getShift_y(){
		return shift_y;
	}
	
	public void change_shift_x(int amount){
		if(amount >= 0)
	    {
	        if(over_shift_x < 0){
	            if(over_shift_x + amount <= 0) over_shift_x += amount;
	            else{
	                shift_x += amount + over_shift_x;
	                over_shift_x = 0;
	            }
	        }
	        else if(shift_x + amount < size_x - screen_size_x){
	            shift_x += amount;
	        }
	        else{
	            if(shift_x < size_x - screen_size_x){
	                over_shift_x += amount-( (size_x-screen_size_x) - shift_x );
	                shift_x = size_x - screen_size_x;
	            }else{                                              //shift_x == size_x - screen_size_x
	                over_shift_x += amount;
	            }
	        }
	    }
	    else
	    {
	        if(over_shift_x > 0){
	            if(over_shift_x + amount >= 0) over_shift_x += amount;
	            else{
	                shift_x += amount + over_shift_x;
	                over_shift_x = 0;
	            }
	        }
	        else if(shift_x + amount > 0){
	            shift_x += amount;
	        }
	        else{
	            if(shift_x > 0){
	                over_shift_x += amount + shift_x;           //amount - (0-shift_x)
	                shift_x = 0;
	            }else{                  // shift_x == 0
	                over_shift_x += amount;
	            }
	        }
	    }
	}
	
	public void change_shift_y(int amount){
		if(amount >= 0)
	    {
	        if(over_shift_y < 0){
	            if(over_shift_y + amount <= 0) over_shift_y += amount;
	            else{
	                shift_y += amount + over_shift_y;
	                over_shift_y = 0;
	            }
	        }
	        else if(shift_y + amount < size_y - screen_size_y){
	            shift_y += amount;
	        }else{
	            if(shift_y < size_y - screen_size_y){
	                over_shift_y += amount-( (size_y-screen_size_y) - shift_y );
	                shift_y = size_y - screen_size_y;
	            }else{                   //shift_y == size_y - screen_size_y
	                over_shift_y += amount;
	            }
	        }
	    }
	    else    //amount < 0
	    {
	        if(over_shift_y > 0){
	            if(over_shift_y + amount >= 0) over_shift_y += amount;
	            else{
	                shift_y += amount + over_shift_y;
	                over_shift_y = 0;
	            }
	        }
	        else if(shift_y + amount > 0){
	            shift_y += amount;
	        }
	        else{                           // over_shift_y <= 0 && shift_y + amount <= 0
	            if(shift_y > 0){
	                over_shift_y += amount + shift_y;           //amount - (0-shift_y)
	                shift_y = 0;
	            }else{                      // shift_y == 0
	                over_shift_y += amount;
	            }
	        }
	    }
	}
	
	public ArrayList<Pig> createMonster(){
		//pig_num = 0;
	    /*vector <BlockAndMonsterLimit> blk = map.get_each_BlockAndMonsterLimit();
	    std::vector <BlockAndMonsterLimit>::iterator it;*/
		Random ran = new Random();
		ArrayList<Pig> monsters = new ArrayList<Pig>();
	    for(int i = 0 ; i < BAML.size() ; i++){
	    	BlockAndMonsterLimit baml = BAML.get(i);
	        for(int j = 0 ; j < baml.limit ; j++){
	            Pig piggy = new Pig(parent, display, this);
	            piggy.move_range_left = baml.start_x;
	            piggy.move_range_right = baml.end_x;
	            piggy.x = baml.start_x + ran.nextInt(baml.end_x -piggy.width() - baml.start_x + 1);
	            piggy.y = baml.start_y - piggy.height() - 10;
	            monsters.add(piggy);
	            //piggy[pig_num]->show();
	            /*connect(Timer,SIGNAL(timeout()),piggy[pig_num],SLOT(update()));
	            connect(Timer,SIGNAL(timeout()),piggy[pig_num],SLOT(RoleAction()));
	            connect(Timer,SIGNAL(timeout()),piggy[pig_num],SLOT(RandomMove()));
	            connect(role,SIGNAL(atk(int,int,int,int,int,int)),piggy[pig_num],SLOT(Be_atk(int,int,int,int,int,int)));
	            connect(piggy[pig_num],SIGNAL(atk(int,int,int,int,int,int)),role,SLOT(Be_atk(int,int,int,int,int,int)));
	            connect(piggy[pig_num],SIGNAL(dead(int)),role,SLOT(gain_exp(int)));
	            connect(piggy[pig_num],SIGNAL(create_treasure(int,int,std::string)),this,SLOT(tcreator(int,int,std::string)));
	            pig_num++;*/
	        }
	    }
	    /*green_num = 0;
	    for(it=blk.begin();it!=blk.end() && which == green;it++){
	        for(int i=0;i<(*it).limit;i++){
	            greens[green_num] = new Green(map, this);
	            greens[green_num]->move_range_left = (*it).start_x;
	            greens[green_num]->move_range_right = (*it).end_x;
	            greens[green_num]->x = (*it).start_x + rand()%((*it).end_x -greens[green_num]->width() - (*it).start_x + 1);
	            greens[green_num]->y = (*it).start_y - greens[green_num]->height() - 10;
	            greens[green_num]->show();
	            connect(Timer,SIGNAL(timeout()),greens[green_num],SLOT(update()));
	            connect(Timer,SIGNAL(timeout()),greens[green_num],SLOT(RoleAction()));
	            connect(Timer,SIGNAL(timeout()),greens[green_num],SLOT(RandomMove()));
	            connect(role,SIGNAL(atk(int,int,int,int,int,int)),greens[green_num],SLOT(Be_atk(int,int,int,int,int,int)));
	            connect(greens[green_num],SIGNAL(atk(int,int,int,int,int,int)),role,SLOT(Be_atk(int,int,int,int,int,int)));
	            connect(greens[green_num],SIGNAL(dead(int)),role,SLOT(gain_exp(int)));
	            connect(greens[green_num],SIGNAL(create_treasure(int,int,std::string)),this,SLOT(tcreator(int,int,std::string)));
	            green_num++;
	        }
	    }*/
	    return monsters;
	}
//----------------------------------------------------------------	
//-----------------inner classes----------------------------------
//----------------------------------------------------------------
	class BlockOnMap
	{
		public int start_x , start_y;
		public int end_x , end_y;
		public BlockOnMap(int sx , int sy , int ex , int ey){
	    	start_x = sx;
	    	start_y = sy;
	    	end_x = ex;
	    	end_y = ey;
	    }
	};
	
	class BlockAndMonsterLimit extends BlockOnMap
	{
		public int limit;
	    public BlockAndMonsterLimit(int sx , int sy , int ex , int ey , int limit){
	    	super(sx, sy, ex, ey);
	    	this.limit = limit;
	    }
	};
}
