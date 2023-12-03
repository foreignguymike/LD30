package com.distraction.ld30.entities;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.ld30.LD30;

public class Background {
	
	private OrthographicCamera cam;
	
	private TextureRegion image;
	private int xdraw;
	private int ydraw;
	
	public Background(TextureRegion image, OrthographicCamera cam) {
		this.image = image;
		this.cam = cam;
		xdraw = LD30.WIDTH / image.getRegionWidth() + 1;
		ydraw = LD30.HEIGHT / image.getRegionHeight() + 1;
	}
	
	public void draw(SpriteBatch sb) {
		float left = cam.position.x - cam.viewportWidth / 2;
		int pos = (int) (left / image.getRegionWidth()) * image.getRegionWidth();
		for(int i = 0; i < xdraw; i++) {
			for(int j = 0; j < ydraw; j++) {
				sb.draw(image, pos + i * image.getRegionWidth(), j * image.getRegionHeight());
			}
		}
	}
	
}
