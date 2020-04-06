package com.raya.megaman;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.raya.screens.PlayScreen;

public class MegaMan extends Game {
	//virtual width and height for game
	public static final int V_WIDTH = 400;
	public static final int V_HEIGHT =250;
	public static final float PPM=100;//pixelspermeter;used to scale all meausurements
	//public SpriteBatch batch;//container that holds all images and textures until rendered to be drawn, memory intensive, public so all screens have access 
	
	public SpriteBatch batch;
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));//this:pass game itself 
	}

	@Override
	public void render () {
		super.render();//delegate render method to play screen or active screen atm
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		//img.dispose();
	}
}
