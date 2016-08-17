package objects;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.Random;

import game.Spawn;
import framework.GameObject;
import framework.ID;
import framework.Texture;
import game.Game;
import game.HUD;
import game.Handler;
import game.Animation;
public class BasicEnemy extends GameObject{

	private Handler handler;
	Random randomGenerator = new Random();
	Texture tex = Game.getInstance();
	private Animation ball;
	int randomInt = randomGenerator.nextInt(6)+1;
	public BasicEnemy(int x, int y, ID ID,Handler handler) {
		super(x, y, ID);
		this.handler =handler;
		ball = new Animation(9,tex.box[3],tex.box[4],tex.box[5],tex.box[6]);
		if(randomInt%2==0)
			velX=5;
		else
			velX=-5;
		velY=5;
	}

	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,16,16);
	}
	
	public void collision(){
		for(int i=0;i<handler.object.size();i++){
			
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player){
				if(getBounds().intersects(tempObject.getBounds())){
					velY *=-1;
				}
			}
			if(tempObject.getId() == ID.Box){
				if(getBounds().intersects(tempObject.getBounds())){
					handler.object.remove(tempObject);
					handler.score+=10;
					velY*=-1;
					if(handler.score==300)
						handler.switchLevel();
				}
			}
			if(tempObject.getId() == ID.MetalBox){
				if(getBounds().intersects(tempObject.getBounds())){
					handler.score+=15;
					velY*=-1;
				}
			}
		}
	}
	
	public void tick() {
		x += velX;
		y += velY;
		collision();
		if(y<=12 ) velY *= -1;
		if(x<=12 || x>= Game.WIDTH - 32) velX *= -1;
		if( y>= Game.HEIGHT - 32)
			HUD.HEALTH=0;
		//if(handler.killed>=20)
			//handler.addObject(new Trail((int)x,(int)y,ID.Trail,Color.cyan,16,16,0.02f, handler));
		ball.runAnimation();
	}

	public void render(Graphics g) {
		//g.setColor(Color.red);
		//g.fillRect((int)x,(int)y,36,36);
		ball.drawAnimation(g,(int)x-15,(int)y-15);
	}
	
}
