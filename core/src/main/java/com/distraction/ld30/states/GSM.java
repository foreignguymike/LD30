package com.distraction.ld30.states;

import java.util.Stack;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class GSM {

	public static final ShaderProgram defaultShader = SpriteBatch.createDefaultShader();

	private SpriteBatch sb;
	private Stack<State> states;

	public GSM() {
		sb = new SpriteBatch();
		states = new Stack<State>();
	}

	public SpriteBatch getSpriteBatch() { return sb; }

	public void set(State s) {
		states.pop();
		states.push(s);
	}

	public void push(State s) {
		states.push(s);
	}

	public void pop() {
		states.pop();
	}

	public void update() {
		states.peek().update();
	}

	public void render() {
		states.peek().render();
	}

}
