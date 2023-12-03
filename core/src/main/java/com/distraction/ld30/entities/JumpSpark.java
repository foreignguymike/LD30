package com.distraction.ld30.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.ld30.LD30;
import com.distraction.ld30.tilemap.TileMap;

public class JumpSpark extends MapObject {
	
	public JumpSpark(TileMap tm) {
		super(tm);
		setAnimation(TextureRegion.split(LD30.res.getTexture("jumpspark"), 25, 25)[0], 3);
		width = height = 25;
	}
	
}
