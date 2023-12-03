package com.distraction.ld30.handlers;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.distraction.ld30.LD30;

public class MyInputProcessor extends InputAdapter {
	
	public boolean keyDown(int k) {
		if(k == Keys.Z) LD30.input.setKey(MyInput.JUMP, true);
		if(k == Keys.UP) LD30.input.setKey(MyInput.UP, true);
		if(k == Keys.LEFT) LD30.input.setKey(MyInput.LEFT, true);
		if(k == Keys.DOWN) LD30.input.setKey(MyInput.DOWN, true);
		if(k == Keys.RIGHT) LD30.input.setKey(MyInput.RIGHT, true);
		if(k == Keys.X) LD30.input.setKey(MyInput.WARP, true); 
		return true;
	}
	
	public boolean keyUp(int k) {
		if(k == Keys.Z) LD30.input.setKey(MyInput.JUMP, false);
		if(k == Keys.UP) LD30.input.setKey(MyInput.UP, false);
		if(k == Keys.LEFT) LD30.input.setKey(MyInput.LEFT, false);
		if(k == Keys.DOWN) LD30.input.setKey(MyInput.DOWN, false);
		if(k == Keys.RIGHT) LD30.input.setKey(MyInput.RIGHT, false);
		if(k == Keys.X) LD30.input.setKey(MyInput.WARP, false);
		return true;
	}
	
}
