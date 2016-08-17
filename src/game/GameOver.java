package game;

import game.Game.STATE;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class GameOver extends MouseAdapter{
	private Game game;
	private Handler handler;
	private Random r = new Random();
	
	public GameOver(Game game,Handler handler){
		this.game=game;
		this.handler=handler;
	}
	
	public void MousePressed(MouseEvent e){
		
	}
	
	public void mouseReleased(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
			if (mouseOver(mx,my,420,200,200,64) && game.gameState==STATE.GameOver){
				game.gameState= STATE.Game;
				game.hud.HEALTH=100;
				game.hud.score(0);
				handler.resetStage();
				handler.switchLevel();
			}
			if (mouseOver(mx,my,420,320,200,64) && game.gameState==STATE.GameOver){
				game.gameState = STATE.Menu;
				handler.object.clear();
				handler.menuParticles();
				game.hud.HEALTH=100;
				game.hud.score(0);
			}
			if (mouseOver(mx,my,420,450,200,64) && game.gameState==STATE.GameOver){
				System.exit(1);
			}
	}
	
	private boolean mouseOver(int mx, int my, int x,int y,int width,int height){
		if(mx > x && mx<x+width){
			if(my > y && my< y + height){
				return true;
			}else return false;
		}else return false;
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g){
		Font fnt = new Font("arial", 1,50);
		Font fnt2 = new Font("arial",1,30);
		
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("GameOver",400,70);
		
		g.setFont(fnt2);
		g.setColor(Color.yellow);
		g.drawString("YourScore: ",400,150);
		g.drawString(String.valueOf(handler.score),610,150);
		g.setColor(Color.orange);
		g.fillRect(420,200,200,64);
		g.fillRect(420,320,200,64);
		g.fillRect(420,450,200,64);
		g.setColor(Color.white);
		g.drawRect(420,200,200,64);
		g.drawString("Play Again",450,240);
		

		g.drawRect(420,320,200,64);
		g.drawString("Menu",480,360);
	
		g.drawRect(420,450,200,64);
		g.drawString("Quit",480,490);
	}
}
