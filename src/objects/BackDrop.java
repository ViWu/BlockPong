package objects;

import java.awt.Graphics;
import java.awt.Rectangle;

import framework.GameObject;
import framework.ID;
import framework.Texture;
import game.Game;
import game.Handler;

public class BackDrop extends GameObject{

	Texture tex = Game.getInstance();
	Handler handler;
	public BackDrop(float x, float y, ID ID,Handler handler) {
		super(x, y, ID);
		this.handler=handler;
		// TODO Auto-generated constructor stub
	}

	public void tick() {
		// TODO Auto-generated method stub
		
	}

	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(tex.backdrop[0],(int)x,(int)y,null);
	}

	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

}
