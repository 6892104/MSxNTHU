package MapleStory;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class KeyControl implements KeyListener {
	private Map<String, Boolean> key;
	public KeyControl(){
		key = new HashMap<String, Boolean>();
		key.put("right", false);
		key.put("left", false);
		key.put("alt", false);
		key.put("control", false);
		key.put("enter", false);
		key.put("up", false);
		key.put("down", false);
		key.put("esc", false);
		key.put("space", false);
		key.put("a", false);
		key.put("b", false);
		key.put("c", false);
		key.put("d", false);
		key.put("e", false);
		key.put("f", false);
		key.put("g", false);
		key.put("h", false);
		key.put("i", false);
		key.put("j", false);
		key.put("k", false);
		key.put("l", false);
		key.put("m", false);
		key.put("n", false);
		key.put("o", false);
		key.put("p", false);
		key.put("q", false);
		key.put("r", false);
		key.put("s", false);
		key.put("t", false);
		key.put("u", false);
		key.put("v", false);
		key.put("w", false);
		key.put("x", false);
		key.put("y", false);
		key.put("z", false);
		key.put("1", false);
		key.put("2", false);
		key.put("3", false);
		key.put("4", false);
		key.put("5", false);
		key.put("6", false);
		key.put("7", false);
		key.put("8", false);
		key.put("F11", false);
	}

	public boolean get(String key){
		boolean yes = this.key.get(key);
		if(key.equals("enter"))
			this.key.put("enter", false);
		return yes;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//if(e.isAltDown()) key.put("alt", true);
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)    key.put("right", true);
		if(e.getKeyCode() == KeyEvent.VK_LEFT)     key.put("left", true);
		if(e.getKeyCode() == KeyEvent.VK_ALT)      key.put("alt", true);
		if(e.getKeyCode() == KeyEvent.VK_CONTROL)  key.put("control", true);
		if(e.getKeyCode() == KeyEvent.VK_ENTER)    key.put("enter", true);
		if(e.getKeyCode() == KeyEvent.VK_UP)       key.put("up", true);
		if(e.getKeyCode() == KeyEvent.VK_DOWN)     key.put("down", true);
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE)   key.put("esc", true);
		if(e.getKeyCode() == KeyEvent.VK_SPACE)    key.put("space", true);
		if(e.getKeyCode() == KeyEvent.VK_A)        key.put("a", true);
		if(e.getKeyCode() == KeyEvent.VK_B)        key.put("b", true);
		if(e.getKeyCode() == KeyEvent.VK_C)        key.put("c", true);
		if(e.getKeyCode() == KeyEvent.VK_D)        key.put("d", true);
		if(e.getKeyCode() == KeyEvent.VK_E)        key.put("e", true);
		if(e.getKeyCode() == KeyEvent.VK_F)        key.put("f", true);
		if(e.getKeyCode() == KeyEvent.VK_G)        key.put("g", true);
		if(e.getKeyCode() == KeyEvent.VK_H)        key.put("h", true);
		if(e.getKeyCode() == KeyEvent.VK_I)        key.put("i", true);
		if(e.getKeyCode() == KeyEvent.VK_J)        key.put("j", true);
		if(e.getKeyCode() == KeyEvent.VK_K)        key.put("k", true);
		if(e.getKeyCode() == KeyEvent.VK_L)        key.put("l", true);
		if(e.getKeyCode() == KeyEvent.VK_M)        key.put("m", true);
		if(e.getKeyCode() == KeyEvent.VK_N)        key.put("n", true);
		if(e.getKeyCode() == KeyEvent.VK_O)        key.put("o", true);
		if(e.getKeyCode() == KeyEvent.VK_P)        key.put("p", true);
		if(e.getKeyCode() == KeyEvent.VK_Q)        key.put("q", true);
		if(e.getKeyCode() == KeyEvent.VK_R)        key.put("r", true);
		if(e.getKeyCode() == KeyEvent.VK_S)        key.put("s", true);
		if(e.getKeyCode() == KeyEvent.VK_T)        key.put("t", true);
		if(e.getKeyCode() == KeyEvent.VK_U)        key.put("u", true);
		if(e.getKeyCode() == KeyEvent.VK_V)        key.put("v", true);
		if(e.getKeyCode() == KeyEvent.VK_W)        key.put("w", true);
		if(e.getKeyCode() == KeyEvent.VK_X)        key.put("x", true);
		if(e.getKeyCode() == KeyEvent.VK_Y)        key.put("y", true);
		if(e.getKeyCode() == KeyEvent.VK_Z)        key.put("z", true);
		if(e.getKeyCode() == KeyEvent.VK_1)        key.put("1", true);
		if(e.getKeyCode() == KeyEvent.VK_2)        key.put("2", true);
		if(e.getKeyCode() == KeyEvent.VK_3)        key.put("3", true);
		if(e.getKeyCode() == KeyEvent.VK_4)        key.put("4", true);
		if(e.getKeyCode() == KeyEvent.VK_5)        key.put("5", true);
		if(e.getKeyCode() == KeyEvent.VK_6)        key.put("6", true);
		if(e.getKeyCode() == KeyEvent.VK_7)        key.put("7", true);
		if(e.getKeyCode() == KeyEvent.VK_8)        key.put("8", true);
		if(e.getKeyCode() == KeyEvent.VK_F11)      key.put("F11", true);
		e.consume();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//System.out.println(KeyEvent.VK_RIGHT + " " + e.getKeyCode()+ " " + e.getKeyChar());
		//if(!e.isAltDown()) key.put("alt", false);
		//if(e.isAltDown() && e.getKeyCode() == KeyEvent.VK_RIGHT) key.put("right", false);
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)    key.put("right", false);
		if(e.getKeyCode() == KeyEvent.VK_LEFT)     key.put("left", false);
		if(e.getKeyCode() == KeyEvent.VK_ALT)      key.put("alt", false);
		if(e.getKeyCode() == KeyEvent.VK_CONTROL)  key.put("control", false);
		if(e.getKeyCode() == KeyEvent.VK_ENTER)    key.put("enter", false);
		if(e.getKeyCode() == KeyEvent.VK_UP)       key.put("up", false);
		if(e.getKeyCode() == KeyEvent.VK_DOWN)     key.put("down", false);
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE)   key.put("esc", false);
		if(e.getKeyCode() == KeyEvent.VK_SPACE)    key.put("space", false);
		if(e.getKeyCode() == KeyEvent.VK_A)        key.put("a", false);
		if(e.getKeyCode() == KeyEvent.VK_B)        key.put("b", false);
		if(e.getKeyCode() == KeyEvent.VK_C)        key.put("c", false);
		if(e.getKeyCode() == KeyEvent.VK_D)        key.put("d", false);
		if(e.getKeyCode() == KeyEvent.VK_E)        key.put("e", false);
		if(e.getKeyCode() == KeyEvent.VK_F)        key.put("f", false);
		if(e.getKeyCode() == KeyEvent.VK_G)        key.put("g", false);
		if(e.getKeyCode() == KeyEvent.VK_H)        key.put("h", false);
		if(e.getKeyCode() == KeyEvent.VK_I)        key.put("i", false);
		if(e.getKeyCode() == KeyEvent.VK_J)        key.put("j", false);
		if(e.getKeyCode() == KeyEvent.VK_K)        key.put("k", false);
		if(e.getKeyCode() == KeyEvent.VK_L)        key.put("l", false);
		if(e.getKeyCode() == KeyEvent.VK_M)        key.put("m", false);
		if(e.getKeyCode() == KeyEvent.VK_N)        key.put("n", false);
		if(e.getKeyCode() == KeyEvent.VK_O)        key.put("o", false);
		if(e.getKeyCode() == KeyEvent.VK_P)        key.put("p", false);
		if(e.getKeyCode() == KeyEvent.VK_Q)        key.put("q", false);
		if(e.getKeyCode() == KeyEvent.VK_R)        key.put("r", false);
		if(e.getKeyCode() == KeyEvent.VK_S)        key.put("s", false);
		if(e.getKeyCode() == KeyEvent.VK_T)        key.put("t", false);
		if(e.getKeyCode() == KeyEvent.VK_U)        key.put("u", false);
		if(e.getKeyCode() == KeyEvent.VK_V)        key.put("v", false);
		if(e.getKeyCode() == KeyEvent.VK_W)        key.put("w", false);
		if(e.getKeyCode() == KeyEvent.VK_X)        key.put("x", false);
		if(e.getKeyCode() == KeyEvent.VK_Y)        key.put("y", false);
		if(e.getKeyCode() == KeyEvent.VK_Z)        key.put("z", false);
		if(e.getKeyCode() == KeyEvent.VK_1)        key.put("1", false);
		if(e.getKeyCode() == KeyEvent.VK_2)        key.put("2", false);
		if(e.getKeyCode() == KeyEvent.VK_3)        key.put("3", false);
		if(e.getKeyCode() == KeyEvent.VK_4)        key.put("4", false);
		if(e.getKeyCode() == KeyEvent.VK_5)        key.put("5", false);
		if(e.getKeyCode() == KeyEvent.VK_6)        key.put("6", false);
		if(e.getKeyCode() == KeyEvent.VK_7)        key.put("7", false);
		if(e.getKeyCode() == KeyEvent.VK_8)        key.put("8", false);
		if(e.getKeyCode() == KeyEvent.VK_F11)      key.put("F11", false);
		e.consume();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
