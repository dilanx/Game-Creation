package me.dilan.game.view;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {

	public static Image[] player_idle;
	public static Image[] player_running;
	
	public static Image boss;
	public static Image boss_vulnerable;
	
	public static Image laser_empty;
	public static Image laser_filled;
	
	public static Image graybrick;
	public static Image topcollider;
	
	public static void loadImages() {
		try {
			
			player_idle = readList("player/adventurer-idle-", 4);
			player_running = readList("player/adventurer-run-", 6);
			boss = read("entities/boss.png");
			boss_vulnerable = read("entities/boss_vulnerable.png");
			
			laser_empty = read("weapons/empty_laser.png");
			laser_filled = read("weapons/filled_laser.png");
			
			graybrick = read("blocks/graybrick.png");
			topcollider = read("blocks/topcollider.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Image[] readList(String pathStart, int frames) throws IOException{
		Image[] list = new Image[frames];
		for (int i = 0; i < frames; i++) {
			list[i] = read(pathStart + i + ".png");
		}
		
		
		return list;
		
	}
	
	public static Image read(String path) throws IOException{
		return ImageIO.read(new File("res/" + path));
	}
	
}
