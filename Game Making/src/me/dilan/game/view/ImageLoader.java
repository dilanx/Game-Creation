package me.dilan.game.view;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {

	public static Image player;
	public static Image boss;
	public static Image boss_vulnerable;
	
	public static Image laser_empty;
	
	public static Image graybrick;
	public static Image topcollider;
	
	public static void loadImages() {
		try {
			
			player = ImageIO.read(res("entities/person.png"));
			boss = ImageIO.read(res("entities/boss.png"));
			boss_vulnerable = ImageIO.read(res("entities/boss_vulnerable.png"));
			
			laser_empty = ImageIO.read(res("weapons/empty_laser.png"));
			
			graybrick = ImageIO.read(res("blocks/graybrick.png"));
			topcollider = ImageIO.read(res("blocks/topcollider.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static File res(String path) {
		return new File("res/" + path);
	}
	
}
