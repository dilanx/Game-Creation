package me.dilan.game.blocks;

import java.awt.Graphics;

import me.dilan.game.util.Bounds;
import me.dilan.game.util.Identification;
import me.dilan.game.view.ImageLoader;

public class TopCollider extends Block{

	public TopCollider(float x, float y) {
		super(x, y, 32, 32, Identification.BLOCK_TOPCOLLIDER);
	}

	public void tick() {
		
	}

	
	public void render(Graphics g) {
		g.drawImage(ImageLoader.topcollider, (int) x, (int) y, null);
	}

	
	public Bounds bounds() {
		return new Bounds().setBounds(x, y, width, 8);
	}
	

	
}
