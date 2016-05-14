package MapleStory;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class DisplayPanel extends JPanel{
	private Image mapImage;
	
	private MapWithObsticle map;
	
	public DisplayPanel(){
		try {
			mapImage = ImageIO.read(this.getClass().getResourceAsStream("/background.png"));
		}catch (IOException ie){
			javax.swing.JOptionPane.showMessageDialog(null, "¸ü¤J¹ÏÀÉ¿ù»~");
		}
	}
	
	@Override
	protected void paintComponent(java.awt.Graphics g) { //paint pictures (using TA's code)
		super.paintComponent(g);
		g.drawImage(mapImage, (-1)*map.getShift_x() , (-1)*map.getShift_y() , map.getMax_x() , map.getMax_y(), null);
	}
	
	public void setMap(MapWithObsticle map){
		this.map = map;
	}
}
