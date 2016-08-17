package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import framework.GameObject;
import framework.ID;
import framework.Texture;
import game.Game;
import game.Handler;

public class MetalBox extends GameObject{
	Handler handler;
	Texture tex = Game.getInstance();
	public MetalBox(float x, float y, ID ID, Handler handler) {
		super(x, y, ID);
		this.handler =handler;
		velX=0;
		velY=0;
	}

	public void tick() {
		// TODO Auto-generated method stub
	}

	public void render(Graphics g) {
		g.drawImage(tex.box[1],(int)x,(int)y,null);
		//g.setColor(Color.orange);
		Graphics2D g2d = (Graphics2D) g;
		//g.setColor(Color.red);
		//g2d.draw(getBounds());
		//g.fillRect((int)x,(int)y,45,45);
		
	}

	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x,(int)y,70,60);
	}

}
