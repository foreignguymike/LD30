package com.distraction.ld30.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.ld30.LD30;
import com.distraction.ld30.tilemap.TileMap;

public class JumpSphere extends MapObject {

	public JumpSphere(TileMap tm, float x, float y) {
		super(tm);
		this.x = x;
		this.y = y;
		setAnimation(TextureRegion.split(LD30.res.getTexture("jumpspark"), 25, 25)[0], 3);
		width = height = 35;
		cwidth = cheight = 35;
	}

}
