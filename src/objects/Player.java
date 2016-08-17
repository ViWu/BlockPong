package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import framework.GameObject;
import framework.ID;
import framework.Texture;
import game.Game;
import game.Handler;

public class Player extends GameObject{
	Random r = new Random();
	Handler handler;
	Texture tex = Game.getInstance();
	
	public Player(float x,float y,ID id,Handler handler){
		super(x,1520,id);
		this.handler = handler;
		//velX=r.nextInt(5)+1;
		//velY = r.nextInt(5);

	}
	
	public Rectangle getBounds(){
		return new Rectangle((int) x,(int) y,240,2);
	}
	
	public void tick(){
		x += velX*3;
		y += velY;
		x = Game.clamp((int) x, 0, Game.WIDTH-205);
		y = Game.clamp((int) y, 0, Game.HEIGHT-60);
		collision();
	}
	public void collision(){
		for(int i=0;i<handler.object.size();i++){
			
			GameObject tempObject = handler.object.get(i);
			
		}
	}
	
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g.drawImage(tex.box[2],(int)x,(int)y,null);
		g.setColor(Color.red);
		//g2d.draw(getBounds());
		//g.setColor(Color.white);
		//g.fillRect((int)x, (int)y, 200, 32);
	}
}
