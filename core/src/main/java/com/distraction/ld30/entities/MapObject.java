package com.distraction.ld30.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.distraction.ld30.handlers.Animation;
import com.distraction.ld30.tilemap.TileMap;

public abstract class MapObject {

	// tile stuff
	protected TileMap tileMap;
	protected int tileSize;

	// position and vector
	protected float x;
	protected float y;
	protected float dx;
	protected float dy;

	// dimensions
	protected int width;
	protected int height;

	// collision box
	protected int cwidth;
	protected int cheight;
	protected Rectangle crect;

	// collision
	protected int currRow;
	protected int currCol;
	protected float xdest;
	protected float ydest;
	protected float xtemp;
	protected float ytemp;
	protected int leftTile;
	protected int rightTile;
	protected int topTile;
	protected int bottomTile;
	protected boolean topCollision;
	protected boolean leftCollision;
	protected boolean rightCollision;
	protected boolean bottomCollision;

	// animation
	protected Animation animation;
	protected int currentAction;
	protected int previousAction;
	protected boolean facingRight;

	// movement
	protected boolean left;
	protected boolean right;
	protected boolean up;
	protected boolean down;
	protected boolean jumping;
	protected boolean falling;
	protected boolean ledgeFall;

	// movement attributes
	protected float moveSpeed;
	protected float maxSpeed;
	protected float stopSpeed;
	protected float fallSpeed;
	protected float maxFallSpeed;
	protected float jumpStart;
	protected float stopJumpSpeed;

	// constructor
	public MapObject(TileMap tm) {
		tileMap = tm;
		tileSize = tm.getTileSize();
		animation = new Animation();
		facingRight = true;
		crect = new Rectangle();
	}

	public boolean intersects(MapObject o) {
		Rectangle r1 = getRectangle();
		Rectangle r2 = o.getRectangle();
		return r1.overlaps(r2);
	}

	public boolean intersects(Rectangle r) {
		return getRectangle().overlaps(r);
	}

	public boolean contains(int x, int y) {
		return getRectangle().contains(x, y);
	}

	public boolean contains(MapObject o) {
		Rectangle r1 = getRectangle();
		Rectangle r2 = o.getRectangle();
		return r1.contains(r2);
	}

	public boolean contains(Rectangle r) {
		return getRectangle().contains(r);
	}

	public Rectangle getRectangle() {
		crect.x = (int) (x - cwidth / 2);
		crect.y = (int) (y - cheight / 2);
		crect.width = cwidth;
		crect.height = cheight;
		return crect;
	}

	protected void calculateCollision(float x, float y) {
		topCollision = leftCollision = rightCollision = bottomCollision = false;
		int xl = (int) (x - cwidth / 2);
		int xr = (int) (x + cwidth / 2 - 1);
		int yt = (int) (y + cheight / 2 - 1);
		int yb = (int) (y - cheight / 2);
		leftTile = xl / tileSize;
		rightTile = xr / tileSize;
		topTile = yt / tileSize;
		bottomTile = yb / tileSize;
		if(topTile < 0 || bottomTile >= tileMap.getNumRows() ||
			leftTile < 0 || rightTile >= tileMap.getNumCols()) {
			return;
		}
		for(int i = 0; i < rightTile - leftTile + 1; i++) {
			topCollision |= tileMap.getType(topTile, leftTile + i) == TileMap.BLOCKED;
			bottomCollision |= tileMap.getType(bottomTile, leftTile + i) == TileMap.BLOCKED;
		}
		for(int i = 0; i < topTile - bottomTile + 1; i++) {
			leftCollision |= tileMap.getType(bottomTile + i, leftTile) == TileMap.BLOCKED;
			rightCollision |= tileMap.getType(bottomTile + i, rightTile) == TileMap.BLOCKED;
		}
	}

	protected boolean checkTileMapCollision() {

		currCol = (int)x / tileSize;
		currRow = (int)y / tileSize;

		xdest = x + dx;
		ydest = y + dy;

		xtemp = x;
		ytemp = y;

		boolean collision = false;

		calculateCollision(x, ydest);
		if(dy > 0) {
			if(topCollision) {
				dy = 0;
				ytemp = (topTile) * tileSize - cheight / 2;
				collision = true;
			}
			else {
				ytemp += dy;
			}
		}
		if(dy < 0) {
			if(bottomCollision) {
				dy = 0;
				falling = false;
				ytemp = (bottomTile + 1) * tileSize + cheight / 2;
				collision = true;
				ledgeFall = false;
			}
			else {
				ytemp += dy;
			}
		}

		calculateCollision(xdest, y);
		if(dx < 0) {
			if(leftCollision) {
				dx = 0;
				xtemp = (leftTile + 1) * tileSize + cwidth / 2;
				collision = true;
			}
			else {
				xtemp += dx;
			}
		}
		if(dx > 0) {
			if(rightCollision) {
				dx = 0;
				xtemp = rightTile * tileSize - cwidth / 2;
				collision = true;
			}
			else {
				xtemp += dx;
			}
		}

		if(!falling) {
			calculateCollision(x, ydest - 1);
			if(!bottomCollision) {
				falling = true;
			}
		}

		return collision;

	}

	public int getx() { return (int)x; }
	public int gety() { return (int)y; }
	public double getdx() { return dx; }
	public double getdy() { return dy; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public int getCWidth() { return cwidth; }
	public int getCHeight() { return cheight; }
	public boolean isFacingRight() { return facingRight; }
	public boolean isFalling() { return falling; }
	public int getRow() { return (int) (y / tileMap.getTileSize()); }
	public int getCol() { return (int) (x / tileMap.getTileSize()); }

	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}
	public void translate(float dx, float dy) {
		x += dx;
		y += dy;
	}
	public void setVector(float dx, float dy) {
		this.dx = dx;
		this.dy = dy;
	}
	public void setCollisionDimensions(int w, int h) {
		cwidth = w;
		cheight = h;
	}

	public void setLeft(boolean b) { left = b; }
	public void setRight(boolean b) { right = b; }
	public void setUp(boolean b) { up = b; }
	public void setDown(boolean b) { down = b; }
	public void setJumping() { if(!falling) jumping = true; }

	protected void setAnimation(Texture frames, int delay) {
		setAnimation(new TextureRegion[] { new TextureRegion(frames) }, delay);
	}
	protected void setAnimation(TextureRegion frames, int delay) {
		setAnimation(new TextureRegion[] { frames }, delay);
	}
	protected void setAnimation(TextureRegion[] frames, int delay) {
		if(frames == animation.getFrames()) return;
		animation.setAnimation(frames, delay);
		width = frames[0].getRegionWidth();
		height = frames[0].getRegionHeight();
	}

	public void update() {
		animation.update();
	}

	public void draw(SpriteBatch sb) {
		if(facingRight) {
			sb.draw(animation.getImage(), (int) x - width / 2, y - height / 2, width, height);
		}
		else {
			sb.draw(animation.getImage(), (int) x + width / 2, y - height / 2, -width, height);
		}
	}

}
