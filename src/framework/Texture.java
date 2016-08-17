package framework;

import java.awt.image.BufferedImage;
import game.BufferedImageLoader;
import java.awt.Graphics2D;

public class Texture {
	SpriteSheet bs,ps,ball,fs,background;
	private BufferedImage box_sheet=null;
	private BufferedImage paddle_sheet=null;
	private BufferedImage ball_sheet=null;
	private BufferedImage fire_sheet=null;
	private BufferedImage bg_sheet=null;
	
	public BufferedImage[] box = new BufferedImage[13];
	public BufferedImage[] fire = new BufferedImage[8];
	public BufferedImage[] backdrop = new BufferedImage[1];
	
	public Texture(){
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
			box_sheet = loader.loadImage("/box.png");
			paddle_sheet = loader.loadImage("/paddle.png");
			ball_sheet = loader.loadImage("/ball.png");
			fire_sheet = loader.loadImage("/fire.png");
			bg_sheet = loader.loadImage("/backdrop.png");
		}catch(Exception e){
			e.printStackTrace();
		}
		bs= new SpriteSheet(box_sheet);
		ps=new SpriteSheet(paddle_sheet);
		ball = new SpriteSheet(ball_sheet);
		fs = new SpriteSheet(fire_sheet);
		background = new SpriteSheet(bg_sheet);
		
		getTextures();
	}
	private void getTextures(){
		box[0] = bs.grabImage(20,35,70,60);
		box[1] = bs.grabImage(20, 298, 70, 60);
		box[2] = ps.grabImage(12, 25, 240, 35);
		box[3] = ball.grabImage(0, 0, 60, 60);
		box[4] = ball.grabImage(0, 75, 60, 60);
		box[5] = ball.grabImage(0, 150, 60, 60);
		box[6] = ball.grabImage(0, 225 ,50, 60);
		box[7] = bs.grabImage(10, 110, 120, 100);
		box[8] = bs.grabImage(100, 30, 120, 100);
		box[9] = bs.grabImage(100, 150, 120, 100);
		box[10] = bs.grabImage(100, 280, 100, 100);
		/*fire[0] = fs.grabImage(232, 37, 150, 360);
		fire[1] = fs.grabImage(198, 200, 180, 360);
		fire[2] = fs.grabImage(198, 360, 180, 550);
		fire[3] = fs.grabImage(190, 555, 180, 530);
		fire[4] = fs.grabImage(178, 760, 150, 480);
		fire[5] = fs.grabImage(178, 900, 125, 360);*/
		backdrop[0] = background.grabImage(98,70,1100,1005);
	}
}
