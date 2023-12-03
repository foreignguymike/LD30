package com.distraction.ld30.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.distraction.ld30.LD30;
import com.distraction.ld30.tilemap.TileMap;

public class Player extends MapObject {

    private boolean warping;
    private float teleDest;
    private float teleSpeed = 600 / 60f;

    private Array<SingleAnimatedGraphic> animatedGraphics;
    private int warpTimer;
    private int sparkDelay = 2;
    private float sparkRange = 10;

    private TextureRegion[] idleAnimation;
    private TextureRegion[] walkingAnimation;
    private TextureRegion[] jumpingAnimation;
    private TextureRegion[] fallingAnimation;
    private TextureRegion[] warpingAnimation;

    private boolean hasJumpSpark;
    private JumpSpark jumpSpark;
    private int jumpCount;

    public Player(TileMap tm, Array<SingleAnimatedGraphic> sparks) {

        super(tm);
        this.animatedGraphics = sparks;

        Texture tex = LD30.res.getTexture("player");

        idleAnimation = new TextureRegion[] { new TextureRegion(tex, 0, 0, 30, 30) };
        walkingAnimation = new TextureRegion[4];
        for(int i = 0; i < walkingAnimation.length; i++) {
            walkingAnimation[i] = new TextureRegion(tex, i * 30, 30, 30, 30);
        }
        jumpingAnimation = new TextureRegion[] { new TextureRegion(tex, 0, 60, 30, 30) };
        fallingAnimation = new TextureRegion[] { new TextureRegion(tex, 0, 90, 30, 30) };
        warpingAnimation = new TextureRegion[3];
        for(int i = 0; i < warpingAnimation.length; i++) {
            warpingAnimation[i] = new TextureRegion(tex, i * 30, 120, 30, 30);
        }

        setAnimation(idleAnimation, -1);

        width = height = 30;
        cwidth = 10;
        cheight = 18;

        moveSpeed = stopSpeed = 10 / 60f;
        maxSpeed = 100 / 60f;
        fallSpeed = 8 / 60f;
        maxFallSpeed = 200 / 60f;
        jumpStart = 250 / 60f;

        hasJumpSpark = false;
        jumpSpark = new JumpSpark(tileMap);

    }

    public boolean isWarping() { return warping; }
    public void getJumpSpark() {
        hasJumpSpark = true;
        jumpSpark.setPosition(x, y);
        jumpCount++;
    }

    public void setJumping() {
        if(jumpCount <= 0) return;
        if(jumping) return;
        if(falling && !hasJumpSpark) return;
        jumping = true;
    }

    public boolean warp() {

        if(warping) return false;

        if(y > LD30.HEIGHT / 2) {
            teleDest = y - LD30.HEIGHT / 2;
        }
        else {
            teleDest = y + LD30.HEIGHT / 2;
        }

        calculateCollision(x, teleDest);
        if(topCollision || bottomCollision || leftCollision || rightCollision) return false;

        warping = true;
        return true;

    }

    private void getNextPosition() {
        if(left) {
            dx -= moveSpeed;
            if(dx < -maxSpeed) {
                dx = -maxSpeed;
            }
        }
        else if(right) {
            dx += moveSpeed;
            if(dx > maxSpeed) {
                dx = maxSpeed;
            }
        }
        else {
            if(dx > 0) {
                dx -= stopSpeed;
                if(dx < 0) {
                    dx = 0;
                }
            }
            else if(dx < 0) {
                dx += stopSpeed;
                if(dx > 0) {
                    dx = 0;
                }
            }
        }

        if(jumping) {
            dy = jumpStart;
            falling = true;
            jumping = false;
            jumpCount--;
            if(hasJumpSpark) {
                hasJumpSpark = false;
                animatedGraphics.add(new SingleAnimatedGraphic(tileMap, TextureRegion.split(LD30.res.getTexture("jumpexplode"), 50, 50)[0], 2, x, y));
                LD30.res.getSFX("SFXjumpspark").play();
            }
            else {
                LD30.res.getSFX("SFXjump").play();
            }
        }

        if(falling) {
            dy -= fallSpeed;
            if(dy < -maxFallSpeed) dy = -maxFallSpeed;
        }

    }

    public void update() {

        if(warping) {
            if(y <= teleDest) {
                y += teleSpeed;
                if(y > teleDest) {
                    y = teleDest;
                    warping = false;
                }
            }
            else if(y >= teleDest) {
                y -= teleSpeed;
                if(y < teleDest) {
                    y = teleDest;
                    warping = false;
                }
            }
        }
        else {
            boolean b = falling;
            getNextPosition();
            checkTileMapCollision();
            x = xtemp;
            y = ytemp;
            if(b && !falling) {
                animatedGraphics.add(new SingleAnimatedGraphic(tileMap, TextureRegion.split(LD30.res.getTexture("dust"), 20, 20)[0], 2, x, y - 10));
                LD30.res.getSFX("SFXstep").play();
            }
        }

        if(!falling) {
            jumpCount = 1;
        }

        if(warping) {
            warpTimer++;
            if(warpTimer == sparkDelay) {
                SingleAnimatedGraphic s = new SingleAnimatedGraphic(tileMap, TextureRegion.split(LD30.res.getTexture("spark"), 15, 15)[0], 4, x, y);
                s.translate(MathUtils.random(-sparkRange, sparkRange), MathUtils.random(-sparkRange, sparkRange));
                animatedGraphics.add(s);
                warpTimer = 0;
            }
        }

        if(warping) {
            setAnimation(warpingAnimation, 3);
        }
        else if(dy > 0) {
            setAnimation(jumpingAnimation, 0);
        }
        else if(dy < 0) {
            setAnimation(fallingAnimation, 0);
        }
        else if(left || right) {
            setAnimation(walkingAnimation, 6);
        }
        else {
            setAnimation(idleAnimation, 0);
        }
        animation.update();

        if(right) facingRight = true;
        if(left) facingRight = false;

        if(hasJumpSpark) {
            jumpSpark.update();
            jumpSpark.setPosition(x, y);
            if(!falling) {
                hasJumpSpark = false;
                animatedGraphics.add(new SingleAnimatedGraphic(tileMap, TextureRegion.split(LD30.res.getTexture("jumpexplode"), 50, 50)[0], 2, x, y));
            }
        }

        if(animation.getFrames() == walkingAnimation &&
            (animation.getFrame() == 0 || animation.getFrame() == 2) &&
            animation.getTimer() == 0) {
            LD30.res.getSFX("SFXstep").play(0.5f);
        }

    }

    public void draw(SpriteBatch sb) {
        if(hasJumpSpark) {
            jumpSpark.draw(sb);
        }
        super.draw(sb);
    }

}
