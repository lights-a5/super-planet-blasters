package com.blasters.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.blasters.game.SuperPlanetBlasters;
import com.blasters.game.gameworld.GameRenderer;
import com.blasters.game.gameworld.GameWorld;
import com.blasters.game.scenes.Hud;

import javax.swing.text.View;

/**
 * The game screen class. This is the main screen where
 * the game will be played. It has a world that maintains the game state
 * and the renderer that will render everything inside the world.
 */

public class GameScreen implements Screen {
    private GameWorld world;
    private GameRenderer renderer;
    public SuperPlanetBlasters game;
    private Music gameMusic;
    private Sound playerDie;
    private boolean playedDeadSound;

    public GameScreen(SuperPlanetBlasters game) {
        Viewport gameViewPort = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), game.camera);
        gameViewPort.apply();
        this.game = game;

    }

    @Override
    public void show() {
        world = new GameWorld(game);
        renderer = new GameRenderer(world, this);
        gameMusic = game.assetManager.get("MOI.mp3", Music.class);
        playerDie = game.assetManager.get("Ship_die.wav", Sound.class);
        playedDeadSound = false;
        if(game.playMusic) {
            gameMusic.setLooping(true);
            gameMusic.play();
        }
    }

    @Override
    public void render(float delta) {
        world.update(delta);
        if (world.player.isDead() && game.playMusic && !playedDeadSound) {
            gameMusic.pause();
            playerDie.play();
            playedDeadSound = true;
        }
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.sb.setProjectionMatrix(game.camera.combined); //only draw what the camera can see
        renderer.render();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        gameMusic.stop();
        playerDie.stop();

    }

    @Override
    public void dispose() {
        if (world != null) {
            world.dispose();
        }
        if (renderer != null) {
            renderer.dispose();
        }
        if (gameMusic != null) {
            gameMusic.dispose();
        }

    }

}