package com.blasters.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;//
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.blasters.game.SuperPlanetBlasters;


public class MenuScreen implements Screen{
    
    public SuperPlanetBlasters game;
    private Texture startButton;
    private Texture logo;
    private Texture soundBtn;
    private Texture extras;
    private Texture bg;

    

    private Sprite start;
    private Sprite sound;

    public MenuScreen(SuperPlanetBlasters game) {


        OrthographicCamera camera = new OrthographicCamera();
        startButton = new Texture("StartButn.png");
        logo = new Texture("SPB_logo.png");
        soundBtn = new Texture("soundOn.png");
        extras = new Texture("paper_planet1.png");
        bg = new Texture("menuBg.jpg");
        this.game = game;

        start = new Sprite(startButton);
        start.setScale(.3f, .3f);
        start.setPosition((Gdx.graphics.getWidth() /2 - start.getWidth() / 2), (Gdx.graphics.getHeight() / 5 - start.getHeight() /2));

        sound = new Sprite(soundBtn);
        sound.setScale(.5f, .5f);
        sound.setPosition( (Gdx.graphics.getWidth() - sound.getWidth() ), (Gdx.graphics.getHeight() / 5 - sound.getWidth() ));

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
        sound.draw(game.sb);
        start.draw(game.sb);

        //game.sb.draw(startButton, (Gdx.graphics.getWidth() /2 - 140), (Gdx.graphics.getHeight() / 10), 280, 280);
        //game.sb.draw(soundBtn, (Gdx.graphics.getWidth() /2 - 250), (Gdx.graphics.getHeight() / 11), 105, 105);
        game.sb.draw(logo, (Gdx.graphics.getWidth() / 2 - 240), (Gdx.graphics.getHeight() / 2 -100),500, 500);
        game.sb.draw(extras, (Gdx.graphics.getWidth() /2 - 280), (Gdx.graphics.getHeight() / 14 -12), 130, 130 );
        game.sb.end();

    }


    @Override
    public void resize(int width, int height) {



    }

    public void handleInput(){
        if(Gdx.input.justTouched()){
            game.setScreen(new MenuScreen(game));
        }
    }

    @Override
    public void pause() {
            Gdx.app.log("GameScreen", "pause called");
    }

    @Override
    public void resume() {
        Gdx.app.log("GameScreen", "resume called");
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}