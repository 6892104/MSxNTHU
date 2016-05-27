package MapleStory;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Role.Beginner;
import Role.Monster;
import Role.RoleMode;


public class DisplayPanel extends JPanel {
	
	private Image mapImage;
	private Image chImage;
	private Image pigImage;
	
	private MapWithObsticle map;
	private Beginner character;
	private ArrayList<Monster> monsters;
	
	private Status status;
	private CharacterPic chPic;
	private PigPic pigPic;
	
	public DisplayPanel(){
		status = new Status();
		chPic = new CharacterPic();
		pigPic = new PigPic();
		try {
			mapImage = ImageIO.read(this.getClass().getResourceAsStream("/background.png"));
		}catch (IOException ie){
			javax.swing.JOptionPane.showMessageDialog(null, "¸ü¤J¹ÏÀÉ¿ù»~");
		}
	}
	
	@Override
	protected void paintComponent(java.awt.Graphics g) { //paint pictures (using TA's code)
		super.paintComponent(g);
		if(map != null) g.drawImage(mapImage, (-1)*map.getShift_x() , (-1)*map.getShift_y() , map.getMax_x() , map.getMax_y(), null);
		if(monsters != null){
			for(int i = 0 ; i < monsters.size() ; i++){
				Monster piggy = monsters.get(i);
				//System.out.println(piggy.x() + " " + piggy.y());
				if(piggy.visiable()){
					pigImage = pigPic.getImage(piggy);
					g.drawImage(pigImage, piggy.x() - map.getShift_x(), piggy.y() - map.getShift_y(), piggy.width(), piggy.height(), null);
				}
			}
		}
		if(character != null){
			//System.out.println("fuck : "+  character.x()+ " "+ character.y() + " " + map.getShift_x() + " " + map.getShift_y());
			if(character.isDead()){
				//System.out.println(character.tomb.getY() - map.getShift_y() );
				g.drawImage(chPic.getTombImage(), character.x() - map.getShift_x()  , character.tomb.getY() - map.getShift_y()  , character.width() , character.height(), null);
			}else{
				g.drawImage(chPic.getImage(), character.x() - map.getShift_x()  , character.y() - map.getShift_y()  , character.width() , character.height(), null);
			}
		}
		if(status != null && character != null) status.paintStatus(g);
	}
	
	private void displayCharacter(java.awt.Graphics g){

	    /*if(tomb_effect > 0){
	        pix.load(tomb_pic[tomb_effect]);
	        painter.drawPixmap(0,0,this->width(),this->height(),pix);
	        if(tomb_effect > 1) {
	            tomb_effect--;
	        }
	        return;
	    }*/

	    
	    //painter.drawPixmap(0,0,this->width(),this->height(),pix);


	    /*if(level_effect > 0){
	        pix.load(levelup_pic[level_effect/3]);
	        painter.drawPixmap(0,0,this->width(),this->height(),pix);
	        level_effect--;
	    }*/
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
	
	public void setMap(MapWithObsticle map){
		this.map = map;
	}
	
	public void setCharacter(Beginner ch){
		this.character = ch;
		chPic = new CharacterPic();
	}
	
	public void setMonsters(ArrayList<Monster> monsters){
		this.monsters = monsters;
		pigPic = new PigPic();
	}
//----------------------------------------------------------------	
//-----------------inner classes----------------------------------
//----------------------------------------------------------------
	private class Status{
		
		private int y;
		private Image stbg, sthpmpexp, LV;
		private Image hp, mp, exp;
		private Image[] LvNumber;
		
		public Status(){
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
				javax.swing.JOptionPane.showMessageDialog(null, "¸ü¤JStatus¹ÏÀÉ¿ù»~");
			}
		}
		
		public void paintStatus(java.awt.Graphics g){
			int tenth = character.level()/10;
			int oneth = character.level()%10;

		    g.drawImage(stbg, 0, y, 1280, 100, null);
		    g.drawImage(sthpmpexp, 150, y + 28, 700, 60, null);
		    g.drawImage(LV, 0, y + 10, 100, 100, null);
		    g.drawImage(hp, 210, y + 32, (int)(2.8*character.hp()), 18, null);
		    g.drawImage(mp, 565, y + 32, (int)(2.8*character.mp()), 18, null);
		    g.drawImage(exp, 210, y + 65, (int)(6.3*character.exp()), 20, null);
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
	
	private class RolePic{
		
	    protected Image[] std_pic;
	    protected Image[] jump_pic;
	    protected Image[][] move_pic;
	    protected Image[] climb_pic;
	    protected Image[] be_hit_pic;
	    
	    protected int pic_num;
		protected int climb_pic_num;
	    
	    public RolePic(){
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
		
		public CharacterPic(){
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
				javax.swing.JOptionPane.showMessageDialog(null, "¸ü¤J¥D¨¤¹ÏÀÉ¿ù»~");
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
	}
	
	private class PigPic extends RolePic{
		public PigPic(){
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
				javax.swing.JOptionPane.showMessageDialog(null, "¸ü¤JªÎªÎ¹ÏÀÉ¿ù»~");
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
}
