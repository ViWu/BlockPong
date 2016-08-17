package game;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoader {
private BufferedImage image;
	
	public BufferedImage loadImage(String path){
		
		try{
			image = ImageIO.read(getClass().getResource(path));
			//image = ImageIO.read(getClass().getResourceAsStream("res/" + path));
			//path="res"+path;
			//System.out.println(path);
			//image = ImageIO.read(new FileInputStream(path));
		} catch(IOException e){
			e.printStackTrace();
		}
		return image;
	}
}
