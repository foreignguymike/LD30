package com.distraction.ld30.handlers;

import com.badlogic.gdx.utils.Array;
import com.distraction.ld30.entities.HorizontalSpike;
import com.distraction.ld30.entities.JumpSphere;
import com.distraction.ld30.entities.Spike;
import com.distraction.ld30.entities.VerticalSpike;
import com.distraction.ld30.tilemap.TileMap;

public abstract class LevelManager {
	
	private LevelManager() {}
	
	public static LevelData getLevelData(int level, TileMap tm) {
		
		LevelData data = new LevelData();
		Array<Spike> spikes = new Array<Spike>();
		Array<JumpSphere> jumpSpheres = new Array<JumpSphere>();
		
		data.mapPath = "maps/level" + level + ".tme";
		data.spikes = spikes;
		data.jumpSpheres = jumpSpheres;
		if(level >= 1 && level <= 4) {
			data.playerx = 60;
			data.playery = 220;
			data.portalx = 440;
			data.portaly = 190;
		}
		else if(level == 5) {
			data.playerx = 60;
			data.playery = 290;
			data.portalx = 60;
			data.portaly = 190;
		}
		else if(level == 6) {
			data.playerx = 60;
			data.playery = 250;
			data.portalx = 425;
			data.portaly = 270;
		}
		else if(level == 7) {
			data.playerx = 60;
			data.playery = 250;
			data.portalx = 425;
			data.portaly = 254;
			for(int i = 0; i < 17; i++) {
				spikes.add(new Spike(tm, 135 + i * 15, 186));
			}
		}
		else if(level == 8) {
			data.playerx = 60;
			data.playery = 220;
			data.portalx = 400;
			data.portaly = 30;
			for(int i = 0; i < 10; i++) {
				spikes.add(new Spike(tm, 125 + i * 15, 186));
			}
		}
		else if(level == 9) {
			data.playerx = 60;
			data.playery = 220;
			data.portalx = 440;
			data.portaly = 270;
			jumpSpheres.add(new JumpSphere(tm, 340, 220));
		}
		else if(level == 10) {
			data.playerx = 60;
			data.playery = 220;
			data.portalx = 440;
			data.portaly = 190;
			for(int i = 0; i < 20; i++) {
				spikes.add(new Spike(tm, 100 + i * 15, 186));
			}
			for(int i = 0; i < 4; i++) {
				jumpSpheres.add(new JumpSphere(tm, 140 + i * 70, 230));
			}
		}
		else if(level == 11) {
			data.playerx = 120;
			data.playery = 200;
			data.portalx = 400;
			data.portaly = 190;
			for(int i = 0; i < 31; i++) {
				spikes.add(new Spike(tm, 31 + i * 15, 26));
			}
			jumpSpheres.add(new JumpSphere(tm, 150, 90));
		}
		else if(level == 12) {
			data.playerx = 120;
			data.playery = 200;
			data.portalx = 400;
			data.portaly = 190;
			for(int i = 0; i < 31; i++) {
				spikes.add(new Spike(tm, 31 + i * 15, 26));
			}
			for(int i = 0; i < 6; i++) {
				spikes.add(new Spike(tm, 170 + i * 15, 186));
			}
			jumpSpheres.add(new JumpSphere(tm, 190, 230));
		}
		else if(level == 13) {
			data.playerx = 30;
			data.playery = 200;
			data.portalx = 482;
			data.portaly = 190;
			for(int i = 0; i < 24; i++) {
				spikes.add(new Spike(tm, 75 + i * 15, 26));
				spikes.add(new Spike(tm, 75 + i * 15, 186));
			}
			jumpSpheres.add(new JumpSphere(tm, 120, 230));
			jumpSpheres.add(new JumpSphere(tm, 200, 70));
			jumpSpheres.add(new JumpSphere(tm, 280, 230));
			jumpSpheres.add(new JumpSphere(tm, 360, 70));
		}
		else if(level == 14) {
			data.playerx = 30;
			data.playery = 30;
			data.portalx = 482;
			data.portaly = 30;
			for(int i = 0; i < 21; i++) {
				spikes.add(new Spike(tm, 105 + i * 15, 186));
			}
			jumpSpheres.add(new JumpSphere(tm, 50, 230));
			jumpSpheres.add(new JumpSphere(tm, 462, 230));
			jumpSpheres.add(new JumpSphere(tm, 130, 230));
			jumpSpheres.add(new JumpSphere(tm, 210, 230));
			jumpSpheres.add(new JumpSphere(tm, 290, 230));
			jumpSpheres.add(new JumpSphere(tm, 370, 230));
		}
		else if(level == 15) {
			data.playerx = 30;
			data.playery = 200;
			data.portalx = 482;
			data.portaly = 190;
			for(int i = 0; i < 16; i++) {
				spikes.add(new Spike(tm, 135 + i * 15, 186));
				spikes.add(new Spike(tm, 135 + i * 15, 26));
			}
			jumpSpheres.add(new JumpSphere(tm, 152, 230));
			jumpSpheres.add(new JumpSphere(tm, 216, 230));
			jumpSpheres.add(new JumpSphere(tm, 280, 230));
			jumpSpheres.add(new JumpSphere(tm, 344, 230));
		}
		
		else if(level == 16) {
			data.playerx = 30;
			data.playery = 240;
			data.portalx = 482;
			data.portaly = 238;
			spikes.add(new HorizontalSpike(tm, 462, 232, -400, 200 / 60f));
			spikes.add(new HorizontalSpike(tm, 462, 248, -400, 200 / 60f));
		}
		else if(level == 17) {
			data.playerx = 30;
			data.playery = 200;
			data.portalx = 482;
			data.portaly = 190;
			for(int i = 0; i < 24; i++) {
				spikes.add(new Spike(tm, 75 + i * 15, 26));
				spikes.add(new Spike(tm, 75 + i * 15, 186));
			}
			jumpSpheres.add(new JumpSphere(tm, 120, 230));
			jumpSpheres.add(new JumpSphere(tm, 200, 70));
			jumpSpheres.add(new JumpSphere(tm, 280, 230));
			jumpSpheres.add(new JumpSphere(tm, 360, 70));
			spikes.add(new VerticalSpike(tm, 160, 290, -100, 80 / 60f));
			spikes.add(new VerticalSpike(tm, 240, 190, 100, 80 / 60f));
			spikes.add(new VerticalSpike(tm, 320, 290, -100, 80 / 60f));
			spikes.add(new VerticalSpike(tm, 160, 130, -100, 80 / 60f));
			spikes.add(new VerticalSpike(tm, 240, 30, 100, 80 / 60f));
			spikes.add(new VerticalSpike(tm, 320, 130, -100, 80 / 60f));
		}
		else if(level == 18) {
			data.playerx = 30;
			data.playery = 200;
			data.portalx = 482;
			data.portaly = 190;
			for(int i = 0; i < 24; i++) {
				spikes.add(new Spike(tm, 75 + i * 15, 26));
			}
			jumpSpheres.add(new JumpSphere(tm, 120, 70));
			jumpSpheres.add(new JumpSphere(tm, 334, 70));
			spikes.add(new HorizontalSpike(tm, 100, 280, 100, 40 / 60f));
			spikes.add(new HorizontalSpike(tm, 316, 280, 100, 40 / 60f));
			spikes.add(new HorizontalSpike(tm, 170, 186, 170, 100 / 60f));
			
		}
		
		return data;
		
	}
	
}
