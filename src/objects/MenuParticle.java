package objects;

import framework.GameObject;
import framework.ID;
import framework.Texture;
import game.Animation;
import game.Game;
import game.HUD;
import game.Handler;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MenuParticle extends GameObject{
	private Handler handler;
	Random randomGenerator = new Random();
	Texture tex = Game.getInstance();
	private Animation ball;
	int randomInt = randomGenerator.nextInt(6)+1;
	int randomVelocity = randomGenerator.nextInt(7)+5;
	
	public MenuParticle(int x, int y, ID ID,Handler handler) {
		super(x, y, ID);
		this.handler =handler;
		ball = new Animation(9,tex.box[3],tex.box[4],tex.box[5],tex.box[6]);
		randomVelocity = randomGenerator.nextInt(6)+5;
		if(randomInt%2==0)
			velX=randomVelocity;
		else
			velX=-randomVelocity;
		velY=randomVelocity;
	}

	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,16,16);
	}
	
	
	public void tick() {
		x += velX;
		y += velY;
		if(y<=12 || y>= Game.HEIGHT-52) velY *= -1;
		if(x<=12 || x>= Game.WIDTH - 32) velX *= -1;
		ball.runAnimation();
	}

	public void render(Graphics g) {
		//g.setColor(Color.red);
		//g.fillRect((int)x,(int)y,36,36);
		ball.drawAnimation(g,(int)x-15,(int)y-15);
	}
}