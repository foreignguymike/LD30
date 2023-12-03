package com.distraction.ld30.tilemap;

import java.io.BufferedReader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.ld30.LD30;

public class TileMap {
	
	// map
	private int[][] collision;
	private int[][] map;
	private int[][] depth;
	private int tileSize;
	private int numRows;
	private int numCols;
	private int width;
	private int height;
	
	private TextureRegion[][] tileset;
	private int numTilesAcross;
	
	public static final int NORMAL = 0;
	public static final int BLOCKED = 1;
	
	private int rowOffset;
	private int colOffset;
	private int numRowsToDraw;
	private int numColsToDraw;
	
	public TileMap(int tileSize) {
		this.tileSize = tileSize;
		numRowsToDraw = LD30.HEIGHT / tileSize + 2;
		numColsToDraw = LD30.WIDTH / tileSize + 2;
	}
	
	public void loadTileset(Texture tex) {
		numTilesAcross = tex.getWidth() / tileSize;
		tileset = TextureRegion.split(tex, tileSize, tileSize);
	}
	
	public void loadMap(String s) {
		
		try {
			
			FileHandle file = Gdx.files.internal(s);
			BufferedReader br = file.reader(8192);
			
			// save file structure:
			// <tileset path>
			// <tile size>
			// <map width>
			// <map height>
			// <map>
			// <collision map>
			// <depth map>
			
			br.readLine();
			br.readLine();
			numCols = Integer.parseInt(br.readLine());
			numRows = Integer.parseInt(br.readLine());
			map = new int[numRows][numCols];
			collision = new int[numRows][numCols];
			depth = new int[numRows][numCols];
			width = numCols * tileSize;
			height = numRows * tileSize;
			
			String delims = "\\s+";
			for(int row = numRows - 1; row >= 0; row--) {
				String line = br.readLine();
				String[] tokens = line.split(delims);
				for(int col = 0; col < numCols; col++) {
					map[row][col] = Integer.parseInt(tokens[col]);
					collision[row][col] = Integer.parseInt(tokens[col]);
				}
			}
			for(int row = numRows - 1; row >= 0; row--) {
				String line = br.readLine();
				String[] tokens = line.split(delims);
				for(int col = 0; col < numCols; col++) {
					collision[row][col] = Integer.parseInt(tokens[col]);
				}
			}
			for(int row = numRows - 1; row >= 0; row--) {
				String line = br.readLine();
				String[] tokens = line.split(delims);
				for(int col = 0; col < numCols; col++) {
					depth[row][col] = Integer.parseInt(tokens[col]);
				}
			}
			
		}
		catch(Exception e) {
			System.out.println("Could not load map: " + s);
			e.printStackTrace();
		}
		
	}
	
	public int getTileSize() { return tileSize; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public int getNumRows() { return numRows; }
	public int getNumCols() { return numCols; }
	
	public int getType(int row, int col) {
		if(row < 0 || row >= numRows || col < 0 || col >= numCols) return 0;
		return collision[row][col];
	}
	public int getDepth(int row, int col) {
		if(row < 0 || row >= numRows || col < 0 || col >= numCols) return 0;
		return depth[row][col];
	}
	
	public void draw(SpriteBatch sb) {
		
		for(int row = rowOffset; row < rowOffset + numRowsToDraw; row++) {
		
			if(row >= numRows) break;
			
			for(int col = colOffset; col < colOffset + numColsToDraw; col++) {
				
				if(col >= numCols) break;
				if(map[row][col] == 0) continue;
				
				int rc = map[row][col];
				int r = rc / numTilesAcross;
				int c = rc % numTilesAcross;
				
				sb.draw(tileset[r][c], col * tileSize, row * tileSize);
				
			}
			
		}
		
	}
	
}



















