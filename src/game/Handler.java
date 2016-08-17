package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;

import objects.BackDrop;
import objects.BasicEnemy;
import objects.Box;
import objects.FallingBox;
import objects.Fire;
import objects.MenuParticle;
import objects.MetalBox;
import objects.Player;
import framework.GameObject;
import framework.ID;
import game.Game.STATE;

public class Handler {
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	private BufferedImage level = null;
	private BufferedImageLoader loader = new BufferedImageLoader();
	private Random randomGenerator = new Random();
	public int stage=0;
	public int score=0;
	int cooldown=0;
	int randomInt = randomGenerator.nextInt(9)+1;
	int randomIntY = randomGenerator.nextInt(9)+1;
	Font fnt = new Font("arial", 1,30);
	
	public void tick(){
		//System.out.println("Cooldown: "+object.size());
		for(int i=0;i<object.size(); i++){
			GameObject tempObject = object.get(i);
			tempObject.tick();
				if(tempObject.getId() == ID.Hazard){
					if (cooldown>=150){
						cooldown=0;
				}
			}
		}
		if(cooldown>=150)
			cooldown=0;
		if (Game.gameState == STATE.Game)
			cooldown++;
		if(cooldown ==100 && Game.gameState == STATE.Game){
			randomInt = randomGenerator.nextInt(800)+1;
			addObject(new FallingBox(Game.WIDTH/2-402+randomInt, Game.HEIGHT/2-800, ID.Hazard,this));
		}
		
	}
	
	public void render(Graphics g){
		for(int i=0;i<object.size(); i++){
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
		if(Game.gameState==STATE.Game){
			g.setColor(Color.orange);
			g.setFont(fnt);
			g.drawString("Level: " + (stage-1),25,74);
			g.drawString("Score: " + (score),25,104);
		}
	}
	
	private void LoadImageLevel(BufferedImage image){
		int w = image.getWidth();
		int h =image.getHeight();
		int playerX=0,playerY=0;
		addObject(new BackDrop(Game.WIDTH/2-532, Game.HEIGHT/2-532, ID.MenuParticle,this));
		for(int xx=0;xx<h;xx++){
			for(int yy=0;yy<w;yy++){
				int pixel = image.getRGB(xx,yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red == 255 && green==0 && blue==0){
					this.addObject(new Box(xx*17,yy*17,ID.Box,this));
				}
				else if(red == 0 &&  green==0 && blue==0)
					this.addObject(new MetalBox(xx*17,yy*17,ID.MetalBox,this));
			} 
		}
		randomInt = randomGenerator.nextInt(9)+1;
		addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player,this));		//add player last so it's in front of everything
		addObject(new BasicEnemy(Game.WIDTH-randomInt*100, 525, ID.BasicEnemy,this));
		//addObject(new Fire(Game.WIDTH/2-32, Game.HEIGHT/2+200, ID.Fire,this));
	}
	
	public void switchLevel(){
		object.clear();
		if(stage==1){
			level = loader.loadImage("/level.png");    //loading the level
			LoadImageLevel(level);
			stage++;
		}
		else if(stage==2){
			level = loader.loadImage("/level2.png");    //loading the level
			LoadImageLevel(level);
			stage++;
		}
	}
	
	public void resetStage(){
		stage=1;
		score=0;
		cooldown=0;
	}
	
	public void menuParticles(){
		for(int i=0;i<32;i++){
			randomInt = randomGenerator.nextInt(9)+1;
			randomIntY = randomGenerator.nextInt(9)+1;
			addObject(new MenuParticle(Game.WIDTH-randomInt*100, Game.HEIGHT-randomIntY*100, ID.MenuParticle,this));
		}
	}
	
	public void addObject(GameObject object){
		this.object.add(object);
	}
	public void removeObject(GameObject object){
		this.object.remove(object);
	}
	public GameObject get(int i){
		return this.object.get(i);
	}
}
