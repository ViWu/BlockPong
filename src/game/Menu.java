package game;

import framework.ID;
import game.Game.STATE;
import objects.BackDrop;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu extends MouseAdapter{
	
	private Game game;
	private Handler handler;
	private Random r = new Random();

	public Menu(Game game,Handler handler){
		this.game=game;
		this.handler=handler;
	}
	

	
	public void mouseReleased(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
			if (mouseOver(mx,my,420,200,200,64) && game.gameState==STATE.Menu){
				handler.object.clear();
				game.gameState= STATE.Game;
				handler.resetStage();
				handler.switchLevel();
			}
			if (mouseOver(mx,my,420,450,200,64) && game.gameState==STATE.Menu){
				System.exit(1);
			}
	}
	
	private boolean mouseOver(int mx, int my, int x,int y,int width,int height){
		if(mx > x && mx<x+width){
			if(my > y && my< y + height){
			
				return true;
			}else{
				
				return false;
			}
		}else{
			return false;
		}
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g){
		Font fnt = new Font("arial", 1,50);
		Font fnt2 = new Font("arial",1,30);
		
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("Menu",460,150);
		
		
		g.setFont(fnt2);
		g.setColor(Color.orange);

		g.fillRect(420,200,200,64);
		g.fillRect(420,320,200,64);
		g.fillRect(420,450,200,64);
		g.setColor(Color.white);
		g.drawRect(420,200,200,64);
		g.drawString("Play",480,240);
		

		g.drawRect(420,320,200,64);
		g.drawString("Help",480,360);
	
		g.drawRect(420,450,200,64);
		g.drawString("Quit",480,490);
	}
}
