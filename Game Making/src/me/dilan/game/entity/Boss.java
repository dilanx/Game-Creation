package me.dilan.game.entity;

import java.awt.Graphics;

import me.dilan.game.GObject;
import me.dilan.game.Game;
import me.dilan.game.util.Bounds;
import me.dilan.game.util.Direction;
import me.dilan.game.util.Identification;
import me.dilan.game.util.ObjectManager;
import me.dilan.game.view.ImageLoader;

public class Boss extends Entity{


	private BossPhase phase;
	private boolean isVulnerable;
	private int hits = 50;

	private Player playerFighting;
	
	public Boss(float x, float y, ObjectManager manager) {
		super(x, y, 256, 256, Identification.ENTITY_BOSS, manager);
		this.phase = BossPhase.BULLETS_ONE;
		manager.health.setMaxMiniHealth(hits);
	}

	public enum BossPhase{
		BULLETS_ONE,
		LASERS,

	}

	private int steps = 0;

	private int bulletsPhaseCount = 0;


	private int lasersPhaseCount = 0;
	private int lasersX = 0;
	private boolean showLaser = false;

	private int currentVelocity = 2;

	private int wait = 0;

	//60 ticks per second
	public void tick() {

		if (!isVulnerable && hits < 50)
			hits++;

		if (phase == BossPhase.LASERS) {
			
			if (lasersPhaseCount % 2 == 0) {
				lasersX += 2;
				if (lasersX == Game.WIDTH - 64)
					lasersPhaseCount++;
			}
			else if (lasersPhaseCount % 2 == 1) {
				lasersX -= 2;
				
				if (lasersPhaseCount == 5) {
					//if (lasersX == ) TODO
				}
				
				if (lasersX == 0)
					lasersPhaseCount++;
			}

		}

		if (phase == BossPhase.BULLETS_ONE) {

			if (steps == 60 && bulletsPhaseCount < 10) {
				Bullet.fire(this, Direction.NORTHWEST, manager, "BossShoot");
				Bullet.fire(this, Direction.NORTH, manager, "BossShoot");
				Bullet.fire(this, Direction.NORTHEAST, manager, "BossShoot");
				Bullet.fire(this, Direction.EAST, manager, "BossShoot");
				Bullet.fire(this, Direction.WEST, manager, "BossShoot");
				steps = 0;
				bulletsPhaseCount++;
			}
			if (bulletsPhaseCount == 10) {
				isVulnerable = true;
				wait++;
				if (wait == 5 * 60) {
					isVulnerable = false;
					bulletsPhaseCount = 0;
					wait = 0;
					steps = 0;
				}
			}
			if (hits == 0) {
				phase = BossPhase.LASERS;
				steps = 0;
				wait = 0;
			}


			steps += 1;
		}



		bulletCollision();
	}

	private boolean initialMovement = false;

	@Override
	public void collisionSuccess(GObject with, CollisionType type) {



		if (initialMovement == false) {
			vx = -currentVelocity;
			initialMovement = true;
		}

		if (type == CollisionType.RIGHT) {
			vx = -currentVelocity;
		}

		if (type == CollisionType.LEFT) {
			vx = currentVelocity;
		}

	}

	

	public void bulletCollision() {

		for (int i = 0; i < manager.secondary.size(); i++) {
			GObject obj = manager.secondary.get(i);
			if (obj != null && obj.getId() == Identification.ENTITY_BULLET_PLAYER) {
				if (obj.bounds().bounds().intersects(this.bounds().bounds())) {
					if (isVulnerable && hits > 0) {
						hits-=10;
					}

					if (manager.secondary.contains(obj))
						manager.secondary.remove(obj);
				}
			}
		}
	}

	public void render(Graphics g) {

		if (hits <= 50) {
			manager.health.setMiniHealth(hits);
		}

		if (isVulnerable)
			g.drawImage(ImageLoader.boss_vulnerable, (int) x, (int) y, null);
		else
			g.drawImage(ImageLoader.boss, (int) x, (int) y, null);

		if (showLaser) {
			g.drawImage(ImageLoader.laser_empty, lasersX, 0, null);
		}

	}

	public Bounds bounds() {
		return new Bounds().setAllBounds(x, y, width, height);
	}

}
