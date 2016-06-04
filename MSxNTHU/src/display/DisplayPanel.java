package display;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import MapleStory.MapWithObsticle;
import bag.Bag;
import item.Consumable;
import item.Equipment;
import item.Item;
import item.Money;
import item.OtherItem;
import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;
import role.Beginner;
import role.Monster;
import role.NPC;
import role.Role;
import role.RoleMode;


public class DisplayPanel extends JPanel {
	
	private Image mapImage;
	private Image chImage;
	private Image pigImage;
	private ArrayList<Image> bagImage;
	
	private boolean drawName;
	
	private MapWithObsticle map;
	private Beginner character;
	private ArrayList<Monster> monsters;
	private ArrayList<NPC> npcs;
	private ArrayList<Item> items;
	private ArrayList<Money> moneys;
	private Bag bag;
	
	private Status status;
	private CharacterPic chPic;
	private PigPic pigPic;
	private GreenPic greenPic;
	private NPCPic npcPic;
	private ItemPic itemPic;
	private MoneyPic moneyPic;
	
	private ChatPanel chatPanel;
	
	public DisplayPanel(){
		this.setLayout(null);
		chatPanel = new ChatPanel(this);
		chatPanel.setLocation(0, 645 - chatPanel.getHeight());
		this.add(chatPanel);
		
		
		status = new Status();
		chPic = new CharacterPic();
		pigPic = new PigPic();
		greenPic = new GreenPic();
		npcPic = new NPCPic();
		itemPic = new ItemPic();
		moneyPic = new MoneyPic();
		
		try {
			bagImage = new ArrayList<Image>();
			bagImage.add(ImageIO.read(this.getClass().getResourceAsStream("/bag_image/bag1.jpg")));
			bagImage.add(ImageIO.read(this.getClass().getResourceAsStream("/bag_image/bag2.jpg")));
			bagImage.add(ImageIO.read(this.getClass().getResourceAsStream("/bag_image/bag3.jpg")));
			bagImage.add(ImageIO.read(this.getClass().getResourceAsStream("/bag_image/bag4.jpg")));
			bagImage.add(ImageIO.read(this.getClass().getResourceAsStream("/bag_image/bag5.jpg")));
		}catch (IOException ie){
			javax.swing.JOptionPane.showMessageDialog(null, "載入背包圖檔錯誤");
		}
		drawName = true;
	}
	
	@Override
	protected void paintComponent(java.awt.Graphics g) { //paint pictures (using TA's code)
		super.paintComponent(g);
		if(map != null) g.drawImage(mapImage, (-1)*map.getShift_x() , (-1)*map.getShift_y() , map.getMax_x() , map.getMax_y(), null);
		if(monsters != null){
			for(int i = 0 ; i < monsters.size() ; i++){
				Monster mon = monsters.get(i);
				//System.out.println(piggy.x() + " " + piggy.y());
				if(mon.visiable()){
					if(mon.name().equals("肥肥")){
						pigImage = pigPic.getImage(mon);
						g.drawImage(pigImage, mon.x() - map.getShift_x(), mon.y() - map.getShift_y(), mon.width(), mon.height(), null);
						if(drawName) drawName(g, mon);
					}else if(mon.name().equals("綠水靈")){
						g.drawImage(greenPic.getImage(mon), mon.x() - map.getShift_x(), mon.y() - map.getShift_y(), mon.width(), mon.height(), null);
						if(drawName) drawName(g, mon);
					}
				}
			}
		}
		if(npcs != null){
			for(int i = 0 ; i < npcs.size() ; i++){
				NPC npc = npcs.get(i);
				int x = npc.x() - map.getShift_x();
				int y = npc.y() - map.getShift_y();
				//System.out.println(npc.x() + " " + npc.y());
				g.drawImage(npcPic.getImage(npc.name()), npc.x() - map.getShift_x(), npc.y() - map.getShift_y(), npc.width(), npc.height(), null);
			}
		}
		if(character != null){
			//System.out.println("fuck : "+  character.x()+ " "+ character.y() + " " + map.getShift_x() + " " + map.getShift_y());
			if(character.isDead()){
				//System.out.println(character.tomb.getY() - map.getShift_y() );
				g.drawImage(chPic.getTombImage(), character.x() - map.getShift_x()  , character.tomb.getY() - map.getShift_y()  , character.width() , character.height(), null);
			}else{
				g.drawImage(chPic.getImage(), character.x() - map.getShift_x()  , character.y() - map.getShift_y()  , character.width() , character.height(), null);
				if(drawName) drawName(g, character);
			}
			if(character.levelEffect() > 0)
				g.drawImage(chPic.getLevelUPImage(), character.x() - map.getShift_x()  , character.y() - map.getShift_y()  , character.width() , character.height(), null);
		}
		if(items != null){
			for(int i = 0 ; i < items.size() ; i++){
				Item item = items.get(i);
				/*int x = item.x - map.getShift_x();
				int y = item.y - map.getShift_y();
				System.out.println("fuck" + x + " " + y);*/
				g.drawImage(itemPic.getImage(item.name()), item.x - map.getShift_x(), item.y - map.getShift_y() - item.width(), item.width(), item.height(), null);
			}
		}
		if(moneys != null){
			for(int i = 0 ; i < moneys.size() ; i++){
				Money money = moneys.get(i);
				g.drawImage(moneyPic.getImage(money.amount()), money.x() - map.getShift_x(), money.y() - map.getShift_y() - money.height() - 10, money.width(), money.height(), null);
			}
		}
		if(status != null && character != null) status.paintStatus(g);
		if(bag != null && bag.visiable()) g.drawImage(bagImage.get(bag.getMenu()), bag.x(), bag.y(), bag.width(), bag.height(), null);
	}
	
