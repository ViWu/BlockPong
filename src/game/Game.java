package game;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.*;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;

import javax.swing.JFrame;

import objects.BackDrop;
import framework.ID;
import framework.KeyInput;
import framework.Texture;
public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 195582347;
	
	public static final int WIDTH = 1024, HEIGHT = 1024;
	private Thread thread;
	private boolean running = false;
	
	static Texture tex;
	private Random r;
	static Handler handler;
	private Spawn spawner;
	private Menu menu;
	private GameOver gameOver;
	public HUD hud;
	MouseEvent e;
	
	public enum STATE{
		Menu,Game,GameOver;
	}
	
	public static STATE gameState = STATE.Menu;
	
	public Game(){
		tex = new Texture();
		handler = new Handler();
		menu = new Menu(this, handler);
		gameOver = new GameOver(this,handler);
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);
		this.addMouseListener(gameOver);
		
		new Window(WIDTH, HEIGHT, "Super Pong Survival",this);
		
		hud = new HUD();
		spawner = new Spawn(handler,hud);
		r = new Random();
		//if(gameState == STATE.Game){
			//handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player,handler));
			//handler.addObject(new BasicEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.BasicEnemy,handler));
		//}
			
	}
	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running=true;
	}
	
	public synchronized void stop(){
		try{
			thread.join();
			running=false;
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void run(){
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta =0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				//System.out.println("FPS: "+ frames);
				frames = 0;
			}
		}
		stop();
	}
	private void tick(){
		handler.tick();
		boolean change;
		if(gameState == STATE.Game){
			change = hud.tick();
			spawner.tick();
			if(!change){
				gameState=STATE.GameOver;
				if(gameState==STATE.GameOver){
					handler.object.clear();
					spawner.scoreKeep=0;
					hud.setLevel(1);
				}
			}
		}else if(gameState == STATE.Menu){
			menu.tick();
		}
		
	}
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.gray);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		handler.render(g);
		if(gameState==STATE.Game){
			hud.render(g);
		}else if(gameState == STATE.Menu){
			menu.render(g);
		}else if(gameState==STATE.GameOver)
			gameOver.render(g);
			
		g.dispose();
		bs.show();
	}
	
	public static float clamp(float var, float min, float max){
		if(var >= max)
			return var=max;
		else if(var <=min)
			return var=min;
		else 
			return var;			
	}
	public static Texture getInstance(){
		return tex;
	}
	
	public static void main(String args[]){
		new Game();
		handler.menuParticles();
	}
}
