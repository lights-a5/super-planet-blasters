package com.blasters.game;


import com.badlogic.gdx.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.blasters.game.screens.GameScreen;
import com.blasters.game.screens.LoadingScreen;
import com.blasters.game.screens.MenuScreen;

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
    public SpriteBatch sb;
    public Screen gameScreen;
    public Screen menuScreen;
    public Screen loadingScreen;
    public boolean playMusic;
    public OrthographicCamera camera;
    public AssetManager assetManager;

    @Override
    public void create () {
        assetManager = new AssetManager();
        sb = new SpriteBatch();
        playMusic = true;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        loadingScreen = new LoadingScreen(this);
        menuScreen = new MenuScreen(this);
        gameScreen = new GameScreen(this);
        setScreen(loadingScreen);
    }

    @Override
    public void render () {
        super.render();
    }

    @Override
    public void dispose () {
        sb.dispose();
        loadingScreen.dispose();
        gameScreen.dispose();
        menuScreen.dispose();
        assetManager.dispose();
    }
}