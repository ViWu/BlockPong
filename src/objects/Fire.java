package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import framework.GameObject;
import framework.ID;
import framework.Texture;
import game.Animation;
import game.Game;
import game.HUD;
import game.Handler;

public class Fire extends GameObject{
	Handler handler;
	HUD hud;
	int cooldown=0;
	Texture tex = Game.getInstance();
	private Animation fire;
	public Fire(float x, float y, ID ID,Handler handler) {
		super(x, y, ID);
		this.handler =handler;
		fire = new Animation(5,tex.fire[0],tex.fire[1],tex.fire[2],tex.fire[3],tex.fire[4],tex.fire[5]);
		// TODO Auto-generated constructor stub
	}

	public void tick() {
		// TODO Auto-generated method stub
		collision();
		fire.runAnimation();
	}

	public void collision(){
		for(int i=0;i<handler.object.size();i++){
			
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player){
				if(getBounds().intersects(tempObject.getBounds())){
					hud.HEALTH-=0.2;
				}
			}
		}
	}
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.red);
		//g.fillRect((int)x,(int)y,150,300);
		//g.drawImage(tex.fire[5],(int)x,(int)y,null);
		fire.drawAnimation(g,(int)x-15,(int)y-15);
	}

	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return  new Rectangle((int)x,(int)y,120,300);
	}

}