	private void drawName(java.awt.Graphics g, Role role){
		int fontSize = 20;
		int nameX = role.x() - map.getShift_x() + role.width()/2 - role.name().length()/2*fontSize;
		g.setFont(new Font(g.getFont().getFontName(), Font.BOLD, fontSize));//deriveFont(float size).
		g.setColor(Color.WHITE);
		g.fillRect(nameX, role.y() - map.getShift_y() + role.height(), (int)(role.name().length()*fontSize*1.1), (int)(fontSize*1.3));
		g.setColor(Color.BLACK);
		g.drawString(role.name(), nameX , role.y() - map.getShift_y() + role.height() + fontSize);
	}
	
	private Image mergeImage(Image image1, Image image2){
		// create the new image, canvas size is the max. of both image sizes
		int w = Math.max(image1.getWidth(null), image2.getWidth(null));
		int h = Math.max(image1.getHeight(null), image2.getHeight(null));
		BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

		// paint both images, preserving the alpha channels
		Graphics g = combined.getGraphics();
		g.drawImage(image1, 0, 0, null);
		g.drawImage(image2, image1.getWidth(null) - image2.getWidth(null), image1.getHeight(null) - image2.getHeight(null), null);

		return combined;
	}
	
	public Image getItemImage(String name, int amount){
		//System.out.println(amount);
		if(amount == 1)
			return itemPic.getImage(name);
		else
			return mergeImage(itemPic.getImage(name), itemPic.getNumber(amount));
	}
	
	public void closeConnection(){
		chatPanel.closeConnection();
	}
	
	public void getKey(){
		chatPanel.getKey();
	}
	
	public Beginner getCharacter(){
		return character;
	}
	
	public Image getNPCImage(String name){
		return npcPic.getImage(name);
	}
	
	public int getCharacterPictureNumber(){
		return chPic.pic_num;
	}
	
	public int getPigPictureNumber(){
		return pigPic.pic_num;
	}
	
	public int getAtkParameter(){
		return chPic.atk_pic_num * chPic.atk_co;
	}
	
	public void setItem(ArrayList<Item> items){
		this.items = items;
	}
	
	public void setMoney(ArrayList<Money> moneys){
		this.moneys = moneys;
	}
	
	public void setMap(MapWithObsticle map){
		this.map = map;
		try {
			mapImage = ImageIO.read(this.getClass().getResourceAsStream("/map_image/" + map.name() + ".png"));
		}catch (IOException ie){
			javax.swing.JOptionPane.showMessageDialog(null, "載入地圖圖檔錯誤");
		}
	}
	
	public void setCharacter(Beginner ch){
		this.character = ch;
		chPic = new CharacterPic();
	}
	
