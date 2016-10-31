package com.blasters.game.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by SHELIVES on 10/31/2016.
 */

public class GameRenderer {
    private GameWorld myWorld;
    private OrthographicCamera cam;
    private SpriteBatch sb;

    public GameRenderer(GameWorld world) {
        sb = new SpriteBatch();
        myWorld = world;
        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void render() {
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sb.begin();
        sb.draw(new Texture("badlogic.jpg"), 100, 100);
        sb.end();
    }
}
