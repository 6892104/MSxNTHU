package MapleStory;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class KeyControl implements KeyListener {
	Map<String, Boolean> key;
	KeyControl(){
		key = new HashMap<String, Boolean>();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)     key.put("right", true);
		/*if(event->key()==Qt::Key_Right)     key["right"]=false;
		if(event->key()==Qt::Key_Left)      key["left"]=true;
	    if(event->key()==Qt::Key_Alt)       key["alt"]=true;
	    if(event->key()==Qt::Key_Up)        key["up"]=true;
	    if(event->key()==Qt::Key_Down)      key["down"]=true;
	    if(event->key()==Qt::Key_Escape)    key["esc"]=true;
	    if(event->key()==Qt::Key_Space)     key["space"]=true;
	    if(event->key()==Qt::Key_A)         key["a"]=true;
	    if(event->key()==Qt::Key_B)         key["b"]=true;
	    if(event->key()==Qt::Key_C)         key["c"]=true;
	    if(event->key()==Qt::Key_D)         key["d"]=true;
	    if(event->key()==Qt::Key_E)         key["e"]=true;
	    if(event->key()==Qt::Key_F)         key["f"]=true;
	    if(event->key()==Qt::Key_G)         key["g"]=true;
	    if(event->key()==Qt::Key_H)         key["h"]=true;
	    if(event->key()==Qt::Key_I)         key["i"]=true;
	    if(event->key()==Qt::Key_J)         key["j"]=true;
	    if(event->key()==Qt::Key_K)         key["k"]=true;
	    if(event->key()==Qt::Key_L)         key["l"]=true;
	    if(event->key()==Qt::Key_M)         key["m"]=true;
	    if(event->key()==Qt::Key_N)         key["n"]=true;
	    if(event->key()==Qt::Key_O)         key["o"]=true;
	    if(event->key()==Qt::Key_P)         key["p"]=true;
	    if(event->key()==Qt::Key_Q)         key["q"]=true;
	    if(event->key()==Qt::Key_R)         key["r"]=true;
	    if(event->key()==Qt::Key_S)         key["s"]=true;
	    if(event->key()==Qt::Key_T)         key["t"]=true;
	    if(event->key()==Qt::Key_U)         key["u"]=true;
	    if(event->key()==Qt::Key_V)         key["v"]=true;
	    if(event->key()==Qt::Key_W)         key["w"]=true;
	    if(event->key()==Qt::Key_X)         key["x"]=true;
	    if(event->key()==Qt::Key_Y)         key["y"]=true;
	    if(event->key()==Qt::Key_Z)         key["z"]=true;
	    if(event->key()==Qt::Key_1)         key["1"]=true;
	    if(event->key()==Qt::Key_2)         key["2"]=true;
	    if(event->key()==Qt::Key_3)         key["3"]=true;
	    if(event->key()==Qt::Key_4)         key["4"]=true;
	    if(event->key()==Qt::Key_5)         key["5"]=true;
	    if(event->key()==Qt::Key_6)         key["6"]=true;
	    if(event->key()==Qt::Key_7)         key["7"]=true;
	    if(event->key()==Qt::Key_8)         key["8"]=true;
	    if(event->key()==Qt::Key_F11)       key["F11"]=true;*/
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
