package com.distraction.ld30.handlers;

import com.badlogic.gdx.utils.Array;
import com.distraction.ld30.entities.JumpSphere;
import com.distraction.ld30.entities.Spike;

public class LevelData {
	
	public String mapPath;
	public float playerx;
	public float playery;
	public float portalx;
	public float portaly;
	
	public Array<Spike> spikes;
	public Array<JumpSphere> jumpSpheres;
	
}
