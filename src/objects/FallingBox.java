package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;
import game.Animation;
import game.Game;
import framework.GameObject;
import framework.ID;
import framework.Texture;
import game.Handler;
import game.HUD;

public class FallingBox extends GameObject{
	private Handler handler;
	private Animation box;
	HUD hud;
	Texture tex = Game.getInstance();
	Random randomGenerator = new Random();
	int randomInt = randomGenerator.nextInt(6)+1;
	
	public FallingBox(float x, float y, ID ID,Handler handler) {
		super(x, y, ID);
		this.handler =handler;
		box = new Animation(15,tex.box[0],tex.box[7],tex.box[8],tex.box[9],tex.box[10]);
		velY=10;
	}

	public void tick() {
		collision();
		y += velY;
		box.runAnimation();
		if(this.getY()>1000)
			handler.removeObject(this);
	}

	public void render(Graphics g) {
		//g.drawImage(tex.box[10],(int)x,(int)y,null);
		box.drawAnimation(g,(int)x-15,(int)y-15);
		//g.setColor(Color.orange);
		Graphics2D g2d = (Graphics2D) g;
		//g.setColor(Color.red);
		//g2d.draw(getBounds());
		//g.fillRect((int)x,(int)y,45,45);
		
	}
	
	public void collision(){
		for(int i=0;i<handler.object.size();i++){
			
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player){
				if(getBounds().intersects(tempObject.getBounds())){
					hud.HEALTH-=5;
				}
			}
		}
	}


	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,70,60);
	}

}

