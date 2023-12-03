package com.distraction.ld30.entities;

import com.distraction.ld30.tilemap.TileMap;

public class HorizontalSpike extends Spike {
	
	private float p1;
	private float p2;
	
	public HorizontalSpike(TileMap tm, float x, float y, float dist, float moveSpeed) {
		super(tm, x, y);
		if(dist > 0) {
			p1 = x;
			p2 = x + dist;
			dx = moveSpeed;
		}
		else {
			p2 = x;
			p1 = x + dist;
			dx = -moveSpeed;
		}
		this.moveSpeed = moveSpeed;
	}
	
	public void update() {
		super.update();
		if(x < p1 && dx < 0) {
			dx = moveSpeed;
		}
		else if(x > p2 && dx > 0) {
			dx = -moveSpeed;
		}
		x += dx;
		y += dy;
	}
	
}
