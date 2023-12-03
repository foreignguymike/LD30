package com.distraction.ld30.states;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.ld30.LD30;
import com.distraction.ld30.entities.Graphic;
import com.distraction.ld30.handlers.MyInput;

public class MenuState extends State {

	private TextureRegion bg;
	private Graphic text;

	public MenuState(GSM gsm) {
		super(gsm);
		bg = new TextureRegion(LD30.res.getTexture("transition"));
		text = new Graphic(new TextureRegion(LD30.res.getTexture("menutext")), LD30.WIDTH / 2, LD30.HEIGHT / 2);
	}

	public void handleInput() {
		if(LD30.input.isPressed(MyInput.JUMP)) {
			LD30.res.getSFX("SFXjumpsphere").play();
			gsm.set(new TransitionState(gsm, this, new PlayState(gsm, 1, 0)));
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
