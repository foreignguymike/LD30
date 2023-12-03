package com.distraction.ld30.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Graphic {
	
	private TextureRegion image;
	private int x;
	private int y;
	private int width;
	private int height;
	
	public Graphic(TextureRegion image, int x, int y) {
		this.image = image;
		this.x = x;
		this.y = y;
		width = image.getRegionWidth();
		height = image.getRegionHeight();
	}
	
	public void draw(SpriteBatch sb) {
		sb.draw(image, x - width / 2, y - height / 2);
	}
	
}
