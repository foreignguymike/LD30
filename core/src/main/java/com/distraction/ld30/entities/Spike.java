package com.distraction.ld30.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.ld30.LD30;
import com.distraction.ld30.tilemap.TileMap;

public class Spike extends MapObject {
	
	public Spike(TileMap tm, float x, float y) {
		super(tm);
		this.x = x;
		this.y = y;
		TextureRegion[] frames = TextureRegion.split(LD30.res.getTexture("spike"), 15, 15)[0];
		setAnimation(frames, 3);
		width = height = 15;
		cwidth = cheight = 12;
	}
	
}
