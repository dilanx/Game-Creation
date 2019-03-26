package me.dilan.game.util;

import java.awt.Graphics;
import java.awt.Image;

public class Animate {

	private int speed, index, currentFrame;
		
	private Image[][] sets;
	private Image currentImg;
	
	public Animate(int speed, Image[]... sets) {
		this.speed = speed;
		this.sets = sets;
		
	}
	
	public void run(int setNumber) {
		index++;
		if (index > speed) {
			index = 0;
			next(setNumber);
		}
	}
	
	
	//repeater repeats animation after fully complete
	private void next(int setNumber) {
		for (int i = 0; i < sets.length; i++) {
			if (currentFrame == i) currentImg = sets[setNumber][i];
		}
		currentFrame++;
		
		if (currentFrame > sets.length) currentFrame = 0;
	}
	
	public void draw(Graphics g, int x, int y, int w, int h) {
		g.drawImage(currentImg, x, y, w, h, null);
		
	}
	
}
