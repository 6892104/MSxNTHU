package MapleStory;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;

import bag.Bag;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import display.DisplayPanel;
import item.Item;
import item.ItemDatabase;
import item.Money;
import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;
import role.Beginner;
import role.Monster;
import role.NPC;
import sign_up.SignUpClient;
import skill.Skill;

public class Control extends Thread{

	private MapWithObsticle map;
	private Beginner character;
	private ArrayList<Monster> monsters;
	private ArrayList<NPC> npcs;
	private ArrayList<Skill> skills;
	private ArrayList<Item> items;
	private ArrayList<Money> moneys;
	private Bag bag;
	private int bagDelay;
	private int[] fastDelay;
	private ItemDatabase dataBase;
	
	private DisplayPanel display;
	private KeyControl keyControl;

	Minim minim;
	AudioPlayer drop;
	AudioPlayer pick;
	private boolean soundOn;
	public boolean gameing;

	public Control(DisplayPanel display){
		this.display = display;
		map = new MapWithObsticle(display, this);
	    skills = new ArrayList<Skill>();
	    dataBase = new ItemDatabase();
	    bagDelay = 0;
	    fastDelay = new int[9];
	    items = new ArrayList<Item>();
	    moneys = new ArrayList<Money>();
	    npcs = new ArrayList<NPC>();
	    /*JButton button = new JButton();
	    button.setBorderPainted(false);
	    button.setBorder(null);
	    button.setFocusable(false);
	    button.setMargin(new Insets(0, 0, 0, 0));
	    button.setContentAreaFilled(false);
	    button.setIcon(myIcon1);
	    button.setRolloverIcon(myIcon2);
	    button.setPressedIcon(myIcon3);
	    button.setDisabledIcon(myIcon4);*/
	    minim = new Minim(new PApplet());
	    drop = minim.loadFile(this.getClass().getResource("/DropItem.mp3").getPath());
	    pick = minim.loadFile(this.getClass().getResource("/PickUpItem.mp3").getPath());
	    soundOn = true;
	}
	
	public void setData(String name, String newMap, 
			int lv, int exp, int hp, int max_hp, int mp, int max_mp, int atk, int matk, int def, int mdef){
		character = new Beginner(name, display, map, lv, exp, hp, max_hp, mp, max_mp, atk, matk, def, mdef);
		display.setCharacter(character);
		resetMap(newMap);
	    bag = new Bag(display, character);
	    display.setBag(bag);
	}
	
