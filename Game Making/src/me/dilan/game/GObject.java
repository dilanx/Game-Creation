package me.dilan.game;

import java.awt.Graphics;

import me.dilan.game.util.Bounds;
import me.dilan.game.util.Identification;

public abstract class GObject {

	protected float x, y;
	protected int width, height;
	protected float vx = 0, vy = 0;
	
	protected Identification id;
	
	public GObject(float x, float y, int width, int height, Identification id) {
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.id = id;
		
	}
	
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getVx() {
		return vx;
	}


	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}


	public void setVx(float vx) {
		this.vx = vx;
	}


	public float getVy() {
		return vy;
	}


	public void setVy(float vy) {
		this.vy = vy;
	}


	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public Identification getId() {
		return id;
	}
	public void setId(Identification id) {
		this.id = id;
	}
	
	
	public abstract void objTick();
	public abstract void objRender(Graphics g);
	public abstract Bounds bounds();
	
}
