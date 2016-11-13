package com.blasters.game;


import com.badlogic.gdx.Game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.blasters.game.screens.GameScreen;
import com.blasters.game.screens.MenuScreen;

public class SuperPlanetBlasters extends Game {
	public SpriteBatch sb;
	
	@Override
	public void create () {
		sb = new SpriteBatch();

		setScreen(new MenuScreen(this));

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