	public void setMonsters(ArrayList<Monster> monsters){
		this.monsters = monsters;
		pigPic = new PigPic();
	}
	
	public void setBag(Bag bag){
		this.bag = bag;
	}
//----------------------------------------------------------------	
//-----------------inner classes----------------------------------
//----------------------------------------------------------------
	private class MoneyPic{
		private Image money_10;
		private Image money_50;
		private Image money_100;
		private Image money_1000;
		
		private MoneyPic(){
			try {
				money_10 = ImageIO.read(this.getClass().getResourceAsStream("/item/money/money_10.png"));
				money_50 = ImageIO.read(this.getClass().getResourceAsStream("/item/money/money_50.png"));
				money_100 = ImageIO.read(this.getClass().getResourceAsStream("/item/money/money_100.png"));
				money_1000 = ImageIO.read(this.getClass().getResourceAsStream("/item/money/money_1000.png"));
			}catch (Exception ie){
				javax.swing.JOptionPane.showMessageDialog(null, "載入money圖檔錯誤");
			}
		}
		
		private Image getImage(int amount){
			if(amount < 50)
				return money_10;
			else if(amount < 100)
				return money_50;
			else if(amount < 1000)
				return money_100;
			else
				return money_1000;
		}
	}
	
	private class ItemPic{
		private HashMap<String, Image> itemPicture;
		private ArrayList<Image> numberImage;
		
		private ItemPic(){
			itemPicture = new HashMap<String, Image>();
			
			JSONObject data;
			JSONArray data_array;
			try{
				String file = "item/item_data.json";
				data = new PApplet().loadJSONObject(file);
			
				data_array = data.getJSONArray("Consumables");
				for(int i =0; i< data_array.size(); i++) {
					String name = data_array.getJSONObject(i).getString("name");
					try { 
						itemPicture.put(name, ImageIO.read(this.getClass().getResourceAsStream("/item/consumable/" + name + ".png")));
					}catch (Exception ie){
						javax.swing.JOptionPane.showMessageDialog(null, "載入"+name+"圖檔錯誤");
					}
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
					try { 
						itemPicture.put(name, ImageIO.read(this.getClass().getResourceAsStream("/item/other/" + name + ".png")));
					}catch (Exception ie){
						javax.swing.JOptionPane.showMessageDialog(null, "載入"+name+"圖檔錯誤");
					}
				}
				
				numberImage = new ArrayList<Image>();
				try { 
					for(int i = 0 ; i < 10 ; i++){
						numberImage.add(ImageIO.read(this.getClass().getResourceAsStream("/itemNumber/ItemNo." + i + ".png")));
					}
				}catch (Exception ie){
					javax.swing.JOptionPane.showMessageDialog(null, "載入item number圖檔錯誤");
					ie.printStackTrace();
				}
			
			}catch (NullPointerException ie){
				javax.swing.JOptionPane.showMessageDialog(null, "載入物品data錯誤");
			}
		}
		
		private Image getImage(String name){
			if(itemPicture.containsKey(name))
				return itemPicture.get(name);
			else
				return null;
		}
		
		private Image getNumber(int number){
			if(number < 0)
				return null;
			if(number < 10)
				return numberImage.get(number);
			return mergeImage(getNumber(number / 10), numberImage.get(number % 10));
		}
		
		private Image mergeImage(Image image1, Image image2){
			// create the new image, canvas size is the max. of both image sizes
			BufferedImage combined = new BufferedImage(image1.getWidth(null) * 2, image1.getHeight(null), BufferedImage.TYPE_INT_ARGB);

			// paint both images, preserving the alpha channels
			Graphics g = combined.getGraphics();
			g.drawImage(image1, 0, 0, null);
			g.drawImage(image2, image1.getWidth(null), 0, null);

			return combined;
		}
	}
	
	private class Status{
		
		private int y;
		private Image stbg, sthpmpexp, LV;
		private Image hp, mp, exp;
		private Image[] LvNumber;
		
