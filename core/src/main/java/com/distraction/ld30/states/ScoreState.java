package com.distraction.ld30.states;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.ld30.LD30;
import com.distraction.ld30.entities.Graphic;
import com.distraction.ld30.handlers.MyInput;

public class ScoreState extends State {

	private TextureRegion bg;
	private Graphic text;

	public ScoreState(GSM gsm, int numDeaths) {
		super(gsm);
		bg = new TextureRegion(LD30.res.getTexture("transition"));
		if(numDeaths == 0) {
			text = new Graphic(new TextureRegion(LD30.res.getTexture("srank")), LD30.WIDTH / 2, LD30.HEIGHT / 2);
		}
		else if(numDeaths >= 1 && numDeaths <= 3) {
			text = new Graphic(new TextureRegion(LD30.res.getTexture("arank")), LD30.WIDTH / 2, LD30.HEIGHT / 2);
		}
		else if(numDeaths >= 4 && numDeaths <= 6) {
			text = new Graphic(new TextureRegion(LD30.res.getTexture("brank")), LD30.WIDTH / 2, LD30.HEIGHT / 2);
		}
		else if(numDeaths >= 7 && numDeaths <= 10) {
			text = new Graphic(new TextureRegion(LD30.res.getTexture("crank")), LD30.WIDTH / 2, LD30.HEIGHT / 2);
		}
		else if(numDeaths >= 11 && numDeaths <= 20) {
			text = new Graphic(new TextureRegion(LD30.res.getTexture("drank")), LD30.WIDTH / 2, LD30.HEIGHT / 2);
		}
		else if(numDeaths >= 21) {
			text = new Graphic(new TextureRegion(LD30.res.getTexture("frank")), LD30.WIDTH / 2, LD30.HEIGHT / 2);
		}
	}

	public void handleInput() {
		if(LD30.input.isPressed(MyInput.JUMP)) {
			gsm.set(new TransitionState(gsm, this, new MenuState(gsm)));
		}
	}

	public void update() {
		handleInput();
	}

	public void render() {
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		sb.draw(bg, 0, 0, LD30.WIDTH, LD30.HEIGHT);
		text.draw(sb);
		sb.end();
	}

}
