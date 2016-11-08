package com.blasters.game;


import com.badlogic.gdx.Game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.blasters.game.screens.GameScreen;

/*
 * SUPERPLANETBLASTERS
 * Our game. It extends the game class so that it will render and dispose everything that we need
 * it to. It sets screens. We pass this to the screens so that all the screens can have a spritebatch.
 * Also, we need to pass the game class to our screens so that our screens can set other screens.
 * Also, we should define our width and height here instead of using Gdx.graphics.getWidth and
 * Height all the time. That would give us much cleaner code.
 */
public class SuperPlanetBlasters extends Game {
	public static final int HEIGHT = 800;
	public static final int WIDTH =  600;
	public static final String TITLE = "Super Planet Blasters!";
	//currently this just sets the game screen. When we have a menu screen, this will need to set
	//that instead.
	@Override
	public void create () {
		setScreen(new GameScreen());
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}
}
