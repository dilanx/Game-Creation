package me.dilan.game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import me.dilan.game.entity.Bullet;
import me.dilan.game.entity.Player;
import me.dilan.game.util.Identification;
import me.dilan.game.util.ObjectManager;

public class Controls extends KeyAdapter{

	ObjectManager manager;
	
	public Controls(ObjectManager manager) {
		this.manager = manager;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for (int i = 0; i < manager.primary.size(); i++) {
			GObject obj = manager.primary.get(i);
			if (obj.getId() == Identification.ENTITY_PLAYER) {
				Player p = (Player) obj;
				if (key == KeyEvent.VK_D) p.setVx(p.walkingSpeed);
				if (key == KeyEvent.VK_A) p.setVx(-p.walkingSpeed);
				if (key == KeyEvent.VK_SPACE && p.getVy() == 0) {
					p.setVy(-8);
				}
				if (key == KeyEvent.VK_W) p.setHoldingUp(true);
				if (key == KeyEvent.VK_S) p.setHoldingDown(true);
				if (key == KeyEvent.VK_P && Bullet.canFire) {
					Bullet.fire(p, manager);
				}
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < manager.primary.size(); i++) {
			GObject obj = manager.primary.get(i);
			if (obj.getId() == Identification.ENTITY_PLAYER) {
				Player p = (Player) obj;
				if (key == KeyEvent.VK_D) p.setVx(0);
				if (key == KeyEvent.VK_A) p.setVx(0);
				if (key == KeyEvent.VK_W) p.setHoldingUp(false);
				if (key == KeyEvent.VK_S) p.setHoldingDown(false);
				
			}
		}
	}
	
}
