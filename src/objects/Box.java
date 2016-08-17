package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import game.Game;
import framework.GameObject;
import framework.ID;
import framework.Texture;
import game.Handler;

public class Box extends GameObject{
	private Handler handler;
	Texture tex = Game.getInstance();
	public Box(float x, float y, ID ID,Handler handler) {
		super(x, y, ID);
		this.handler =handler;
		velX=0;
		velY=0;
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.drawImage(tex.box[0],(int)x,(int)y,null);
		//g.setColor(Color.orange);
		Graphics2D g2d = (Graphics2D) g;
		//g.setColor(Color.red);
		//g2d.draw(getBounds());
		//g.fillRect((int)x,(int)y,45,45);
		
	}
	


	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,70,60);
	}

}
