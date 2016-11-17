package com.blasters.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
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
    public Music gameMusic;
    private Viewport gameViewPort;
    private OrthographicCamera cam;
    public Hud hud;
    private float runTime; //do we need this?

    public GameScreen(SuperPlanetBlasters game, Boolean isMusicPlaying) {
        this.game = game;
        hud = new Hud(game.sb);
        world = new GameWorld(this);
        cam = new OrthographicCamera();

        playGameMusic(isMusicPlaying);

        gameViewPort = new FitViewport(SuperPlanetBlasters.WIDTH, SuperPlanetBlasters.HEIGHT, cam);
        gameViewPort.apply();
        cam.position.set(gameViewPort.getWorldWidth() / 2, gameViewPort.getWorldHeight() / 2, 0);
        renderer = new GameRenderer(world, this);
        cam.update();
    }

    private void playGameMusic(Boolean isMusicPlaying) {
        if(isMusicPlaying) {
            gameMusic = Gdx.audio.newMusic(Gdx.files.internal("GSLevel_1.mp3"));
            gameMusic.setLooping(isMusicPlaying);
            gameMusic.play();
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        world.update(delta);
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.sb.setProjectionMatrix(cam.combined); //only draw what the camera can see
        renderer.render(); //put the runtime as a parameter? eg. renderer.render(runtime);
        hud.draw();


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
        world.dispose();
        renderer.dispose();
        gameMusic.dispose();

    }
}
