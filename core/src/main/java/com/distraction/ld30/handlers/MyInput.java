package com.distraction.ld30.handlers;

public class MyInput {
	
	public static final int NUM_KEYS = 6;
	public static final int UP = 0;
	public static final int LEFT = 1;
	public static final int DOWN = 2;
	public static final int RIGHT = 3;
	public static final int JUMP = 4;
	public static final int WARP = 5;
	
	private boolean[] keys;
	private boolean[] pkeys;
	
	public MyInput() {
		keys = new boolean[NUM_KEYS];
		pkeys = new boolean[NUM_KEYS];
	}
	
	public void update() {
		for(int i = 0; i < NUM_KEYS; i++) {
			pkeys[i] = keys[i];
		}
	}
	
	public void setKey(int id, boolean b) {
		keys[id] = b;
	}
	
	public boolean isDown(int id) { return keys[id]; }
	public boolean isPressed(int id) { return keys[id] && !pkeys[id]; }
	
}
