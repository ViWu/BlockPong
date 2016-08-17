package game;

import game.Game.STATE;

import java.util.Random;

public class Spawn {
	private Handler handler;
	private HUD hud;
	public int scoreKeep=0;
	private Random r = new Random();
	public Spawn(Handler handler, HUD hud){
		this.handler=handler;
		this.hud=hud;
	}
	public void tick(){
			//scoreKeep++;
			if(scoreKeep>=200){
				scoreKeep=0;
			}
		
	}
}