		private Status(){
			y = 620;
			try {
				stbg = ImageIO.read(this.getClass().getResourceAsStream("/stbg.png"));
				sthpmpexp = ImageIO.read(this.getClass().getResourceAsStream("/sthpmpexp.png"));
				LV = ImageIO.read(this.getClass().getResourceAsStream("/LV.png"));
				hp = ImageIO.read(this.getClass().getResourceAsStream("/hp.png"));
				mp = ImageIO.read(this.getClass().getResourceAsStream("/mp.png"));
				exp = ImageIO.read(this.getClass().getResourceAsStream("/exp.png"));
			    
			    LvNumber = new Image[10];
			    LvNumber[0] = ImageIO.read(this.getClass().getResourceAsStream("/lv_number0.png"));
			    LvNumber[1] = ImageIO.read(this.getClass().getResourceAsStream("/lv_number1.png"));
			    LvNumber[2] = ImageIO.read(this.getClass().getResourceAsStream("/lv_number2.png"));
			    LvNumber[3] = ImageIO.read(this.getClass().getResourceAsStream("/lv_number3.png"));
			    LvNumber[4] = ImageIO.read(this.getClass().getResourceAsStream("/lv_number4.png"));
			    LvNumber[5] = ImageIO.read(this.getClass().getResourceAsStream("/lv_number5.png"));
			    LvNumber[6] = ImageIO.read(this.getClass().getResourceAsStream("/lv_number6.png"));
			    LvNumber[7] = ImageIO.read(this.getClass().getResourceAsStream("/lv_number7.png"));
			    LvNumber[8] = ImageIO.read(this.getClass().getResourceAsStream("/lv_number8.png"));
			    LvNumber[9] = ImageIO.read(this.getClass().getResourceAsStream("/lv_number9.png"));
			}catch (IOException ie){
				javax.swing.JOptionPane.showMessageDialog(null, "載入Status圖檔錯誤");
			}
		}
		
		public void paintStatus(java.awt.Graphics g){
			int tenth = character.level()/10;
			int oneth = character.level()%10;

		    g.drawImage(stbg, 0, y, 1280, 100, null);
		    g.drawImage(sthpmpexp, 150, y + 28, 700, 60, null);
		    g.drawImage(LV, 0, y + 10, 100, 100, null);
		    g.drawImage(hp, 210, y + 32, (int)(280 * character.hp() / character.maxHP()), 18, null);
		    g.drawImage(mp, 565, y + 32, (int)(280 * character.mp() / character.maxMP()), 18, null);
		    g.drawImage(exp, 210, y + 65, (int)(630*character.exp() / character.maxEXP()), 20, null);
		    g.drawImage(LvNumber[tenth], 80, y + 40, 30, 40, null);
		    g.drawImage(LvNumber[oneth], 110, y + 40, 30, 40, null);
		    /*painter.drawPixmap(150,28,700,60,sthpmpexp);
		    painter.drawPixmap(0,10,100,100,LV);
		    painter.drawPixmap(210,32,2.8*HP,18,hp);
		    painter.drawPixmap(565,32,2.8*MP,18,mp);
		    painter.drawPixmap(210,65,6.3*EXP,20,exp);
		    painter.drawPixmap(80,40,30 ,40 ,lv[tenth]);
		    painter.drawPixmap(110,40,30 ,40 ,lv[oneth]);*/
		}
	}
	
	private abstract class RolePic{
		
	    protected Image[] std_pic;
	    protected Image[] jump_pic;
	    protected Image[][] move_pic;
	    protected Image[] climb_pic;
	    protected Image[] be_hit_pic;
	    
	    protected int pic_num;
		protected int climb_pic_num;
	    
		private RolePic(){
	    	std_pic = new Image[2];
	    	jump_pic = new Image[2];
	    	move_pic = new Image[2][10];
	    	climb_pic = new Image[2];
	    	be_hit_pic = new Image[2];
	    }
	    
	    /*public void setPictureNumber(int number){
	    	pic_num = number;
	    }
	    
	    public int getPictureNumber(){
	    	return pic_num;
	    }*/
	    
	    
	}
	
	private class CharacterPic extends RolePic{
		
		private Image[][] atk_pic;
		private Image[] levelup_pic;
		private Image[] tomb_pic;
		private int atk_pic_num;
		private int atk_co;
		
		private int level_effect;

		private int tomb_effect;
		
