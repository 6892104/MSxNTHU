package skill;

import skill.Skill.Direction;

public class Skymad extends Skill{
	public Skymad(int x, int y, int damage, Direction dir, boolean human){
		super(x - 600, y - 300, 1200, 500, 0, 0, damage, dir, human);
		System.out.println(x + " " + y);
	}
}
