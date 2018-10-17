package me.dilan.game.entity;

import java.awt.Color;
import java.awt.Graphics;

import me.dilan.game.GObject;
import me.dilan.game.util.Bounds;
import me.dilan.game.util.Direction;
import me.dilan.game.util.Identification;
import me.dilan.game.util.ObjectManager;
import me.dilan.game.util.SoundPlayer;

public class Bullet extends Entity{

	private int bulletSpeed = 10;

	public static boolean canFire = true;
	private static int maxFireDelay = 10;
	private static int currentFireDelay = maxFireDelay;

	public static void fire(GObject obj, Direction dir, ObjectManager manager, String soundTitle) {

		manager.addSecondary(new Bullet(obj.getX() + (obj.getWidth() / 2), obj.getY() + (obj.getHeight() / 2), Identification.ENTITY_BULLET_NOTPLAYER, manager, dir));
		SoundPlayer.playEffect(soundTitle);

	}

	public static void fire(Player p, ObjectManager manager) {

		currentFireDelay = 0;

		Direction dir;

		if (p.isHoldingUp()) {
			if (p.getVx() > 0) {
				dir = Direction.NORTHEAST;
			}else if (p.getVx() < 0) {
				dir = Direction.NORTHWEST;
			}else {
				dir = Direction.NORTH;
			}
		}
		else if (p.isHoldingDown()) {
			if (p.getVx() > 0) {
				dir = Direction.SOUTHEAST;
			}else if (p.getVx() < 0) {
				dir = Direction.SOUTHWEST;
			}else {
				dir = Direction.SOUTH;
			}
		}else {
			if (p.isFacingLeft()) {
				dir = Direction.WEST;
			}
			else{
				dir = Direction.EAST;
			}
		}

		manager.addSecondary(new Bullet(p.getX() + (p.getWidth() / 2), p.getY() + (p.getHeight() / 2), Identification.ENTITY_BULLET_PLAYER, manager, dir));
		SoundPlayer.playEffect("Shoot");
	}

	public Bullet(float x, float y, Identification id, ObjectManager manager, Direction dir) {
		super(x, y, 8, 8, id, manager);

		this.gravityEnabled = false;

		switch(dir) {

		case NORTH:
			vy = -bulletSpeed; break;
		case EAST:
			vx = bulletSpeed; break;
		case WEST:
			vx = -bulletSpeed; break;
		case SOUTH:
			vy = bulletSpeed; break;
		case NORTHEAST:
			vy = -bulletSpeed;
			vx = bulletSpeed;
			break;
		case NORTHWEST:
			vy = -bulletSpeed;
			vx = -bulletSpeed;
			break;
		case SOUTHEAST:
			vy = bulletSpeed;
			vx = bulletSpeed;
			break;
		case SOUTHWEST:
			vy = bulletSpeed;
			vx = -bulletSpeed;
			break;


		}
	}

	public static void countdownTick() {
		if (currentFireDelay >= maxFireDelay) {
			canFire = true;
		}else {
			canFire = false;
			currentFireDelay++;

		}
	}

	public void tick() {
		playerCollision();
	}

	public void playerCollision() {
		
		if (id == Identification.ENTITY_BULLET_NOTPLAYER) {
			for (int i = 0; i < manager.primary.size(); i++) {
				GObject obj = manager.primary.get(i);
				if (obj.getId() == Identification.ENTITY_PLAYER) {
					if (bounds().bounds().intersects(obj.bounds().bounds())) {
						Player p = (Player) obj; 
						p.setHealth(p.getHealth() - 10);
						if (manager.secondary.contains(this))
							manager.secondary.remove(this);
					}
				}
			}
		}

	}
	
	


	@Override
	public void collisionSuccess(GObject with, CollisionType type) {

		if (with.getId() == Identification.BLOCK_TOPCOLLIDER && vy >= 0) {
			if (manager.secondary.contains(this))
				manager.secondary.remove(this);
		}

		if (with.getId() == Identification.BLOCK_GROUND) {
			if (manager.secondary.contains(this))
				manager.secondary.remove(this);
		}
	}


	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int) x, (int) y, width, height);
	}


	public Bounds bounds() {
		return new Bounds().setAllBounds(x, y, width, height);
	}

}
