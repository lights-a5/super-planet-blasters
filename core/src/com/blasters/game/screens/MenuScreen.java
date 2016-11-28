package com.blasters.game.screens;

/**
 * Created by Adam on 11/15/2016.
 * modified- most recent 11/27/16
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.blasters.game.SuperPlanetBlasters;



public class MenuScreen implements Screen, InputProcessor {

    public SuperPlanetBlasters game;
    private Music menuMusic;
    //private Sound click; //no click sound yet

    private Texture bg;
    private Texture logo;
    private Texture extras;

    private Sprite start;
    private Sprite soundOn;
    private Sprite soundOff;
    private Sprite sound;

    public MenuScreen (SuperPlanetBlasters game) {
        this.game = game;
    }


    @Override
    public void show() {
        menuMusic = game.assetManager.get("bolt.mp3", Music.class);
        if (game.playMusic) {
            menuMusic.play();
        }
        start = new Sprite(game.assetManager.get("StartButn.png", Texture.class));
        start.setScale(.3f, .3f);
        start.setPosition((Gdx.graphics.getWidth() / 2 - start.getWidth() / 2),
                (Gdx.graphics.getHeight() / 5 - start.getHeight() /2));

        sound = new Sprite(game.assetManager.get("soundOn.png", Texture.class));
        soundOff = new Sprite(game.assetManager.get("soundOff.png", Texture.class));
        soundOn = new Sprite(game.assetManager.get("soundOn.png", Texture.class));

        bg = game.assetManager.get("menuBg.jpg", Texture.class);
        logo = game.assetManager.get("SPB_logo.png", Texture.class);
        // This will be turned into a sprite later as stretch goals
        extras = game.assetManager.get("paper_planet1.png", Texture.class);
        Gdx.app.log("MenuScreen", "Show Called");


    }

    @Override
    public void render(float delta) {

        game.sb.begin();
        game.sb.draw(bg, 0, 0, Gdx.graphics.getWidth(), (Gdx.graphics.getHeight()));
        sound.setScale(.5f, .5f);
        sound.setPosition( (Gdx.graphics.getWidth() - sound.getWidth() ),
                (Gdx.graphics.getHeight() / 5 - sound.getWidth() ));
        sound.draw(game.sb);
        start.draw(game.sb);


        game.sb.draw(logo, (Gdx.graphics.getWidth() / 2 - 240), (Gdx.graphics.getHeight() / 2 -100),500, 500);
        game.sb.draw(extras, (Gdx.graphics.getWidth() /2 - 280), (Gdx.graphics.getHeight() / 14 -12), 130, 130 );
        game.sb.end();
        Gdx.input.setInputProcessor(this);//

    }




    @Override
    public void resize(int width, int height) {



    }

    @Override
    public void pause() {
        Gdx.app.log("MenuScreen", "Pause, Device in lock");
    }

    @Override
    public void resume() {
        Gdx.app.log("MenuScreen", "Resume called");
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        logo.dispose();
        extras.dispose();
        bg.dispose();
        menuMusic.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        if (start.getBoundingRectangle().contains(screenX, (Gdx.graphics.getHeight() - screenY))) {
            menuMusic.pause();
            game.setScreen(game.gameScreen);
        }
        if (sound.getBoundingRectangle().contains(screenX, (Gdx.graphics.getHeight() - screenY))) {
            if (game.playMusic) {
                menuMusic.pause();
                sound.set(soundOff);
                game.playMusic = false;
            } else {
                sound.set(soundOn);
                menuMusic.play();
                game.playMusic = true;
            }
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}