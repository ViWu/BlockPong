package game;

import java.applet.Applet;
import java.awt.Dimension;
import java.awt.Graphics;

public class GameApplet extends Applet{
	private Game c;
	private static final long serialVersionUID = 195582347;
	
	public void init(){
		c = new Game();
		c.setPreferredSize(new Dimension(1024, 1024));
		c.setVisible(true);
		c.setFocusable(true);
		this.add(c);
		this.setVisible(true);
		this.setSize(new Dimension(1024, 1024));
		c.handler.menuParticles();
	}
	
	public void paint(Graphics g){
		this.setSize(new Dimension(1024, 1024));
	}
	
}