		private CharacterPic(){
			super();
			try {
				// 0  left   1  right
				std_pic[0] = ImageIO.read(this.getClass().getResourceAsStream("/Character/Beginner/stand.0.png"));
				std_pic[0] = ImageIO.read(this.getClass().getResourceAsStream("/Character/Beginner/stand.0.png"));
			    move_pic[0][3] = ImageIO.read(this.getClass().getResourceAsStream("/Character/Beginner/move.0.png"));
			    move_pic[0][2] = ImageIO.read(this.getClass().getResourceAsStream("/Character/Beginner/move.1.png"));
			    move_pic[0][1] = ImageIO.read(this.getClass().getResourceAsStream("/Character/Beginner/move.2.png"));
			    move_pic[0][0] = ImageIO.read(this.getClass().getResourceAsStream("/Character/Beginner/move.3.png"));
			    std_pic[1] = ImageIO.read(this.getClass().getResourceAsStream("/Character/Beginner/Right/stand.0.png"));
			    move_pic[1][3] = ImageIO.read(this.getClass().getResourceAsStream("/Character/Beginner/Right/move.0.png"));
			    move_pic[1][2] = ImageIO.read(this.getClass().getResourceAsStream("/Character/Beginner/Right/move.1.png"));
			    move_pic[1][1] = ImageIO.read(this.getClass().getResourceAsStream("/Character/Beginner/Right/move.2.png"));
			    move_pic[1][0] = ImageIO.read(this.getClass().getResourceAsStream("/Character/Beginner/Right/move.3.png"));
			    pic_num=4;
			    jump_pic[0] = ImageIO.read(this.getClass().getResourceAsStream("/Character/Beginner/jump.0.png"));
			    jump_pic[1] = ImageIO.read(this.getClass().getResourceAsStream("/Character/Beginner/Right/jump.0.png"));


			    climb_pic[0] = ImageIO.read(this.getClass().getResourceAsStream("/Character/Beginner/climb.0.png"));
			    climb_pic[1] = ImageIO.read(this.getClass().getResourceAsStream("/Character/Beginner/climb.1.png"));
			    climb_pic_num=2;


			    atk_co=3;
			    atk_pic_num=5;
			    atk_pic = new Image[2][atk_pic_num];
			    atk_pic[0][4] = ImageIO.read(this.getClass().getResourceAsStream("/Character/Beginner/attack.4.png"));
			    atk_pic[0][3] = ImageIO.read(this.getClass().getResourceAsStream("/Character/Beginner/attack.3.png"));
			    atk_pic[0][2] = ImageIO.read(this.getClass().getResourceAsStream("/Character/Beginner/attack.2.png"));
			    atk_pic[0][1] = ImageIO.read(this.getClass().getResourceAsStream("/Character/Beginner/attack.1.png"));
			    atk_pic[0][0] = ImageIO.read(this.getClass().getResourceAsStream("/Character/Beginner/attack.0.png"));
			    atk_pic[1][4] = ImageIO.read(this.getClass().getResourceAsStream("/Character/Beginner/Right/attack.4.png"));
			    atk_pic[1][3] = ImageIO.read(this.getClass().getResourceAsStream("/Character/Beginner/Right/attack.3.png"));
			    atk_pic[1][2] = ImageIO.read(this.getClass().getResourceAsStream("/Character/Beginner/Right/attack.2.png"));
			    atk_pic[1][1] = ImageIO.read(this.getClass().getResourceAsStream("/Character/Beginner/Right/attack.1.png"));
			    atk_pic[1][0] = ImageIO.read(this.getClass().getResourceAsStream("/Character/Beginner/Right/attack.0.png"));


			    be_hit_pic[0] = ImageIO.read(this.getClass().getResourceAsStream("/Character/Beginner/be_attack.png"));
			    be_hit_pic[1] = ImageIO.read(this.getClass().getResourceAsStream("/Character/Beginner/Right/be_attack.png"));

			    levelup_pic = new Image[25];
			    levelup_pic[1] = ImageIO.read(this.getClass().getResourceAsStream("/level_up/LevelUp.20.png"));
			    levelup_pic[2] = ImageIO.read(this.getClass().getResourceAsStream("/level_up/LevelUp.19.png"));
			    levelup_pic[3] = ImageIO.read(this.getClass().getResourceAsStream("/level_up/LevelUp.18.png"));
			    levelup_pic[4] = ImageIO.read(this.getClass().getResourceAsStream("/level_up/LevelUp.17.png"));
			    levelup_pic[5] = ImageIO.read(this.getClass().getResourceAsStream("/level_up/LevelUp.16.png"));
			    levelup_pic[6] = ImageIO.read(this.getClass().getResourceAsStream("/level_up/LevelUp.15.png"));
			    levelup_pic[7] = ImageIO.read(this.getClass().getResourceAsStream("/level_up/LevelUp.14.png"));
			    levelup_pic[8] = ImageIO.read(this.getClass().getResourceAsStream("/level_up/LevelUp.13.png"));
			    levelup_pic[9] = ImageIO.read(this.getClass().getResourceAsStream("/level_up/LevelUp.12.png"));
			    levelup_pic[10] = ImageIO.read(this.getClass().getResourceAsStream("/level_up/LevelUp.11.png"));
			    levelup_pic[11] = ImageIO.read(this.getClass().getResourceAsStream("/level_up/LevelUp.10.png"));
			    levelup_pic[12] = ImageIO.read(this.getClass().getResourceAsStream("/level_up/LevelUp.9.png"));
			    levelup_pic[13] = ImageIO.read(this.getClass().getResourceAsStream("/level_up/LevelUp.8.png"));
			    levelup_pic[14] = ImageIO.read(this.getClass().getResourceAsStream("/level_up/LevelUp.7.png"));
			    levelup_pic[15] = ImageIO.read(this.getClass().getResourceAsStream("/level_up/LevelUp.6.png"));
			    levelup_pic[16] = ImageIO.read(this.getClass().getResourceAsStream("/level_up/LevelUp.5.png"));
			    levelup_pic[17] = ImageIO.read(this.getClass().getResourceAsStream("/level_up/LevelUp.4.png"));
			    levelup_pic[18] = ImageIO.read(this.getClass().getResourceAsStream("/level_up/LevelUp.3.png"));
			    levelup_pic[19] = ImageIO.read(this.getClass().getResourceAsStream("/level_up/LevelUp.2.png"));
			    levelup_pic[20] = ImageIO.read(this.getClass().getResourceAsStream("/level_up/LevelUp.1.png"));


			    tomb_effect = 0;
			    tomb_pic = new Image[25];
			    tomb_pic[1] = ImageIO.read(this.getClass().getResourceAsStream("/Character/Tomb/land.0.png"));
			    tomb_pic[2] = ImageIO.read(this.getClass().getResourceAsStream("/Character/Tomb/fall.19.png"));
			    tomb_pic[3] = ImageIO.read(this.getClass().getResourceAsStream("/Character/Tomb/fall.18.png"));
			    tomb_pic[4] = ImageIO.read(this.getClass().getResourceAsStream("/Character/Tomb/fall.17.png"));
			    tomb_pic[5] = ImageIO.read(this.getClass().getResourceAsStream("/Character/Tomb/fall.16.png"));
			    tomb_pic[6] = ImageIO.read(this.getClass().getResourceAsStream("/Character/Tomb/fall.15.png"));
			    tomb_pic[7] = ImageIO.read(this.getClass().getResourceAsStream("/Character/Tomb/fall.14.png"));
			    tomb_pic[8] = ImageIO.read(this.getClass().getResourceAsStream("/Character/Tomb/fall.13.png"));
			    tomb_pic[9] = ImageIO.read(this.getClass().getResourceAsStream("/Character/Tomb/fall.12.png"));
			    tomb_pic[10] = ImageIO.read(this.getClass().getResourceAsStream("/Character/Tomb/fall.11.png"));
			    tomb_pic[11] = ImageIO.read(this.getClass().getResourceAsStream("/Character/Tomb/fall.10.png"));
			    tomb_pic[12] = ImageIO.read(this.getClass().getResourceAsStream("/Character/Tomb/fall.9.png"));
			    tomb_pic[13] = ImageIO.read(this.getClass().getResourceAsStream("/Character/Tomb/fall.8.png"));
			    tomb_pic[14] = ImageIO.read(this.getClass().getResourceAsStream("/Character/Tomb/fall.7.png"));
			    tomb_pic[15] = ImageIO.read(this.getClass().getResourceAsStream("/Character/Tomb/fall.6.png"));
			    tomb_pic[16] = ImageIO.read(this.getClass().getResourceAsStream("/Character/Tomb/fall.5.png"));
			    tomb_pic[17] = ImageIO.read(this.getClass().getResourceAsStream("/Character/Tomb/fall.4.png"));
			    tomb_pic[18] = ImageIO.read(this.getClass().getResourceAsStream("/Character/Tomb/fall.3.png"));
			    tomb_pic[19] = ImageIO.read(this.getClass().getResourceAsStream("/Character/Tomb/fall.2.png"));
			    tomb_pic[20] = ImageIO.read(this.getClass().getResourceAsStream("/Character/Tomb/fall.1.png"));
			    tomb_pic[21] = ImageIO.read(this.getClass().getResourceAsStream("/Character/Tomb/fall.0.png"));
			}catch (IOException ie){
				javax.swing.JOptionPane.showMessageDialog(null, "載入主角圖檔錯誤");
			}
		}
		
