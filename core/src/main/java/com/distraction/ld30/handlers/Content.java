package com.distraction.ld30.handlers;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class Content {
	
	private HashMap<String, Texture> textures;
	private HashMap<String, Sound> sfx;
	private HashMap<String, Music> music;
	
	public Content() {
		textures = new HashMap<String, Texture>();
		sfx = new HashMap<String, Sound>();
		music = new HashMap<String, Music>();
	}
	
	public void loadTexture(String path, String key) { textures.put(key, new Texture(path)); }
	public Texture getTexture(String key) {	return textures.get(key); }
	public void disposeTexture(String key) {
		Texture t = textures.get(key);
		if(t != null) {
			t.dispose();
			textures.remove(key);
		}
	}
	
	public void loadSFX(String path, String key) { sfx.put(key, Gdx.audio.newSound(Gdx.files.internal(path))); }
	public Sound getSFX(String key) { return sfx.get(key); }
	public void disposeSFX(String key) {
		Sound s = sfx.get(key);
		if(s != null) {
			s.dispose();
			sfx.remove(key);
		}
	}
	
	public void loadMusic(String path, String key) { music.put(key, Gdx.audio.newMusic(Gdx.files.internal(path))); }
	public Music getMusic(String key) { return music.get(key); }
	public void disposeMusic(String key) {
		Music m = music.get(key);
		if(m != null) {
			m.dispose();
			music.remove(key);
		}
	}
	
}
