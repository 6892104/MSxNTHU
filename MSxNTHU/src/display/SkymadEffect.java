package display;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SkymadEffect {
	protected int picEffect;
	protected int picNumber;
	protected int effect_co;
	protected BufferedImage[] images;
	protected BufferedImage[] pillar_images;
	protected int pillar_image_num;
	
	protected SkymadEffect(){
		picNumber = 7;
		effect_co = 5;
		picEffect = picNumber*effect_co;
		images = new BufferedImage[picNumber + 1];
		pillar_image_num = 5;
		pillar_images = new BufferedImage[pillar_image_num + 1];
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
			
			pillar_images[0] = ImageIO.read(this.getClass().getResourceAsStream("/skill_image/skymad_image/skymad_piller.1.png"));
			pillar_images[1] = ImageIO.read(this.getClass().getResourceAsStream("/skill_image/skymad_image/skymad_piller.2.png"));
			pillar_images[2] = ImageIO.read(this.getClass().getResourceAsStream("/skill_image/skymad_image/skymad_piller.3.png"));
			pillar_images[3] = ImageIO.read(this.getClass().getResourceAsStream("/skill_image/skymad_image/skymad_piller.4.png"));
			pillar_images[4] = ImageIO.read(this.getClass().getResourceAsStream("/skill_image/skymad_image/skymad_piller.5.png"));
			pillar_images[5] = ImageIO.read(this.getClass().getResourceAsStream("/skill_image/skymad_image/skymad_piller.5.png"));
		}catch (IOException ie){
			javax.swing.JOptionPane.showMessageDialog(null, "¸ü¤Jskymad¹ÏÀÉ¿ù»~");
		}
	}
	
	public void paintSkill(java.awt.Graphics g, int x, int y){
		if(picEffect < picNumber*effect_co){
			//System.out.println("skill : " + picEffect/2);
		    int parameter = picEffect / 7;
		    //System.out.println(parameter +" " + x  + " "+ y);
		    g.drawImage(pillar_images[parameter], x - 587, y - 300, null);
		    g.drawImage(pillar_images[parameter], x + 400, y - 250, null);
		    g.drawImage(pillar_images[parameter], x - 200, y - 324, null);
		    g.drawImage(pillar_images[parameter], x + 154, y - 265, null);
		    g.drawImage(pillar_images[parameter], x - 489, y - 123, null);
		    g.drawImage(pillar_images[parameter], x + 246, y - 64, null);
		    
		    parameter = picEffect / effect_co;
		    g.drawImage(images[parameter], x - images[parameter].getWidth()/2, y - images[parameter].getHeight(), null);
		    picEffect++;
		}
	}
}
