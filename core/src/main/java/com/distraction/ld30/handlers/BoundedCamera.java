package com.distraction.ld30.handlers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;

public class BoundedCamera extends OrthographicCamera {

    private float xmin;
    private float ymin;
    private float xmax;
    private float ymax;

    private boolean shaking;
    private float shakeRange;
    private int shakingTimer;

    public void setBounds(float xmin, float ymin, float xmax, float ymax) {
        this.xmin = xmin;
        this.ymin = ymin;
        this.xmax = xmax;
        this.ymax = ymax;
        shakeRange = 3;
    }

    public void setShakeRange(float f) {
        shakeRange = f;
    }

    public void setShaking(int time) {
        shakingTimer = time;
        shaking = true;
    }

    public void updateShaking() {
        shakingTimer--;
        if(shakingTimer == 0) {
            shaking = false;
        }
    }

    public void setPosition(float x, float y) {
        position.x = x;
        position.y = y;
        fixBounds();
        if(shaking) {
            position.x += MathUtils.random(-shakeRange, shakeRange);
            position.y += MathUtils.random(-shakeRange, shakeRange);
            position.x = (int) position.x;
            position.y = (int) position.y;
        }
        update();
    }

    public void fixBounds() {
        if(position.x < xmin + viewportWidth / 2) position.x = xmin + viewportWidth / 2;
        if(position.y < ymin + viewportHeight / 2) position.y = ymin + viewportHeight / 2;
        if(position.x > xmax - viewportWidth / 2) position.x = xmax - viewportWidth / 2;
        if(position.y > ymax - viewportHeight / 2) position.y = ymax - viewportHeight / 2;
    }

}
