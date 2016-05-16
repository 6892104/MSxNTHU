package MapleStory;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class DisplayPanel extends JPanel {
	
	private Image mapImage;
	private Image chImage;
	
	private MapWithObsticle map;
	private Beginner character;
	private CharacterPic chPic;
	
	public DisplayPanel(){
		chPic = new CharacterPic();
		try {
			mapImage = ImageIO.read(this.getClass().getResourceAsStream("/background.png"));
		}catch (IOException ie){
			javax.swing.JOptionPane.showMessageDialog(null, "載入圖檔錯誤");
		}
	}
	
	@Override
	protected void paintComponent(java.awt.Graphics g) { //paint pictures (using TA's code)
		super.paintComponent(g);
		if(map != null) g.drawImage(mapImage, (-1)*map.getShift_x() , (-1)*map.getShift_y() , map.getMax_x() , map.getMax_y(), null);
		if(character != null){
			//System.out.println("fuck : "+  character.x()+ " "+ character.y());
			chImage = chPic.getImage();
			g.drawImage(chImage, character.x() , character.y() , character.width() , character.height(), null);
		}
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
	
	public void setMap(MapWithObsticle map){
		this.map = map;
	}
	
	public void setCharacter(Beginner ch){
		this.character = ch;
		chPic = new CharacterPic();
	}
	
	private class RolePic
	{
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
	
	private class CharacterPic extends RolePic
	{
		private Image[][] atk_pic;
		private Image[] levelup_pic;
		private Image[] tomb_pic;
		private int atk_pic_num;
		private int atk_co;
		
		private int level_effect;

		private int tomb_effect;
		
		public CharacterPic(){
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
	}
}
