package com.distraction.ld30.entities;

import com.distraction.ld30.tilemap.TileMap;

public class VerticalSpike extends Spike {
	
	private float p1;
	private float p2;
	
	public VerticalSpike(TileMap tm, float x, float y, float dist, float moveSpeed) {
		super(tm, x, y);
		if(dist > 0) {
			p1 = y;
			p2 = y + dist;
			dy = moveSpeed;
		}
		else {
			p2 = y;
			p1 = y + dist;
			dy = -moveSpeed;
		}
		this.moveSpeed = moveSpeed;
	}
	
	public void update() {
		super.update();
		if(y < p1 && dy < 0) {
			dy = moveSpeed;
		}
		else if(y > p2 && dy > 0) {
			dy = -moveSpeed;
		}
		x += dx;
		y += dy;
	}
	
}
