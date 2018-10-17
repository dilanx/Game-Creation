package me.dilan.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import me.dilan.game.entity.Bullet;
import me.dilan.game.entity.Player;
import me.dilan.game.util.ObjectManager;
import me.dilan.game.util.SoundPlayer;
import me.dilan.game.view.Camera;
import me.dilan.game.view.Health;
import me.dilan.game.view.ImageLoader;
import me.dilan.game.view.LevelLoader;
import me.dilan.game.view.Window;

public class Game extends Canvas implements Runnable{


	/*
	 * 
	 * 
	 */

	private ObjectManager manager;
	private Camera camera;
	private Health health;

	private static final long serialVersionUID = 2248161283577396772L;
	public boolean running = false;
	private Thread thread;

	public static int WIDTH = 700, HEIGHT = 500;

	public Game() {

		new Window(this);


		this.start();

	}


	public synchronized void start() {

		if (running) return;

		thread = new Thread(this);
		thread.start();
		running = true;

	}

	public void load() {
		manager = new ObjectManager();
		
		SoundPlayer.musicPlay("Captain Scurvy");
		SoundPlayer.musicLoop(500);
		
		ImageLoader.loadImages();
		
		camera = new Camera(0, 0, this, 0.1f);
		manager.camera = camera;
		health = new Health(camera);
		manager.health = health;
		
		
		
		LevelLoader.loadLevel("1-1", this, manager);

	

		

		
		
		this.addKeyListener(new Controls(manager));




	}

	public synchronized void stop() {




	}


	public static void main(String[] args) {
		new Game();
	}

	public void run() {

		load();
		this.requestFocus();
		long time = System.nanoTime();
		double nanoframes = 1e9 / 60.0;
		double difference = 0;

		while(running) {
			long now = System.nanoTime();
			difference += (now - time) / nanoframes;
			time = now;
			while (difference >= 1) {
				tick();
				difference--;
			}
			render();
		}
	}

	

	private void tick() {
		for (int i = 0; i < manager.primary.size(); i++) {
			GObject obj = manager.primary.get(i);
			obj.objTick();
			if (obj instanceof Player) {
				camera.tick((Player) obj);
				health.setHealth(((Player) obj).getHealth());
			}
		}
		
		for (int i = 0; i < manager.secondary.size(); i++) {
			GObject obj = manager.secondary.get(i);
			obj.objTick();
		}
		
		
		
		Bullet.countdownTick();
		
		health.tick();



	}




	private void render() {

		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());

		g.translate(-(int) camera.getX(), -(int) camera.getY());
		//explain why using for loop instead of for each
		for (int i = 0; i < manager.primary.size(); i++) {
			
			GObject obj = manager.primary.get(i);
			if (obj == null) break;
			obj.objRender(g);
		}

		for (int i = 0; i < manager.secondary.size(); i++) {
			GObject obj = manager.secondary.get(i);
			obj.objRender(g);
		}
		
		for (int i = 0; i < manager.blocks.size(); i++) {
			GObject obj = manager.blocks.get(i);
			obj.objRender(g);
		}
		
		health.render(g);
		

		g.dispose();
		bs.show();
	}


}
