package display;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SkymadEffect {
	protected int picEffect;
	protected int picNumber;
	protected int effect_co;
	protected BufferedImage[] images;
	
	protected SkymadEffect(){
		picNumber = 7;
		effect_co = 3;
		picEffect = picNumber*effect_co;
		images = new BufferedImage[picNumber + 1];
		loadImage();
	}
	
	private void loadImage(){
		try{
			images[0] = ImageIO.read(this.getClass().getResourceAsStream("/skill_image/skymad_image/skymad.1.png"));
			images[1] = ImageIO.read(this.getClass().getResourceAsStream("/skill_image/skymad_image/skymad.2.png"));
			images[2] = ImageIO.read(this.getClass().getResourceAsStream("/skill_image/skymad_image/skymad.3.png"));
			images[3] = ImageIO.read(this.getClass().getResourceAsStream("/skill_image/skymad_image/skymad.4.png"));
			images[4] = ImageIO.read(this.getClass().getResourceAsStream("/skill_image/skymad_image/skymad.5.png"));
			images[5] = ImageIO.read(this.getClass().getResourceAsStream("/skill_image/skymad_image/skymad.6.png"));
			images[6] = ImageIO.read(this.getClass().getResourceAsStream("/skill_image/skymad_image/skymad.7.png"));
		}catch (IOException ie){
			javax.swing.JOptionPane.showMessageDialog(null, "¸ü¤Jskymad¹ÏÀÉ¿ù»~");
		}
	}
	
	public void paintSkill(java.awt.Graphics g, int x, int y){
		if(picEffect < picNumber*effect_co){
			//System.out.println("skill : " + picEffect/2);
			int parameter = picEffect / effect_co;
		    g.drawImage(images[parameter], x - images[parameter].getWidth()/2, y - images[parameter].getHeight(), null);
		    picEffect++;
		}
	}
}
