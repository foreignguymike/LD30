package com.distraction.ld30.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.ld30.LD30;
import com.distraction.ld30.tilemap.TileMap;

public class Teleport extends MapObject {
	
	public Teleport(TileMap tm) {
		super(tm);
		setAnimation(TextureRegion.split(LD30.res.getTexture("teleport"), 16, 28)[0], 2);
		width = 16;
		height = 28;
		cwidth = 20;
		cheight = 30;
	}
	
}
