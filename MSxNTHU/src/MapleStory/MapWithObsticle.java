package MapleStory;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;
import role.Green;
import role.Monster;
import role.NPC;
import role.Pig;


public class MapWithObsticle {
	private String name;
	private DisplayPanel display;
	private int size_x, size_y, screen_size_x, screen_size_y;
	private int shift_x, shift_y, over_shift_x, over_shift_y;

	private Vector<BlockOnMap> floors;
	private Vector<BlockOnMap> slopes;
	private Vector<BlockAndMonsterLimit> BAML;
	private Vector<TransPoint> transPoints;
	private Vector<NPCpoint> npcs;

	JSONObject data;
	JSONArray data_array;

	public MapWithObsticle(DisplayPanel display, String file){
		this.display = display;
		this.name = file;
		
		floors = new Vector<BlockOnMap>();
		slopes = new Vector<BlockOnMap>();
		BAML = new Vector<BlockAndMonsterLimit>();
		transPoints = new Vector<TransPoint>();
		npcs = new Vector<NPCpoint>();
		
		try{
			file = "map/" + name + "_data.json";
			data = new PApplet().loadJSONObject(file);
		}catch (NullPointerException ie){
			javax.swing.JOptionPane.showMessageDialog(null, "載入地圖data錯誤");
		}
		size_x = data.getInt("size_x");
		size_y = data.getInt("size_y");
		
		data_array = data.getJSONArray("Floors");

		for(int i=0; i<data_array.size(); i++) {
			int startX = data_array.getJSONObject(i).getInt("startX");
			int startY = data_array.getJSONObject(i).getInt("startY");
			int endX = data_array.getJSONObject(i).getInt("endX");
			int endY = data_array.getJSONObject(i).getInt("endY");
			
			floors.add(new BlockOnMap(startX, startY, endX, endY));
		}
		data_array = data.getJSONArray("Slopes");
		for(int i=0; i<data_array.size(); i++) {
			int startX = data_array.getJSONObject(i).getInt("startX");
			int startY = data_array.getJSONObject(i).getInt("startY");
			int endX = data_array.getJSONObject(i).getInt("endX");
			int endY = data_array.getJSONObject(i).getInt("endY");

			slopes.add(new BlockOnMap(startX, startY, endX, endY));
		}
		data_array = data.getJSONArray("BAML");
		for(int i=0; i<data_array.size(); i++) {
			int startX = data_array.getJSONObject(i).getInt("startX");
			int startY = data_array.getJSONObject(i).getInt("startY");
			int endX = data_array.getJSONObject(i).getInt("endX");
			int endY = data_array.getJSONObject(i).getInt("endY");
			int limit = data_array.getJSONObject(i).getInt("limit");
			String name = data_array.getJSONObject(i).getString("monster");

			BAML.add(new BlockAndMonsterLimit(startX, startY, endX, endY, limit, name));
		}

		data_array = data.getJSONArray("TransPoints");
		for(int i=0; i<data_array.size(); i++) {
			int startX = data_array.getJSONObject(i).getInt("startX");
			int startY = data_array.getJSONObject(i).getInt("startY");
			int endX = data_array.getJSONObject(i).getInt("endX");
			int endY = data_array.getJSONObject(i).getInt("endY");
			String name = data_array.getJSONObject(i).getString("name");

			transPoints.add(new TransPoint(startX, startY, endX, endY, name));
		}
		
		data_array = data.getJSONArray("NPCpoints");
		for(int i=0; i<data_array.size(); i++) {
			int x = data_array.getJSONObject(i).getInt("x");
			int y = data_array.getJSONObject(i).getInt("y");
			int width = data_array.getJSONObject(i).getInt("width");
			int height = data_array.getJSONObject(i).getInt("height");
			String name = data_array.getJSONObject(i).getString("name");

			npcs.add(new NPCpoint(x, y, width, height, name));
		}

		screen_size_x = 1280;
		screen_size_y = 720;

		shift_x = size_x/2 - screen_size_x/2 + 100;
		shift_y = size_y/2 - screen_size_y/2 + 50;
		over_shift_x = 0;
		over_shift_y = 0;
		
/*		floors.add(new BlockOnMap( 0, 1392 , 2840 , 1402) );	  //1
		BAML.add(new BlockAndMonsterLimit( 0, 1392 , 2840 , 1402 , 4) );	   //1
		floors.add(new BlockOnMap( 822, 1360 , 930 , 1368) );	 //2
		floors.add(new BlockOnMap( 930, 1282 , 1037 , 1290) );	//3
		floors.add(new BlockOnMap( 1043, 1206 , 1898 , 1216) );   //4
		BAML.add(new BlockAndMonsterLimit( 1043, 1206 , 1898 , 1216 , 2) );	//4
		floors.add(new BlockOnMap( 1903, 1283 , 2009 , 1291) );   //5
		floors.add(new BlockOnMap( 2012, 1359 , 2123 , 1367) );   //6

		floors.add(new BlockOnMap( 481, 1055 , 756 , 1066) );	 //7
		BAML.add(new BlockAndMonsterLimit( 481, 1055 , 756 , 1066 , 1));	   //7
		floors.add(new BlockOnMap( 823, 1129 , 987 , 1139) );	 //8
		floors.add(new BlockOnMap( 1960, 1131 , 2124 , 1141) );   //9
		floors.add(new BlockOnMap( 2188, 1056 , 2349 , 1065) );   //10

		floors.add(new BlockOnMap( 355, 822 , 871 , 832) );	   //11
		BAML.add(new BlockAndMonsterLimit( 355, 822 , 871 , 832 , 1));		 //11
		floors.add(new BlockOnMap( 820, 748 , 986 , 758) );	   //12
		floors.add(new BlockOnMap( 1038, 674 , 1899 , 684) );	 //13
		BAML.add(new BlockAndMonsterLimit( 1038, 674 , 1899 , 684 , 2));	   //13
		floors.add(new BlockOnMap( 1954, 597 , 2122 , 607) );	 //14
		floors.add(new BlockOnMap( 2067, 674 , 2242 , 684) );	 //15
		floors.add(new BlockOnMap( 2180, 750 , 2809 , 760) );	 //16
		BAML.add(new BlockAndMonsterLimit( 2180, 750 , 2809 , 760 , 2));	   //16

		floors.add(new BlockOnMap( 22, 446 , 539 , 456 ) );	   //17
		floors.add(new BlockOnMap( 588, 522 , 764 , 532) );	   //18
		floors.add(new BlockOnMap( 814, 597 , 990 , 607) );	   //19
		floors.add(new BlockOnMap( 1043, 295 , 1900 , 305) );	 //20
		BAML.add(new BlockAndMonsterLimit( 1043, 295 , 1900 , 305 , 3));	   //20
		floors.add(new BlockOnMap( 2293, 443 , 2810 , 453) );	 //21

		slopes.add(new BlockOnMap(675 , 993 , 690 , 1311) );	  //1
		slopes.add(new BlockOnMap(777 , 759 , 815 , 1044) );	  //2
		slopes.add(new BlockOnMap(2220 , 689 , 2260 , 969) );	 //3
		slopes.add(new BlockOnMap(336 , 389 , 371 , 736) );	   //4
		slopes.add(new BlockOnMap(1465 , 226 , 1500 , 625) );	 //5
		slopes.add(new BlockOnMap(2536 , 382 , 2571 , 666) );	 //6
*/
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
	
	public String name(){
		return name;
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
				}else{											  //shift_x == size_x - screen_size_x
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
					over_shift_x += amount + shift_x;		   //amount - (0-shift_x)
					shift_x = 0;
				}else{				  // shift_x == 0
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
				}else{				   //shift_y == size_y - screen_size_y
					over_shift_y += amount;
				}
			}
		}
		else	//amount < 0
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
			else{						   // over_shift_y <= 0 && shift_y + amount <= 0
				if(shift_y > 0){
					over_shift_y += amount + shift_y;		   //amount - (0-shift_y)
					shift_y = 0;
				}else{					  // shift_y == 0
					over_shift_y += amount;
				}
			}
		}
	}
	
	public ArrayList<Monster> createMonster(){
		Random ran = new Random();
		ArrayList<Monster> monsters = new ArrayList<Monster>();
		for(int i = 0 ; i < BAML.size() ; i++){
			BlockAndMonsterLimit baml = BAML.get(i);
			for(int j = 0 ; j < baml.limit ; j++){
				Monster mon;
				if(baml.monster.equals("Pig"))
					mon = new Pig("肥肥", display, this);
				else
					mon = new Green("綠水靈", display, this);
				mon.setRange(baml.start_x, baml.end_x);
				int tmpX = baml.start_x + ran.nextInt(baml.end_x -mon.width() - baml.start_x + 1);
				int tmpY = baml.start_y - mon.height() - 10;
				mon.setStartPosition(tmpX, tmpY);
				monsters.add(mon);
			}
		}
		return monsters;
	}
	
	public ArrayList<NPC> createNPC(){
		ArrayList<NPC> npclist = new ArrayList<NPC>();
		for(int i = 0 ; i < npcs.size() ; i++){
			NPCpoint npcpoint = npcs.get(i);
			NPC npc = new NPC(npcpoint.name, display, this);
			//System.out.println(npcpoint.x +" " +npcpoint.y);
			npc.set(npcpoint.x - npcpoint.width/2, npcpoint.y - npcpoint.height, npcpoint.width, npcpoint.height);
			npclist.add(npc);
		}
		return npclist;
	}
//----------------------------------------------------------------	
//-----------------inner classes----------------------------------
//----------------------------------------------------------------
	private class BlockOnMap
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
	
	private class BlockAndMonsterLimit extends BlockOnMap
	{
		private String monster;
		private int limit;
		private BlockAndMonsterLimit(int sx , int sy , int ex , int ey , int limit, String monster){
			super(sx, sy, ex, ey);
			this.limit = limit;
			this.monster = monster;
		}
	};
	
	private class TransPoint extends BlockOnMap {
		private String mapName;
		private TransPoint(int sx, int sy, int ex, int ey, String name) {
			super(sx, sy, ex, ey);
			this.mapName = name;
		}
	};
	
	private class NPCpoint{
		private String name;
		private int x, y;
		private int width, height;
		private NPCpoint(int x, int y, int width, int height, String name){
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
			this.name = name;
		}
	}
}
