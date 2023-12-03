package com.distraction.ld30.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.ld30.tilemap.TileMap;

public class SingleAnimatedGraphic extends MapObject {
	
	private boolean remove;
	
	public SingleAnimatedGraphic(TileMap tm, TextureRegion[] frames, int delay, float x, float y) {
		super(tm);
		setAnimation(frames, delay);
		this.x = x;
		this.y = y;
	}
	
	public boolean shouldRemove() {
		return remove;
	}
	
	public void update() {
		animation.update();
		if(animation.hasPlayedOnce()) {
			remove = true;
		}
	}
	
}
