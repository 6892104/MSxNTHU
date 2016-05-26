package Skill;

public abstract class Skill {
	
	protected int skill_width, skill_height;
	protected int curX, curY;
	protected int pace, range, damage;
	protected Direction dir;
	protected boolean human;
	
	public Skill(int startX, int startY, int width, int height, int pace, int range, int damage, Direction dir, boolean human){
		this.skill_width = width;
		this.skill_height = height;
		this.curX = startX;
		this.curY = startY;
		this.pace = pace;
		this.range = range;
		this.damage = damage;
		this.dir = dir;
		this.human = human;
	}
	
	public boolean hit(int x, int y, int width, int height){
		//emit atk(x+width()/2 - 100 , y , x+width()/2 , y+height(),20,dir);

  	  //emit atk(x+width()/2 , y , x+width()/2 + 100 , y+height(),20,dir);
		if(curX+skill_width > x && curX < x+width && curY+skill_height > y && curY < y+height)
			return true;
		else
			return false;
	}
	
	public boolean arrive(){
		if(range > 0){
			range -= pace;
			if(dir == Direction.up)
				curY += pace;
			else if(dir == Direction.down)
				curY -= pace;
			else if(dir == Direction.left)
				curX += pace;
			else if(dir == Direction.right)
				curX -= pace;
			
			return false;
		}else
			return true;
	}
	
	public boolean isHuman(){
		return human;
	}
	
	public int damage(){
		return damage;
	}
	
	public enum Direction{
		up, down, left, right
	}
}
