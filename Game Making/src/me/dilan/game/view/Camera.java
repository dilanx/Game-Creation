package me.dilan.game.view;

import me.dilan.game.Game;
import me.dilan.game.entity.Player;

public class Camera {

	private float x, y, cameraSpeed;
	private boolean smoothCam;

	
	public static int minX = 0, maxX = 0;
	public static int minY = 0, maxY = 0;


	public Camera(float x, float y, Game game) {
		this.x = x;
		this.y = y;
		smoothCam = false;
	}

	public Camera(float x, float y, Game game, float cameraSpeed) {
		this.x = x;
		this.y = y;
		this.cameraSpeed = cameraSpeed;
		smoothCam = true;
	}

	public void tick(Player player) {
		
		
		if (smoothCam) {
			x += ((player.getX() - Game.WIDTH / 2 + (player.getWidth() / 2)) - x) * cameraSpeed;
			y += ((player.getY() - Game.HEIGHT / 2 + (player.getHeight() / 2)) - y) * cameraSpeed;
		}else {
			x = player.getX() - Game.WIDTH / 2;
			y = player.getY() - Game.HEIGHT / 2;
		}
		
		if (x < minX) x = minX;
		if (x > maxX - Game.WIDTH) x = maxX - Game.WIDTH;
		if (y < minY) y = minY;
		if (y > maxY - Game.HEIGHT) y = maxY - Game.HEIGHT;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}



}
