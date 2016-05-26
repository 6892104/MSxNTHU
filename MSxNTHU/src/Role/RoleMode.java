package Role;

public class RoleMode {
	public enum Mode{
		stand, move, jump, climb, attack, be_hit
	}
	public int value;
	public Mode mode;
	
	public RoleMode(Mode mode, int value){
		this.mode = mode;
		this.value = value;
	}
}
