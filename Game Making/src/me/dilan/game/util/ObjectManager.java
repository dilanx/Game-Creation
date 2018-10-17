package me.dilan.game.util;

import java.util.ArrayList;
import java.util.List;

import me.dilan.game.GObject;
import me.dilan.game.blocks.Block;
import me.dilan.game.view.Camera;
import me.dilan.game.view.Health;

public class ObjectManager {

	
	public List<GObject> primary = new ArrayList<GObject>();
	
	public List<GObject> secondary = new ArrayList<GObject>();
	
	public List<GObject> blocks = new ArrayList<GObject>();
	
	public Camera camera;
	public Health health;
	
	public void addPrimary(GObject obj) {
		primary.add(obj);
	}
	
	public void delPrimary(GObject obj) {
		primary.remove(obj);
	}
	
	public void addSecondary(GObject obj) {
		secondary.add(obj);
	}
	
	public void delSecondary(GObject obj) {
		secondary.remove(obj);
	}
	
	public void addBlock(Block block) {
		blocks.add(block);
	}
	
	public void delBlock(Block block) {
		blocks.remove(block);
	}
	
}