		public Image getImage(){
			RoleMode rm = character.getMode();
			RoleMode.Mode mode = rm.mode;
			if(mode == RoleMode.Mode.attack)
		        return atk_pic[character.dir()][((rm.value-1)/atk_co)%atk_pic_num];
		    else if(mode == RoleMode.Mode.be_hit)
		        return be_hit_pic[character.dir()];
		    else if(mode == RoleMode.Mode.climb)
		        return climb_pic[rm.value%climb_pic_num];
		    else if(mode == RoleMode.Mode.jump)
		        return jump_pic[character.dir()];
		    else if(mode == RoleMode.Mode.move)
		        return move_pic[character.dir()][rm.value%pic_num];
		    else
		    	return std_pic[character.dir()];
		}
		
		public Image getTombImage(){
			return tomb_pic[character.tomb.getParameter()];
		}
		
		public Image getLevelUPImage(){
			return levelup_pic[character.levelEffect()/3];
		}
	}
	
	private class PigPic extends RolePic{
		private PigPic(){
			super();
			try {
				// 0  left   1  right
				std_pic[0] = ImageIO.read(this.getClass().getResourceAsStream("/Monster/Pig/move.1.png"));
				move_pic[0][3] = ImageIO.read(this.getClass().getResourceAsStream("/Monster/Pig/move.0.png"));
				move_pic[0][2] = ImageIO.read(this.getClass().getResourceAsStream("/Monster/Pig/move.1.png"));
				move_pic[0][1] = ImageIO.read(this.getClass().getResourceAsStream("/Monster/Pig/move.2.png"));
				move_pic[0][0] = ImageIO.read(this.getClass().getResourceAsStream("/Monster/Pig/move.1.png"));
				std_pic[1] = ImageIO.read(this.getClass().getResourceAsStream("/Monster/Pig/Right/move.1.png"));
				move_pic[1][3] = ImageIO.read(this.getClass().getResourceAsStream("/Monster/Pig/Right/move.0.png"));
				move_pic[1][2] = ImageIO.read(this.getClass().getResourceAsStream("/Monster/Pig/Right/move.1.png"));
				move_pic[1][1] = ImageIO.read(this.getClass().getResourceAsStream("/Monster/Pig/Right/move.2.png"));
				move_pic[1][0] = ImageIO.read(this.getClass().getResourceAsStream("/Monster/Pig/Right/move.1.png"));
				pic_num=4;
				jump_pic[0] = ImageIO.read(this.getClass().getResourceAsStream("/Monster/Pig/jump.0.png"));
				jump_pic[1] = ImageIO.read(this.getClass().getResourceAsStream("/Monster/Pig/Right/jump.0.png"));
				
				be_hit_pic[0] = ImageIO.read(this.getClass().getResourceAsStream("/Monster/Pig/hit1.0.png"));
				be_hit_pic[1] = ImageIO.read(this.getClass().getResourceAsStream("/Monster/Pig/Right/hit1.0.png"));
			}catch (IOException ie){
				javax.swing.JOptionPane.showMessageDialog(null, "載入肥肥圖檔錯誤");
			}
		}
		
