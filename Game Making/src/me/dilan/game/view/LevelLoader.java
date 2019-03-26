package me.dilan.game.view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import me.dilan.game.Game;
import me.dilan.game.blocks.Elevator;
import me.dilan.game.blocks.Ground;
import me.dilan.game.blocks.TopCollider;
import me.dilan.game.entity.Boss;
import me.dilan.game.entity.Player;
import me.dilan.game.util.ObjectManager;

public class LevelLoader {

	
	public static void loadLevel(String levelId, Game game, ObjectManager manager) {
		
		try {
			BufferedImage level = ImageIO.read(new File("res/levels/" + levelId + ".png"));
			
			Game.LEVEL_WIDTH = level.getWidth() * 32;
			Game.LEVEL_HEIGHT = level.getHeight() * 32;
			
			Camera.maxX = level.getWidth() * 32;
			Camera.maxY = level.getHeight() * 32 + 32;
			
			Player p = null;
			
			for (int x = 0; x < level.getWidth(); x++) {
				for (int y = 0; y < level.getHeight(); y++) {
					int rgb = level.getRGB(x, y);
					//int a = (rgb>>24) & 0xff;
					int r = (rgb>>16) & 0xff;
					int g = (rgb>>8) & 0xff;
					int b = rgb & 0xff;
					
	
					if (isBlack(r, g, b)) {
						manager.addBlock(new Ground(x * 32, y * 32));
					}
					if (isRed(r, g, b)) {
						p = new Player(x * 32, y * 32, manager);
						manager.addPrimary(p);
					}
					if (isGreen(r, g, b)) {
						manager.addBlock(new Elevator(x * 32, y * 32));
					}
					if (isBlue(r, g, b)) {
						manager.addPrimary(new Boss(x * 32, y * 32, manager, p));
					}
					if (isStrawberry(r, g, b)) {
						manager.addBlock(new TopCollider(x * 32, y * 32));
					}
				}
			}
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	private static boolean isStrawberry(int r, int g, int b) {
		return (r == 255 && g == 47 && b == 146);
	}
	
	private static boolean isBlue(int r, int g, int b) {
		return (r == 0 && g == 0 && b == 255);
	}
	
	private static boolean isBlack(int r, int g, int b) {
		return (r == 0 && g == 0 && b == 0);
	}
	
	private static boolean isRed(int r, int g, int b) {
		return (r == 255 && g == 0 && b == 0);
	}
	
	private static boolean isGreen(int r, int g, int b) {
		return (r == 0 && g == 255 && b == 0);
	}
	
	
}
