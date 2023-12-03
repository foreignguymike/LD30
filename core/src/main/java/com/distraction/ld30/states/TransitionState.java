package com.distraction.ld30.states;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.ld30.LD30;

public class TransitionState extends State {

    private State prev;
    private State next;

    private int prevTime;
    private int nextTime;

    private int timer;

    private TextureRegion image;

    public TransitionState(GSM gsm, State prev, State next) {
        super(gsm);
        this.prev = prev;
        this.next = next;
        image = new TextureRegion(LD30.res.getTexture("transition"));
        prevTime = 30;
        nextTime = prevTime + 30;
    }

    public void handleInput() {}

    public void update() {
        timer++;
        if(timer > nextTime) {
            gsm.set(next);
        }
    }

    public void render() {

        sb.setProjectionMatrix(cam.combined);

        if(timer < prevTime) {
            prev.render();
            sb.setShader(GSM.defaultShader);
            sb.begin();
            sb.draw(image, 0, 0, (1f * timer / prevTime) * LD30.WIDTH, LD30.HEIGHT);
            sb.end();
        }
        else if(timer < nextTime) {
            next.render();
            sb.setShader(GSM.defaultShader);
            sb.begin();
            sb.draw(image, 0, 0, (1f * (nextTime - timer) / prevTime) * LD30.WIDTH, LD30.HEIGHT);
            sb.end();
        }

    }

}