		public Image getImage(Monster piggy){
			RoleMode rm = piggy.getMode();
			RoleMode.Mode mode = rm.mode;
		    if(mode == RoleMode.Mode.be_hit)
		        return be_hit_pic[piggy.dir()];
		    else if(mode == RoleMode.Mode.jump)
		        return jump_pic[piggy.dir()];
		    else if(mode == RoleMode.Mode.move)
		        return move_pic[piggy.dir()][rm.value%pic_num];
		    else
		    	return std_pic[piggy.dir()];
		}
	}
	
	private class GreenPic extends RolePic{
		private GreenPic(){
			super();
			try {
				// 0  left   1  right
				pic_num=7;
				std_pic[0] = ImageIO.read(this.getClass().getResourceAsStream("/Monster/Green/move.1.png"));
				move_pic[0][6] = ImageIO.read(this.getClass().getResourceAsStream("/Monster/Green/move.0.png"));
				move_pic[0][5] = ImageIO.read(this.getClass().getResourceAsStream("/Monster/Green/move.1.png"));
				move_pic[0][4] = ImageIO.read(this.getClass().getResourceAsStream("/Monster/Green/move.2.png"));
				move_pic[0][3] = ImageIO.read(this.getClass().getResourceAsStream("/Monster/Green/move.3.png"));
				move_pic[0][2] = ImageIO.read(this.getClass().getResourceAsStream("/Monster/Green/move.4.png"));
				move_pic[0][1] = ImageIO.read(this.getClass().getResourceAsStream("/Monster/Green/move.5.png"));
				move_pic[0][0] = ImageIO.read(this.getClass().getResourceAsStream("/Monster/Green/move.6.png"));
				std_pic[1] = ImageIO.read(this.getClass().getResourceAsStream("/Monster/Green/Right/move.1.png"));
				move_pic[1][6] = ImageIO.read(this.getClass().getResourceAsStream("/Monster/Green/Right/move.0.png"));
				move_pic[1][5] = ImageIO.read(this.getClass().getResourceAsStream("/Monster/Green/Right/move.1.png"));
				move_pic[1][4] = ImageIO.read(this.getClass().getResourceAsStream("/Monster/Green/Right/move.2.png"));
				move_pic[1][3] = ImageIO.read(this.getClass().getResourceAsStream("/Monster/Green/Right/move.3.png"));
				move_pic[1][2] = ImageIO.read(this.getClass().getResourceAsStream("/Monster/Green/Right/move.4.png"));
				move_pic[1][1] = ImageIO.read(this.getClass().getResourceAsStream("/Monster/Green/Right/move.5.png"));
				move_pic[1][0] = ImageIO.read(this.getClass().getResourceAsStream("/Monster/Green/Right/move.6.png"));
				
				jump_pic[0] = ImageIO.read(this.getClass().getResourceAsStream("/Monster/Green/jump.0.png"));
				jump_pic[1] = ImageIO.read(this.getClass().getResourceAsStream("/Monster/Green/Right/jump.0.png"));
				
				be_hit_pic[0] = ImageIO.read(this.getClass().getResourceAsStream("/Monster/Green/hit1.0.png"));
				be_hit_pic[1] = ImageIO.read(this.getClass().getResourceAsStream("/Monster/Green/Right/hit1.0.png"));
			}catch (IOException ie){
				javax.swing.JOptionPane.showMessageDialog(null, "載入綠水靈圖檔錯誤");
			}
		}
		
		public Image getImage(Monster piggy){
			RoleMode rm = piggy.getMode();
			RoleMode.Mode mode = rm.mode;
		    if(mode == RoleMode.Mode.be_hit)
		        return be_hit_pic[piggy.dir()];
		    else if(mode == RoleMode.Mode.jump)
		        return jump_pic[piggy.dir()];
		    else if(mode == RoleMode.Mode.move)
		        return move_pic[piggy.dir()][rm.value%pic_num];
		    else
		    	return std_pic[piggy.dir()];
		}
	}
	
	private class NPCPic{
		private HashMap<String, Image> images;
		
		private NPCPic(){
			images = new HashMap<String, Image>();
		}
		
		public Image getImage(String name){
			if(images.containsKey(name))
				return images.get(name);
			else{
				try {
					Image image = ImageIO.read(this.getClass().getResourceAsStream("/NPC/" + name + ".png"));
					images.put(name, image);
					return image;
				}catch (IOException ie){
					javax.swing.JOptionPane.showMessageDialog(null, "載入NPC: " + name + " 圖檔錯誤");
				}
			}
			return null;
		}
	}
}