	@Override
	public void run() {
		super.run();
		long lastTime = System.currentTimeMillis();
		while (gameing){
			try{
				Thread.sleep(40);
				keyDetect();
				if(character.isDead()){
					character.tomb.move();
				}else
					character.RoleAction();
				
				for(int i = 0 ; i < monsters.size() ; i++){
					Monster mon = monsters.get(i);
					mon.RandomMove();
					mon.RoleAction();
					if(mon.visiable()){
						character.beBumped(mon.x(), mon.y(), mon.width(), mon.height(), mon.damage());
					}
				}
				
				for(int i = 0 ; i < npcs.size() ; i++){
					NPC npc = npcs.get(i);
					npc.move(npc.x() - map.getShift_x(), npc.y()- map.getShift_y());
				}
				
				Iterator<Skill> it = skills.iterator();
				while(it.hasNext()){
					Skill sk = it.next();
					if(sk.isHuman()){
						for(int j = 0 ; j < monsters.size() ; j++){
							Monster mon = monsters.get(j);
							if(sk.hit(mon.x(), mon.y(), mon.width(), mon.height())){
								boolean isDead = mon.beAttacked(sk.damage(), character.dir());
								if(isDead){
									character.gainEXP(mon.exp());
									createTreasure( mon.getTreasureList(), mon.money(), mon.x() + mon.width()/2, mon.y() + mon.height());
								}
							}
						}
					}
					if(sk.arrive()){
						//System.out.println("fuck");
						it.remove();
					}
				}
				
				bagDelay--;
				for(int i = 1 ; i <= 8 ; i++){
					fastDelay[i]--;
			    }
				
				display.setItem(items);
				display.setMoney(moneys);
				display.repaint();
				//System.out.println(keyControl.get("right"));
			} catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	
	private void createTreasure(ArrayList<String> list, int money, int x, int y){
		if(soundOn) {
			drop.rewind();
			drop.play();
		}
		for(int i = 0; i < list.size() ; i++){
			Item item = dataBase.createItem(list.get(i));
			if(item != null){
				item.x = x;
				item.y = y;
				items.add(item);
				if(x + item.width() > map.getMax_x() - 20)
					x -= item.width();
				x = x + item.width();
			}
		}
		if(x + 50 > map.getMax_x())
			x -= 50;
		moneys.add(new Money(money, x, y));
	}
	
	private void pickUp(){
		Iterator<Item> it = items.iterator();
		while(it.hasNext()){
			Item item = it.next();
			if(character.x() + character.width()/2 > item.x && character.x() + character.width()/2 < item.x + item.width()){
				if(character.y() + character.height() > item.y - item.height() && character.y() < item.y){
					//System.out.println("fuck");
					bag.putItem(item);
					it.remove();
					if(soundOn) {
						pick.rewind();
						pick.play();
					}
				}
			}
		}
		Iterator<Money> money_it = moneys.iterator();
		while(money_it.hasNext()){
			Money money = money_it.next();
			if(character.x() + character.width()/2 > money.x() && character.x() + character.width()/2 < money.x() + money.width()){
				if(character.y() + character.height() > money.y() - money.height() && character.y() < money.y()){
					//System.out.println("fuck");
					bag.putMoney(money.amount());
					money_it.remove();
					if(soundOn) {
						pick.rewind();
						pick.play();
					}
				}
			}
		}
	}
	
	public Item creatItem(String name){
		System.out.println(name);
		return dataBase.createItem(name, true);
	}
	
	public void closeGame(String account){
		SignUpClient client = new SignUpClient("127.0.0.1", 6687);
		client.connect();
		client.sendMessage("write");
		client.sendMessage(account);
		//client.sendMessage(character.name());
		client.sendMessage(map.name());
		client.sendMessage(Integer.toString(character.level()));
		client.sendMessage(Integer.toString(character.exp()));
		client.sendMessage(Integer.toString(character.hp()));
		client.sendMessage(Integer.toString(character.maxHP()));
		client.sendMessage(Integer.toString(character.mp()));
		client.sendMessage(Integer.toString(character.maxMP()));
		client.sendMessage(Integer.toString(character.atk()));
		client.sendMessage(Integer.toString(character.matk()));
		client.sendMessage(Integer.toString(character.def()));
		client.sendMessage(Integer.toString(character.mdef()));
		client.sendMessage(Integer.toString(bag.money()));
		storeBag(client);
		client.sendMessage("done");
		client.sendMessage("completed");
		System.out.println("fuck1");
		//client.closeConnection();
		System.out.println("fuck");
	}
	
	private void storeBag(SignUpClient client){
		JSONObject data;
		JSONArray data_array;
		try{
			String file = "item/item_data.json";
			data = new PApplet().loadJSONObject(file);
		
			data_array = data.getJSONArray("Consumables");
			for(int i =0; i< data_array.size(); i++) {
				String name = data_array.getJSONObject(i).getString("name");
				int number = bag.searchNumber(name);
				client.sendMessage(name);
				client.sendMessage(Integer.toString(number));
			}
			
			/*data_array = data.getJSONArray("Equipments");
			for(int i =0; i< data_array.size(); i++) {
				String name = data_array.getJSONObject(i).getString("name");
				try { 
					itemPicture.put(name, ImageIO.read(this.getClass().getResourceAsStream("/item/consumable/" + name + ".png")));
				}catch (Exception ie){
					javax.swing.JOptionPane.showMessageDialog(null, "載入"+name+"圖檔錯誤");
				}
			}*/
			
			data_array = data.getJSONArray("Other Items");
			for(int i =0; i< data_array.size(); i++) {
				String name = data_array.getJSONObject(i).getString("name");
				int number = bag.searchNumber(name);
				client.sendMessage(name);
				client.sendMessage(Integer.toString(number));
			}
		}catch (NullPointerException ie){
			javax.swing.JOptionPane.showMessageDialog(null, "載入物品data錯誤");
		}
	}
	
	public void setBagItem(String name, int number){
		//System.out.println("fuck" + name);
		for(int i = 0 ; i < number ; i++){
			bag.putItem(dataBase.createItem(name, true));
		}
	}
	
	public void setBagItem(Item item){
		bag.putItem(item);
	}
	
	public void setBagMoney(int amount){
		bag.putMoney(amount);
	}
	
	public boolean checkBag(String name, int number){
		return bag.search(name, number);
	}
	
	public boolean checkBag(int money){
		return bag.payMoney(money);
	}
	
	public void rewardCharacter(int exp, int money){
		character.gainEXP(exp);
		bag.putMoney(money);
	}
	
	public void resetMap(String newMap){
		map.loadData(newMap);
		display.setMap(map);
		character.setPosition(0, 0);
		monsters = map.createMonster();
	    display.setMonsters(monsters);
	    for(int i = 0 ; i < npcs.size() ; i++){
	    	npcs.get(i).removeButton();
	    }
	    npcs = map.createNPC();
	    skills = new ArrayList<Skill>();
		items = new ArrayList<Item>();
		moneys  = new ArrayList<Money>();
	}
	
	private void keyDetect(){
		if(keyControl.get("right"))      character.RoleMove(1);
		if(keyControl.get("left"))       character.RoleMove(0);
		if(keyControl.get("space"))      character.jump();
	    if(keyControl.get("down"))     	 character.climb(1);
	    if(keyControl.get("z"))			 pickUp();
	    if(keyControl.get("x")){
	    	Skill sk = character.skymad();
	    	if(sk != null){
	    		display.showSkill("skymad");
	    		skills.add(sk);
	    	}
	    }
	    if(keyControl.get("i")){		 
	    	if(bagDelay <= 0){
	    		bag.open();
	    		bagDelay = 3;
	    	}
	    }
	    for(int i = 1 ; i <= 8 ; i++){
	    	if(keyControl.get(new Integer(i).toString())){		 
		    	if(fastDelay[i] <= 0){
		    		bag.useFast(i);
		    		fastDelay[i] = 5;
		    	}
		    }
	    }
	    if(keyControl.get("control")){
	    	Skill sk = character.normal_attack();
	    	if(sk != null)
	    		skills.add(sk);
	    }
	    if(keyControl.get("enter")){
	    	//display.requestFocus();
	    	//System.out.println(display.isRequestFocusEnabled());
			display.transferFocusBackward();
	    }
	    if(keyControl.get("up")){
	    	String newMap = map.atTransPoint(character.x() + character.width()/2, character.y() + character.height()); 
	    	if(newMap != null){
	    		//System.out.println("fuck");
	    		resetMap(newMap);
	    	}else
	    		character.climb(0);
	    }
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
