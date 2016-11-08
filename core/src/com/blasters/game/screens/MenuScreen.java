package com.blasters.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;//
import com.badlogic.gdx.graphics.Texture;
import com.blasters.game.SuperPlanetBlasters;


public class MenuScreen implements Screen{
    
    public SuperPlanetBlasters game;
    private Texture startButton;
    private Texture logo;
    private Texture soundBtn;
    private Texture bg;

    public MenuScreen(SuperPlanetBlasters game) {
        startButton = new Texture("StartButn.png");
        logo = new Texture("SPB_logo.png");
        soundBtn = new Texture("soundOn.png");
        bg = new Texture("menuBg.jpg");
        this.game = game;

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //test if touched within coordinates of button
        //game.setScreen(new Screen(GameScreen(game, isSound));
        //test if touched within coordinates of volume button
        //turn on or off sound
        game.sb.begin();
        game.sb.draw(bg, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.sb.draw(startButton, (Gdx.graphics.getWidth() /2 - 140), (Gdx.graphics.getHeight() / 10), 280, 280);
        game.sb.draw(soundBtn, (Gdx.graphics.getWidth() /2 - 300), (Gdx.graphics.getHeight() / 11), 105, 105);
        game.sb.draw(logo, (Gdx.graphics.getWidth() / 2 - 250), (Gdx.graphics.getHeight() / 2 -100),500, 500);
        game.sb.end();
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

    }

}