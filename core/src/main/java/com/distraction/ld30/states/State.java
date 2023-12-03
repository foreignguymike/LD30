package com.distraction.ld30.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.distraction.ld30.LD30;

public abstract class State {

	protected GSM gsm;
	protected SpriteBatch sb;
	protected OrthographicCamera cam;

	protected State(GSM gsm) {
		this.gsm = gsm;
		cam = new OrthographicCamera();
		cam.setToOrtho(false, LD30.WIDTH, LD30.HEIGHT);
		sb = gsm.getSpriteBatch();
	}

	protected abstract void handleInput();
	protected abstract void update();
	protected abstract void render();

}
