package me.dilan.game.entity;

import java.awt.Graphics;

import me.dilan.game.GObject;
import me.dilan.game.util.Identification;
import me.dilan.game.util.ObjectManager;

public abstract class Entity extends GObject{

	protected boolean falling = true;
	protected boolean jumping = false;
	
	protected boolean gravityEnabled = true;
	
	protected float gravity = 0.4f;
	protected float gravMax = 10;
	protected ObjectManager manager;
	
	protected int health = 50;
	

	public Entity(float x, float y, int width, int height, Identification id, ObjectManager manager) {
		super(x, y, width, height, id);
		
		this.manager = manager;

	}

	
	

	public void objTick() {
		x += vx;
		y += vy;
		
		if (gravityEnabled) {
			if (falling || jumping) {
				vy += gravity;

				if (vy > gravMax) {
					vy = gravMax;
				}
			}
		}
		tick();
		collision();
	}

	public void collisionSuccess(GObject with, CollisionType type) {

	}
	
	public enum CollisionType{
		UNKNOWN,
		LEFT,
		RIGHT,
		TOP,
		BOTTOM
	}

	public void collision() {
		
		
		for (int i = 0; i < manager.blocks.size(); i++) {
			GObject obj = manager.blocks.get(i);
			
			if (this instanceof Player) {
				
				if (obj.getId() == Identification.BLOCK_ELEVATOR) {
					if (bounds().bottom().intersects(obj.bounds().bounds())) {
						vy = -7;
						
					}
				}
			}
			
			
			if (obj.getId() == Identification.BLOCK_TOPCOLLIDER) {
				if (bounds().bottom().intersects(obj.bounds().bounds())) {
					y = obj.getY() - height;
					if (vy > 0) vy = 0;
					falling = false;
					jumping = false;
					collisionSuccess(obj, CollisionType.BOTTOM);
				}else {
					falling = true;
				}
			}
			
			if (obj.getId() == Identification.BLOCK_GROUND) {

				if (bounds().top().intersects(obj.bounds().bounds())) {
					y = obj.getY() + obj.getHeight();
					vy = 0;
					
					collisionSuccess(obj, CollisionType.TOP);
				}
				
				if (bounds().bottom().intersects(obj.bounds().bounds())) {
					y = obj.getY() - height;
					vy = 0;
					falling = false;
					jumping = false;
					collisionSuccess(obj, CollisionType.BOTTOM);
				}else {
					falling = true;
				}

				if (bounds().right().intersects(obj.bounds().bounds())) {
					x = obj.getX() - width;
					collisionSuccess(obj, CollisionType.RIGHT);

				}
				else if (bounds().left().intersects(obj.bounds().bounds())) {
					x = obj.getX() + obj.getWidth();
					collisionSuccess(obj, CollisionType.LEFT);

				}

			}

		}
		
		
	}

	public int getHealth() {
		return health;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}

	public abstract void tick();

	public void objRender(Graphics g) {

		render(g);
	}

	public abstract void render(Graphics g);



}
