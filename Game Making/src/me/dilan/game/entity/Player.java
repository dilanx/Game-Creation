package me.dilan.game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import me.dilan.game.util.Animate;
import me.dilan.game.util.Bounds;
import me.dilan.game.util.Identification;
import me.dilan.game.util.ObjectManager;
import me.dilan.game.view.ImageLoader;

public class Player extends Entity{
	
	private boolean holdingUp, holdingDown, sprinting, facingLeft;
	
	private Animate animation;
	
	public int walkingSpeed = 5;
	
	public boolean isFacingLeft() {
		return facingLeft;
	}

	public void setFacingLeft(boolean facingLeft) {
		this.facingLeft = facingLeft;
	}

	public boolean isHoldingUp() {
		return holdingUp;
	}

	public boolean isSprinting() {
		return sprinting;
	}

	public void setSprinting(boolean sprinting) {
		this.sprinting = sprinting;
	}

	public void setHoldingUp(boolean holdingUp) {
		this.holdingUp = holdingUp;
	}

	public boolean isHoldingDown() {
		return holdingDown;
	}

	public void setHoldingDown(boolean holdingDown) {
		this.holdingDown = holdingDown;
	}
	
	

	public Player(float x, float y, ObjectManager manager) {
		super(x, y, 100-24-24, 74, Identification.ENTITY_PLAYER, manager);
		//gravityEnabled = false;
		animation = new Animate(10, ImageLoader.player_idle, ImageLoader.player_running);
		
	}
	
	
	public void tick() {
		if (vx != 0)
			animation.run(1);
		else
			animation.run(0);
	}
	
	
	
	public void render(Graphics g) {
		
		if (vx < 0) facingLeft = true;
		if (vx > 0) facingLeft = false;
			
		animation.draw(g, (facingLeft ? (int) x+((width+48)/2) : (int) x-((width+48)/2)), (int) y, (facingLeft ? -(width+48) : (width+48)), height);
		
		/*if (facingLeft)
			g.drawImage(ImageLoader.player, (int) x + width, (int) y, -width, height, null);
		else
			g.drawImage(ImageLoader.player, (int) x, (int) y, null);
		*/
		
//		g.setColor(Color.red);
//		g.fillRect((int) x, (int) y, width, height);
//		
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.white);
//		g2.draw(bounds().bottom());
		//g2.draw(bounds().top());
//		g2.draw(bounds().left());
//		g2.draw(bounds().right());

	}
	
	public Bounds bounds() {
		Bounds bounds = new Bounds();
		bounds.setAllBounds(x, y, width, height);
		/*bounds.setBottom(new Rectangle((int) x+(width/2)-((width/2)/2), (int) y+(height/2), width/2, height/2));
		bounds.setTop(new Rectangle((int) x+(width/2)-((width/2)/2), (int) y, width/2, height/2));
		bounds.setRight(new Rectangle((int) x+width-5, (int) y+5, 5, height-10));
		bounds.setLeft(new Rectangle((int) x, (int) y+5, 5, height-10));*/
		return bounds;
	}
	
	
	

	
	
}
