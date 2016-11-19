package com.blasters.game.screens;

/**
 * Created by Adam on 11/18/2016.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.blasters.game.SuperPlanetBlasters;


public class LoadingScreen implements Screen {
    private final SuperPlanetBlasters game;
    private ShapeRenderer shapeRenderer;
    private float progress;

    public LoadingScreen(final SuperPlanetBlasters game) {
        this.game = game;
        this.shapeRenderer = new ShapeRenderer();
    }
    @Override
    public void show() {
        shapeRenderer.setProjectionMatrix(game.camera.combined);
        progress = 0f;
        queueAssets();
    }

    private void update(float delta) {
        progress = MathUtils.lerp(progress, game.assetManager.getProgress(), 1f);
        if (game.assetManager.update() && progress >= game.assetManager.getProgress() - .01f) {
            game.setScreen(game.menuScreen);
        }
    }

    private void queueAssets() {
        game.assetManager.load("bolt.mp3", Music.class);
        game.assetManager.load("MOI.mp3", Music.class);
        game.assetManager.load("ships_and_bullets.pack", TextureAtlas.class);
        game.assetManager.load("menuBg.jpg", Texture.class);
        game.assetManager.load("StartButn.png", Texture.class);
        game.assetManager.load("SPB_logo.png", Texture.class);
        game.assetManager.load("soundOn.png", Texture.class);
        game.assetManager.load("soundOff.png", Texture.class);
        game.assetManager.load("paper_planet1.png", Texture.class);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f,1f,1f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(32, game.camera.viewportHeight / 2 - 8, game.camera.viewportWidth - 64, 16);
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(32, game.camera.viewportHeight / 2 - 8, progress * (game.camera.viewportWidth - 64), 16);
        shapeRenderer.end();

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

    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();

    }
}