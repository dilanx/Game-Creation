package me.dilan.game.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import me.dilan.game.Game;

public class Health {

	private int health = 50;
	private int maxHealth = 50;
	
	private int miniHealth = 0, maxMiniHealth = 0;
	
	private Camera camera;

	public Health(Camera camera) {
		this.camera = camera;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getMiniHealth() {
		return miniHealth;
	}

	public void setMiniHealth(int miniHealth) {
		this.miniHealth = miniHealth;
	}

	public int getMaxMiniHealth() {
		return maxMiniHealth;
	}

	public void setMaxMiniHealth(int maxMiniHealth) {
		this.miniHealth = maxMiniHealth;
		this.maxMiniHealth = maxMiniHealth;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getHealth() {
		return health;
	}

	public void tick() {

	}

	public void render(Graphics g) {
		
		int x = (int) camera.getX();
		int y = (int) camera.getY();
		
		if (miniHealth < maxMiniHealth) {
			
			g.setColor(Color.green);
			double miniDispY = (1 - (double) miniHealth/maxMiniHealth) * 50;
			g.fillRect(x + Game.WIDTH - 25, y + 10 + (int) miniDispY, 15, 50 - (int) miniDispY);
			g.setColor(Color.white);
			g.drawRect(x + Game.WIDTH - 25, y + 10, 15, 50);

			
		}

		g.setColor(Color.white);
		g.fillRect(x + 10, y + 10, 150, 30);

		
		
		
		g.setFont(new Font("monospaced", Font.PLAIN, 18));
		FontMetrics fontMetrics = g.getFontMetrics();
	
		g.drawString("HP " + health + "/" + maxHealth, x + 165, y+fontMetrics.getAscent() + 15);

		g.setColor(Color.gray);
		g.fillRect(x + 15, y + 15, 140, 20);

		g.setColor(Color.red);

		double disp = (double) health/maxHealth * 140;
		g.fillRect(x + 15, y + 15, (int) disp, 20);



	}



}
