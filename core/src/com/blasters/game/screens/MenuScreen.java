package com.blasters.game.screens;

/**
 * Created by Adam on 11/1/2016.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.blasters.game.SuperPlanetBlasters;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MenuScreen implements Screen{
    

    private OrthographicCamera camera;
    private SpriteBatch sb;
    public Actor button;
    public MenuScreen(SuperPlanetBlasters game) {

        super();

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
    }

    @Override
    public void resize(int width, int height) {
        super.resize( width, height );
        


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