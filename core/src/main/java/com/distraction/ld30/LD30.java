package com.distraction.ld30;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.distraction.ld30.handlers.Content;
import com.distraction.ld30.handlers.MyInput;
import com.distraction.ld30.handlers.MyInputProcessor;
import com.distraction.ld30.states.GSM;
import com.distraction.ld30.states.MenuState;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class LD30 extends ApplicationAdapter {

    public static final String TITLE = "AR Suit";

    public static final int WIDTH = 512;
    public static final int HEIGHT = 320;
    public static final int SCALE = 2;

    public static Content res;
    public static MyInput input;

    private GSM gsm;
    private float time;

    public void create () {

        Gdx.gl.glClearColor(0.6f, 0.6f, 0.7f, 1);

        res = new Content();
        res.loadTexture("sprites/menutext.png", "menutext");
        res.loadTexture("tilesets/tileset.png", "tileset");
        res.loadTexture("sprites/player.png", "player");
        res.loadTexture("sprites/sparks.png", "spark");
        res.loadTexture("sprites/teleport.png", "teleport");
        res.loadTexture("sprites/transition.png", "transition");
        for(int i = 1; i <= 4; i++) {
            res.loadTexture("sprites/level" + i + "text.png", "level" + i + "text");
        }
        res.loadTexture("sprites/level3text2.png", "level3text2");
        res.loadTexture("sprites/level9text.png", "level9text");
        res.loadTexture("sprites/spikes.png", "spike");
        res.loadTexture("sprites/jumpspark.png", "jumpspark");
        res.loadTexture("sprites/jumpexplode.png", "jumpexplode");
        res.loadTexture("sprites/dust.png", "dust");
        res.loadTexture("sprites/bg.png", "bg");
        res.loadTexture("sprites/srank.png", "srank");
        res.loadTexture("sprites/arank.png", "arank");
        res.loadTexture("sprites/brank.png", "brank");
        res.loadTexture("sprites/crank.png", "crank");
        res.loadTexture("sprites/drank.png", "drank");
        res.loadTexture("sprites/frank.png", "frank");

        res.loadSFX("sfx/warp.ogg", "SFXwarp");
        res.loadSFX("sfx/warpfail.ogg", "SFXwarpfail");
        res.loadSFX("sfx/jumpsphere.ogg", "SFXjumpsphere");
        res.loadSFX("sfx/jumpspark.ogg", "SFXjumpspark");
        res.loadSFX("sfx/jump.ogg", "SFXjump");
        res.loadSFX("sfx/clear.ogg", "SFXclear");
        res.loadSFX("sfx/step.ogg", "SFXstep");

        res.loadMusic("music/ld30music.ogg", "ld30music");

        Gdx.input.setInputProcessor(new MyInputProcessor());
        input = new MyInput();

        gsm = new GSM();
//        gsm.push(new com.distraction.ld30.states.PlayState(gsm, 17, 0));
        gsm.push(new MenuState(gsm));

    }

    public void render () {
        time += Gdx.graphics.getDeltaTime();
        while (time > 1f / 60f) {
            gsm.update();
            gsm.render();
            input.update();
            time -= 1f / 60f;
        }
    }
}
