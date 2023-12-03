package com.distraction.ld30.states;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.Array;
import com.distraction.ld30.LD30;
import com.distraction.ld30.entities.Graphic;
import com.distraction.ld30.entities.JumpSphere;
import com.distraction.ld30.entities.Player;
import com.distraction.ld30.entities.SingleAnimatedGraphic;
import com.distraction.ld30.entities.Spike;
import com.distraction.ld30.entities.Teleport;
import com.distraction.ld30.handlers.BoundedCamera;
import com.distraction.ld30.handlers.LevelData;
import com.distraction.ld30.handlers.LevelManager;
import com.distraction.ld30.handlers.MyInput;
import com.distraction.ld30.handlers.MyShaders;
import com.distraction.ld30.tilemap.TileMap;

public class PlayState extends State {

    public static final int MAX_LEVEL = 18;
    private int level;
    private int numDeaths;
    private LevelData data;

    ShaderProgram defaultShader;
    ShaderProgram halfGrayShader;

    private BoundedCamera cam;

    private TileMap tileMap;

    private Player player;

    private Array<SingleAnimatedGraphic> animatedGraphics;
    private Array<Spike> spikes;
    private Teleport teleport;
    private Array<JumpSphere> jumpSpheres;

    private Array<Graphic> graphics;

    private TextureRegion bg;

    public PlayState(GSM gsm, int level, int numDeaths) {

        super(gsm);

        this.level = level;
        this.numDeaths = numDeaths;

        defaultShader = SpriteBatch.createDefaultShader();
        halfGrayShader = new ShaderProgram(MyShaders.VERT_INVERT, MyShaders.FRAG_INVERT);

        tileMap = new TileMap(16);
        data = LevelManager.getLevelData(level, tileMap);
        tileMap.loadTileset(LD30.res.getTexture("tileset"));
        tileMap.loadMap(data.mapPath);

        cam = new BoundedCamera();
        cam.setToOrtho(false, LD30.WIDTH, LD30.HEIGHT);
        cam.setBounds(0, 0, tileMap.getWidth(), tileMap.getHeight());

        player = new Player(tileMap, animatedGraphics = new Array<SingleAnimatedGraphic>());
        player.setPosition(data.playerx, data.playery);

        teleport = new Teleport(tileMap);
        teleport.setPosition(data.portalx, data.portaly);

        spikes = data.spikes;
        jumpSpheres = new Array<JumpSphere>();
        getJumpSpheres();

        graphics = new Array<Graphic>();
        if((level >= 1 && level <= 4) || level == 9) {
            graphics.add(new Graphic(new TextureRegion(LD30.res.getTexture("level" + level + "text")), 256, 240));
        }
        if(level == 3) {
            graphics.add(new Graphic(new TextureRegion(LD30.res.getTexture("level3text2")), 256, 80));
        }

        Music m = LD30.res.getMusic("ld30music");
        if(!m.isPlaying()) {
            m.setLooping(true);
            m.play();
        }

        bg = new TextureRegion(LD30.res.getTexture("bg"));

    }

    private void getJumpSpheres() {
        for(int i = 0; i < data.jumpSpheres.size; i++) {
            float x = data.jumpSpheres.get(i).getx();
            float y = data.jumpSpheres.get(i).gety();
            boolean found = false;
            for(int j = 0; j < jumpSpheres.size; j++) {
                if(jumpSpheres.get(j).getx() == x && jumpSpheres.get(j).gety() == y) {
                    found = true;
                    break;
                }
            }
            if(!found) {
                jumpSpheres.add(new JumpSphere(tileMap, x, y));
            }
        }
    }

    public void handleInput() {

        player.setLeft(LD30.input.isDown(MyInput.LEFT));
        player.setRight(LD30.input.isDown(MyInput.RIGHT));
        if(LD30.input.isPressed(MyInput.JUMP)) {
            player.setJumping();
        }

        if(LD30.input.isPressed(MyInput.WARP)) {
            if(!player.isWarping()) {
                if(player.warp()) {
                    cam.setShaking(8);
                    LD30.res.getSFX("SFXwarp").play();
                }
                else {
                    LD30.res.getSFX("SFXwarpfail").play();
                }
            }
        }

    }

    public void update() {

        handleInput();

        cam.updateShaking();

        player.update();
        if(teleport != null) {
            teleport.update();
            if(!player.isWarping() && teleport.contains(player)) {
                LD30.res.getSFX("SFXclear").play();
                if(level == MAX_LEVEL) {
                    LD30.res.getMusic("ld30music").stop();
                    gsm.set(new TransitionState(gsm, this, new ScoreState(gsm, numDeaths)));
                }
                else {
                    gsm.set(new TransitionState(gsm, this, new PlayState(gsm, level + 1, numDeaths)));
                }
            }
        }

        if(!player.isFalling() && jumpSpheres.size < data.jumpSpheres.size) {
            getJumpSpheres();
        }

        for(int i = 0; i < animatedGraphics.size; i++) {
            animatedGraphics.get(i).update();
            if(animatedGraphics.get(i).shouldRemove()) {
                animatedGraphics.removeIndex(i);
                i--;
            }
        }

        for(int i = 0; i < spikes.size; i++) {
            spikes.get(i).update();
            if(!player.isWarping() && player.intersects(spikes.get(i))) {
                gsm.set(new TransitionState(gsm, this, new PlayState(gsm, level, numDeaths + 1)));
            }
        }

        for(int i = 0; i < jumpSpheres.size; i++) {
            jumpSpheres.get(i).update();
            if(jumpSpheres.get(i).contains(player) && !player.isWarping()) {
                jumpSpheres.removeIndex(i);
                i--;
                player.getJumpSpark();
                LD30.res.getSFX("SFXjumpsphere").play();
            }
        }


    }

    public void render() {

        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cam.setPosition(player.getx(), player.gety());

        sb.setProjectionMatrix(cam.combined);
        if(level > 2 && level != 9 && level != 10) sb.setShader(halfGrayShader);
        else sb.setShader(GSM.defaultShader);

        sb.begin();

        sb.draw(bg, 0, 0, LD30.WIDTH, LD30.HEIGHT);
        tileMap.draw(sb);
        for(int i = 0; i < graphics.size; i++) {
            graphics.get(i).draw(sb);
        }
        for(int i = 0; i < jumpSpheres.size; i++) {
            jumpSpheres.get(i).draw(sb);
        }
        player.draw(sb);
        for(int i = 0; i < animatedGraphics.size; i++) {
            animatedGraphics.get(i).draw(sb);
        }
        if(teleport != null) teleport.draw(sb);

        for(int i = 0; i < spikes.size; i++) {
            spikes.get(i).draw(sb);
        }

        sb.end();

    }

}
